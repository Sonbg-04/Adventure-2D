package objects;

import entity.Entity;
import main.GamePanel;

public class OBJGoldenSword extends Entity {
	public static final String objName = "Golden Sword";

	public GamePanel gp;

	public OBJGoldenSword(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_golden_sword;
		this.down1 = this.Setup("/item/tancong/golden/golden_sword", this.gp.tileSize, this.gp.tileSize);
		this.attackValue = 3;
		this.description = "[" +this. name + "]\nAttack: " + this.attackValue + "\nAn old sword. ";
		this.attackArea.width = 36;
		this.attackArea.height = 36;
		this.price = 15;
		this.knockBackPower = 5;
		this.motion1_duration = 5;
		this.motion2_duration = 25;
		this.stackable = true;
	}
}
