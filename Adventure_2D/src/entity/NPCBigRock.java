package entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.GamePanel;
import objects.OBJDoorIron;
import tileinteractive.ITMetaPlate;
import tileinteractive.InteractiveTile;

public class NPCBigRock extends Entity {
	public static final String NPCName = "Big Rock";

	public NPCBigRock(GamePanel gp) {
		super(gp);
		this.direction = "down";
		this.speed = 4;
		this.name = NPCName;

		this.solidArea = new Rectangle();
		this.solidArea.x = 2;
		this.solidArea.y = 6;
		this.solidAreaDefaultX = solidArea.x;
		this.solidAreaDefaultY = solidArea.y;
		this.solidArea.width = 40;
		this.solidArea.height = 40;
		this.dialogueSet = -1;

		this.getNPCImage();
		this.setDialogue();
	}

	public void getNPCImage() {
		this.up1 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
		this.up2 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
		this.down1 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
		this.down2 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
		this.left1 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
		this.left2 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
		this.right1 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
		this.right2 = Setup("/npc/bigrock", this.gp.tileSize, this.gp.tileSize);
	}

	public void setDialogue() {
		this.dialogues[0][0] = "It's a giant rock.";

	}

	public void setAction() {
	}

	public void Update() {

	}

	public void Speak() {

		// DO THIS CHARACTER SPECIFIC STUFF
		this.facePlayer();
		this.startDialogue(this, dialogueSet);
		this.dialogueSet++;
		if (this.dialogues[this.dialogueSet][0] == null) {
			this.dialogueSet--;
		}
	}

	public void move(String s) {
		this.direction = s;
		this.checkCollision();
		if (this.collisionOn == false) {
			switch (this.direction) {
			case "up": {
				this.worldY -= this.speed;
				break;
			}
			case "down": {
				this.worldY += this.speed;
				break;
			}
			case "left": {
				this.worldX -= this.speed;
				break;
			}
			case "right": {
				this.worldX += this.speed;
				break;
			}
			}
		}
		this.DetectPlate();
	}

	public void DetectPlate() {
		ArrayList<InteractiveTile> plateList = new ArrayList<>();
		ArrayList<Entity> rockList = new ArrayList<>();
		for (int i = 0; i < this.gp.iTile[1].length; i++) {
			if (this.gp.iTile[this.gp.currentMap][i] != null && this.gp.iTile[this.gp.currentMap][i].name != null
					&& this.gp.iTile[this.gp.currentMap][i].name.equals(ITMetaPlate.ITName)) {
				plateList.add(this.gp.iTile[this.gp.currentMap][i]);
			}
		}
		for (int j = 0; j < this.gp.npc[1].length; j++) {
			if (this.gp.npc[this.gp.currentMap][j] != null && this.gp.npc[this.gp.currentMap][j].name != null
					&& this.gp.npc[this.gp.currentMap][j].name.equals(NPCBigRock.NPCName)) {
				rockList.add(this.gp.npc[this.gp.currentMap][j]);
			}
		}

		int count = 0;

		for (int i = 0; i < plateList.size(); i++) {
			int xDistance = Math.abs(worldX - plateList.get(i).worldX);
			int yDistance = Math.abs(worldY - plateList.get(i).worldY);
			int distance = Math.max(xDistance, yDistance);

			if (distance < 8) {
				if (this.linkedEntity == null) {
					this.linkedEntity = plateList.get(i);
					this.gp.playSE(21);
				}
			} else {
				if (this.linkedEntity == plateList.get(i)) {
					this.linkedEntity = null;
				}
			}

		}
		for (int i = 0; i < rockList.size(); i++) {
			if (rockList.get(i).linkedEntity != null) {
				count++;
			}
		}
		if (count == rockList.size()) {
			for (int i = 0; i < this.gp.obj[1].length; i++) {
				if (this.gp.obj[this.gp.currentMap][i] != null && this.gp.obj[this.gp.currentMap][i].name != null
						&& this.gp.obj[this.gp.currentMap][i].name.equals(OBJDoorIron.objName)) {
					this.gp.obj[this.gp.currentMap][i] = null;
					this.gp.playSE(7);
				}
			}
		}
	}
}
