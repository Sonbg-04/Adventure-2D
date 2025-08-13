package objects;

import entity.Entity;
import main.GamePanel;

public class OBJCoin extends Entity {
	public static final String objName = "Coin";
	public GamePanel gp;

	public OBJCoin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.name = objName;
		this.type = this.type_pickupOnly;
		this.down1 = this.Setup("/item/khac/coin", this.gp.tileSize, this.gp.tileSize);
		this.value = 1;
		this.coin = 500;

	}

	@Override
	public boolean use(Entity entity) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				this.gp.ui.Addmessage("Coin: " + this.value);
				this.gp.boy.coin += this.value;
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				this.gp.ui.Addmessage("Coin: " + this.value);
				this.gp.girl.coin += this.value;
			}
		}
		return true;
	}
}
