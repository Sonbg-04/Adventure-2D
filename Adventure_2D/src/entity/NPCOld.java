package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPCOld extends Entity {

	public NPCOld(GamePanel gp) {
		super(gp);
		this.direction = "down";
		this.speed = 1;

		this.solidArea = new Rectangle();
		this.solidArea.x = 8;
		this.solidArea.y = 16;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.width = 30;
		this.solidArea.height = 30;
		this.dialogueSet = -1;

		this.getNPCImage();
		this.setDialogue();
	}

	public void getNPCImage() {
		this.up1 = Setup("/npc/old/old_up_1", this.gp.tileSize, this.gp.tileSize);
		this.up2 = Setup("/npc/old/old_up_2", this.gp.tileSize, this.gp.tileSize);
		this.down1 = Setup("/npc/old/old_down_1", this.gp.tileSize, this.gp.tileSize);
		this.down2 = Setup("/npc/old/old_down_2", this.gp.tileSize, this.gp.tileSize);
		this.left1 = Setup("/npc/old/old_left_1", this.gp.tileSize, this.gp.tileSize);
		this.left2 = Setup("/npc/old/old_left_2", this.gp.tileSize, this.gp.tileSize);
		this.right1 = Setup("/npc/old/old_right_1", this.gp.tileSize, this.gp.tileSize);
		this.right2 = Setup("/npc/old/old_right_2", this.gp.tileSize, this.gp.tileSize);
	}

	public void setDialogue() {
		this.dialogues[0][0] = "Hello, Son!";
		this.dialogues[0][1] = "So you're come to this island to the treasure? ";
		this.dialogues[0][2] = "I used to be a great wizard but now... I'm a bit too \nold for taking an adventure. ";
		this.dialogues[0][3] = "Well, good luck on you! ";

		this.dialogues[1][0] = "If you become tired, rest at the water.";
		this.dialogues[1][1] = "However, the monsters resappear if you rest.\nI don't know why but that's how it works.";
		this.dialogues[1][2] = "In any case, don't push yourself too hard.";

		this.dialogues[2][0] = "I wonder how to open that door....";

	}

	public void setAction() {
		if (this.onPath == true) {
//			int goalCol = 12;
//			int goalRow = 9;
////			int goalCol = (this.gp.player.worldX + this.gp.player.this.solidArea.x)/this.gp.tileSize;
////			int goalRow = (this.gp.player.worldY + this.gp.player.this.solidArea.y)/this.gp.tileSize;
////			
//			
//			searchPath(goalCol, goalRow);
		} else {
			this.actionlockcounter++;
			if (this.actionlockcounter == 120) {
				Random rd = new Random();
				int i = rd.nextInt(100) + 1; // PICK UP A NUMBER FROM 1 TO 100

				if (i <= 25) {
					this.direction = "up";
				} else if (i > 25 && i <= 50) {
					this.direction = "down";
				} else if (i > 50 && i <= 75) {
					this.direction = "left";
				} else {
					this.direction = "right";
				}
				this.actionlockcounter = 0;
			}
		}

	}

	public void Speak() {

		// DO THIS CHARACTER SPECIFIC STUFF
		this.facePlayer();
		this.startDialogue(this, dialogueSet);
		this.dialogueSet++;
		if (this.dialogues[this.dialogueSet][0] == null) {
			this.dialogueSet--;
		}
		this.onPath = true;
	}
}
