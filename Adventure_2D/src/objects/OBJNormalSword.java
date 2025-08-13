package objects;

import entity.Entity;
import main.GamePanel;

public class OBJNormalSword extends Entity {
	public static final String objName = "Normal Sword";

	public GamePanel gp;

	public OBJNormalSword(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_normal_sword;
		this.down1 = Setup("/item/tancong/normal/normal_sword", this.gp.tileSize,this.gp.tileSize);
		this.attackValue = 3;
		this.description = "[" + this.name + "]\nAttack: " + this.attackValue + "\nAn old sword. ";
		this.attackArea.width = 36;
		this.attackArea.height = 36;
		this.price = 15;
		this.knockBackPower = 5;
		this.motion1_duration = 5;
		this.motion2_duration = 25;
		this.stackable = true;
	}
}
