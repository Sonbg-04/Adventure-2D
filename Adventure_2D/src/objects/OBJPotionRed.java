package objects;

import entity.Entity;
import main.GamePanel;

public class OBJPotionRed extends Entity {
	public static final String objName = "Potion Red";

	public GamePanel gp;

	public OBJPotionRed(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_consumable;
		this.down1 = this.Setup("/item/khac/potion_red", this.gp.tileSize, this.gp.tileSize);
		this.value = 5;
		this.description = "[Potion Red]\nHeals your life by " + this.value + ".";
		this.stackable = true;
		this.setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You drink the " + this.name + "!\n" + "Your life has been recovered by " + this.value
				+ ".";
	}

	@Override
	public boolean use(Entity entity) {
		this.startDialogue(this, 0);
		entity.life += this.value;
		return true;
	}
}
