package objects;

import java.awt.Color;

import entity.Entity;
import entity.ProjectTile;
import main.GamePanel;

public class OBJRock extends ProjectTile {
	public static final String objName = "Rock";

	public GamePanel gp;

	public OBJRock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.speed = 8;
		this.maxLife = 80;
		this.life = this.maxLife;
		this.attack = 2;
		this.useCost = 1;
		this.alive = false;
		this.getImage();
	}

	public void getImage() {
		this.up1 = this.Setup("/monster/slime/tancong/rock", this.gp.tileSize, this.gp.tileSize);
		this.up2 = this.Setup("/monster/slime/tancong/rock", this.gp.tileSize, this.gp.tileSize);
		this.down1 = this.Setup("/monster/slime/tancong/rock", this.gp.tileSize, this.gp.tileSize);
		this.down2 = this.Setup("/monster/slime/tancong/rock", this.gp.tileSize, this.gp.tileSize);
		this.left1 = this.Setup("/monster/slime/tancong/rock", this.gp.tileSize, this.gp.tileSize);
		this.left2 = this.Setup("/monster/slime/tancong/rock", this.gp.tileSize, this.gp.tileSize);
		this.right1 = this.Setup("/monster/slime/tancong/rock",this.gp.tileSize, this.gp.tileSize);
		this.right2 = this.Setup("/monster/slime/tancong/rock", this.gp.tileSize, this.gp.tileSize);
	}

	@Override
	public boolean haveResources(Entity user) {
		boolean haveResource = false;
		if (user.ammo >= this.useCost) {
			haveResource = true;
		}
		return haveResource;
	}

	@Override
	public void subtractResources(Entity user) {
		user.ammo -= this.useCost;
	}

	public Color getParticleColor() {
		Color cl = new Color(40, 50, 0);
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
