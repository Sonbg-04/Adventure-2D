package entity;

import main.GamePanel;

public class GirlDummy extends Entity {
	public static final String NPCName = "Girl Dummy";

	public GirlDummy(GamePanel gp) {
		super(gp);
		this.name = NPCName;
		this.getImage();
	}

	public void getImage() {
		this.up1 = Setup("/player/dichuyen/girl/girl_up_1", gp.tileSize, gp.tileSize);
		this.up2 = Setup("/player/dichuyen/girl/girl_up_2", gp.tileSize, gp.tileSize);
		this.down1 = Setup("/player/dichuyen/girl/girl_down_1", gp.tileSize, gp.tileSize);
		this.down2 = Setup("/player/dichuyen/girl/girl_down_2", gp.tileSize, gp.tileSize);
		this.left1 = Setup("/player/dichuyen/girl/girl_left_1", gp.tileSize, gp.tileSize);
		this.left2 = Setup("/player/dichuyen/girl/girl_left_2", gp.tileSize, gp.tileSize);
		this.right1 = Setup("/player/dichuyen/girl/girl_right_1", gp.tileSize, gp.tileSize);
		this.right2 = Setup("/player/dichuyen/girl/girl_right_2", gp.tileSize, gp.tileSize);
	}
}
