package objects;

import entity.Entity;
import main.GamePanel;

public class OBJPotionBlue extends Entity {
	public static final String objName = "Potion Blue";

	public GamePanel gp;

	public OBJPotionBlue(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_consumable;
		this.down1 = this.Setup("/item/khac/potion_blue", this.gp.tileSize, this.gp.tileSize);
		this.value = 2;
		this.description = "[Potion Blue]\nHeals your mana by " + this.value + ".";
		this.stackable = true;
		this.setDialogue();
	}

	public void setDialogue() {
		this.dialogues[0][0] = "You drink the " + this.name + "!\n" + "Your mana has been recovered by " + this.value
				+ ".";
	}

	@Override
	public boolean use(Entity entity) {
		this.startDialogue(this, 0);
		entity.mana += this.value;
		return true;
	}
}
