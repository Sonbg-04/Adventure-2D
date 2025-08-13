package objects;

import entity.Entity;
import main.GamePanel;

public class OBJGoldenShield extends Entity {
	public static final String objName = "Golden Shield";

	public GamePanel gp;

	public OBJGoldenShield(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_golden_shield;
		this.name = objName;
		this.down1 = this.Setup("/item/phongthu/golden_guard",this. gp.tileSize, this.gp.tileSize);
		this.defValue = 1;
		this.description = "[" + this.name + "]\nMake by wood. ";
		this.price = 15;
		this.stackable = true;
	}
}
