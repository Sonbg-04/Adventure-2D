package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Particle extends Entity {
	public Entity generator;
	public Color cl;
	public int xd, yd;
	public int size;

	public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxlife, int xd, int yd) {
		super(gp);
		this.generator = generator;
		this.cl = color;
		this.size = size;
		this.speed = speed;
		this.maxLife = maxlife;
		this.xd = xd;
		this.yd = yd;
		this.life = maxlife;
		int offset = (this.gp.tileSize / 2) - (size / 2);
		this.worldX = generator.worldX + offset;
		this.worldY = generator.worldY + offset;

	}

	@Override
	public void Update() {
		this.life--;
		if (this.life < (this.maxLife / 4)) {
			this.yd++;
		}
		this.worldX += this.xd * this.speed;
		this.worldY += this.yd * this.speed;
		if (this.life == 0) {
			this.alive = false;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				int screenX = this.worldX - this.gp.boy.worldX + this.gp.boy.screenX;
				int screenY = this.worldY - this.gp.boy.worldY + this.gp.boy.screenY;

				g2.setColor(cl);
				g2.fillRect(screenX, screenY, this.size, this.size);
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				int screenX = this.worldX - this.gp.girl.worldX + this.gp.girl.screenX;
				int screenY = this.worldY - this.gp.girl.worldY + this.gp.girl.screenY;

				g2.setColor(cl);
				g2.fillRect(screenX, screenY, this.size, this.size);
			}
		}
	}
}
