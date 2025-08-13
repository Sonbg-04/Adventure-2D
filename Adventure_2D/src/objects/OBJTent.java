package objects;

import entity.Entity;
import main.GamePanel;

public class OBJTent extends Entity {
	public static final String objName = "Tent";
	public GamePanel gp;

	public OBJTent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.type = this.type_consumable;
		this.name = objName;
		this.down1 = this.Setup("/item/khac/tent", this.gp.tileSize, this.gp.tileSize);
		this.price = 20;
		this.stackable = true;

	}

	@Override
	public boolean use(Entity entity) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				this.gp.gameState = this.gp.sleepState;
				this.gp.boy.life = this.gp.boy.maxLife;
				this.gp.boy.mana = this.gp.boy.Maxmana;
				this.gp.boy.getSleepingImage(up1);
				this.gp.playSE(18);
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				this.gp.gameState = this.gp.sleepState;
				this.gp.girl.life = this.gp.girl.maxLife;
				this.gp.girl.mana = this.gp.girl.Maxmana;
				this.gp.girl.getSleepingImage(up1);
				this.gp.playSE(18);
			}
		}
		return true;
	}
}
