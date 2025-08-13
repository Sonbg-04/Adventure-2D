package objects;

import entity.Entity;
import main.GamePanel;

public class OBJPickAxe extends Entity {

	public static final String objName = "Pick Axe";

	public GamePanel gp;

	public OBJPickAxe(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_pickaxe;
		this.down1 = Setup("/item/tancong/pickaxe", this.gp.tileSize, this.gp.tileSize);
		this.attackValue = 4;
		this.description = "Use the dig wall!";
		this.attackArea.width = 30;
		this.attackArea.height = 30;
		this.price = 20;
		this.knockBackPower = 10;
		this.stackable = true;

		this.motion1_duration = 10;
		this.motion2_duration = 20;
	}
}
