package tile;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Map extends TileManager {

	GamePanel gp;
	BufferedImage worldMap[];
	public boolean onMiniMap = false;

	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.CreateWorldMap();
	}

	public void CreateWorldMap() {
		this.worldMap = new BufferedImage[this.gp.maxMap];
		int worldMapWidth = this.gp.tileSize * this.gp.maxWorldCol;
		int worldMapHeight = this.gp.tileSize * this.gp.maxWorldRow;
		for (int i = 0; i < this.gp.maxMap; i++) {
			this.worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D) (this.worldMap[i].createGraphics());
			int col = 0;
			int row = 0;
			while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
				int tileNum = this.mapTileNum[i][col][row];
				int x = this.gp.tileSize * col;
				int y = this.gp.tileSize * row;
				g2.drawImage(this.tile[tileNum].image, x, y, null);
				col++;
				if (col == this.gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			g2.dispose();
		}

	}

	public void drawFullMapScreen(Graphics2D g2) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				g2.setColor(Color.black);
				g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
				int width = 500;
				int height = 500;
				int x = this.gp.screenWidth / 2 - width / 2;
				int y = this.gp.screenHeight / 2 - height / 2;
				g2.drawImage(this.worldMap[this.gp.currentMap], x, y, width, height, null);

				// DRAW PLAYER
				double scale = (double) (this.gp.tileSize * this.gp.maxWorldCol) / width;
				int playerX = (int) (x + this.gp.boy.worldX / scale);
				int playerY = (int) (y + this.gp.boy.worldY / scale);
				int playerSize = (int) (this.gp.tileSize / scale);
				g2.drawImage(this.gp.boy.down1, playerX, playerY, playerSize, playerSize, null);

				// HINT
				g2.setFont(this.gp.ui.marumonica.deriveFont(23F));
				g2.setColor(Color.white);
				g2.drawString("Press M to close!", 760, 550);
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				g2.setColor(Color.black);
				g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
				int width = 500;
				int height = 500;
				int x = this.gp.screenWidth / 2 - width / 2;
				int y = this.gp.screenHeight / 2 - height / 2;
				g2.drawImage(this.worldMap[this.gp.currentMap], x, y, width, height, null);

				// DRAW PLAYER
				double scale = (double) (this.gp.tileSize * this.gp.maxWorldCol) / width;
				int playerX = (int) (x + this.gp.girl.worldX / scale);
				int playerY = (int) (y + this.gp.girl.worldY / scale);
				int playerSize = (int) (this.gp.tileSize / scale);
				g2.drawImage(this.gp.girl.down1, playerX, playerY, playerSize, playerSize, null);

				// HINT
				g2.setFont(this.gp.ui.marumonica.deriveFont(23F));
				g2.setColor(Color.white);
				g2.drawString("Press M to close!", 760, 550);
			}
		}

	}

	public void drawMiniMap(Graphics2D g2) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {

				if (this.onMiniMap == true) {
					int width = 200;
					int height = 200;
					int x = this.gp.screenWidth - width - 50;
					int y = 50;

					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));
					g2.drawImage(this.worldMap[this.gp.currentMap], x, y, width, height, null);
					
					// DRAW PLAYER
					double scale = (double) (this.gp.tileSize * this.gp.maxWorldCol) / width;
					int playerX = (int) (x + this.gp.boy.worldX / scale);
					int playerY = (int) (y + this.gp.boy.worldY / scale);
					int playerSize = (int) (this.gp.tileSize / 3);
					g2.drawImage(this.gp.boy.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {

				if (this.onMiniMap == true) {
					int width = 200;
					int height = 200;
					int x = this.gp.screenWidth - width - 50;
					int y = 50;

					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));
					g2.drawImage(this.worldMap[this.gp.currentMap], x, y, width, height, null);
					// DRAW PLAYER
					double scale = (double) (this.gp.tileSize * this.gp.maxWorldCol) / width;
					int playerX = (int) (x + this.gp.girl.worldX / scale);
					int playerY = (int) (y + this.gp.girl.worldY / scale);
					int playerSize = (int) (this.gp.tileSize / 3);
					g2.drawImage(this.gp.girl.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
				}
			}
		}
	}
}
