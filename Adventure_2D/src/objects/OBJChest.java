package objects;

import entity.Entity;

import main.GamePanel;

public class OBJChest extends Entity {
	public static final String objName = "Chest";
	public GamePanel gp;

	public OBJChest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_obstacle;
		this.img = this.Setup("/item/khac/chest", this.gp.tileSize, this.gp.tileSize);
		this.img2 = this.Setup("/item/khac/chest_opened", this.gp.tileSize, this.gp.tileSize);
		this.solidArea.x = 4;
		this.solidArea.y = 16;
		this.solidArea.width = 40;
		this.solidArea.height = 32;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.name = objName;
		this.down1 = this.img;
		this.collision = true;
		this.price = 100;
		this.stackable = true;

	}

	public void setDialogue() {
		this.dialogues[0][0] = "You open the chest and find a " + this.loot.name + "\n...But you can't carry any more!";
		this.dialogues[1][0] = "You open the chest and find a " + this.loot.name + "\nYou obtain the " + this.loot.name + "!";
		this.dialogues[2][0] = "It's empty!";

	}

	public void setLoot(Entity loot) {
		this.loot = loot;
		this.setDialogue();
	}

	@Override
	public void Interact() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				if (this.opened == false) {
					if (this.gp.boy.canObainItem(this.loot) == false) {
						this.startDialogue(this, 0);
					} else {
						this.startDialogue(this, 1);
						this.up1 = this.img2;
						this.opened = true;
					}
				} else {
					this.startDialogue(this, 2);
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				if (this.opened == false) {
					if (this.gp.girl.canObainItem(this.loot) == false) {
						this.startDialogue(this, 0);
					} else {
						this.startDialogue(this, 1);
						this.up1 = this.img2;
						this.opened = true;
					}
				} else {
					this.startDialogue(this, 2);
				}
			}
		}
	}
}
