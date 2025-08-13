package objects;

import entity.Entity;
import main.GamePanel;

public class OBJNormalAxe extends Entity {

	public static final String objName = "Normal Axe";

	public GamePanel gp;

	public OBJNormalAxe(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_normal_axe;
		this.down1 = Setup("/item/tancong/normal/normal_axe", this.gp.tileSize,this. gp.tileSize);
		this.attackValue = 4;
		this.description = "[" + this.name + "]\nIt's a great axe. ";
		this.attackArea.width = 30;
		this.attackArea.height = 30;
		this.price = 20;
		this.knockBackPower = 10;
		this.stackable = true;

		this.motion1_duration = 15;
		this.motion2_duration = 20;
	}
}
