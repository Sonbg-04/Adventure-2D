package entity;

import main.GamePanel;

public class ProjectTile extends Entity {
	public Entity user;

	public ProjectTile(GamePanel gp) {
		super(gp);
	}

	public void Set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}

	@Override
	public void Update() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				if (this.user == this.gp.boy) {
					int monsterIndex = this.gp.ccheck.checkEntity(this, this.gp.mons);
					if (monsterIndex != 999) {
						this.gp.boy.DamageMonster(monsterIndex, this, this.attack * (this.gp.boy.level / 2),
								this.knockBackPower);
						this.generateParticle(this.user.projecttile, this.gp.mons[this.gp.currentMap][monsterIndex]);
						this.alive = false;
					}
				}
				if (this.user != this.gp.boy) {
					boolean contactPlayer = this.gp.ccheck.CheckPlayer(this);
					if (this.gp.boy.invincible == false && contactPlayer == true) {
						this.DamagePlayer(this.attack);
						this.generateParticle(this.user.projecttile, this.user.projecttile);
						this.alive = false;
						this.gp.playSE(17);
					}
				}
				switch (this.direction) {
					case "up":
						this.worldY -= this.speed;
						break;
					case "down":
						this.worldY += this.speed;
						break;
					case "left":
						this.worldX -= this.speed;
						break;
					case "right":
						this.worldX += this.speed;
						break;
				}
				this.life--;
				if (this.life <= 0) {
					this.alive = false;
				}
				this.spriteCounter++;
				if (this.spriteCounter > 12) {
					if (this.spriteNum == 1) {
						this.spriteNum = 2;
					}
					if (this.spriteNum == 2) {
						this.spriteNum = 1;
					}
					this.spriteCounter = 0;
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				if (this.user == this.gp.girl) {
					int monsterIndex = this.gp.ccheck.checkEntity(this, this.gp.mons);
					if (monsterIndex != 999) {
						this.gp.girl.DamageMonster(monsterIndex, this, this.attack * (this.gp.girl.level / 2),
								this.knockBackPower);
						this.generateParticle(this.user.projecttile, this.gp.mons[this.gp.currentMap][monsterIndex]);
						this.alive = false;
					}
				}
				if (this.user != this.gp.girl) {
					boolean contactPlayer = this.gp.ccheck.CheckPlayer(this);
					if (this.gp.girl.invincible == false && contactPlayer == true) {
						this.DamagePlayer(this.attack);
						this.generateParticle(this.user.projecttile, this.user.projecttile);
						this.alive = false;
						this.gp.playSE(17);
					}
				}
				switch (this.direction) {
					case "up":
						this.worldY -= this.speed;
						break;
					case "down":
						this.worldY += this.speed;
						break;
					case "left":
						this.worldX -= this.speed;
						break;
					case "right":
						this.worldX += this.speed;
						break;

				}
				this.life--;
				if (this.life <= 0) {
					this.alive = false;
				}
				this.spriteCounter++;
				if (this.spriteCounter > 12) {
					if (this.spriteNum == 1) {
						this.spriteNum = 2;
					}
					if (this.spriteNum == 2) {
						this.spriteNum = 1;
					}
					this.spriteCounter = 0;
				}
			}
		}
	}

	public boolean haveResources(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}

	public void subtractResources(Entity user) {
	}
}
