package objects;

import entity.Entity;
import main.GamePanel;

public class OBJDiamondShield extends Entity {
	public static final String objName = "Diamond Shield";

	public GamePanel gp;

	public OBJDiamondShield(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_diamond_shield;
		this.name = objName;
		this.down1 = this.Setup("/item/phongthu/diamond_guard", this.gp.tileSize, this.gp.tileSize);
		this.defValue = 2;
		this.description = "[" + this.name + "]\nMake by blue. ";
		this.price = 125;
		this.stackable = true;
	}
}
