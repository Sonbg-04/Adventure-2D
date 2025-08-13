package main;

import data.Progress;
import entity.Entity;

public class EventHandler {
	public GamePanel gp;
	public Entity eventMaster;
	public EventRect eventRec[][][];
	public int previousEventX, previousEventY;
	public boolean canTouchEvent = true;
	public int tempMap, tempCol, tempRow;

	public EventHandler(GamePanel gp) {
		this.gp = gp;
		this.eventMaster = new Entity(this.gp);
		this.eventRec = new EventRect[this.gp.maxMap][this.gp.maxWorldCol][this.gp.maxWorldRow];
		int map = 0;
		int col = 0;
		int row = 0;
		while (map < this.gp.maxMap && col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
			this.eventRec[map][col][row] = new EventRect();
			this.eventRec[map][col][row].x = 23;
			this.eventRec[map][col][row].y = 23;
			this.eventRec[map][col][row].width = 2;
			this.eventRec[map][col][row].height = 2;
			this.eventRec[map][col][row].eventRecDefaultX = this.eventRec[map][col][row].x;
			this.eventRec[map][col][row].eventRecDefaultY = this.eventRec[map][col][row].y;
			col++;
			if (col == this.gp.maxWorldCol) {
				col = 0;
				row++;
				if (row == this.gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}

		}
		this.setDialogue();
	}

	public void setDialogue() {
		this.eventMaster.dialogues[0][0] = "You drink the water.\nYour life and mana has been recovered!"
				+ "\n(The progress has been saved)";
		this.eventMaster.dialogues[0][1] = "Damn, this is good water.";
		this.eventMaster.dialogues[1][0] = "You fall into a pit!";
	}

	public void checkEvent() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				// CHECK IF THE PLAYER CHARACTER IS MORE THAN 1 TILE AWAY FROM THE LAST EVENT
				int xDistance = Math.abs(this.gp.boy.worldX - this.previousEventX);
				int yDistance = Math.abs(this.gp.boy.worldY - this.previousEventY);
				int distance = Math.max(xDistance, yDistance);
				if (distance > this.gp.tileSize) {
					this.canTouchEvent = true;
				}
				if (this.canTouchEvent == true) {
					if (this.hit(0, 27, 16, "right") == true) {
						this.damagePit(this.gp.dialogueState);
					} else if (this.hit(0, 23, 12, "up") == true) {
						this.healingpool(this.gp.dialogueState);
					} else if (this.hit(0, 10, 39, "any") == true) {
						this.teleports(1, 12, 13, this.gp.indoor);
					} else if (this.hit(1, 12, 13, "any") == true) {
						this.teleports(0, 10, 39, this.gp.outside);
					} else if (this.hit(1, 12, 9, "up") == true) {
						this.Speak(this.gp.npc[1][0]);
					} else if (this.hit(0, 12, 9, "any") == true) {
						this.teleports(2, 9, 41, this.gp.dungeon);
					} else if (this.hit(2, 9, 41, "any") == true) {
						this.teleports(0, 12, 9, this.gp.outside);
					} else if (this.hit(2, 8, 7, "any") == true) {
						this.teleports(3, 26, 41, this.gp.dungeon);
					} else if (this.hit(3, 26, 41, "any") == true) {
						this.teleports(2, 8, 7, this.gp.dungeon);
					} else if (this.hit(0, 30, 29, "any") == true) {
						this.teleports(4, 15, 12, this.gp.desert);
					} else if (this.hit(4, 15, 12, "any") == true) {
						this.teleports(0, 30, 29, this.gp.outside);
					} else if (this.hit(3, 25, 27, "any") == true) {
						this.SkeletonLord();
					}

				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				// CHECK IF THE PLAYER CHARACTER IS MORE THAN 1 TILE AWAY FROM THE LAST EVENT
				int xDistance = Math.abs(this.gp.girl.worldX - this.previousEventX);
				int yDistance = Math.abs(this.gp.girl.worldY - this.previousEventY);
				int distance = Math.max(xDistance, yDistance);
				if (distance > this.gp.tileSize) {
					this.canTouchEvent = true;
				}
				if (this.canTouchEvent == true) {
					if (this.hit(0, 27, 16, "right") == true) {
						this.damagePit(this.gp.dialogueState);
					} else if (this.hit(0, 23, 12, "up") == true) {
						this.healingpool(this.gp.dialogueState);
					} else if (this.hit(0, 10, 39, "any") == true) {
						this.teleports(1, 12, 13, this.gp.indoor);
					} else if (this.hit(1, 12, 13, "any") == true) {
						this.teleports(0, 10, 39, this.gp.outside);
					} else if (this.hit(1, 12, 9, "up") == true) {
						this.Speak(this.gp.npc[1][0]);
					} else if (this.hit(0, 12, 9, "any") == true) {
						this.teleports(2, 9, 41, this.gp.dungeon);
					} else if (this.hit(2, 9, 41, "any") == true) {
						this.teleports(0, 12, 9, this.gp.outside);
					} else if (this.hit(2, 8, 7, "any") == true) {
						this.teleports(3, 26, 41, this.gp.dungeon);
					} else if (this.hit(3, 26, 41, "any") == true) {
						this.teleports(2, 8, 7, this.gp.dungeon);
					} else if (this.hit(0, 30, 29, "any") == true) {
						this.teleports(4, 15, 12, this.gp.desert);
					} else if (this.hit(4, 15, 12, "any") == true) {
						this.teleports(0, 30, 29, this.gp.outside);
					} else if (this.hit(3, 25, 27, "any") == true) {
						this.SkeletonLord();
					}

				}
			}
		}
	}

	public void damagePit(int gst) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				this.gp.gameState = gst;
				this.eventMaster.startDialogue(this.eventMaster, 1);
				this.gp.boy.life -= 1;
				this.canTouchEvent = false;
				this.gp.playSE(17);
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				this.gp.gameState = gst;
				this.eventMaster.startDialogue(this.eventMaster, 1);
				this.gp.girl.life -= 1;
				this.canTouchEvent = false;
				this.gp.playSE(17);
			}
		}
	}

	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				if (map == this.gp.currentMap) {
					this.gp.boy.solidArea.x = this.gp.boy.worldX + this.gp.boy.solidArea.x;
					this.gp.boy.solidArea.y = this.gp.boy.worldY + this.gp.boy.solidArea.y;
					this.eventRec[map][col][row].x = col * this.gp.tileSize + this.eventRec[map][col][row].x;
					this.eventRec[map][col][row].y = row * this.gp.tileSize + this.eventRec[map][col][row].y;
					if (this.gp.boy.solidArea.intersects(this.eventRec[map][col][row])
							&& this.eventRec[map][col][row].eventDone == false) {
						if (this.gp.boy.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
							hit = true;
							this.previousEventX = this.gp.boy.worldX;
							this.previousEventY = this.gp.boy.worldY;
						}
					}
					this.gp.boy.solidArea.x = this.gp.boy.solidAreaDefaultX;
					this.gp.boy.solidArea.y = this.gp.boy.solidAreaDefaultY;
					this.eventRec[map][col][row].x = this.eventRec[map][col][row].eventRecDefaultX;
					this.eventRec[map][col][row].y = this.eventRec[map][col][row].eventRecDefaultY;
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				if (map == this.gp.currentMap) {
					this.gp.girl.solidArea.x = this.gp.girl.worldX + this.gp.girl.solidArea.x;
					this.gp.girl.solidArea.y = this.gp.girl.worldY + this.gp.girl.solidArea.y;
					this.eventRec[map][col][row].x = col * this.gp.tileSize + this.eventRec[map][col][row].x;
					this.eventRec[map][col][row].y = row * this.gp.tileSize + this.eventRec[map][col][row].y;
					if (this.gp.girl.solidArea.intersects(this.eventRec[map][col][row])
							&& this.eventRec[map][col][row].eventDone == false) {
						if (this.gp.girl.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
							hit = true;
							this.previousEventX = this.gp.girl.worldX;
							this.previousEventY = this.gp.girl.worldY;
						}
					}
					this.gp.girl.solidArea.x = this.gp.girl.solidAreaDefaultX;
					this.gp.girl.solidArea.y = this.gp.girl.solidAreaDefaultY;
					this.eventRec[map][col][row].x = this.eventRec[map][col][row].eventRecDefaultX;
					this.eventRec[map][col][row].y = this.eventRec[map][col][row].eventRecDefaultY;
				}
			}
		}
		return hit;
	}

	public void healingpool(int gst) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				if (this.gp.khl.enterPressed == true) {
					this.gp.gameState = gst;
					this.gp.boy.attackCancel = true;
					this.eventMaster.startDialogue(this.eventMaster, 0);
					this.gp.boy.life = this.gp.boy.maxLife;
					this.gp.boy.mana = this.gp.boy.Maxmana;
					this.gp.aSetter.setMonster();
					this.gp.playSE(9);
					this.gp.saveload.Save();
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				if (this.gp.khl.enterPressed == true) {
					this.gp.gameState = gst;
					this.gp.girl.attackCancel = true;
					this.eventMaster.startDialogue(this.eventMaster, 0);
					this.gp.girl.life = this.gp.girl.maxLife;
					this.gp.girl.mana = this.gp.girl.Maxmana;
					this.gp.aSetter.setMonster();
					this.gp.playSE(9);
					this.gp.saveload.Save();
				}
			}
		}
	}

	public void teleports(int map, int col, int row, int area) {
		this.gp.gameState = this.gp.transitionState;
		this.tempMap = map;
		this.tempCol = col;
		this.tempRow = row;
		this.gp.nextArea = area;
		this.canTouchEvent = false;
		this.gp.playSE(20);
	}

	public void Speak(Entity entity) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				if (this.gp.khl.enterPressed == true) {
					this.gp.gameState = this.gp.dialogueState;
					this.gp.boy.attackCancel = true;
					entity.Speak();
					this.gp.playSE(19);
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				if (this.gp.khl.enterPressed == true) {
					this.gp.gameState = this.gp.dialogueState;
					this.gp.girl.attackCancel = true;
					entity.Speak();
					this.gp.playSE(19);
				}
			}
		}
	}

	public void SkeletonLord() {
		if (this.gp.bossBattleOn == false && Progress.skeletonLordDefeated == false) {
			this.gp.gameState = this.gp.cutsceneState;
			this.gp.csManager.sceneNum = this.gp.csManager.skeletonLord;
		}
	}
}
