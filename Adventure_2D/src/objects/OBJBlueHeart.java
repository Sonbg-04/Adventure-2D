package objects;

import entity.Entity;
import main.GamePanel;

public class OBJBlueHeart extends Entity {
	public GamePanel gp;
	public static final String OBJName = "Blue Heart";

	public OBJBlueHeart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = OBJName;
		this.type = this.type_pickupOnly;
		this.down1 = this.Setup("/item/khac/blueheart", this.gp.tileSize, this.gp.tileSize);
		this.setDialogue();

	}

	public void setDialogue() {
		this.dialogues[0][0] = "You pick up a beautiful blue gem.";
		this.dialogues[0][1] = "You find the Blue Heart, the legendary treasure.";
	}

	public void Interact() {
		this.startDialogue(this, 0);
	}

	@Override
	public boolean use(Entity entity) {
		this.gp.gameState = this.gp.cutsceneState;
		this.gp.csManager.sceneNum = this.gp.csManager.ending;

		return true;
	}
}
