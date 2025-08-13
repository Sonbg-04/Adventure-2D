package tileinteractive;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {
	public GamePanel gp;
	public boolean destructible = false;

	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;

	}

	public boolean isCorrectItem(Entity ent) {
		boolean iscorrect = false;
		return iscorrect;
	}

	public void playSe() {
	}

	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}

	@Override
	public void Update() {
		if (this.invincible == true) {
			this.invincibleCount++;
			if (this.invincibleCount > 20) {
				this.invincibleCount = 0;
				this.invincible = false;
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				int screenX = this.worldX - this.gp.boy.worldX + this.gp.boy.screenX;
				int screenY = this.worldY - this.gp.boy.worldY + this.gp.boy.screenY;

				if (this.worldX + this.gp.tileSize > this.gp.boy.worldX - this.gp.boy.screenX
						&& this.worldX - this.gp.tileSize < this.gp.boy.worldX + this.gp.boy.screenX
						&& this.worldY + this.gp.tileSize > this.gp.boy.worldY - this.gp.boy.screenY
						&& this.worldY - this.gp.tileSize < this.gp.boy.worldY + this.gp.boy.screenY) {
					g2.drawImage(this.down1, screenX, screenY, null);
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				int screenX = this.worldX - this.gp.girl.worldX + this.gp.girl.screenX;
				int screenY = this.worldY - this.gp.girl.worldY + this.gp.girl.screenY;

				if (this.worldX + this.gp.tileSize > this.gp.girl.worldX - this.gp.girl.screenX
						&& this.worldX - this.gp.tileSize < this.gp.girl.worldX + this.gp.girl.screenX
						&& this.worldY + this.gp.tileSize > this.gp.girl.worldY - this.gp.girl.screenY
						&& this.worldY - this.gp.tileSize < this.gp.girl.worldY + this.gp.girl.screenY) {
					g2.drawImage(this.down1, screenX, screenY, null);
				}
			}
		}
	}
}
