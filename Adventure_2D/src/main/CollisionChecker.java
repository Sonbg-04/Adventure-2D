package main;

import entity.Entity;

public class CollisionChecker {
	public GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void Checktile(Entity ent) {
		int leftEntityworldX = ent.worldX + ent.solidArea.x;
		int rightEntityworldX = ent.worldX + ent.solidArea.x + ent.solidArea.width;
		int topWorldY = ent.worldY + ent.solidArea.y;
		int bottomWorldY = ent.worldY + ent.solidArea.y + ent.solidArea.height;
		int leftCol = leftEntityworldX / this.gp.tileSize;
		int rightCol = rightEntityworldX / this.gp.tileSize;
		int topRow = topWorldY / this.gp.tileSize;
		int bottomRow = bottomWorldY / this.gp.tileSize;
		int tileNum1, tileNum2;
		String direction = ent.direction;
		if (ent.knockBack == true) {
			direction = ent.knockbackDirection;
		}
		switch (direction) {
		case "up":
			topRow = (topWorldY - ent.speed) / this.gp.tileSize;
			tileNum1 = this.gp.tmg.mapTileNum[this.gp.currentMap][leftCol][topRow];
			tileNum2 = this.gp.tmg.mapTileNum[this.gp.currentMap][rightCol][topRow];
			if (this.gp.tmg.tile[tileNum1].collision == true || this.gp.tmg.tile[tileNum2].collision == true) {
				ent.collisionOn = true;
			}
			break;
		case "down":
			bottomRow = (bottomWorldY + ent.speed) / this.gp.tileSize;
			tileNum1 = this.gp.tmg.mapTileNum[this.gp.currentMap][leftCol][bottomRow];
			tileNum2 = this.gp.tmg.mapTileNum[this.gp.currentMap][rightCol][bottomRow];
			if (this.gp.tmg.tile[tileNum1].collision == true || this.gp.tmg.tile[tileNum2].collision == true) {
				ent.collisionOn = true;
			}
			break;
		case "left":
			leftCol = (leftEntityworldX - ent.speed) / this.gp.tileSize;
			tileNum1 = this.gp.tmg.mapTileNum[this.gp.currentMap][leftCol][topRow];
			tileNum2 = this.gp.tmg.mapTileNum[this.gp.currentMap][leftCol][bottomRow];
			if (this.gp.tmg.tile[tileNum1].collision == true || this.gp.tmg.tile[tileNum2].collision == true) {
				ent.collisionOn = true;
			}
			break;
		case "right":
			rightCol = (rightEntityworldX + ent.speed) / this.gp.tileSize;
			tileNum1 = this.gp.tmg.mapTileNum[this.gp.currentMap][rightCol][topRow];
			tileNum2 = this.gp.tmg.mapTileNum[this.gp.currentMap][rightCol][bottomRow];
			if (this.gp.tmg.tile[tileNum1].collision == true || this.gp.tmg.tile[tileNum2].collision == true) {
				ent.collisionOn = true;
			}
			break;
		}
	}

	public int CheckObject(Entity ent, boolean player) {
		int index = 999;

		String direction = ent.direction;
		if (ent.knockBack == true) {
			direction = ent.knockbackDirection;
		}
		for (int i = 0; i < this.gp.obj[1].length; i++) {
			if (this.gp.obj[this.gp.currentMap][i] != null) {

				// GET ENTITY'S SOLID AREA POSITION
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;

				// GET THE OBJECT'S SOLID AREA POSITION
				this.gp.obj[this.gp.currentMap][i].solidArea.x += this.gp.obj[this.gp.currentMap][i].worldX;
				this.gp.obj[this.gp.currentMap][i].solidArea.y += this.gp.obj[this.gp.currentMap][i].worldY;
				switch (direction) {
				case "up":
					ent.solidArea.y -= ent.speed;
					break;
				case "down":
					ent.solidArea.y += ent.speed;
					break;
				case "left":
					ent.solidArea.x -= ent.speed;
					break;
				case "right":
					ent.solidArea.x += ent.speed;
					break;
				}
				if (ent.solidArea.intersects(this.gp.obj[this.gp.currentMap][i].solidArea)) {
					if (this.gp.obj[this.gp.currentMap][i].collision == true) {
						ent.collisionOn = true;
					}
					if (player == true) {
						index = i;
					}
				}
				ent.solidArea.x = ent.solidAreaDefaultX;
				ent.solidArea.y = ent.solidAreaDefaultY;
				this.gp.obj[this.gp.currentMap][i].solidArea.x = this.gp.obj[this.gp.currentMap][i].solidAreaDefaultX;
				this.gp.obj[this.gp.currentMap][i].solidArea.y = this.gp.obj[this.gp.currentMap][i].solidAreaDefaultY;
			}
		}
		return index;
	}

