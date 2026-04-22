package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.GirlPlayer;
import entity.BoyPlayer;
import environment.EnvironmentManager;
import tile.Map;
import tile.TileManager;
import tileinteractive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	public final int originalTileSize = 16; // 16 x 16 tile
	public final int scale = 3;
	public int tileSize = originalTileSize * scale; // 48 x 48 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;

	// WORLD SETTINGS
	public final int maxWorldCol = 50, maxWorldRow = 50;
	public final int maxMap = 20;
	public int currentMap = 0;

	// FOR FULL SCREEN
	public int screenWidth2 = screenWidth;
	public int screenHeight2 = screenHeight;
	public BufferedImage tempScreen;
	public Graphics2D g2;
	public boolean fullScreenOn = false;

	// FPS
	public int FPS = 60;

	// SYSTEM
	public TileManager tmg = new TileManager(this);
	public KeyHandler khl = new KeyHandler(this);
	public Sounds s = new Sounds();
	public Sounds music = new Sounds();
	public UI ui = new UI(this);
	public EventHandler evl = new EventHandler(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker ccheck = new CollisionChecker(this);
	public Thread gameThread;
	public Config cfg = new Config(this);
	public PathFinder pFind = new PathFinder(this);
	public EnvironmentManager eManager = new EnvironmentManager(this);
	public Map map = new Map(this);
	public SaveLoad saveload = new SaveLoad(this);
	public EntityGenerator egenerator = new EntityGenerator(this);
	public CutSceneManager csManager = new CutSceneManager(this);

	// ENTITY AND OBJECT
	public Entity currentPlayer = new Entity(this);
	public BoyPlayer boy = new BoyPlayer(this, khl);
	public GirlPlayer girl = new GirlPlayer(this, khl);
	public Entity obj[][] = new Entity[maxMap][20];
	public Entity npc[][] = new Entity[maxMap][20];
	public Entity mons[][] = new Entity[maxMap][20];
	public Entity projectTile[][] = new Entity[maxMap][20];
	public InteractiveTile iTile[][] = new InteractiveTile[maxMap][20];
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();

	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playstate = 1;
	public final int pausestate = 2;
	public final int dialogueState = 3;
	public final int CharacterState = 4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int sleepState = 9;
	public final int mapState = 10;
	public final int cutsceneState = 11;

	// OTHER
	public boolean bossBattleOn = false;

	// AREA
	public int currentArea;
	public int nextArea;
	public final int outside = 50;
	public final int indoor = 51;
	public final int dungeon = 52;
	public final int desert = 53;
	public final int frozen = 54;
	public final int hell = 55;

	public GamePanel() {
		this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(this.khl);
		this.setFocusable(true);
	}

	public void SetupGame() {
		this.playMusic(0);
		this.aSetter.setObject();
		this.aSetter.setNPC();
		this.gameState = this.titleState;
		this.aSetter.setMonster();
		this.aSetter.setInteractiveTile();
		this.currentArea = this.outside;
		this.tempScreen = new BufferedImage(this.screenWidth, this.screenHeight, BufferedImage.TYPE_INT_ARGB);
		this.g2 = (Graphics2D) (this.tempScreen.getGraphics());
		if (this.fullScreenOn == true) {
			this.setFullScreen();
		}
		this.eManager.setup();
	}

	public void resetGame(boolean restart) {
		if (this.currentPlayer != null) {
			this.Stopmusic();
			this.RemoveTempEntity();
			this.currentArea = this.outside;
			this.bossBattleOn = false;
			if (this.currentPlayer == this.boy && this.boy.type == this.currentPlayer.type_player_boy) {
				this.boy.setDefaultPositions();
				this.boy.resetCounter();
				this.boy.RestoreStatus();
			}
			if (this.currentPlayer == this.girl && this.girl.type == this.currentPlayer.type_player_girl) {
				this.girl.setDefaultPositions();
				this.girl.resetCounter();
				this.girl.RestoreStatus();
			}
			this.aSetter.setNPC();
			this.aSetter.setMonster();

			if (restart == true) {
				if (this.currentPlayer == this.boy && this.boy.type == this.currentPlayer.type_player_boy) {
					this.boy.SetDefaultValue();
				}
				if (this.currentPlayer == this.girl && this.girl.type == this.currentPlayer.type_player_girl) {
					this.girl.SetDefaultValue();
				}
				this.aSetter.setObject();
				this.aSetter.setInteractiveTile();
				this.eManager.lighting.resetDay();
			}
		}
	}

	public void setFullScreen() {
		// GET LOCAL SCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.wd);

		// GET FULL SCREEN WIDTH AND HEIGHT
		this.screenWidth2 = Main.wd.getWidth();
		this.screenHeight2 = Main.wd.getHeight();
	}

	public void StartgameThread() {
		this.gameThread = new Thread(this);
		this.gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / this.FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		int drawCount = 0;
		long timer = 0;
		while (this.gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				this.Update();
				this.drawToTempScreen(); // DRAW EVERYTHING TO THE BUFFERED IMAGE
				this.drawToScreen(); // DRAW THE BUFFERED IMAGE TO THE SCREEN
				delta--;
				drawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void Update() {
		if (this.gameState == this.playstate) {
			if (this.currentPlayer != null) {
				if (this.currentPlayer == this.boy && this.boy.type == this.currentPlayer.type_player_boy) {

					// PLAYER BOY
					this.boy.Update();
				}
				if (this.currentPlayer == this.girl && this.girl.type == this.currentPlayer.type_player_girl) {

					// PLAYER GIRL
					this.girl.Update();
				}
			}

			// NPC
			for (int i = 0; i < this.npc[1].length; i++) {
				if (this.npc[this.currentMap][i] != null) {
					this.npc[this.currentMap][i].Update();
				}
			}

			// MONSTER
			for (int i = 0; i < this.mons[1].length; i++) {
				if (this.mons[currentMap][i] != null) {
					if (this.mons[currentMap][i].alive == true && this.mons[currentMap][i].dead == false) {
						this.mons[currentMap][i].Update();
					}
					if (this.mons[currentMap][i].alive == false) {
						this.mons[currentMap][i].checkDrop();
						this.mons[currentMap][i] = null;
					}
				}
			}

			for (int i = 0; i < this.projectTile[1].length; i++) {
				if (this.projectTile[currentMap][i] != null) {
					if (this.projectTile[currentMap][i].alive == true) {
						this.projectTile[currentMap][i].Update();
					}
					if (this.projectTile[currentMap][i].alive == false) {
						this.projectTile[currentMap][i] = null;
					}
				}
			}

			for (int i = 0; i < this.particleList.size(); i++) {
				if (this.particleList.get(i) != null) {
					if (this.particleList.get(i).alive == true) {
						this.particleList.get(i).Update();
					}
					if (this.particleList.get(i).alive == false) {
						this.particleList.remove(i);
					}
				}
			}

			for (int i = 0; i < this.iTile[1].length; i++) {
				if (this.iTile[currentMap][i] != null) {
					this.iTile[currentMap][i].Update();
				}
			}

			this.eManager.Update();
		}
		if (this.gameState == this.pausestate) {
			// NOTHING
		}

	}

	public void drawToTempScreen() {
		long drawStart = 0;

		if (this.khl.showDebugText == true) {
			drawStart = System.nanoTime();
		}

		// TITLE SCREEN
		if (this.gameState == this.titleState) {
			this.ui.draw(this.g2);
		}

		// MAP SCREEN
		if (this.gameState == this.mapState) {
			this.map.drawFullMapScreen(this.g2);
		}

		// OTHERS
		else {

			// TILE
			this.tmg.draw(this.g2);

			// INTERACTIVE TILE
			for (int i = 0; i < this.iTile[1].length; i++) {
				if (this.iTile[this.currentMap][i] != null) {
					this.iTile[this.currentMap][i].draw(this.g2);
				}
			}

			// ADD ENTITIES TO THE LIST
			if (this.currentPlayer != null) {
				if (this.currentPlayer == this.boy && this.boy.type == this.currentPlayer.type_player_boy) {
					this.entities.add(this.boy);
				}
				if (this.currentPlayer == this.girl && this.girl.type == this.currentPlayer.type_player_girl) {
					this.entities.add(this.girl);
				}
			}

			for (int i = 0; i < this.npc[1].length; i++) {
				if (this.npc[this.currentMap][i] != null) {
					this.entities.add(this.npc[this.currentMap][i]);
				}
			}
			for (int i = 0; i < this.obj[1].length; i++) {
				if (this.obj[this.currentMap][i] != null) {
					this.entities.add(this.obj[this.currentMap][i]);
				}
			}
			for (int i = 0; i < this.mons[1].length; i++) {
				if (this.mons[currentMap][i] != null) {
					this.entities.add(this.mons[this.currentMap][i]);
				}
			}
			for (int i = 0; i < this.projectTile[1].length; i++) {
				if (this.projectTile[this.currentMap][i] != null) {
					this.entities.add(this.projectTile[this.currentMap][i]);
				}
			}
			for (int i = 0; i < this.particleList.size(); i++) {
				if (this.particleList.get(i) != null) {
					this.entities.add(this.particleList.get(i));
				}
			}

			// SORT
			Collections.sort(this.entities, new Comparator<Entity>() {
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});

			// DRAW ENTITIES
			for (int i = 0; i < this.entities.size(); i++) {
				this.entities.get(i).draw(this.g2);
			}

			// EMPTY ENTITIES LIST
			this.entities.clear();

			// EMVIRONMENT
			this.eManager.draw(this.g2);

			// MINI MAP
			this.map.drawMiniMap(this.g2);

			// CUTSCENE
			this.csManager.draw(this.g2);

			// UI
			this.ui.draw(this.g2);
		}
		if (this.currentPlayer != null) {
			if (this.currentPlayer == this.boy && this.boy.type == this.currentPlayer.type_player_boy) {
				// DEBUG
				if (this.khl.showDebugText == true) {
					long drawend = System.nanoTime();
					long passed = drawend - drawStart;
					this.g2.setFont(new Font("Arial", Font.PLAIN, 20));
					this.g2.setColor(Color.yellow);
					int x = 10;
					int y = 400;
					int lineheight = 20;
					this.g2.drawString("World X: " + this.boy.worldX, x, y);
					y += lineheight;
					this.g2.drawString("World Y: " + this.boy.worldY, x, y);
					y += lineheight;
					this.g2.drawString("Col: " + (((this.boy.worldX + this.boy.solidArea.x) / this.tileSize)), x, y);
					y += lineheight;
					this.g2.drawString("Row: " + (((this.boy.worldY + this.boy.solidArea.y) / this.tileSize)), x, y);
					y += lineheight;
					this.g2.drawString("Draw time: " + passed, x, y);
					y += lineheight;
					this.g2.drawString("God mode: " + this.khl.godmode, x, y);
				}
			}
			if (this.currentPlayer == this.girl && this.girl.type == this.currentPlayer.type_player_girl) {
				// DEBUG
				if (this.khl.showDebugText == true) {
					long drawend = System.nanoTime();
					long passed = drawend - drawStart;
					this.g2.setFont(new Font("Arial", Font.PLAIN, 20));
					this.g2.setColor(Color.yellow);
					int x = 10;
					int y = 400;
					int lineheight = 20;
					this.g2.drawString("World X: " + this.girl.worldX, x, y);
					y += lineheight;
					this.g2.drawString("World Y: " + this.girl.worldY, x, y);
					y += lineheight;
					this.g2.drawString("Col: " + (((this.girl.worldX + this.girl.solidArea.x) / this.tileSize)), x, y);
					y += lineheight;
					this.g2.drawString("Row: " + (((this.girl.worldY + this.girl.solidArea.y) / this.tileSize)), x, y);
					y += lineheight;
					this.g2.drawString("Draw time: " + passed, x, y);
					y += lineheight;
					this.g2.drawString("God mode: " + this.khl.godmode, x, y);
				}
			}
		}
	}

	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(this.tempScreen, 0, 0, this.screenWidth2, this.screenHeight2, null);
	}

	public void playMusic(int i) {
		this.music.setFile(i);
		this.music.Play();
		this.music.loop();
	}

	public void Stopmusic() {
		this.music.Stop();
	}

	public void playSE(int i) {
		this.s.setFile(i);
		this.s.Play();
	}

	public void changeArea() {
		if (this.nextArea != this.currentArea) {
			this.Stopmusic();
			if (this.nextArea == this.outside) {
				this.playMusic(0);
			}
			if (this.nextArea == this.indoor) {
				this.playMusic(14);
			}
			if (this.nextArea == this.dungeon) {
				this.playMusic(8);
			}
			if (this.nextArea == this.desert) {

			}
			if (this.nextArea == this.frozen) {

			}
			if (this.nextArea == this.hell) {

			}
			this.aSetter.setNPC();
		}
		this.currentArea = this.nextArea;
		this.aSetter.setMonster();
	}

	public void RemoveTempEntity() {
		for (int i = 0; i < this.maxMap; i++) {
			for (int j = 0; j < this.obj[1].length; j++) {
				if (this.obj[i][j] != null && this.obj[i][j].temp == true) {
					this.obj[i][j] = null;
				}
			}
		}
	}

}
