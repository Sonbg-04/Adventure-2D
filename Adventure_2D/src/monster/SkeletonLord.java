package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import objects.OBJCoin;
import objects.OBJDoorIron;
import objects.OBJHeart;
import objects.OBJManaCrysTal;

public class SkeletonLord extends Entity {
	public GamePanel gp;
	public static final String MonName = "Skeleton Lord";

	public SkeletonLord(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = MonName;
		this.DefaultSpeed = 1;
		this.speed = this.DefaultSpeed;
		this.maxLife = 50;
		this.life = this.maxLife;
		this.type = this.type_monster;
		this.boss = true;
		this.attack = 10;
		this.def = 2;
		this.exp = 50;
		this.knockBackPower = 5;
		this.Sleep = true;

		int size = this.gp.tileSize * 5;

		this.motion1_duration = 25;
		this.motion2_duration = 50;
		this.solidArea.x = 48;
		this.solidArea.y = 48;
		this.solidArea.width = size - 48 * 2;
		this.solidArea.height = size - 48;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.attackArea.width = 170;
		this.attackArea.height = 170;

		this.getImage();
		this.getAttackImage();
		this.setDialogue();
	}

	public void getImage() {

		int i = 5;
		if (this.inRage == false) {
			this.up1 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_up_1", this.gp.tileSize * i,
					this.gp.tileSize * i);
			this.up2 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_up_2", this.gp.tileSize * i,
					this.gp.tileSize * i);
			this.down1 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_down_1",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.down2 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_down_2",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.left1 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_left_1",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.left2 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_left_2",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.right1 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_right_1",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.right2 = this.Setup("/monster/skeletonlord/dichuyen/ttbinhthuong/skeletonlord_right_2",
					this.gp.tileSize * i, this.gp.tileSize * i);
		} else {
			this.up1 = this.Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_up_1",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.up2 = this.Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_up_2",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.down1 = this.Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_down_1",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.down2 = this.Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_down_2",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.left1 = this.Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_left_1",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.left2 = Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_left_2",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.right1 = Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_right_1",
					this.gp.tileSize * i, this.gp.tileSize * i);
			this.right2 = Setup("/monster/skeletonlord/dichuyen/ttnoigian/skeletonlord_phase2_right_2",
					this.gp.tileSize * i, this.gp.tileSize * i);
		}

	}

	public void getAttackImage() {

		int i = 5;

		if (this.inRage == false) {
			this.attackUp1 = this.Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_up_1",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackUp2 = this.Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_up_2",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackDown1 = this.Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_down_1",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackDown2 = this.Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_down_2",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackLeft1 = Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_left_1",
					this.gp.tileSize * i * 2, this.gp.tileSize * i);
			this.attackleft2 = Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_left_2",
					this.gp.tileSize * i * 2, this.gp.tileSize * i);
			this.attackRight1 = Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_right_1",
					this.gp.tileSize * i * 2, this.gp.tileSize * i);
			this.attackRight2 = Setup("/monster/skeletonlord/tancong/ttbinhthuong/skeletonlord_attack_right_2",
					this.gp.tileSize * i * 2, this.gp.tileSize * i);

		} else {
			this.attackUp1 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_up_1",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackUp2 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_up_2",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackDown1 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_down_1",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackDown2 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_down_2",
					this.gp.tileSize * i, this.gp.tileSize * i * 2);
			this.attackLeft1 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_left_1",
					this.gp.tileSize * i * 2, this.gp.tileSize * i);
			this.attackleft2 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_left_2",
					this.gp.tileSize * i * 2, this.gp.tileSize * i);
			this.attackRight1 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_right_1", this.gp.tileSize * i * 2,
					this.gp.tileSize * i);
			this.attackRight2 = this.Setup("/monster/skeletonlord/tancong/ttnoigian/skeletonlord_phase2_attack_right_2", this.gp.tileSize * i * 2,
					this.gp.tileSize * i);

		}

	}

	public void setDialogue() {
		this.dialogues[0][0] = "No one can steal my treasure!";
		this.dialogues[0][1] = "You'll die here!";
		this.dialogues[0][2] = "WELCOME TO YOUR DOOM!";
	}

	@Override
	public void setAction() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
               if (this.inRage == false && this.life < this.maxLife / 2) {
            	   this.inRage = true;
            	   this.getImage();
            	   this.getAttackImage();
            	   this.DefaultSpeed++;
            	   this.speed = this.DefaultSpeed;
            	   this.attack *= 2;

				}

				if (this.getTileDistance(this.gp.boy) < 10) {
					this.moveTowardPlayer(60);
				} else {

					this.getRandomDirection(120);

				}
				if (this.attacking == false) {
					this.CheckAttackOrNot(60, this.gp.tileSize * 7, this.gp.tileSize * 5);
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				if (this.inRage == false && life < this.maxLife / 2) {
					this.inRage = true;
					this.getImage();
					this.getAttackImage();
					this.DefaultSpeed++;
					this.speed = this.DefaultSpeed;
					this.attack *= 2;

				}

				if (this.getTileDistance(this.gp.girl) < 10) {
					this.moveTowardPlayer(60);
				} else {

					this.getRandomDirection(120);

				}
				if (this.attacking == false) {
					this.CheckAttackOrNot(60, this.gp.tileSize * 7, this.gp.tileSize * 5);
				}
			}
		}
	}

	@Override
	public void DamageReaction() {
		this.actionlockcounter = 0;

	}

	@Override
	public void checkDrop() {
		this.gp.bossBattleOn = false;
		Progress.skeletonLordDefeated = true;

		this.gp.Stopmusic();
		this.gp.playMusic(8);

		for (int i = 0; i < this.gp.obj[1].length; i++) {
			if (this.gp.obj[this.gp.currentMap][i] != null && this.gp.obj[this.gp.currentMap][i].name.equals(OBJDoorIron.objName)) {
				this.gp.playMusic(7);
				this.gp.obj[this.gp.currentMap][i] = null;
			}
		}

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
