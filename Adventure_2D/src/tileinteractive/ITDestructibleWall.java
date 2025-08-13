package tileinteractive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class ITDestructibleWall extends InteractiveTile {
	public GamePanel gp;

	public static final String ITName = "Destructible Wall";

	public ITDestructibleWall(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.name = ITName;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		this.life = 2;
		this.down1 = this.Setup("/tile/tiledestructible/destructiblewall", this.gp.tileSize, this.gp.tileSize);
		this.destructible = true;
	}

	public boolean isCorrectItem(Entity ent) {
		boolean iscorrect = false;
		if (ent.currentWeapon.type == this.type_pickaxe) {
			iscorrect = true;
		}
		return iscorrect;
	}

	public void playSe() {
		this.gp.playSE(3);
	}

	public Color getParticleColor() {
		Color cl = new Color(65, 65, 65);
		return cl;
	}

	public int getParticleSize() {
		int size = 6;
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
