package tileinteractive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class ITDrytree extends InteractiveTile {
	public GamePanel gp;

	public ITDrytree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.worldX = this.gp.tileSize * col;
		this.worldY = this.gp.tileSize * row;
		this.life = 2;
		this.down1 = this.Setup("/tile/tiledestructible/drytree", this.gp.tileSize, this.gp.tileSize);
		this.destructible = true;
	}

	public boolean isCorrectItem(Entity ent) {
		boolean iscorrect = false;
		if (ent.currentWeapon.type == this.type_normal_axe) {
			iscorrect = true;
		}
		return iscorrect;
	}

	public void playSe() {
		this.gp.playSE(6);
	}

	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new ITTrunk(this.gp, this.worldX / this.gp.tileSize, this.worldY / this.gp.tileSize);
		return tile;
	}

	public Color getParticleColor() {
		Color cl = new Color(65, 50, 30);
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
