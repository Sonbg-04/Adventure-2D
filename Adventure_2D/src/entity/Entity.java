package entity;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtivityTool;

public class Entity {

	public GamePanel gp;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, attackUp1, attackUp2, attackDown1,
			attackDown2, attackLeft1, attackleft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft,
			guardRight, img, img2, img3;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public String dialogues[][] = new String[20][20];
	public boolean collision = false;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public Entity Attacker, linkedEntity;
	public boolean temp = false;

	// STATE
	public int worldX, worldY, spriteNum = 1, dialogueSet = 0, DialogueIndex = 0;
	public boolean collisionOn = false, invincible = false, attacking = false, alive = true, dead = false,
			HPbarOn = false, onPath = false, knockBack = false, guarding = false, transparent = false,
			offBalance = false, stackable = false, opened = false, inRage = false, Sleep = false, Drawing = true;
	public Entity loot;
	public String knockbackDirection = "down", direction = "down";

	// COUNTER
	public int shotAvailableCounter = 0;
	public int spriteCounter = 0;
	public int actionlockcounter = 0;
	public int invincibleCount = 0;
	public int dieCount = 0;
	public int Hpcounter = 0;
	public int knockbackCounter = 0;
	public int amount = 1;
	public int knockBackPower = 0;
	public int guardCounter = 0;
	public int offBalanceCounter = 0;

	// CHARACTER ATTRIBUTES
	public int maxLife, life;
	public int DefaultSpeed;
	public int ammo;
	public int Maxmana, mana;
	public String name;
	public int speed;
	public int level, strength, dex, attack, def, exp, nextLVexp, coin, motion1_duration, motion2_duration;
	public Entity currentWeapon, currentShield, currentLight;
	public ProjectTile projecttile;
	public boolean boss;

	// ITEM ATTRIBUTES
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int ivtrSizeMax = 999;
	public int value;
	public int attackValue, defValue;
	public String description = " ";
	public int useCost;
	public int price;
	public int lightRadius;

	// TYPE
	public int type;
	public final int type_player_boy = 0;
	public final int type_player_girl = 1;
	public final int type_npc = 2;
	public final int type_monster = 3;

	// SWORD
	public final int type_normal_sword = 4;
	public final int type_golden_sword = 5;
	public final int type_diamond_sword = 6;

	// AXE
	public final int type_normal_axe = 7;
	public final int type_golden_axe = 8;
	public final int type_diamond_axe = 9;

	// GUARD
	public final int type_normal_shield = 10;
	public final int type_golden_shield = 11;
	public final int type_diamond_shield = 12;
	public final int type_premium_shield = 13;

	// KNIFE
	public final int type_normal_knife = 14;
	public final int type_golden_knife = 15;
	public final int type_diamond_knife = 16;

	public final int type_consumable = 17;
	public final int type_pickupOnly = 18;
	public final int type_obstacle = 19;
	public final int type_light = 20;
	public final int type_pickaxe = 21;

