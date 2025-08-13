package tileinteractive;

import main.GamePanel;

public class ITMetaPlate extends InteractiveTile {
	public GamePanel gp;
	public static final String ITName = "Metal Plate";

	public ITMetaPlate(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.name = ITName;
		this.worldX = this.gp.tileSize * col;
		this.worldY = this.gp.tileSize * row;

		this.down1 = this.Setup("/tile/tiledestructible/metalplate", this.gp.tileSize, this.gp.tileSize);
		this.solidArea.x = 0;
		this.solidArea.y = 0;
		this.solidArea.height = 0;
		this.solidArea.width = 0;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}
}
