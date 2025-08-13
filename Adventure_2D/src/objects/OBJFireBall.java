package objects;

import java.awt.Color;

import entity.Entity;
import entity.ProjectTile;
import main.GamePanel;

public class OBJFireBall extends ProjectTile {
	public static final String objName = "Fire Ball";

	public GamePanel gp;

	public OBJFireBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.speed = 10;
		this.maxLife = 80;
		this.attack = 2;
		this.knockBackPower = 15;
		this.useCost = 1;
		this.alive = false;
		this.getImage();
	}

	public void getImage() {
		this.up1 = Setup("/fireball/fireball_up_1", this.gp.tileSize, this.gp.tileSize);
		this.up2 = Setup("/fireball/fireball_up_2", this.gp.tileSize, this.gp.tileSize);
		this.down1 = Setup("/fireball/fireball_down_1", this.gp.tileSize, this.gp.tileSize);
		this.down2 = Setup("/fireball/fireball_down_2", this.gp.tileSize, this.gp.tileSize);
		this.left1 = Setup("/fireball/fireball_left_1", this.gp.tileSize, this.gp.tileSize);
		this.left2 = Setup("/fireball/fireball_left_2", this.gp.tileSize, this.gp.tileSize);
		this.right1 = Setup("/fireball/fireball_right_1", this.gp.tileSize, this.gp.tileSize);
		this.right2 = Setup("/fireball/fireball_right_2", this.gp.tileSize, this.gp.tileSize);
	}

	public boolean haveResources(Entity user) {
		boolean haveResource = false;
		if (user.mana >= this.useCost) {
			haveResource = true;
		}
		return haveResource;
	}

	public void subtractResources(Entity user) {
		user.mana -= this.useCost;
	}

	public Color getParticleColor() {
		Color cl = new Color(240, 50, 0);
		return cl;
	}

	public int getParticleSize() {
		int size = 10;
		return size;
	}

	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}

	public int getParticlemaxLife() {
		int maxlife = 20;
		return maxlife;
	}
}
