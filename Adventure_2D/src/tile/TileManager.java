package tile;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtivityTool;

public class TileManager {
	public GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	public boolean drawPath = true;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		this.tile = new Tile[999];
		this.mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		this.getTileImage();
		this.Loadmap("/map/ground.txt", 0);
		this.Loadmap("/map/house.txt", 1);
		this.Loadmap("/map/dungeon01.txt", 2);
		this.Loadmap("/map/dungeon02.txt", 3);
//		this.Loadmap("/map/desert.txt", 4);
//		this.Loadmap("/map/frozen.txt", 5);
//		this.Loadmap("/map/hell.txt", 6);
		
	}

	public void getTileImage() {

		// PLACEHOLDER
		this.SetUp(0, "000", false);
		this.SetUp(1, "001", false);
		this.SetUp(2, "002", false);
		this.SetUp(3, "003", false);
		this.SetUp(4, "004", false);
		this.SetUp(5, "005", false);
		this.SetUp(6, "006", false);
		this.SetUp(7, "007", false);
		this.SetUp(8, "008", false);
		this.SetUp(9, "009", false);
		this.SetUp(10, "010", false);
		this.SetUp(11, "011", false);
		this.SetUp(12, "012", false);
		this.SetUp(13, "013", false);
		this.SetUp(14, "014", false);
		this.SetUp(15, "015", false);
		this.SetUp(16, "016", true);
		this.SetUp(17, "017", false);
		this.SetUp(18, "018", true);
		this.SetUp(19, "019", true);
		this.SetUp(20, "020", true);
		this.SetUp(21, "021", true);
		this.SetUp(22, "022", true);
		this.SetUp(23, "023", true);
		this.SetUp(24, "024", true);
		this.SetUp(25, "025", true);
		this.SetUp(26, "026", true);
		this.SetUp(27, "027", true);
		this.SetUp(28, "028", true);
		this.SetUp(29, "029", true);
		this.SetUp(30, "030", true);
		this.SetUp(31, "031", true);
		this.SetUp(32, "032", true);
		this.SetUp(33, "033", false);
		this.SetUp(34, "034", false);
		this.SetUp(35, "035", true);
		this.SetUp(36, "036", false);
		this.SetUp(37, "037", false);
		this.SetUp(38, "038", false);
		this.SetUp(39, "039", false);
		this.SetUp(40, "040", true);
		this.SetUp(41, "041", false);
		this.SetUp(42, "042", false);
		this.SetUp(43, "043", false);
		this.SetUp(44, "044", true);
		this.SetUp(45, "045", true);
		this.SetUp(46, "046", true);
		this.SetUp(47, "047", true);
		this.SetUp(48, "048", true);
		this.SetUp(49, "049", true);
		this.SetUp(50, "050", true);
		this.SetUp(51, "051", true);
		this.SetUp(52, "052", true);
		this.SetUp(53, "053", true);
		this.SetUp(54, "054", true);
		this.SetUp(55, "055", true);
		this.SetUp(56, "056", false);
		this.SetUp(57, "057", true);
		this.SetUp(58, "058", true);
		this.SetUp(59, "059", true);
		this.SetUp(60, "060", true);
		this.SetUp(61, "061", false);
		this.SetUp(62, "062", true);
		this.SetUp(63, "063", true);
		this.SetUp(64, "064", true);
//		this.SetUp(65, "065", true);
//		this.SetUp(66, "066", true);
//		this.SetUp(67, "067", true);
//		this.SetUp(68, "068", true);
//		this.SetUp(69, "069", true);
//		this.SetUp(70, "070", true);
//		this.SetUp(71, "071", true);
//		this.SetUp(72, "072", true);
//		this.SetUp(73, "073", true);
//		this.SetUp(74, "074", true);
//		this.SetUp(75, "075", true);
	}

	public void SetUp(int index, String imgPath, boolean collision) {
		UtivityTool utool = new UtivityTool();
		try {
			this.tile[index] = new Tile();
			this.tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/tilenum/" + imgPath + ".png"));
			this.tile[index].image = utool.scaleImage(this.tile[index].image, this.gp.tileSize, this.gp.tileSize);
			this.tile[index].collision = collision;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Loadmap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0, row = 0;
			while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
				String line = br.readLine();
				while (line != null && col < this.gp.maxWorldCol) {
					String nb[] = line.split(" ");
					int number = Integer.parseInt(nb[col]);
					this.mapTileNum[map][col][row] = number;
					col++;
				}
				if (col == this.gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				int worldCol = 0, worldRow = 0;
				while (worldCol < this.gp.maxWorldCol && worldRow < this.gp.maxWorldRow) {
					int tilenum = this.mapTileNum[this.gp.currentMap][worldCol][worldRow];
					int worldX = worldCol * this.gp.tileSize;
					int worldY = worldRow * this.gp.tileSize;
					int screenX = worldX - this.gp.boy.worldX + this.gp.boy.screenX;
					int screenY = worldY - this.gp.boy.worldY + this.gp.boy.screenY;
					if (worldX + this.gp.tileSize > this.gp.boy.worldX - this.gp.boy.screenX
							&& worldX - this.gp.tileSize < this.gp.boy.worldX + this.gp.boy.screenX
							&& worldY + this.gp.tileSize > this.gp.boy.worldY - this.gp.boy.screenY
							&& worldY - this.gp.tileSize < this.gp.boy.worldY + this.gp.boy.screenY) {
						g2.drawImage(this.tile[tilenum].image, screenX, screenY, null);
					}
					worldCol++;
					if (worldCol == this.gp.maxWorldCol) {
						worldCol = 0;
						worldRow++;
					}
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				int worldCol = 0, worldRow = 0;
				while (worldCol < this.gp.maxWorldCol && worldRow < this.gp.maxWorldRow) {
					int tilenum = this.mapTileNum[this.gp.currentMap][worldCol][worldRow];
					int worldX = worldCol * this.gp.tileSize;
					int worldY = worldRow * this.gp.tileSize;
					int screenX = worldX - this.gp.girl.worldX + this.gp.girl.screenX;
					int screenY = worldY - this.gp.girl.worldY + this.gp.girl.screenY;
					if (worldX + this.gp.tileSize > this.gp.girl.worldX - this.gp.girl.screenX
							&& worldX - this.gp.tileSize < this.gp.girl.worldX + this.gp.girl.screenX
							&& worldY + this.gp.tileSize > this.gp.girl.worldY - this.gp.girl.screenY
							&& worldY - this.gp.tileSize < this.gp.girl.worldY + this.gp.girl.screenY) {
						g2.drawImage(this.tile[tilenum].image, screenX, screenY, null);
					}
					worldCol++;
					if (worldCol == this.gp.maxWorldCol) {
						worldCol = 0;
						worldRow++;
					}
				}
			}
		}

//		if (drawPath == true) {
//			g2.setColor(new Color(255, 0, 0, 70));
//			for (int i = 0; i < gp.pFind.pathlist.size(); i++) {
//				int worldX = gp.pFind.pathlist.get(i).col * gp.tileSize;
//				int worldY = gp.pFind.pathlist.get(i).row * gp.tileSize;
//
//				int screenX = worldX - gp.player.worldX + gp.player.screenX;
//				int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//				g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//
//			}
//		}
	}
}
