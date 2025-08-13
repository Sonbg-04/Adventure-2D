package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import objects.OBJCoin;
import objects.OBJHeart;
import objects.OBJManaCrysTal;

public class Bat extends Entity {
	public GamePanel gp;
	public static final String MonName = "Bat";

	public Bat(GamePanel gp) {
		super(gp);
		this.gp =gp;
		this.name = MonName;
		this.DefaultSpeed = 4;
		this.speed = this.DefaultSpeed;
		this.maxLife = 7;
		this.life = this.maxLife;
		this.type = this.type_monster;
		this.attack = 7;
		this.def = 0;
		this.exp = 7;

		this.solidArea.x = 3;
		this.solidArea.y = 15;
		this.solidArea.width = 42;
		this.solidArea.height = 21;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.getImage();
	}

	public void getImage() {
		this.up1 = this.Setup("/monster/bat/bat_1", this.gp.tileSize, this.gp.tileSize);
		this.up2 = this.Setup("/monster/bat/bat_2", this.gp.tileSize, this.gp.tileSize);
		this.down1 = this.Setup("/monster/bat/bat_1", this.gp.tileSize, this.gp.tileSize);
		this.down2 = this.Setup("/monster/bat/bat_2", this.gp.tileSize,this.gp.tileSize);
		this.left1 = this.Setup("/monster/bat/bat_1", this.gp.tileSize, this.gp.tileSize);
		this.left2 = this.Setup("/monster/bat/bat_2", this.gp.tileSize,this.gp.tileSize);
		this.right1 = this.Setup("/monster/bat/bat_1", this.gp.tileSize, this.gp.tileSize);
		this.right2 = this.Setup("/monster/bat/bat_2", this.gp.tileSize, this.gp.tileSize);
	}

	@Override
	public void setAction() {
		if (this.onPath == true) {

//			checkStopChasingOrNot(gp.player, 15, 100);
//			
//			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
//			
//			// CHECK IF IT SHOOTS A PROTECTILE	
//			CheckShootOrNot(200, 30);
//			
		} else {
//				CheckStartChasingOrNot(gp.player, 5, 100);
			this.getRandomDirection(10);
		}
	}

	@Override
	public void DamageReaction() {
		this.actionlockcounter = 0;
//		direction = gp.player.direction;
		this.onPath = true;
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