	// NPC ON MONSTER
	public int checkEntity(Entity ent, Entity[][] target) {
		int index = 999;
		String direction = ent.direction;
		if (ent.knockBack == true) {
			direction = ent.knockbackDirection;
		}
		for (int i = 0; i < target[1].length; i++) {
			if (target[this.gp.currentMap][i] != null) {

				// GET ENTITY'S SOLID AREA POSITION
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;

				// GET THE OBJECT'S SOLID AREA POSITION
				target[this.gp.currentMap][i].solidArea.x += target[this.gp.currentMap][i].worldX;
				target[this.gp.currentMap][i].solidArea.y += target[this.gp.currentMap][i].worldY;
				switch (direction) {
				case "up":
					ent.solidArea.y -= ent.speed;
					break;
				case "down":
					ent.solidArea.y += ent.speed;
					break;
				case "left":
					ent.solidArea.x -= ent.speed;
					break;
				case "right":
					ent.solidArea.x += ent.speed;
					break;
				}
				if (ent.solidArea.intersects(target[this.gp.currentMap][i].solidArea)) {
					if (target[this.gp.currentMap][i] != ent) {
						ent.collisionOn = true;
						index = i;
					}
				}
				ent.solidArea.x = ent.solidAreaDefaultX;
				ent.solidArea.y = ent.solidAreaDefaultY;
				target[this.gp.currentMap][i].solidArea.x = target[this.gp.currentMap][i].solidAreaDefaultX;
				target[this.gp.currentMap][i].solidArea.y = target[this.gp.currentMap][i].solidAreaDefaultY;
			}
		}
		return index;
	}

	public boolean CheckPlayer(Entity ent) {
		boolean contactPlayer = false;
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				// GET ENTITY'S SOLID AREA POSITION
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;

				// GET THE OBJECT'S SOLID AREA POSITION
				this.gp.boy.solidArea.x += this.gp.boy.worldX;
				this.gp.boy.solidArea.y += this.gp.boy.worldY;
				switch (ent.direction) {
				case "up":
					ent.solidArea.y -= ent.speed;
					break;
				case "down":
					ent.solidArea.y += ent.speed;
					break;
				case "left":
					ent.solidArea.x -= ent.speed;
					break;
				case "right":
					ent.solidArea.x += ent.speed;
					break;
				}
				if (ent.solidArea.intersects(this.gp.boy.solidArea)) {
					ent.collisionOn = true;
					contactPlayer = true;
				}
				ent.solidArea.x = ent.solidAreaDefaultX;
				ent.solidArea.y = ent.solidAreaDefaultY;
				this.gp.boy.solidArea.x = this.gp.boy.solidAreaDefaultX;
				this.gp.boy.solidArea.y = this.gp.boy.solidAreaDefaultY;
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				// GET ENTITY'S SOLID AREA POSITION
				ent.solidArea.x += ent.worldX;
				ent.solidArea.y += ent.worldY;

				// GET THE OBJECT'S SOLID AREA POSITION
				this.gp.girl.solidArea.x += this.gp.girl.worldX;
				this.gp.girl.solidArea.y += this.gp.girl.worldY;
				switch (ent.direction) {
				case "up":
					ent.solidArea.y -= ent.speed;
					break;
				case "down":
					ent.solidArea.y += ent.speed;
					break;
				case "left":
					ent.solidArea.x -= ent.speed;
					break;
				case "right":
					ent.solidArea.x += ent.speed;
					break;
				}
				if (ent.solidArea.intersects(this.gp.girl.solidArea)) {
					ent.collisionOn = true;
					contactPlayer = true;
				}
				ent.solidArea.x = ent.solidAreaDefaultX;
				ent.solidArea.y = ent.solidAreaDefaultY;
				this.gp.girl.solidArea.x = this.gp.girl.solidAreaDefaultX;
				this.gp.girl.solidArea.y = this.gp.girl.solidAreaDefaultY;
			}
		}
		return contactPlayer;
	}
}
