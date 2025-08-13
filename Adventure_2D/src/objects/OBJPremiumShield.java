package objects;

import entity.Entity;
import main.GamePanel;

public class OBJPremiumShield extends Entity {
	public static final String objName = "Premium Shield";

	public GamePanel gp;

	public OBJPremiumShield(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_premium_shield;
		this.name = objName;
		this.down1 = this.Setup("/item/phongthu/premium_guard", this.gp.tileSize, this.gp.tileSize);
		this.defValue = 999;
		this.description = "[" + this.name + "]\nMake by Son. ";
		this.price = 2500;
		this.stackable = true;
	}
}
