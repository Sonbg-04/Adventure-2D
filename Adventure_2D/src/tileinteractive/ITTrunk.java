package tileinteractive;

import main.GamePanel;

public class ITTrunk extends InteractiveTile {
	public GamePanel gp;

	public ITTrunk(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.worldX = this.gp.tileSize * col;
		this.worldY = this.gp.tileSize * row;

		this.down1 = this.Setup("/tile/tiledestructible/trunk", this.gp.tileSize, this.gp.tileSize);
		this.solidArea.x = 0;
		this.solidArea.y = 0;
		this.solidArea.height = 0;
		this.solidArea.width = 0;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}
}
