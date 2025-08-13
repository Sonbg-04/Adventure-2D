package objects;

import entity.Entity;
import main.GamePanel;

public class OBJGoldenAxe extends Entity {

	public static final String objName = "Golden Axe";

	public GamePanel gp;

	public OBJGoldenAxe(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_golden_axe;
		this.down1 = this.Setup("/item/tancong/golden/golden_axe", this.gp.tileSize, this.gp.tileSize);
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
