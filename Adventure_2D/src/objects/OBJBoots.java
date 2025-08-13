package objects;

import entity.Entity;
import main.GamePanel;

public class OBJBoots extends Entity {
	public static final String objName = "Boot";
	public GamePanel gp;

	public OBJBoots(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.down1 = this.Setup("/item/khac/boots", this.gp.tileSize, this.gp.tileSize);
		this.price = 25;
		this.stackable = true;
	}

}