	// CONSTRUCTOR
	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public int getScreenX() {
		int screenX = 0;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				screenX = this.worldX - this.gp.boy.worldX + this.gp.boy.screenX;
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				screenX = this.worldX - this.gp.girl.worldX + this.gp.girl.screenX;
			}
		}
		return screenX;
	}

	public int getScreenY() {
		int screenY = 0;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				screenY = this.worldY - this.gp.boy.worldY + this.gp.boy.screenY;
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				screenY = this.worldY - this.gp.girl.worldY + this.gp.girl.screenY;
			}

		}
		return screenY;
	}

	public int getLeftX() {
		return this.worldX + this.solidArea.x;
	}

	public int getRightX() {
		return this.worldX + this.solidArea.x + this.solidArea.width;
	}

	public int getTopY() {
		return this.worldY + this.solidArea.y;
	}

	public int getBottomY() {
		return this.worldY + this.solidArea.y + this.solidArea.height;
	}

	public int getCol() {
		return (this.worldX + this.solidArea.x) / this.gp.tileSize;
	}

	public int getRow() {
		return (this.worldY + this.solidArea.y) / this.gp.tileSize;
	}

	public int getCenterX() {
		int centerX = 0;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				centerX = this.worldX + this.gp.boy.left1.getWidth() / 2;
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				centerX = this.worldX + this.gp.girl.left1.getWidth() / 2;
			}
		}
		return centerX;
	}

	public int getCenterY() {
		int centerY = 0;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				centerY = this.worldY + this.gp.boy.up1.getHeight() / 2;
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				centerY = this.worldY + this.gp.girl.up1.getHeight() / 2;
			}
		}
		return centerY;
	}

	public int getXdistance(Entity target) {
		int xDistance = Math.abs(getCenterX() - target.getCenterX());
		return xDistance;
	}

	public int getYdistance(Entity target) {
		int yDistance = Math.abs(getCenterY() - target.getCenterY());
		return yDistance;
	}

	public int getTileDistance(Entity target) {
		int tileDistance = (getXdistance(target) + getYdistance(target)) / this.gp.tileSize;
		return tileDistance;
	}

	public int getGoalCol(Entity target) {
		int goalCol = (target.worldX + target.solidArea.x) / this.gp.tileSize;
		return goalCol;
	}

	public int getGoalRow(Entity target) {
		int goalRow = (target.worldY + target.solidArea.y) / this.gp.tileSize;
		return goalRow;
	}

	//

	public void checkDrop() {
	}

	public void dropItem(Entity droppedItem) {
		for (int i = 0; i < this.gp.obj[1].length; i++) {
			if (this.gp.obj[this.gp.currentMap][i] == null) {
				this.gp.obj[this.gp.currentMap][i] = droppedItem;
				this.gp.obj[this.gp.currentMap][i].worldX = this.worldX; // THE DEAD MONSTER'S WORLD X
				this.gp.obj[this.gp.currentMap][i].worldY = this.worldY;
				break;
			}
		}
	}

	public void Speak() {
	}

	public void facePlayer() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				switch (this.gp.boy.direction) {
				case "up":
					this.direction = "down";
					break;
				case "down":
					this.direction = "up";
					break;
				case "left":
					this.direction = "right";
					break;
				case "right":
					this.direction = "left";
					break;
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				switch (this.gp.girl.direction) {
				case "up":
					this.direction = "down";
					break;
				case "down":
					this.direction = "up";
					break;
				case "left":
					this.direction = "right";
					break;
				case "right":
					this.direction = "left";
					break;
				}
			}
		}
	}

	public void startDialogue(Entity ent, int setNum) {
		this.gp.gameState = this.gp.dialogueState;
		this.gp.ui.npc = ent;
		this.dialogueSet = setNum;
	}

	public boolean use(Entity entity) {
		return false;
	}

	public void setLoot(Entity loot) {

	}

	public void setAction() {
	}

	public void move(String direction) {
	}

	public void DamageReaction() {
	}

	public void Interact() {

	}

	public Color getParticleColor() {
		Color cl = null;
		return cl;
	}

	public int getParticleSize() {
		int size = 0;
		return size;
	}

	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}

	public int getParticlemaxLife() {
		int maxlife = 0;
		return maxlife;
	}

	public void checkCollision() {
		this.collisionOn = false;
		this.gp.ccheck.Checktile(this);
		this.gp.ccheck.CheckObject(this, false);
		this.gp.ccheck.checkEntity(this, this.gp.npc);
		this.gp.ccheck.checkEntity(this, this.gp.mons);
		this.gp.ccheck.checkEntity(this, this.gp.iTile);
		boolean contactPlay = this.gp.ccheck.CheckPlayer(this);

		if (this.type == this.type_monster && contactPlay == true) {
			DamagePlayer(this.attack);
		}
	}

	public String getOppositeDirection(String direction) {
		String oppositeDirection = " ";
		switch (direction) {
		case "up": {
			oppositeDirection = "down";
			break;
		}
		case "down": {
			oppositeDirection = "up";
			break;
		}
		case "left": {
			oppositeDirection = "right";
			break;
		}
		case "right": {
			oppositeDirection = "left";
			break;
		}
		}
		return oppositeDirection;
	}

	public void resetCounter() {
		this.shotAvailableCounter = 0;
		this.spriteCounter = 0;
		this.actionlockcounter = 0;
		this.invincibleCount = 0;
		this.dieCount = 0;
		this.Hpcounter = 0;
		this.knockbackCounter = 0;
		this.guardCounter = 0;
		this.offBalanceCounter = 0;

	}

	public void Attacking() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				this.spriteCounter++;
				if (this.spriteCounter <= this.motion1_duration) {
					this.spriteNum = 1;
				}
				if (this.spriteCounter > this.motion1_duration && this.spriteCounter <= this.motion2_duration) {
					this.spriteNum = 2;

					// SAVE THE CURRENT WORLDX, WORLDY AND SOLIDAREA
					int CurrentWorldX = this.worldX;
					int CurrentWorldY = this.worldY;
					int SolidareaWidth = this.solidArea.width;
					int SolidareaHeight = this.solidArea.height;

					// ADJUST PLAYER'S WORLDX/Y FOR THE ATTACKAREA
					switch (this.direction) {
					case "up":
						this.worldY -= this.attackArea.height;
						break;
					case "down":
						this.worldY += this.attackArea.height;
						break;
					case "left":
						this.worldX -= this.attackArea.width;
						break;
					case "right":
						this.worldX += this.attackArea.width;
						break;
					}

					// ATTACKAREA BECOMES SOLIDAREA
					this.solidArea.height = this.attackArea.height;
					this.solidArea.width = this.attackArea.width;
					if (this.type == this.type_monster) {
						if (this.gp.ccheck.CheckPlayer(this) == true) {
							this.DamagePlayer(this.attack);
						}
					} else {
						// CHECK MONSTER COLLISION WITH THE UPDATED WORLDX, WORLDY AND SOLIDAREA
						int MonsIndex = this.gp.ccheck.checkEntity(this, this.gp.mons);
						this.gp.boy.DamageMonster(MonsIndex, this, this.attack, this.currentWeapon.knockBackPower);

						int itileIndex = this.gp.ccheck.checkEntity(this, this.gp.iTile);
						this.gp.boy.DamageInteractiveTile(itileIndex);

						int projectTileIndex = this.gp.ccheck.checkEntity(this, this.gp.projectTile);
						this.gp.boy.DamageProJectTile(projectTileIndex);

					}
					// AFTER CHECKING COLLISION, RESULT THE ORIGINAL DATA
					this.worldX = CurrentWorldX;
					this.worldY = CurrentWorldY;
					this.solidArea.width = SolidareaWidth;
					this.solidArea.height = SolidareaHeight;
				}
				if (this.spriteCounter > this.motion2_duration) {
					this.spriteNum = 1;
					this.spriteCounter = 0;
					this.attacking = false;
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				this.spriteCounter++;
				if (this.spriteCounter <= this.motion1_duration) {
					this.spriteNum = 1;
				}
				if (this.spriteCounter > this.motion1_duration && this.spriteCounter <= this.motion2_duration) {
					this.spriteNum = 2;

					// SAVE THE CURRENT WORLDX, WORLDY AND SOLIDAREA
					int CurrentWorldX = this.worldX;
					int CurrentWorldY = this.worldY;
					int SolidareaWidth = this.solidArea.width;
					int SolidareaHeight = this.solidArea.height;

					// ADJUST PLAYER'S WORLDX/Y FOR THE ATTACKAREA
					switch (this.direction) {
					case "up":
						this.worldY -= this.attackArea.height;
						break;
					case "down":
						this.worldY += this.attackArea.height;
						break;
					case "left":
						this.worldX -= this.attackArea.width;
						break;
					case "right":
						this.worldX += this.attackArea.width;
						break;
					}

					// ATTACKAREA BECOMES SOLIDAREA
					this.solidArea.height = this.attackArea.height;
					this.solidArea.width = this.attackArea.width;
					if (this.type == this.type_monster) {
						if (this.gp.ccheck.CheckPlayer(this) == true) {
							this.DamagePlayer(this.attack);
						}
					} else {
						// CHECK MONSTER COLLISION WITH THE UPDATED WORLDX, WORLDY AND SOLIDAREA
						int MonsIndex = this.gp.ccheck.checkEntity(this, this.gp.mons);
						this.gp.girl.DamageMonster(MonsIndex, this, this.attack, this.currentWeapon.knockBackPower);

						int itileIndex = this.gp.ccheck.checkEntity(this, this.gp.iTile);
						this.gp.girl.DamageInteractiveTile(itileIndex);

						int projectTileIndex = this.gp.ccheck.checkEntity(this, this.gp.projectTile);
						this.gp.girl.DamageProJectTile(projectTileIndex);

					}
					// AFTER CHECKING COLLISION, RESULT THE ORIGINAL DATA
					this.worldX = CurrentWorldX;
					this.worldY = CurrentWorldY;
					this.solidArea.width = SolidareaWidth;
					this.solidArea.height = SolidareaHeight;
				}
				if (this.spriteCounter > this.motion2_duration) {
					this.spriteNum = 1;
					this.spriteCounter = 0;
					this.attacking = false;
				}
			}
		}
	}

	public void Update() {
		if (this.Sleep == false) {
			if (this.knockBack == true) {
				this.checkCollision();

				if (this.collisionOn == true) {
					this.knockbackCounter = 0;
					this.knockBack = false;
					this.speed = this.DefaultSpeed;
				} else {
					switch (this.knockbackDirection) {
					case "up":
						this.worldY -= this.speed;
						break;

					case "down":
						this.worldY += this.speed;
						break;

					case "left":
						this.worldX -= this.speed;
						break;

					case "right":
						this.worldX += this.speed;
						break;

					}
				}
				this.knockbackCounter++;
				if (this.knockbackCounter == 10) {
					this.knockbackCounter = 0;
					this.knockBack = false;
					this.speed = this.DefaultSpeed;
				}
			}

			else if (this.attacking == true) {
				this.Attacking();
			}

			else {
				this.setAction();
				this.checkCollision();

				// IF COLLISION IS FALSE, PLAYER CAN MOVE
				if (this.collisionOn == false) {
					switch (this.direction) {
					case "up":
						this.worldY -= this.speed;
						break;

					case "down":
						this.worldY += this.speed;
						break;

					case "left":
						this.worldX -= this.speed;
						break;

					case "right":
						this.worldX += this.speed;
						break;
					}
				}
				this.spriteCounter++;
				if (this.spriteCounter > 12) {
					if (this.spriteNum == 1) {
						this.spriteNum = 2;
					} else if (this.spriteNum == 2) {
						this.spriteNum = 1;
					}
					this.spriteCounter = 0;
				}
			}

			if (this.invincible == true) {
				this.invincibleCount++;
				if (this.invincibleCount > 40) {
					this.invincible = false;
					this.invincibleCount = 0;
				}
			}
			if (this.shotAvailableCounter < 30) {
				this.shotAvailableCounter++;
			}
			if (this.offBalance == true) {
				this.offBalanceCounter++;
				if (this.offBalanceCounter > 60) {
					this.offBalanceCounter = 0;
					this.offBalance = false;
				}
			}

		}
	}

	public void CheckAttackOrNot(int rate, int straight, int horizontal) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				boolean targetInRange = false;
				int xD = getXdistance(this.gp.boy);
				int yD = getYdistance(this.gp.boy);

				switch (this.direction) {
				case "up": {
					if (this.gp.boy.getCenterY() < getCenterY() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				case "down": {
					if (this.gp.boy.getCenterY() > getCenterY() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				case "left": {
					if (this.gp.boy.getCenterX() < getCenterX() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				case "right": {
					if (this.gp.boy.getCenterX() > getCenterX() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				}
				if (targetInRange == true) {
					int i = new Random().nextInt(rate);
					if (i == 0) {
						this.attacking = true;
						this.spriteNum = 1;
						this.spriteCounter = 0;
						this.shotAvailableCounter = 0;

					}
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				boolean targetInRange = false;
				int xD = getXdistance(this.gp.girl);
				int yD = getYdistance(this.gp.girl);

				switch (direction) {
				case "up": {
					if (this.gp.girl.getCenterY() < getCenterY() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				case "down": {
					if (this.gp.girl.getCenterY() > getCenterY() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				case "left": {
					if (this.gp.girl.getCenterX() < getCenterX() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				case "right": {
					if (this.gp.girl.getCenterX() > getCenterX() && yD < straight && xD < horizontal) {
						targetInRange = true;
					}
					break;
				}
				}
				if (targetInRange == true) {
					int i = new Random().nextInt(rate);
					if (i == 0) {
						this.attacking = true;
						this.spriteNum = 1;
						this.spriteCounter = 0;
						this.shotAvailableCounter = 0;

					}
				}
			}
		}
	}

	public void setKnockBack(Entity e, Entity attacker, int knockBackP) {
		this.Attacker = attacker;
		e.knockbackDirection = attacker.direction;

		e.speed += knockBackP;
		e.knockBack = true;
	}

	public void CheckShootOrNot(int rate, int shotInterval) {
		int i = new Random().nextInt(rate);
		if (i == 0 && this.projecttile.alive == false && this.shotAvailableCounter == shotInterval) {
			this.projecttile.Set(this.worldX, this.worldY, this.direction, true, this);
			for (int j = 0; j < this.gp.projectTile[1].length; j++) {
				if (this.gp.projectTile[this.gp.currentMap][j] == null) {
					this.gp.projectTile[this.gp.currentMap][j] = this.projecttile;
					break;
				}
			}
			this.shotAvailableCounter = 0;

		}
	}

	public void CheckStartChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int j = new Random().nextInt(rate);
			if (j == 0) {
				this.onPath = true;
			}
		}
	}

	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				this.onPath = false;
			}
		}
	}

	public void getRandomDirection(int interval) {
		this.actionlockcounter++;
		if (this.actionlockcounter == interval) {
			Random rd = new Random();
			int k = rd.nextInt(100) + 1; // PICK UP A NUMBER FROM 1 TO 100

			if (k <= 25) {
				direction = "up";
			} else if (k > 25 && k <= 50) {
				direction = "down";
			} else if (k > 50 && k <= 75) {
				direction = "left";
			} else {
				direction = "right";
			}
			this.actionlockcounter = 0;
		}
	}

	public void DamagePlayer(int attack) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				if (this.gp.boy.invincible == false) {

					int dmg = attack - this.gp.boy.def;

					// GET AN OPPOSITE DIRECTION OF THIS ATTACKER
					String canGuard = this.getOppositeDirection(this.direction);

					if (this.gp.boy.guarding == true && this.gp.boy.direction.equals(canGuard)) {

						// PARRY
						if (this.gp.boy.guardCounter < 10) {
							dmg = 0;
							this.setKnockBack(this, this.gp.boy, this.knockBackPower);
							this.offBalance = true;
							this.spriteCounter = -60;
						}

						// NORMAL GUARD
						else {
							dmg /= 3;
						}

					} else {
						// NOT GUARDING
						if (dmg < 1) {
							dmg = 1;
						}
					}
					if (dmg != 0) {
						
						
						this.gp.boy.transparent = true;
						this.setKnockBack(this.gp.boy, this, this.knockBackPower);
					}

					this.gp.boy.life -= dmg;
					this.gp.boy.invincible = true;
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				if (this.gp.girl.invincible == false) {

					int dmg = attack - this.gp.girl.def;

					// GET AN OPPOSITE DIRECTION OF THIS ATTACKER
					String canGuard = this.getOppositeDirection(direction);

					if (this.gp.girl.guarding == true && this.gp.girl.direction.equals(canGuard)) {

						// PARaRY
						if (this.gp.girl.guardCounter < 10) {
							dmg = 0;
							this.setKnockBack(this, this.gp.girl, this.knockBackPower);
							this.offBalance = true;
							this.spriteCounter = -60;
						}

						// NORMAL GUARD
						else {
							dmg /= 3;
						}

					} else {
						// NOT GUARDING
						if (dmg < 1) {
							dmg = 1;
						}
					}
					if (dmg != 0) {
						this.gp.girl.transparent = true;
						this.setKnockBack(this.gp.girl, this, this.knockBackPower);
					}

					this.gp.girl.life -= dmg;
					this.gp.girl.invincible = true;
				}
			}
		}

	}

	public BufferedImage Setup(String imgpath, int width, int height) {
		UtivityTool utool = new UtivityTool();
		BufferedImage image = null;
		try {
			image = utool.scaleImage(ImageIO.read(getClass().getResourceAsStream(imgpath + ".png")), width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void moveTowardPlayer(int interval) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				this.actionlockcounter++;
				if (this.actionlockcounter > interval) {
					if (getXdistance(this.gp.boy) > getYdistance(this.gp.boy)) {
						if (this.gp.boy.getCenterX() < getCenterX()) {
							this.direction = "left";
						} else {
							this.direction = "right";
						}
					} else if (getXdistance(this.gp.boy) < getYdistance(this.gp.boy)) {
						if (this.gp.boy.getCenterY() < getCenterY()) {
							this.direction = "up";
						} else {
							this.direction = "down";
						}
					}
					this.actionlockcounter = 0;
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				this.actionlockcounter++;
				if (this.actionlockcounter > interval) {
					if (getXdistance(this.gp.girl) > getYdistance(this.gp.girl)) {
						if (this.gp.girl.getCenterX() < getCenterX()) {
							this.direction = "left";
						} else {
							this.direction = "right";
						}
					} else if (getXdistance(this.gp.girl) < getYdistance(this.gp.girl)) {
						if (this.gp.girl.getCenterY() < getCenterY()) {
							this.direction = "up";
						} else {
							this.direction = "down";
						}
					}
					this.actionlockcounter = 0;
				}
			}
		}
	}

	public boolean inCamera() {
		boolean inCam = false;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				if (worldX + this.gp.tileSize * 5 > this.gp.boy.worldX - this.gp.boy.screenX
						&& worldX - this.gp.tileSize < this.gp.boy.worldX + this.gp.boy.screenX
						&& worldY + this.gp.tileSize * 5 > this.gp.boy.worldY - this.gp.boy.screenY
						&& worldY - this.gp.tileSize < this.gp.boy.worldY + this.gp.boy.screenY) {
					inCam = true;
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				if (worldX + this.gp.tileSize * 5 > this.gp.girl.worldX - this.gp.girl.screenX
						&& worldX - this.gp.tileSize < this.gp.girl.worldX + this.gp.girl.screenX
						&& worldY + this.gp.tileSize * 5 > this.gp.girl.worldY - this.gp.girl.screenY
						&& worldY - this.gp.tileSize < this.gp.girl.worldY + this.gp.girl.screenY) {
					inCam = true;
				}
			}
		}
		return inCam;
	}

	public void draw(Graphics2D g2) {
		BufferedImage img = null;

		if (this.inCamera() == true) {

			int TemScreenX = this.getScreenX(), TemScreenY = this.getScreenY();
			switch (this.direction) {
			case "up":
				if (this.attacking == false) {
					if (this.spriteNum == 1) {
						img = this.up1;
					}
					if (this.spriteNum == 2) {
						img = this.up2;
					}
				}
				if (this.attacking == true) {
					TemScreenY = this.getScreenY() - this.up1.getHeight();
					if (spriteNum == 1) {
						img = attackUp1;
					}
					if (spriteNum == 2) {
						img = attackUp2;
					}
				}
				break;
			case "down":
				if (this.attacking == false) {
					if (this.spriteNum == 1) {
						img = this.down1;
					}
					if (this.spriteNum == 2) {
						img = this.down2;
					}
				}
				if (this.attacking == true) {
					if (this.spriteNum == 1) {
						img = this.attackDown1;
					}
					if (this.spriteNum == 2) {
						img = this.attackDown2;
					}
				}
				break;
			case "left":
				if (this.attacking == false) {
					if (this.spriteNum == 1) {
						img = this.left1;
					}
					if (this.spriteNum == 2) {
						img = this.left2;
					}
				}
				if (this.attacking == true) {
					TemScreenX = this.getScreenX() - this.left1.getWidth();
					if (this.spriteNum == 1) {
						img = this.attackLeft1;
					}
					if (this.spriteNum == 2) {
						img = this.attackleft2;
					}
				}
				break;
			case "right":
				if (this.attacking == false) {
					if (this.spriteNum == 1) {
						img = this.right1;
					}
					if (this.spriteNum == 2) {
						img = this.right2;
					}
				}
				if (this.attacking == true) {
					if (this.spriteNum == 1) {
						img = this.attackRight1;
					}
					if (this.spriteNum == 2) {
						img = this.attackRight2;
					}
				}
				break;
			}
			if (this.invincible == true) {
				this.HPbarOn = true;
				this.Hpcounter = 0;
				ChangeAlpha(g2, 0.4F);
			}
			if (this.dead == true) {
				DieAnimation(g2);
			}
			g2.drawImage(img, TemScreenX, TemScreenY, null);
			ChangeAlpha(g2, 1F);
		}
	}

	public void DieAnimation(Graphics2D g2) {
		dieCount++;
		int i = 5;
		if (dieCount <= i) {
			ChangeAlpha(g2, 0F);
		}
		if (dieCount > i && dieCount <= i * 2) {
			ChangeAlpha(g2, 1F);
		}
		if (dieCount > i * 2 && dieCount <= i * 3) {
			ChangeAlpha(g2, 0F);
		}
		if (dieCount > i * 3 && dieCount <= i * 4) {
			ChangeAlpha(g2, 1F);
		}
		if (dieCount > i * 4 && dieCount <= i * 5) {
			ChangeAlpha(g2, 0F);
		}
		if (dieCount > i * 5 && dieCount <= i * 6) {
			ChangeAlpha(g2, 1F);
		}
		if (dieCount > i * 6 && dieCount <= i * 7) {
			ChangeAlpha(g2, 0F);
		}
		if (dieCount > i * 7 && dieCount <= i * 8) {
			ChangeAlpha(g2, 1F);
		}
		if (dieCount > i * 8) {
			this.alive = false;
		}
	}

	public void ChangeAlpha(Graphics2D g2, float avl) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, avl));
	}

	public void generateParticle(Entity e, Entity target) {
		Color color = e.getParticleColor();
		int size = e.getParticleSize();
		int maxLife = e.getParticlemaxLife();
		int speed = e.getParticleSpeed();
		Particle p1 = new Particle(this.gp, e, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(this.gp, e, color, size, speed, maxLife, -2, 1);
		Particle p3 = new Particle(this.gp, e, color, size, speed, maxLife, 2, -1);
		Particle p4 = new Particle(this.gp, e, color, size, speed, maxLife, 2, 1);
		this.gp.particleList.add(p1);
		this.gp.particleList.add(p2);
		this.gp.particleList.add(p3);
		this.gp.particleList.add(p4);
	}

	public void searchPath(int goalCol, int goalRow) {
		int startCol = (this.worldX + this.solidArea.x) / this.gp.tileSize;
		int startRow = (this.worldY + this.solidArea.y) / this.gp.tileSize;

		this.gp.pFind.setNodes(startCol, startRow, goalCol, goalRow, this);

		if (this.gp.pFind.Search() == true) {

			int nextX = this.gp.pFind.pathList.get(0).col * this.gp.tileSize;
			int nextY = this.gp.pFind.pathList.get(0).row * this.gp.tileSize;

			int enleftX = this.worldX + this.solidArea.x;
			int enRightX = this.worldX + this.solidArea.x + this.solidArea.width;
			int enTopY = this.worldY + this.solidArea.y;
			int enBottomY = this.worldY + this.solidArea.y + this.solidArea.height;

			if (enleftX >= nextX && enTopY > nextY && enRightX < nextX + this.gp.tileSize) {
				this.direction = "up";
			} else if (enleftX >= nextX && enTopY < nextY && enRightX < nextX + this.gp.tileSize) {
				this.direction = "down";
			} else if (enTopY >= nextY && enleftX > nextX && enBottomY < nextY + this.gp.tileSize) {
				this.direction = "left";
			} else if (enTopY >= nextY && enleftX < nextX && enBottomY < nextY + this.gp.tileSize) {
				this.direction = "right";
			} else if (enTopY >= nextY && enleftX >= nextX) {
				this.direction = "up";
				this.checkCollision();
				if (this.collisionOn == true) {
					this.direction = "left";
				}
			} else if (enTopY >= nextY && enleftX <= nextX) {
				this.direction = "up";
				this.checkCollision();
				if (this.collisionOn == true) {
					this.direction = "right";
				}
			} else if (enTopY <= nextY && enleftX >= nextX) {
				this.direction = "down";
				this.checkCollision();
				if (this.collisionOn == true) {
					this.direction = "left";
				}
			} else if (enTopY <= nextY && enleftX <= nextX) {
				this.direction = "down";
				this.checkCollision();
				if (this.collisionOn == true) {
					this.direction = "right";
				}
			}
			int nextCol = this.gp.pFind.pathList.get(0).col;
			int nextRow = this.gp.pFind.pathList.get(0).row;
			if (nextCol == goalCol && nextRow == goalRow) {
				this.onPath = false;
			}
		}
	}

	public int getDetected(Entity e, Entity obj[][], String target) {
		int index = 999;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				// CHECK THE SURROUNDING OBJECT
				int nextWorldX = e.getLeftX();
				int nextWorldY = e.getTopY();

				switch (e.direction) {
				case "up": {
					nextWorldY = e.getTopY() - this.gp.boy.speed;
					break;
				}
				case "down": {
					nextWorldY = e.getBottomY() + this.gp.boy.speed;
					break;
				}
				case "left": {
					nextWorldX = e.getLeftX() - this.gp.boy.speed;
					break;
				}
				case "right": {
					nextWorldX = e.getRightX() + this.gp.boy.speed;
					break;
				}
				}
				int col = nextWorldX / this.gp.tileSize;
				int row = nextWorldY / this.gp.tileSize;
				for (int i = 0; i < obj[1].length; i++) {
					if (obj[this.gp.currentMap][i] != null) {
						if (obj[this.gp.currentMap][i].getCol() == col && obj[this.gp.currentMap][i].getRow() == row
								&& obj[this.gp.currentMap][i].name.equals(target)) {
							index = i;
							break;

						}
					}
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				// CHECK THE SURROUNDING OBJECT
				int nextWorldX = e.getLeftX();
				int nextWorldY = e.getTopY();

				switch (e.direction) {
				case "up": {
					nextWorldY = e.getTopY() - this.gp.girl.speed;
					break;
				}
				case "down": {
					nextWorldY = e.getBottomY() + this.gp.girl.speed;
					break;
				}
				case "left": {
					nextWorldX = e.getLeftX() - this.gp.girl.speed;
					break;
				}
				case "right": {
					nextWorldX = e.getRightX() + this.gp.girl.speed;
					break;
				}
				}
				int col = nextWorldX / this.gp.tileSize;
				int row = nextWorldY / this.gp.tileSize;
				for (int i = 0; i < obj[1].length; i++) {
					if (obj[this.gp.currentMap][i] != null) {
						if (obj[this.gp.currentMap][i].getCol() == col && obj[this.gp.currentMap][i].getRow() == row
								&& obj[this.gp.currentMap][i].name.equals(target)) {
							index = i;
							break;

						}
					}
				}
			}
		}
		return index;
	}
}
