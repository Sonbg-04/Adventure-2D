package objects;

import entity.Entity;
import main.GamePanel;

public class OBJNormalShield extends Entity {
	public static final String objName = "Normal Shield";

	public GamePanel gp;

	public OBJNormalShield(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_normal_shield;
		this.name = objName;
		this.down1 = this.Setup("/item/phongthu/normal_guard", this.gp.tileSize, this.gp.tileSize);
		this.defValue = 1;
		this.description = "[" + this.name + "]\nMake by wood. ";
		this.price = 15;
		this.stackable = true;
	}
}
