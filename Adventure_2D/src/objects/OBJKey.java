package objects;

import entity.Entity;
import main.GamePanel;

public class OBJKey extends Entity {
	public static final String objName = "Key";

	public GamePanel gp;

	public OBJKey(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_consumable;
		this.down1 = this.Setup("/item/khac/key", this.gp.tileSize, this.gp.tileSize);
		this.description = "[" + this.name + "]\nIt's open the door. ";
		this.price = 30;
		this.stackable = true;
		this.setDialogue();
	}

	@Override
	public boolean use(Entity e) {

		int ObjIndex = this.getDetected(e, this.gp.obj, "Door");
		if (ObjIndex != 999) {
			this.startDialogue(this, 0);
			this.gp.obj[this.gp.currentMap][ObjIndex] = null;
			this.gp.playSE(21);
			return true;
		} else {
			this.startDialogue(this, 1);
			return false;
		}
	}
	public void setDialogue() {
		this.dialogues[0][0] = "You use the " + this.name + " and open the door!";
		this.dialogues[1][0] = "What are you doing?";
	}

}
