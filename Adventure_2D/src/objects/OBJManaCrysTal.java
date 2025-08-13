package objects;

import entity.Entity;
import main.GamePanel;

public class OBJManaCrysTal extends Entity {
	public static final String objName = "Mana Crystal";

	public GamePanel gp;

	public OBJManaCrysTal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_pickupOnly;
		this.value = 3;
		this.down1 = this.Setup("/item/khac/manacrystal_full", this.gp.tileSize,this. gp.tileSize);
		this.img = this.down1;
		this.img2 = this.Setup("/item/khac/manacrystal_half", this.gp.tileSize, this.gp.tileSize);
		this.img3 = this.Setup("/item/khac/manacrystal_blank", this.gp.tileSize, this.gp.tileSize);
	}

	@Override
	public boolean use(Entity entity) {
		this.gp.ui.Addmessage("Mana: " + this.value);
		entity.mana += this.value;
		return true;
	}
}
