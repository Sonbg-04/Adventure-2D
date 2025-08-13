package main;

import data.Progress;
import entity.NPCBigRock;
import entity.NPCMerchant;

import entity.NPCOld;
import monster.Bat;
import monster.BlueSlime;
import monster.GreenSlime;
import monster.Orc;
import monster.RedSlime;
import monster.SkeletonLord;
import monster.YellowSlime;
import objects.OBJNormalAxe;
import objects.OBJBlueHeart;
import objects.OBJChest;
import objects.OBJDoor;
import objects.OBJDoorIron;
import objects.OBJKey;
import objects.OBJLantern;
import objects.OBJPickAxe;
import objects.OBJPotionBlue;
import objects.OBJPotionRed;
import objects.OBJTent;
import tileinteractive.ITDestructibleWall;
import tileinteractive.ITMetaPlate;
import tileinteractive.ITDrytree;

public class AssetSetter {
	public GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		int mapNum = 0;
		int i = 0;
		
		this.gp.obj[mapNum][i] = new OBJNormalAxe(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 35;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 17;
		i++;

		this.gp.obj[mapNum][i] = new OBJLantern(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 31;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 21;
		i++;

		this.gp.obj[mapNum][i] = new OBJTent(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 33;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 7;
		i++;

		this.gp.obj[mapNum][i] = new OBJDoor(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 12;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 12;
		i++;

		this.gp.obj[mapNum][i] = new OBJDoor(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 14;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 28;
		i++;

		this.gp.obj[mapNum][i] = new OBJKey(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 26;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 7;
		i++;
		
		this.gp.obj[mapNum][i] = new OBJKey(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 27;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 16;
		i++;

		this.gp.obj[mapNum][i] = new OBJPotionRed(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 28;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 31;
		i++;

		this.gp.obj[mapNum][i] = new OBJPotionBlue(this.gp);
		this.gp.obj[mapNum][i].worldX = this.gp.tileSize * 27;
		this.gp.obj[mapNum][i].worldY = this.gp.tileSize * 27;
		i++;

		mapNum = 2;
		int j = 0;
		this.gp.obj[mapNum][j] = new OBJChest(this.gp);
		this.gp.obj[mapNum][j].setLoot(new OBJPickAxe(this.gp));
		this.gp.obj[mapNum][j].worldX = this.gp.tileSize * 40;
		this.gp.obj[mapNum][j].worldY = this.gp.tileSize * 41;
		j++;

		this.gp.obj[mapNum][j] = new OBJChest(this.gp);
		this.gp.obj[mapNum][j].setLoot(new OBJPotionRed(this.gp));
		this.gp.obj[mapNum][j].worldX = this.gp.tileSize * 13;
		this.gp.obj[mapNum][j].worldY = this.gp.tileSize * 16;
		j++;

		this.gp.obj[mapNum][j] = new OBJChest(this.gp);
		this.gp.obj[mapNum][j].setLoot(new OBJPotionRed(this.gp));
		this.gp.obj[mapNum][j].worldX = this.gp.tileSize * 26;
		this.gp.obj[mapNum][j].worldY = this.gp.tileSize * 34;
		j++;

		this.gp.obj[mapNum][j] = new OBJChest(this.gp);
		this.gp.obj[mapNum][j].setLoot(new OBJPotionRed(this.gp));
		this.gp.obj[mapNum][j].worldX = this.gp.tileSize * 27;
		this.gp.obj[mapNum][j].worldY = this.gp.tileSize * 15;
		j++;

		this.gp.obj[mapNum][j] = new OBJDoorIron(this.gp);
		this.gp.obj[mapNum][j].worldX = this.gp.tileSize * 18;
		this.gp.obj[mapNum][j].worldY = this.gp.tileSize * 23;
		j++;

		mapNum = 3;
		j = 0;

		this.gp.obj[mapNum][j] = new OBJDoorIron(this.gp);
		this.gp.obj[mapNum][j].worldX = this.gp.tileSize * 25;
		this.gp.obj[mapNum][j].worldY = this.gp.tileSize * 15;
		j++;

		this.gp.obj[mapNum][j] = new OBJBlueHeart(this.gp);
		this.gp.obj[mapNum][j].worldX = this.gp.tileSize * 25;
		this.gp.obj[mapNum][j].worldY = this.gp.tileSize * 8;
		j++;

	}

	public void setNPC() {
		int mapNum = 0;
		int i = 0;
		this.gp.npc[mapNum][i] = new NPCOld(this.gp);
		this.gp.npc[mapNum][i].worldX = this.gp.tileSize * 21;
		this.gp.npc[mapNum][i].worldY = this.gp.tileSize * 21;
		i++;

		mapNum = 1;
		int j = 0;
		this.gp.npc[mapNum][j] = new NPCMerchant(this.gp);
		this.gp.npc[mapNum][j].worldX = this.gp.tileSize * 12;
		this.gp.npc[mapNum][j].worldY = this.gp.tileSize * 7;
		j++;

		mapNum = 2;
		j = 0;
		this.gp.npc[mapNum][j] = new NPCBigRock(this.gp);
		this.gp.npc[mapNum][j].worldX = this.gp.tileSize * 20;
		this.gp.npc[mapNum][j].worldY = this.gp.tileSize * 25;
		j++;

		this.gp.npc[mapNum][j] = new NPCBigRock(this.gp);
		this.gp.npc[mapNum][j].worldX = this.gp.tileSize * 11;
		this.gp.npc[mapNum][j].worldY = this.gp.tileSize * 18;
		j++;

		this.gp.npc[mapNum][j] = new NPCBigRock(this.gp);
		this.gp.npc[mapNum][j].worldX = this.gp.tileSize * 23;
		this.gp.npc[mapNum][j].worldY = this.gp.tileSize * 14;
		j++;
	}

	public void setMonster() {
		int mapNum = 0;
		int i = 0;
		this.gp.mons[mapNum][i] = new GreenSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 21;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 38;
		i++;

		this.gp.mons[mapNum][i] = new GreenSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 23;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 42;
		i++;

		this.gp.mons[mapNum][i] = new GreenSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 24;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 37;
		i++;

		this.gp.mons[mapNum][i] = new BlueSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 34;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 42;
		i++;

		this.gp.mons[mapNum][i] = new BlueSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 37;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 39;
		i++;

		this.gp.mons[mapNum][i] = new BlueSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 38;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 42;
		i++;

		this.gp.mons[mapNum][i] = new RedSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 38;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 10;
		i++;

		this.gp.mons[mapNum][i] = new RedSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 40;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 10;
		i++;

		this.gp.mons[mapNum][i] = new RedSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 39;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 12;
		i++;

		this.gp.mons[mapNum][i] = new Orc(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 13;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 34;
		i++;

		this.gp.mons[mapNum][i] = new YellowSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 12;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 34;
		i++;

		this.gp.mons[mapNum][i] = new YellowSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 11;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 34;
		i++;

		this.gp.mons[mapNum][i] = new YellowSlime(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 15;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 33;
		i++;

		mapNum = 2;
		i = 0;
		this.gp.mons[mapNum][i] = new Bat(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 34;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 39;
		i++;

		this.gp.mons[mapNum][i] = new Bat(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 36;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 25;
		i++;

		this.gp.mons[mapNum][i] = new Bat(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 39;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 26;
		i++;

		this.gp.mons[mapNum][i] = new Bat(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 28;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 11;
		i++;

		this.gp.mons[mapNum][i] = new Bat(this.gp);
		this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 10;
		this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 19;
		i++;

		mapNum = 3;
		i = 0;
		if (Progress.skeletonLordDefeated == false) {
			this.gp.mons[mapNum][i] = new SkeletonLord(this.gp);
			this.gp.mons[mapNum][i].worldX = this.gp.tileSize * 23;
			this.gp.mons[mapNum][i].worldY = this.gp.tileSize * 16;
			i++;

		}

	}

	public void setInteractiveTile() {
		int mapNum = 0;
		int i = 0;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 18, 40);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 17, 40);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 16, 40);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 15, 40);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 14, 40);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 13, 40);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 13, 41);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 12, 41);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 11, 41);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 10, 41);
		i++;
		this.gp.iTile[mapNum][i] = new ITDrytree(this.gp, 10, 40);
		i++;

		mapNum = 2;
		i = 0;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 18, 30);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 17, 31);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 17, 32);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 17, 34);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 18, 34);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 18, 33);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 10, 22);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 10, 24);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 38, 18);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 38, 19);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 38, 20);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 38, 21);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 18, 13);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 18, 14);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 22, 28);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 30, 28);
		i++;
		this.gp.iTile[mapNum][i] = new ITDestructibleWall(this.gp, 32, 28);
		i++;
		this.gp.iTile[mapNum][i] = new ITMetaPlate(this.gp, 20, 22);
		i++;
		this.gp.iTile[mapNum][i] = new ITMetaPlate(this.gp, 8, 17);
		i++;
		this.gp.iTile[mapNum][i] = new ITMetaPlate(this.gp, 39, 31);
		i++;
	}

}
