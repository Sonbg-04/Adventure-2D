package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import objects.OBJCoin;
import objects.OBJHeart;
import objects.OBJManaCrysTal;

public class Orc extends Entity {
	public GamePanel gp;
	public static final String MonName = "Orc";

	public Orc(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = MonName;
		this.DefaultSpeed = 1;
		this.speed = this.DefaultSpeed;
		this.maxLife = 5;
		this.life = this.maxLife;
		this.type = this.type_monster;
		this.attack = 5;
		this.def = 0;
		this.exp = 10;
		this.knockBackPower = 5;
		this.motion1_duration = 10;
		this.motion2_duration = 11;
		this.solidArea.x = 3;
		this.solidArea.y = 18;
		this.solidArea.width = 42;
		this.solidArea.height = 30;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.attackArea.width = 48;
		this.attackArea.height = 48;

		this.getImage();
		this.getAttackImage();
	}

	public void getImage() {
		this.up1 = this.Setup("/monster/orc/dichuyen/orc_up_1", this.gp.tileSize, this.gp.tileSize);
		this.up2 = this.Setup("/monster/orc/dichuyen/orc_up_2", this.gp.tileSize, this.gp.tileSize);
		this.down1 = this.Setup("/monster/orc/dichuyen/orc_down_1", this.gp.tileSize, this.gp.tileSize);
		this.down2 = this.Setup("/monster/orc/dichuyen/orc_down_2", this.gp.tileSize, this.gp.tileSize);
		this.left1 = this.Setup("/monster/orc/dichuyen/orc_left_1", this.gp.tileSize, this.gp.tileSize);
		this.left2 = this.Setup("/monster/orc/dichuyen/orc_left_2", this.gp.tileSize, this.gp.tileSize);
		this.right1 = this.Setup("/monster/orc/dichuyen/orc_right_1", this.gp.tileSize, this.gp.tileSize);
		this.right2 = this.Setup("/monster/orc/dichuyen/orc_right_2", this.gp.tileSize, this.gp.tileSize);
	}

	public void getAttackImage() {
		this.attackUp1 = this.Setup("/monster/orc/tancong/orc_attack_up_1", this.gp.tileSize, this.gp.tileSize * 2);
		this.attackUp2 = this.Setup("/monster/orc/tancong/orc_attack_up_2", this.gp.tileSize, this.gp.tileSize * 2);
		this.attackDown1 = this.Setup("/monster/orc/tancong/orc_attack_down_1", this.gp.tileSize, this.gp.tileSize * 2);
		this.attackDown2 = this.Setup("/monster/orc/tancong/orc_attack_down_2", this.gp.tileSize, this.gp.tileSize * 2);
		this.attackLeft1 = this.Setup("/monster/orc/tancong/orc_attack_left_1", this.gp.tileSize * 2,this. gp.tileSize);
		this.attackleft2 = this.Setup("/monster/orc/tancong/orc_attack_left_2", this.gp.tileSize * 2,this. gp.tileSize);
		this.attackRight1 = this.Setup("/monster/orc/tancong/orc_attack_right_1", this.gp.tileSize * 2, this.gp.tileSize);
		this.attackRight2 = this.Setup("/monster/orc/tancong/orc_attack_right_2", this.gp.tileSize * 2, this.gp.tileSize);

	}

	@Override
	public void setAction() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				if (this.onPath == true) {

					this.checkStopChasingOrNot(this.gp.boy, 15, 100);

					this.searchPath(this.getGoalCol(this.gp.boy), this.getGoalRow(this.gp.boy));

				} else {
					this.CheckStartChasingOrNot(this.gp.boy, 5, 100);
					this.getRandomDirection(120);
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				if (this.onPath == true) {

					this.checkStopChasingOrNot(this.gp.girl, 15, 100);

					this.searchPath(this.getGoalCol(this.gp.girl), this.getGoalRow(this.gp.girl));

				} else {
					this.CheckStartChasingOrNot(this.gp.girl, 5, 100);
					this.getRandomDirection(120);
				}
			}
		}
	}

	@Override
	public void DamageReaction() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				this.actionlockcounter = 0;
				this.direction = this.gp.boy.direction;
				this.onPath = true;
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				this.actionlockcounter = 0;
				this.direction = this.gp.girl.direction;
				this.onPath = true;
			}
		}
	}

	@Override
	public void checkDrop() {
		// CAST A DIE
		int i = new Random().nextInt(100) + 1;

		// SET THE MONSTER DROP
		if (i < 50) {
			this.dropItem(new OBJCoin(this.gp));
		}
		if (i >= 50 && i < 75) {
			this.dropItem(new OBJHeart(this.gp));
		}
		if (i >= 75 && i < 100) {
			this.dropItem(new OBJManaCrysTal(this.gp));
		}
	}

}
