package objects;

import entity.Entity;
import main.GamePanel;

public class OBJLantern extends Entity {
	public static final String objName = "Lantern";

	public GamePanel gp;

	public OBJLantern(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_light;
		this.name = objName;
		this.down1 = this.Setup("/item/khac/lantern", this.gp.tileSize, this.gp.tileSize);
		this.description = "[Lantern]\nIlluminates your \nsurroundings.";
		this.price = 200;
		this.lightRadius = 350;
	}
}
