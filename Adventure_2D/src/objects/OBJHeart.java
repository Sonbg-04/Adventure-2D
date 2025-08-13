package objects;

import entity.Entity;
import main.GamePanel;

public class OBJHeart extends Entity {
	public static final String objName = "Heart";

	public GamePanel gp;

	public OBJHeart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_pickupOnly;
		this.value = 2;
		this.down1 = this.Setup("/item/khac/heart_full", this.gp.tileSize, this.gp.tileSize);
		this.name = objName;
		this.img = this.down1;
		this.img2 = this.Setup("/item/khac/heart_half", this.gp.tileSize, this.gp.tileSize);
		this.img3 = this.Setup("/item/khac/heart_blank", this.gp.tileSize,this. gp.tileSize);
	}

	@Override
	public boolean use(Entity entity) {
		this.gp.ui.Addmessage("Life: " + this.value);
		entity.life += this.value;
		return true;
	}
}
