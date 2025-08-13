package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.BoyDummy;
import entity.GirlDummy;
import monster.SkeletonLord;
import objects.OBJBlueHeart;
import objects.OBJDoorIron;

public class CutSceneManager {
	public GamePanel gp;
	public Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	public int Count = 0;
	public float Alpha = 0F;
	public int y;

	// SCENE NUMBER
	public final int NA = 0;
	public final int skeletonLord = 1;
	public final int ending = 2;

	public CutSceneManager(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		switch (this.sceneNum) {
			case skeletonLord: {
				this.scene_skeletonlord();
				break;
			}
			case ending: {
				this.scene_ending();
				break;
			}
		}
	}

	public void scene_skeletonlord() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				if (this.scenePhase == 0) {
					this.gp.bossBattleOn = true;
					for (int i = 0; i < this.gp.obj[1].length; i++) {
						if (this.gp.obj[this.gp.currentMap][i] == null) {
							this.gp.obj[this.gp.currentMap][i] = new OBJDoorIron(this.gp);
							this.gp.obj[this.gp.currentMap][i].worldX = this.gp.tileSize * 25;
							this.gp.obj[this.gp.currentMap][i].worldY = this.gp.tileSize * 28;
							this.gp.obj[this.gp.currentMap][i].temp = true;
							break;
						}
					}
					for (int i = 0; i < this.gp.npc[1].length; i++) {
						if (this.gp.npc[this.gp.currentMap][i] == null) {
							this.gp.npc[this.gp.currentMap][i] = new BoyDummy(this.gp);
							this.gp.npc[this.gp.currentMap][i].worldX = this.gp.boy.worldX;
							this.gp.npc[this.gp.currentMap][i].worldY = this.gp.boy.worldY;
							this.gp.npc[this.gp.currentMap][i].direction = this.gp.boy.direction;
							break;

						}
					}
					this.gp.boy.Drawing = false;
					this.scenePhase++;
				}
				if (this.scenePhase == 1) {
					this.gp.boy.worldY -= 2;
					if (this.gp.boy.worldY < this.gp.tileSize * 15) {
						this.scenePhase++;
					}
				}
				if (this.scenePhase == 2) {
					for (int i = 0; i < this.gp.mons[1].length; i++) {
						if (this.gp.mons[this.gp.currentMap][i] != null
								&& this.gp.mons[this.gp.currentMap][i].name == SkeletonLord.MonName) {
							this.gp.mons[this.gp.currentMap][i].Sleep = false;
							this.gp.ui.npc = this.gp.mons[this.gp.currentMap][i];
							this.scenePhase++;
							break;
						}
					}
				}
				if (this.scenePhase == 3) {
					this.gp.ui.drawDialogueScreen();

				}
				if (this.scenePhase == 4) {
					for (int i = 0; i < this.gp.npc[1].length; i++) {
						if (this.gp.npc[this.gp.currentMap][i] != null && this.gp.npc[this.gp.currentMap][i].name == BoyDummy.NPCName) {
							this.gp.boy.worldX = this.gp.npc[this.gp.currentMap][i].worldX;
							this.gp.boy.worldY = this.gp.npc[this.gp.currentMap][i].worldY;

							this.gp.npc[this.gp.currentMap][i] = null;
							break;
						}
					}
					this.gp.boy.Drawing = true;
					this.sceneNum = NA;
					this.scenePhase = 0;
					this.gp.gameState = this.gp.playstate;

					this.gp.Stopmusic();
					this.gp.playMusic(10);
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				if (this.scenePhase == 0) {
					this.gp.bossBattleOn = true;

					for (int i = 0; i < this.gp.obj[1].length; i++) {
						if (this.gp.obj[this.gp.currentMap][i] == null) {
							this.gp.obj[this.gp.currentMap][i] = new OBJDoorIron(this.gp);
							this.gp.obj[this.gp.currentMap][i].worldX = this.gp.tileSize * 25;
							this.gp.obj[this.gp.currentMap][i].worldY = this.gp.tileSize * 28;
							this.gp.obj[this.gp.currentMap][i].temp = true;
							break;
						}
					}
					for (int i = 0; i < this.gp.npc[1].length; i++) {
						if (this.gp.npc[this.gp.currentMap][i] == null) {
							this.gp.npc[this.gp.currentMap][i] = new GirlDummy(this.gp);
							this.gp.npc[this.gp.currentMap][i].worldX = this.gp.girl.worldX;
							this.gp.npc[this.gp.currentMap][i].worldY = this.gp.girl.worldY;
							this.gp.npc[this.gp.currentMap][i].direction = this.gp.girl.direction;
							break;
						}
					}
					this.gp.girl.Drawing = false;
					this.scenePhase++;
				}
				if (this.scenePhase == 1) {
					this.gp.girl.worldY -= 2;
					if (this.gp.girl.worldY < this.gp.tileSize * 15) {
						this.scenePhase++;
					}
				}
				if (this.scenePhase == 2) {
					for (int i = 0; i < this.gp.mons[1].length; i++) {
						if (this.gp.mons[this.gp.currentMap][i] != null
								&& this.gp.mons[this.gp.currentMap][i].name == SkeletonLord.MonName) {
							this.gp.mons[this.gp.currentMap][i].Sleep = false;
							this.gp.ui.npc = this.gp.mons[this.gp.currentMap][i];
							this.scenePhase++;
							break;
						}
					}
				}
				if (this.scenePhase == 3) {
					this.gp.ui.drawDialogueScreen();

				}
				if (this.scenePhase == 4) {
					for (int i = 0; i < this.gp.npc[1].length; i++) {
						if (this.gp.npc[this.gp.currentMap][i] != null && this.gp.npc[this.gp.currentMap][i].name == GirlDummy.NPCName) {
							this.gp.girl.worldX = this.gp.npc[this.gp.currentMap][i].worldX;
							this.gp.girl.worldY = this.gp.npc[this.gp.currentMap][i].worldY;
							this.gp.npc[this.gp.currentMap][i] = null;
							break;
						}
					}
					this.gp.girl.Drawing = true;
					this.sceneNum = NA;
					this.scenePhase = 0;
					this.gp.gameState = this.gp.playstate;

					this.gp.Stopmusic();
					this.gp.playMusic(10);
				}
			}
		}
	}

	public void scene_ending() {
		if (this.scenePhase == 0) {
			this.gp.Stopmusic();
			this.gp.ui.npc = new OBJBlueHeart(this.gp);
			this.scenePhase++;
		}
		if (this.scenePhase == 1) {
			this.gp.ui.drawDialogueScreen();
			this.scenePhase++;
		}
		if (this.scenePhase == 2) {
			this.gp.playSE(9);
			this.scenePhase++;
		}
		if (this.scenePhase == 3) {
			if (this.Counterreached(300) == true) {
				this.scenePhase++;
			}
		}
		if (this.scenePhase == 4) {
			this.Alpha += 0.005F;
			if (this.Alpha > 1F) {
				this.Alpha = 1F;
			}
			this.drawBlackBackGround(this.Alpha);
			if (this.Alpha == 1F) {
				this.Alpha = 0;
				this.scenePhase++;
			}
		}
		if (this.scenePhase == 5) {
			this.drawBlackBackGround(1F);

			String text = "After the fierce battle with the Skeleton Lord, \n"
					+ "finally found the legendary treasure. \n" + "But this isn't the end journey. \n"
					+ "The adventure's has just begun. ";
		
//				this.gp.gameState = this.gp.titleState;
//				this.gp.ui.titleScreenState = 0;
//				this.gp.resetGame(true);
//				this.scenePhase++;
			
			this.drawstring(Alpha, 38F, 200, text, 70);
			if (this.Counterreached(600) == true) {
				this.gp.playMusic(0);
				this.scenePhase++;
			}
		}

	}

	public boolean Counterreached(int t) {
		boolean counttereched = false;
		this.Count++;
		if (this.Count > t) {
			counttereched = true;
			this.Count = 0;

		}
		return counttereched;
	}

	public void drawBlackBackGround(float al) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, al));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}

	public void drawstring(float alpha, float fontSize, int y, String text, int lineHeight) {
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	    g2.setColor(Color.white);
	    g2.setFont(g2.getFont().deriveFont(fontSize));
	    
	    int maxY = gp.screenHeight; // Giới hạn y tối đa là chiều cao của màn hình
	    
	    for (String line : text.split("\n")) {
	        int x = this.gp.ui.getXforCenteredText(line);
	        if (y + lineHeight <= maxY) { // Kiểm tra nếu vẫn còn không gian để vẽ dòng tiếp theo
	            g2.drawString(line, x, y);
	            y += lineHeight;
	        } else {
	            break; // Dừng vòng lặp nếu vị trí y vượt quá giới hạn của màn hình
	        }
	    }
	    
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}

}
