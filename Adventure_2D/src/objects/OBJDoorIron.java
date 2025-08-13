package objects;

import entity.Entity;
import main.GamePanel;

public class OBJDoorIron extends Entity {
	public static final String objName = "Iron Door";

	public GamePanel gp;

	public OBJDoorIron(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_obstacle;
		this.name = objName;
		this.down1 = this.Setup("/item/khac/door_iron", this.gp.tileSize, this.gp.tileSize);
		this.collision = true;
		this.solidArea.x = 0;
		this.solidArea.y = 16;
		this.solidArea.width = 48;
		this.solidArea.height = 32;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.setDialogue();

	}

	@Override
	public void Interact() {
		this.startDialogue(this, 0);
	}

	public void setDialogue() {
		this.dialogues[0][0] = "It's won budge.";

	}

}
