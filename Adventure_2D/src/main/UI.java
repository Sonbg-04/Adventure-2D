package main;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import objects.OBJCoin;
import objects.OBJHeart;
import objects.OBJManaCrysTal;

public class UI {
	public GamePanel gp;
	public Font marumonica;
	public Graphics2D g2;
	public BufferedImage heartfull, hearthalf, heartblank, crystal_full, crystal_blank, crystal_half, coin;
	public boolean messageOn = false;
	public ArrayList<String> msList = new ArrayList<>();
	public ArrayList<Integer> mscount = new ArrayList<>();
	public boolean gamefinish = false;
	public String currentDialogue = " ";
	public int commandNum = 0;
	public int titleScreenState = 0;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int subState = 0;
	public int count = 0;
	public Entity npc;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	public int charIndex = 0;
	public String combinedText = " ";

	public UI(GamePanel gp) {
		this.gp = gp;
		this.marumonica = new Font("Arial", Font.PLAIN, 10);

		// CREATE HUD OBJECT
		Entity heart = new OBJHeart(gp);
		this.heartfull = heart.img;
		this.hearthalf = heart.img2;
		this.heartblank = heart.img3;
		Entity mana = new OBJManaCrysTal(gp);
		this.crystal_full = mana.img;
		this.crystal_half = mana.img2;
		this.crystal_blank = mana.img3;
		Entity bronze = new OBJCoin(gp);
		this.coin = bronze.down1;
	}

	public void Addmessage(String text) {
		this.msList.add(text);
		this.mscount.add(0);
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(this.marumonica);
		g2.setColor(Color.white);

		// TITLE STATE
		if (this.gp.gameState == this.gp.titleState) {
			this.drawTitleScreen();
		}

		// PLAY STATE
		if (this.gp.gameState == this.gp.playstate) {
			this.drawPlayerLife();
			this.drawMessage();
			this.drawMonsterLife();
		}

		// PAUSE STATE
		if (this.gp.gameState == this.gp.pausestate) {
			this.drawPlayerLife();
			this.drawPauseScreen();
		}

		// DIALOGUE STATE
		if (this.gp.gameState == this.gp.dialogueState) {
			this.drawDialogueScreen();
		}

		// CHARACTER STATE
		if (this.gp.gameState == this.gp.CharacterState) {
			if (this.gp.currentPlayer != null) {
				if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
					this.drawCharacterScreen();
					this.drawInventory(this.gp.boy, true);
				}
				if (this.gp.currentPlayer == this.gp.girl
						&& this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
					this.drawCharacterScreen();
					this.drawInventory(this.gp.girl, true);
				}
			}

		}

		// OPTIONS STATE
		if (this.gp.gameState == this.gp.optionState) {
			this.drawOptionScreen();
		}

		// GAME OVER STATE
		if (this.gp.gameState == this.gp.gameOverState) {
			this.drawGameoverScreen();
		}

		// TRANSITION STATE
		if (this.gp.gameState == this.gp.transitionState) {
			this.drawTransition();
		}

		// TRADE STATE
		if (this.gp.gameState == this.gp.tradeState) {
			this.drawTradeScreen();
		}

		// SLEEP STATE
		if (this.gp.gameState == this.gp.sleepState) {
			this.drawSleepScreen();
		}
	}
	
	public void drawSleepScreen() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				this.count++;
				if (this.count < 120) {
					this.gp.eManager.lighting.filterAlpha += 0.001F;
					if (this.gp.eManager.lighting.filterAlpha > 1F) {
						this.gp.eManager.lighting.filterAlpha = 1F;
					}
				}
				if (this.count >= 120) {
					this.gp.eManager.lighting.filterAlpha -= 0.001F;
					if (this.gp.eManager.lighting.filterAlpha <= 0F) {
						this.gp.eManager.lighting.filterAlpha = 0F;
						this.count = 0;
						this.gp.eManager.lighting.dayCounter = 0;
						this.gp.eManager.lighting.dayState = this.gp.eManager.lighting.day;
						this.gp.gameState = this.gp.playstate;
						this.gp.boy.getBoyImage();

					}
				}

			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				this.count++;
				if (this.count < 120) {
					this.gp.eManager.lighting.filterAlpha += 0.001F;
					if (this.gp.eManager.lighting.filterAlpha > 1F) {
						this.gp.eManager.lighting.filterAlpha = 1F;
					}
				}
				if (this.count >= 120) {
					this.gp.eManager.lighting.filterAlpha -= 0.001F;
					if (this.gp.eManager.lighting.filterAlpha <= 0F) {
						this.gp.eManager.lighting.filterAlpha = 0F;
						this.count = 0;
						this.gp.eManager.lighting.dayCounter = 0;
						this.gp.eManager.lighting.dayState = this.gp.eManager.lighting.day;
						this.gp.gameState = this.gp.playstate;
						this.gp.girl.getGirlImage();

					}
				}

			}
		}

	}

	public void drawInventory(Entity ent, boolean cursor) {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				int frameX = 0;
				int frameY = 0;
				int framewidth = 0;
				int frameheight = 0;
				int slotCol = 0;
				int slotRow = 0;

				if (ent == this.gp.boy) {
					frameX = this.gp.tileSize * 12;
					frameY = this.gp.tileSize;
					framewidth = this.gp.tileSize * 6;
					frameheight = this.gp.tileSize * 5;
					slotCol = this.playerSlotCol;
					slotRow = this.playerSlotRow;
				} else {
					frameX = this.gp.tileSize * 2;
					frameY = this.gp.tileSize;
					framewidth = this.gp.tileSize * 6;
					frameheight = this.gp.tileSize * 5;
					slotCol = this.npcSlotCol;
					slotRow = this.npcSlotRow;

				}

				// FRAME
				this.drawSubWindow(frameX, frameY, framewidth, frameheight);

				// SLOT
				final int slotXstart = frameX + 20;
				final int slotYstart = frameY + 20;
				int slotX = slotXstart;
				int slotY = slotYstart;
				int SlotSize = this.gp.tileSize;

				// CURSOR
				if (cursor == true) {
					int cursorX = slotXstart + (this.gp.tileSize * slotCol);
					int cursorY = slotYstart + (this.gp.tileSize * slotRow);
					int cursorWidth = this.gp.tileSize;
					int cursorHeight = this.gp.tileSize;
					// DRAW CURSOR
					this.g2.setColor(Color.WHITE);
					this.g2.setStroke(new BasicStroke(3));
					this.g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

					// DESCRIPTION FRAME
					int dFrameX = frameX;
					int dFrameY = frameY + frameheight;
					int dFramewidth = framewidth;
					int dFrameheight = this.gp.tileSize * 3;

					// DRAW DESCRIPTION TEXT
					int dTextX = dFrameX + 20;
					int dTextY = dFrameY + this.gp.tileSize;
					this.g2.setFont(this.g2.getFont().deriveFont(25F));
					int itemindex = this.getItemsIndexOnSlot(slotCol, slotRow);
					if (itemindex < ent.inventory.size()) {
						this.drawSubWindow(dFrameX, dFrameY, dFramewidth, dFrameheight);
						for (String line : ent.inventory.get(itemindex).description.split("\n")) {
							this.g2.drawString(line, dTextX, dTextY);
							dTextY += 35;
						}

					}

				}

				// DRAW PLAYER'S ITEMS
				for (int i = 0; i < ent.inventory.size(); i++) {

					// EQUIP CURSOR
					if (ent.inventory.get(i) == ent.currentWeapon || ent.inventory.get(i) == ent.currentShield
							|| ent.inventory.get(i) == ent.currentLight) {
						this.g2.setColor(new Color(240, 190, 90));
						this.g2.fillRoundRect(slotX, slotY, SlotSize, SlotSize, 10, 10);
					}
					this.g2.drawImage(ent.inventory.get(i).down1, slotX, slotY, null);

					// DISPLAY AMOUNT
					if (ent == this.gp.boy && ent.inventory.get(i).amount > 1) {
						this.g2.setFont(this.g2.getFont().deriveFont(30F));
						int amountX;
						int amountY;

						String s = " " + ent.inventory.get(i).amount;
						amountX = getXforAlignedToRightText(s, slotX + 44);
						amountY = slotY + this.gp.tileSize;

						// SHADOW
						this.g2.setColor(new Color(60, 60, 60));
						this.g2.drawString(s, amountX, amountY);

						// NUMBER
						this.g2.setColor(Color.white);
						this.g2.drawString(s, amountX - 3, amountY - 3);
					}

					slotX += SlotSize;
					if (i == 4 || i == 9 || i == 14) {
						slotX = slotXstart;
						slotY += SlotSize;
					}
				}

			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				int frameX = 0;
				int frameY = 0;
				int framewidth = 0;
				int frameheight = 0;
				int slotCol = 0;
				int slotRow = 0;

				if (ent == this.gp.girl) {
					frameX = this.gp.tileSize * 12;
					frameY = this.gp.tileSize;
					framewidth = this.gp.tileSize * 6;
					frameheight = this.gp.tileSize * 5;
					slotCol = this.playerSlotCol;
					slotRow = this.playerSlotRow;
				} else {
					frameX = this.gp.tileSize * 2;
					frameY = this.gp.tileSize;
					framewidth = this.gp.tileSize * 6;
					frameheight = this.gp.tileSize * 5;
					slotCol = this.npcSlotCol;
					slotRow = this.npcSlotRow;

				}

				// FRAME
				this.drawSubWindow(frameX, frameY, framewidth, frameheight);

				// SLOT
				final int slotXstart = frameX + 20;
				final int slotYstart = frameY + 20;
				int slotX = slotXstart;
				int slotY = slotYstart;
				int SlotSize = this.gp.tileSize;

				// CURSOR
				if (cursor == true) {
					int cursorX = slotXstart + (this.gp.tileSize * slotCol);
					int cursorY = slotYstart + (this.gp.tileSize * slotRow);
					int cursorWidth = this.gp.tileSize;
					int cursorHeight = this.gp.tileSize;
					// DRAW CURSOR
					this.g2.setColor(Color.WHITE);
					this.g2.setStroke(new BasicStroke(3));
					this.g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

					// DESCRIPTION FRAME
					int dFrameX = frameX;
					int dFrameY = frameY + frameheight;
					int dFramewidth = framewidth;
					int dFrameheight = this.gp.tileSize * 3;

					// DRAW DESCRIPTION TEXT
					int dTextX = dFrameX + 20;
					int dTextY = dFrameY + this.gp.tileSize;
					this.g2.setFont(this.g2.getFont().deriveFont(25F));
					int itemindex = getItemsIndexOnSlot(slotCol, slotRow);
					if (itemindex < ent.inventory.size()) {
						this.drawSubWindow(dFrameX, dFrameY, dFramewidth, dFrameheight);
						for (String line : ent.inventory.get(itemindex).description.split("\n")) {
							this.g2.drawString(line, dTextX, dTextY);
							dTextY += 35;
						}

					}

				}

				// DRAW PLAYER'S ITEMS
				for (int i = 0; i < ent.inventory.size(); i++) {

					// EQUIP CURSOR
					if (ent.inventory.get(i) == ent.currentWeapon || ent.inventory.get(i) == ent.currentShield
							|| ent.inventory.get(i) == ent.currentLight) {
						this.g2.setColor(new Color(240, 190, 90));
						this.g2.fillRoundRect(slotX, slotY, SlotSize, SlotSize, 10, 10);
					}
					this.g2.drawImage(ent.inventory.get(i).down1, slotX, slotY, null);

					// DISPLAY AMOUNT
					if (ent == this.gp.girl && ent.inventory.get(i).amount > 1) {
						this.g2.setFont(this.g2.getFont().deriveFont(30F));
						int amountX;
						int amountY;

						String s = " " + ent.inventory.get(i).amount;
						amountX = this.getXforAlignedToRightText(s, slotX + 44);
						amountY = slotY + this.gp.tileSize;

						// SHADOW
						this.g2.setColor(new Color(60, 60, 60));
						this.g2.drawString(s, amountX, amountY);

						// NUMBER
						this.g2.setColor(Color.white);
						this.g2.drawString(s, amountX - 3, amountY - 3);
					}

					slotX += SlotSize;
					if (i == 4 || i == 9 || i == 14) {
						slotX = slotXstart;
						slotY += SlotSize;
					}
				}

			}
		}
	}

	public int getItemsIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}

	public void drawMessage() {
		int msX = this.gp.tileSize;
		int msY = this.gp.tileSize * 4;
		this.g2.setFont(this.g2.getFont().deriveFont(Font.BOLD, 28F));

		for (int i = 0; i < this.msList.size(); i++) {
			if (this.mscount.get(i) != null) {
				this.g2.setColor(Color.black);
				this.g2.drawString(msList.get(i), msX + 2, msY + 2);

				this.g2.setColor(Color.white);
				this.g2.drawString(this.msList.get(i), msX, msY);

				int counter = this.mscount.get(i) + 1; // MESSAGECOUNTER++
				this.mscount.set(i, counter);// SET THE COUNTER TO ARRAY
				msY += 50;

				if (this.mscount.get(i) > 180) {
					this.mscount.remove(i);
					this.msList.remove(i);
				}
			}
		}
	}

	public void drawCharacterScreen() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				// CREATE A FRAME
				final int frameX, frameY, framewidth, frameheight;
				frameX = this.gp.tileSize * 2;
				frameY = this.gp.tileSize;
				framewidth = this.gp.tileSize * 5;
				frameheight = this.gp.tileSize * 10;
				this.drawSubWindow(frameX, frameY, framewidth, frameheight);

				// TEXT
				this.g2.setColor(Color.white);
				this.g2.setFont(this.g2.getFont().deriveFont(30F));

				int textX = frameX + 20;
				int textY = frameY + this.gp.tileSize;
				final int lineHeight = 35;

				// NAMES
				this.g2.drawString("Level: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Life: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Mana: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Strength: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Dexterity: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Attack: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Defense: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Exp: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Next Level: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Coin: ", textX, textY);
				textY += lineHeight + 15;
				this.g2.drawString("Weapon: ", textX, textY);
				textY += lineHeight + 10;
				this.g2.drawString("Shield: ", textX, textY);
				textY += lineHeight;

				// VALUES
				int tailX = (frameX + framewidth) - 20;

				// RESET TEXT Y
				textY = frameY + this.gp.tileSize;
				String value;

				value = String.valueOf(this.gp.boy.level);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.life + "/" + this.gp.boy.maxLife);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.mana + "/" + this.gp.boy.Maxmana);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.strength);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.dex);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.attack);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.def);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.exp);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.nextLVexp);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.boy.coin);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				this.g2.drawImage(this.gp.boy.currentWeapon.down1, tailX - this.gp.tileSize, textY - 24, null);
				textY += this.gp.tileSize;

				this.g2.drawImage(this.gp.boy.currentShield.down1, tailX - this.gp.tileSize, textY - 24, null);
				textY += this.gp.tileSize;

			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				// CREATE A FRAME
				final int frameX, frameY, framewidth, frameheight;
				frameX = this.gp.tileSize * 2;
				frameY = this.gp.tileSize;
				framewidth = this.gp.tileSize * 5;
				frameheight = this.gp.tileSize * 10;
				this.drawSubWindow(frameX, frameY, framewidth, frameheight);

				// TEXT
				this.g2.setColor(Color.white);
				this.g2.setFont(this.g2.getFont().deriveFont(30F));

				int textX = frameX + 20;
				int textY = frameY + this.gp.tileSize;
				final int lineHeight = 35;

				// NAMES
				this.g2.drawString("Level: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Life: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Mana: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Strength: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Dexterity: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Attack: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Defense: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Exp: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Next Level: ", textX, textY);
				textY += lineHeight;
				this.g2.drawString("Coin: ", textX, textY);
				textY += lineHeight + 15;
				this.g2.drawString("Weapon: ", textX, textY);
				textY += lineHeight + 10;
				this.g2.drawString("Shield: ", textX, textY);
				textY += lineHeight;

				// VALUES
				int tailX = (frameX + framewidth) - 20;

				// RESET TEXT Y
				textY = frameY + this.gp.tileSize;
				String value;

				value = String.valueOf(this.gp.girl.level);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.life + "/" + this.gp.girl.maxLife);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.mana + "/" + this.gp.girl.Maxmana);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.strength);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.dex);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.attack);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.def);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.exp);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.nextLVexp);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				value = String.valueOf(this.gp.girl.coin);
				textX = this.getXforAlignedToRightText(value, tailX);
				this.g2.drawString(value, textX, textY);
				textY += lineHeight;

				this.g2.drawImage(this.gp.girl.currentWeapon.down1, tailX - this.gp.tileSize, textY - 24, null);
				textY += this.gp.tileSize;

				this.g2.drawImage(this.gp.girl.currentShield.down1, tailX - this.gp.tileSize, textY - 24, null);
				textY += this.gp.tileSize;

			}
		}
	}

	public void drawMonsterLife() {
		for (int i = 0; i < this.gp.mons[1].length; i++) {
			if (this.gp.mons[this.gp.currentMap][i] != null && this.gp.mons[this.gp.currentMap][i].inCamera() == true) {
				if (this.gp.mons[this.gp.currentMap][i].HPbarOn == true
						&& this.gp.mons[this.gp.currentMap][i].boss == false) {
					double oneScale = (double) this.gp.tileSize / this.gp.mons[this.gp.currentMap][i].maxLife;
					double hpBarvalue = oneScale * this.gp.mons[this.gp.currentMap][i].life;
					this.g2.setColor(new Color(35, 35, 35));
					this.g2.fillRect(this.gp.mons[this.gp.currentMap][i].getScreenX() - 1,
							this.gp.mons[this.gp.currentMap][i].getScreenY() - 16, this.gp.tileSize + 2, 12);
					this.g2.setColor(new Color(255, 0, 30));
					this.g2.fillRect(this.gp.mons[this.gp.currentMap][i].getScreenX(),
							this.gp.mons[this.gp.currentMap][i].getScreenY() - 15, (int) hpBarvalue, 10);
					this.gp.mons[this.gp.currentMap][i].Hpcounter++;
					if (this.gp.mons[this.gp.currentMap][i].Hpcounter > 600) {
						this.gp.mons[this.gp.currentMap][i].Hpcounter = 0;
						this.gp.mons[this.gp.currentMap][i].HPbarOn = false;
					}
				} else if (this.gp.mons[this.gp.currentMap][i].boss == true) {
					double oneScale = (double) this.gp.tileSize * 8 / this.gp.mons[this.gp.currentMap][i].maxLife;
					double hpBarvalue = oneScale * this.gp.mons[this.gp.currentMap][i].life;
					int x = this.gp.screenWidth / 2 - this.gp.tileSize * 4;
					int y = this.gp.tileSize * 10;
					this.g2.setColor(new Color(35, 35, 35));
					this.g2.fillRect(x - 1, y - 1, this.gp.tileSize * 8 + 2, 22);
					this.g2.setColor(new Color(255, 0, 30));
					this.g2.fillRect(x, y, (int) hpBarvalue, 20);

					this.g2.setFont(this.g2.getFont().deriveFont(Font.BOLD, 24F));

					this.g2.setColor(Color.white);
					this.g2.drawString(this.gp.mons[this.gp.currentMap][i].name, x + 4, y - 10);

				}
			}
		}

	}

	public void drawPlayerLife() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				int x = this.gp.tileSize / 2;
				int y = this.gp.tileSize / 2;
				int i = 0;
				int iconSize = 32;
				int kc = 0;

				// DRAW MAX LIFE
				while (i < this.gp.boy.maxLife / 2) {
					this.g2.drawImage(this.heartblank, x, y, iconSize, iconSize, null);
					i++;
					x += iconSize;
					kc = y + 32;
					if (i % 8 == 0) {
						x = this.gp.tileSize / 2;
						y += iconSize;
					}
				}

				// RESET
				x = this.gp.tileSize / 2;
				y = this.gp.tileSize / 2;
				i = 0;

				// DRAW CURRENT LIFE
				while (i < this.gp.boy.life) {
					this.g2.drawImage(this.hearthalf, x, y, iconSize, iconSize, null);
					i++;
					if (i < this.gp.boy.life) {
						this.g2.drawImage(this.heartfull, x, y, iconSize, iconSize, null);
						i++;
					}
					x += iconSize;
				}

				// DRAW MAX MANA
				x = (this.gp.tileSize / 2) - 5;
				y = kc;
				i = 0;
				while (i < this.gp.boy.Maxmana / 2) {
					this.g2.drawImage(this.crystal_blank, x, y, iconSize, iconSize, null);
					i++;
					x += iconSize;
					if (i % 8 == 0) {
						x = (this.gp.tileSize / 2) - 5;
						y += iconSize;
					}
				}

				// RESET
				x = (this.gp.tileSize / 2) - 5;
				y = kc;
				i = 0;

				// DRAW MANA
				while (i < this.gp.boy.mana) {
					this.g2.drawImage(this.crystal_half, x, y, iconSize, iconSize, null);
					i++;
					if (i < this.gp.boy.mana) {
						this.g2.drawImage(this.crystal_full, x, y, iconSize, iconSize, null);
						i++;
					}
					x += iconSize;
				}

			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				int x = this.gp.tileSize / 2;
				int y = this.gp.tileSize / 2;
				int i = 0;
				int iconSize = 32;
				int kc = 0;

				// DRAW MAX LIFE
				while (i < this.gp.girl.maxLife / 2) {
					this.g2.drawImage(this.heartblank, x, y, iconSize, iconSize, null);
					i++;
					x += iconSize;
					kc = y + 32;
					if (i % 8 == 0) {
						x = this.gp.tileSize / 2;
						y += iconSize;
					}
				}

				// RESET
				x = this.gp.tileSize / 2;
				y = this.gp.tileSize / 2;
				i = 0;

				// DRAW CURRENT LIFE
				while (i < this.gp.girl.life) {
					this.g2.drawImage(this.hearthalf, x, y, iconSize, iconSize, null);
					i++;
					if (i < this.gp.girl.life) {
						this.g2.drawImage(this.heartfull, x, y, iconSize, iconSize, null);
						i++;
					}
					x += iconSize;
				}

				// DRAW MAX MANA
				x = (this.gp.tileSize / 2) - 5;
				y = kc;
				i = 0;
				while (i < this.gp.girl.Maxmana / 2) {
					this.g2.drawImage(this.crystal_blank, x, y, iconSize, iconSize, null);
					i++;
					x += iconSize;
					if (i % 8 == 0) {
						x = (this.gp.tileSize / 2) - 5;
						y += iconSize;
					}
				}

				// RESET
				x = (this.gp.tileSize / 2) - 5;
				y = kc;
				i = 0;

				// DRAW MANA
				while (i < this.gp.girl.mana) {
					this.g2.drawImage(this.crystal_half, x, y, iconSize, iconSize, null);
					i++;
					if (i < this.gp.girl.mana) {
						this.g2.drawImage(this.crystal_full, x, y, iconSize, iconSize, null);
						i++;
					}
					x += iconSize;
				}
			}
		}
	}

	public void drawTitleScreen() {
		this.g2.setColor(new Color(0, 0, 0));
		this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
		if (this.titleScreenState == 0) {
			// TITLE NAME
			this.g2.setFont(this.g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "Adventure's 2D";
			int x = this.getXforCenteredText(text);
			int y = this.gp.tileSize * 3;

			// SHADOW
			this.g2.setColor(Color.gray);
			this.g2.drawString(text, x + 5, y + 5);

			// MAIN COLOR
			this.g2.setColor(Color.white);
			this.g2.drawString(text, x, y);

			this.drawIconPlayerScreen(this.gp.boy.down1);

			// MENU
			this.g2.setFont(this.g2.getFont().deriveFont(Font.BOLD, 48F));
			text = "New game";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize * 6;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 0) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}

			text = "Load game";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 1) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}

			text = "Quit";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 2) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}
		} else if (this.titleScreenState == 1) {
			this.g2.setColor(Color.white);
			this.g2.setFont(this.g2.getFont().deriveFont(30F));

			String text = "Select your character:";
			int x = this.getXforCenteredText(text);
			int y = this.gp.tileSize * 2;
			this.g2.drawString(text, x, y);

			text = "Boy";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize * 3;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 0) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}

			text = "Girl";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize * 2;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 1) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}

			text = "Back";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize * 3;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 2) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}
		} else if (this.titleScreenState == 2) {
			this.g2.setColor(Color.white);
			this.g2.setFont(this.g2.getFont().deriveFont(30F));

			String text = "You want to load character: ";
			int x = this.getXforCenteredText(text);
			int y = this.gp.tileSize * 2;
			this.g2.drawString(text, x, y);

			text = "Boy";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize * 3;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 1) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}

			text = "Girl";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize * 2;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 2) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}

			text = "Back";
			x = this.getXforCenteredText(text);
			y += this.gp.tileSize * 3;
			this.g2.drawString(text, x, y);
			if (this.commandNum == 3) {
				this.g2.drawString(">", x - this.gp.tileSize, y);
			}
		}
	}

	public void drawIconPlayerScreen(BufferedImage bf) {
		if (bf != null) {
			int x;
			float y;
			x = this.gp.screenWidth / 2 - (this.gp.tileSize * 3) / 2;
			y = (int) (this.gp.tileSize * 4.5);
			this.g2.drawImage(bf, x, (int) y, this.gp.tileSize * 3, this.gp.tileSize * 3, null);
		}
	}

	public void drawDialogueScreen() {

		// WINDOW
		int x, y, width, height;
		x = this.gp.tileSize * 3;
		y = this.gp.tileSize / 2;
		width = this.gp.screenWidth - (this.gp.tileSize * 4);
		height = this.gp.tileSize * 4;
		this.drawSubWindow(x, y, width, height);
		this.g2.setFont(this.g2.getFont().deriveFont(Font.PLAIN, 20F));
		x += this.gp.tileSize;
		y += this.gp.tileSize;
		if (this.npc.dialogues[this.npc.dialogueSet][this.npc.DialogueIndex] != null) {
			char characters[] = this.npc.dialogues[this.npc.dialogueSet][this.npc.DialogueIndex].toCharArray();
			if (this.charIndex < characters.length) {
				this.gp.playSE(19);
				String s = String.valueOf(characters[charIndex]);
				this.combinedText += s;
				this.currentDialogue = this.combinedText;
				this.charIndex++;

			}
			if (this.gp.khl.enterPressed == true) {
				this.charIndex = 0;
				this.combinedText = " ";

				if (this.gp.gameState == this.gp.dialogueState || this.gp.gameState == this.gp.cutsceneState) {
					this.npc.DialogueIndex++;
					this.gp.khl.enterPressed = false;
				}
			}
		} else {
			this.npc.DialogueIndex = 0;
			if (this.gp.gameState == this.gp.dialogueState) {
				this.gp.gameState = this.gp.playstate;
			}
			if (this.gp.gameState == this.gp.cutsceneState) {
				this.gp.csManager.scenePhase++;
			}
		}

		for (String line : this.currentDialogue.split("\n")) {
			this.g2.drawString(line, x, y);
			y += 40;
		}
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 200);
		this.g2.setColor(c);
		this.g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255, 255, 255);
		this.g2.setColor(c);
		this.g2.setStroke(new BasicStroke(5));
		this.g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}

	public void drawPauseScreen() {
		this.g2.setFont(this.g2.getFont().deriveFont(Font.PLAIN, 40F));
		String text = "Pause";
		int x, y;
		x = this.getXforCenteredText(text);
		y = this.gp.screenHeight / 2;
		this.g2.drawString(text, x, y);
	}

	public void drawGameoverScreen() {
		this.g2.setColor(new Color(0, 0, 0, 150));
		this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
		int x, y;
		String t;
		this.g2.setFont(this.g2.getFont().deriveFont(Font.BOLD, 110F));
		t = "Game over";

		// SHADOW
		this.g2.setColor(Color.black);
		x = this.getXforCenteredText(t);
		y = this.gp.tileSize * 4;
		this.g2.drawString(t, x, y);

		// MAIN
		this.g2.setColor(Color.white);
		this.g2.drawString(t, x - 4, y - 4);

		// RETRY
		this.g2.setFont(this.g2.getFont().deriveFont(50F));
		t = "Retry";
		x = this.getXforCenteredText(t);
		y += this.gp.tileSize * 4;
		this.g2.drawString(t, x, y);
		if (this.commandNum == 0) {
			this.g2.drawString(">", x - 40, y);
		}

		// BACK TO THE TITLE SCREEN
		t = "Quit";
		x = this.getXforCenteredText(t);
		y += 100;
		this.g2.drawString(t, x, y);
		if (this.commandNum == 1) {
			this.g2.drawString(">", x - 40, y);
		}
	}

	public void drawOptionScreen() {
		this.g2.setColor(Color.white);
		this.g2.setFont(this.g2.getFont().deriveFont(20F));

		// SUB WINDOW
		int frameX = this.gp.tileSize * 6;
		int frameY = this.gp.tileSize;
		int frameWidth = this.gp.tileSize * 8;
		int frameHeight = this.gp.tileSize * 10;
		this.drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		switch (this.subState) {
		case 0:
			this.options_top(frameX, frameY);
			break;
		case 1:
			this.options_fullscreenNotification(frameX, frameY);
			break;
		case 2:
			this.options_control(frameX, frameY);
			break;
		case 3:
			this.options_endGame(frameX, frameY);
			break;
		}
		this.gp.khl.enterPressed = false;
	}

	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;
		String text;

		// TITLE
		text = "Options";
		textX = this.getXforCenteredText(text);
		textY = frameY + this.gp.tileSize;
		this.g2.drawString(text, textX, textY);

		// FULL SCREEN ON/OFF
		textX = frameX + this.gp.tileSize;
		textY += this.gp.tileSize * 2;
		this.g2.drawString("Full screen", textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				if (this.gp.fullScreenOn == false) {
					this.gp.fullScreenOn = true;
				} else {
					this.gp.fullScreenOn = false;
				}
				this.subState = 1;
			}
		}

		// MUSIC
		textY += this.gp.tileSize;
		this.g2.drawString("Music", textX, textY);
		if (this.commandNum == 1) {
			this.g2.drawString(">", textX - 25, textY);
		}

		// SE
		textY += this.gp.tileSize;
		this.g2.drawString("SE", textX, textY);
		if (this.commandNum == 2) {
			this.g2.drawString(">", textX - 25, textY);
		}

		// CONTROL
		textY += this.gp.tileSize;
		this.g2.drawString("Control", textX, textY);
		if (this.commandNum == 3) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 2;
			}
		}

		// END GAME
		textY += this.gp.tileSize;
		this.g2.drawString("End game", textX, textY);
		if (this.commandNum == 4) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 3;
			}
		}

		// BACK
		textY += this.gp.tileSize * 2;
		this.g2.drawString("Back", textX, textY);
		if (this.commandNum == 5) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				this.gp.gameState = this.gp.playstate;
				this.commandNum = 0;
			}
		}

		// FULL SCREEN CHECK BOX
		textX = frameX + (int) (this.gp.tileSize * 4.5);
		textY = frameY + this.gp.tileSize * 2 + 31;
		this.g2.setStroke(new BasicStroke(3));
		this.g2.drawRect(textX, textY, 24, 24);
		if (this.gp.fullScreenOn == true) {
			this.g2.fillRect(textX, textY, 24, 24);
		}

		// MUSIC VOLUME
		textY += this.gp.tileSize;
		this.g2.drawRect(textX, textY, 120, 24); // 120 : 5 = 24
		int volumeWidth = 24 * this.gp.music.volumeScale;
		this.g2.fillRect(textX, textY, volumeWidth, 24);

		// SE VOLUME
		textY += this.gp.tileSize;
		this.g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * this.gp.s.volumeScale;
		this.g2.fillRect(textX, textY, volumeWidth, 24);

		this.gp.cfg.saveConfig();

	}

	public void options_fullscreenNotification(int frameX, int frameY) {
		int textX = frameX + this.gp.tileSize;
		int textY = frameY + this.gp.tileSize * 3;
		this.currentDialogue = "Please restart your computer\n to apply screen setting!";
		for (String line : this.currentDialogue.split("\n")) {
			this.g2.drawString(line, textX, textY);
			textY += 40;
		}

		// BACK
		textY = frameY + this.gp.tileSize * 9;
		this.g2.drawString("Back", textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 0;
			}
		}
	}

	public void options_control(int frameX, int frameY) {
		int textY, textX;

		// TITLE
		String text = "Control";
		textX = this.getXforCenteredText(text);
		textY = frameY + this.gp.tileSize;
		this.g2.drawString(text, textX, textY);

		textX = frameX + this.gp.tileSize;
		textY += this.gp.tileSize;
		this.g2.drawString("Move", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("Confirm/Attack", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("Shoot/Cast", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("Character Screen", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("Pause", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("Options", textX, textY);
		textY += this.gp.tileSize;

		textX = frameX + this.gp.tileSize * 6;
		textY = frameY + this.gp.tileSize * 2;
		this.g2.drawString("UDLR", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("ENTER", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("F", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("C", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("P", textX, textY);
		textY += this.gp.tileSize;
		this.g2.drawString("F1", textX, textY);
		textY += this.gp.tileSize;

		// BACK
		textX = frameX + this.gp.tileSize;
		textY = frameY + this.gp.tileSize * 9;
		this.g2.drawString("Back", textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 0;
				this.commandNum = 3;
			}
		}

	}

	public void options_endGame(int frameX, int frameY) {
		int textX = frameX + this.gp.tileSize;
		int textY = frameY + this.gp.tileSize * 3;
		this.currentDialogue = "Do you want to equit the game?";
		for (String line : this.currentDialogue.split("\n")) {
			this.g2.drawString(line, textX, textY);
			textY += 40;
		}

		// YES
		String text = "Yes";
		textX = this.getXforCenteredText(text);
		textY += this.gp.tileSize;
		this.g2.drawString(text, textX, textY);
		if (this.commandNum == 0) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 0;
				this.gp.gameState = this.gp.titleState;
				this.gp.resetGame(true);
			}
		}

		// NO
		text = "No";
		textX = this.getXforCenteredText(text);
		textY += this.gp.tileSize * 2;
		this.g2.drawString(text, textX, textY);
		if (this.commandNum == 1) {
			this.g2.drawString(">", textX - 25, textY);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 0;
				this.commandNum = 4;
			}
		}

	}

	public void drawTransition() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				this.count++;
				this.g2.setColor(new Color(0, 0, 0, count * 5));
				this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
				if (this.count == 50) {
					this.count = 0;
					this.gp.gameState = this.gp.playstate;
					this.gp.currentMap = this.gp.evl.tempMap;
					this.gp.boy.worldX = this.gp.tileSize * this.gp.evl.tempCol;
					this.gp.boy.worldY = this.gp.tileSize * this.gp.evl.tempRow;
					this.gp.evl.previousEventX = this.gp.boy.worldX;
					this.gp.evl.previousEventY = this.gp.boy.worldY;
					this.gp.changeArea();
				}
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				this.count++;
				this.g2.setColor(new Color(0, 0, 0, count * 5));
				this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
				if (this.count == 50) {
					this.count = 0;
					this.gp.gameState = this.gp.playstate;
					this.gp.currentMap = this.gp.evl.tempMap;
					this.gp.girl.worldX = this.gp.tileSize * this.gp.evl.tempCol;
					this.gp.girl.worldY = this.gp.tileSize * this.gp.evl.tempRow;
					this.gp.evl.previousEventX = this.gp.girl.worldX;
					this.gp.evl.previousEventY = this.gp.girl.worldY;
					this.gp.changeArea();
				}
			}
		}
	}

	public void drawTradeScreen() {
		switch (this.subState) {
		case 0: {
			this.trade_select();
			break;
		}
		case 1: {
			this.trade_buy();
			break;
		}
		case 2: {
			this.trade_sell();
			break;
		}
		}
		this.gp.khl.enterPressed = false;
	}

	public void trade_select() {
		this.npc.dialogueSet = 0;
		this.drawDialogueScreen();

		// DRAW WINDOW
		int x = (int) (this.gp.tileSize * 15.4);
		int y = (int) (this.gp.tileSize * 4.5);
		int width = this.gp.tileSize * 3;
		int height = (int) (this.gp.tileSize * 3.5);
		this.drawSubWindow(x, y, width, height);

		// DRAW TEXTS
		x += this.gp.tileSize;
		y += this.gp.tileSize;
		this.g2.drawString("Buy", x, y);
		if (this.commandNum == 0) {
			this.g2.drawString(">", x - 24, y);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 1;
			}
		}
		y += this.gp.tileSize;
		this.g2.drawString("Sell", x, y);
		if (this.commandNum == 1) {
			this.g2.drawString(">", x - 24, y);
			if (this.gp.khl.enterPressed == true) {
				this.subState = 2;
			}
		}
		y += this.gp.tileSize;
		this.g2.drawString("Leave", x, y);
		if (this.commandNum == 2) {
			this.g2.drawString(">", x - 24, y);
			if (this.gp.khl.enterPressed == true) {
				this.commandNum = 0;
				this.npc.startDialogue(this.npc, 1);
			}
		}

	}

	public void trade_sell() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				// DRAW PLAYER INVENTORY
				this.drawInventory(this.gp.boy, true);

				int x;
				int y;
				int width;
				int height;

				// DRAW HINT WINDOW
				x = this.gp.tileSize * 2;
				y = this.gp.tileSize * 9;
				width = this.gp.tileSize * 6;
				height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("[F1] Back", x + 24, y + 60);

				// DRAW PLAYER COIN WINDOW
				x = this.gp.tileSize * 12;
				y = this.gp.tileSize * 9;
				width = this.gp.tileSize * 6;
				height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("Your Coin: " + this.gp.boy.coin, x + 24, y + 60);

				// DRAW PRICE WINDOW
				int itemIndex = this.getItemsIndexOnSlot(this.playerSlotCol, this.playerSlotRow);
				if (itemIndex < this.gp.boy.inventory.size()) {
					x = (int) (this.gp.tileSize * 15.5);
					y = (int) (this.gp.tileSize * 5.5);
					width = (int) (this.gp.tileSize * 2.5);
					height = this.gp.tileSize;
					this.drawSubWindow(x, y, width, height);
					this.g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
					int price = this.gp.boy.inventory.get(itemIndex).price;
					String t = " " + price;
					x = this.getXforAlignedToRightText(t, this.gp.tileSize * 18 - 20);
					this.g2.drawString(t, x, y + 35);

					// SELECT AN ITEM
					if (this.gp.khl.enterPressed == true) {
						if (this.gp.boy.inventory.get(itemIndex) == this.gp.boy.currentWeapon
								|| this.gp.boy.inventory.get(itemIndex) == this.gp.boy.currentShield) {
							this.commandNum = 0;
							this.subState = 0;
							this.npc.startDialogue(this.npc, 4);
						} else {
							if (this.gp.boy.inventory.get(itemIndex).amount > 1) {
								this.gp.boy.inventory.get(itemIndex).amount--;
							} else {
								this.gp.boy.inventory.remove(itemIndex);

							}
							this.gp.boy.coin += price;
						}
					}

				}

			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				// DRAW PLAYER INVENTORY
				this.drawInventory(this.gp.girl, true);

				int x;
				int y;
				int width;
				int height;

				// DRAW HINT WINDOW
				x = this.gp.tileSize * 2;
				y = this.gp.tileSize * 9;
				width = this.gp.tileSize * 6;
				height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("[F1] Back", x + 24, y + 60);

				// DRAW PLAYER COIN WINDOW
				x = this.gp.tileSize * 12;
				y = this.gp.tileSize * 9;
				width = this.gp.tileSize * 6;
				height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("Your Coin: " + this.gp.girl.coin, x + 24, y + 60);

				// DRAW PRICE WINDOW
				int itemIndex = this.getItemsIndexOnSlot(this.playerSlotCol, this.playerSlotRow);
				if (itemIndex < this.gp.girl.inventory.size()) {
					x = (int) (this.gp.tileSize * 15.5);
					y = (int) (this.gp.tileSize * 5.5);
					width = (int) (this.gp.tileSize * 2.5);
					height = this.gp.tileSize;
					this.drawSubWindow(x, y, width, height);
					this.g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
					int price = this.gp.girl.inventory.get(itemIndex).price;
					String t = " " + price;
					x = this.getXforAlignedToRightText(t, this.gp.tileSize * 18 - 20);
					this.g2.drawString(t, x, y + 35);

					// SELECT AN ITEM
					if (this.gp.khl.enterPressed == true) {
						if (this.gp.girl.inventory.get(itemIndex) == this.gp.girl.currentWeapon
								|| this.gp.girl.inventory.get(itemIndex) == this.gp.girl.currentShield) {
							this.commandNum = 0;
							this.subState = 0;
							this.npc.startDialogue(this.npc, 4);
						} else {
							if (this.gp.girl.inventory.get(itemIndex).amount > 1) {
								this.gp.girl.inventory.get(itemIndex).amount--;
							} else {
								this.gp.girl.inventory.remove(itemIndex);

							}
							this.gp.girl.coin += price;
						}
					}

				}

			}
		}
	}

	public void trade_buy() {
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				// DRAW PLAYER INVERTORY
				this.drawInventory(this.gp.boy, false);

				// DRAW NPC INVENTORY
				this.drawInventory(this.npc, true);

				// DRAW HINT WINDOW
				int x = this.gp.tileSize * 2;
				int y = this.gp.tileSize * 9;
				int width = this.gp.tileSize * 6;
				int height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("[F1] Back", x + 24, y + 60);

				// DRAW PLAYER COIN WINDOW
				x = this.gp.tileSize * 12;
				y = this.gp.tileSize * 9;
				width = this.gp.tileSize * 6;
				height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("Your Coin: " + this.gp.boy.coin, x + 24, y + 60);

				// DRAW PRICE WINDOW
				int itemIndex = this.getItemsIndexOnSlot(this.npcSlotCol, this.npcSlotRow);
				if (itemIndex < this.npc.inventory.size()) {
					x = (int) (this.gp.tileSize * 5.5);
					y = (int) (this.gp.tileSize * 5.5);
					width = (int) (this.gp.tileSize * 2.5);
					height = this.gp.tileSize;
					this.drawSubWindow(x, y, width, height);
					this.g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
					int price = this.npc.inventory.get(itemIndex).price;
					String t = " " + price;
					x = this.getXforAlignedToRightText(t, this.gp.tileSize * 8);
					this.g2.drawString(t, x - 22, y + 35);

					// BUY AN ITEM
					if (this.gp.khl.enterPressed == true) {
						if (this.npc.inventory.get(itemIndex).price > this.gp.boy.coin) {
							this.subState = 0;
							this.npc.startDialogue(this.npc, 2);
						} else {
							if (this.gp.boy.canObainItem(this.npc.inventory.get(itemIndex)) == true) {
								this.gp.boy.coin -= this.npc.inventory.get(itemIndex).price;
							} else {
								this.subState = 0;
								this.npc.startDialogue(this.npc, 3);
							}
						}
					}

				}

			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				// DRAW PLAYER INVERTORY
				this.drawInventory(this.gp.girl, false);

				// DRAW NPC INVENTORY
				this.drawInventory(this.npc, true);

				// DRAW HINT WINDOW
				int x = this.gp.tileSize * 2;
				int y = this.gp.tileSize * 9;
				int width = this.gp.tileSize * 6;
				int height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("[F1] Back", x + 24, y + 60);

				// DRAW PLAYER COIN WINDOW
				x = this.gp.tileSize * 12;
				y = this.gp.tileSize * 9;
				width = this.gp.tileSize * 6;
				height = this.gp.tileSize * 2;
				this.drawSubWindow(x, y, width, height);
				this.g2.drawString("Your Coin: " + this.gp.girl.coin, x + 24, y + 60);

				// DRAW PRICE WINDOW
				int itemIndex = this.getItemsIndexOnSlot(this.npcSlotCol, this.npcSlotRow);
				if (itemIndex < this.npc.inventory.size()) {
					x = (int) (this.gp.tileSize * 5.5);
					y = (int) (this.gp.tileSize * 5.5);
					width = (int) (this.gp.tileSize * 2.5);
					height = this.gp.tileSize;
					this.drawSubWindow(x, y, width, height);
					this.g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
					int price = this.npc.inventory.get(itemIndex).price;
					String t = " " + price;
					x = this.getXforAlignedToRightText(t, this.gp.tileSize * 8);
					this.g2.drawString(t, x - 22, y + 35);

					// BUY AN ITEM
					if (this.gp.khl.enterPressed == true) {
						if (this.npc.inventory.get(itemIndex).price > this.gp.girl.coin) {
							subState = 0;
							this.npc.startDialogue(this.npc, 2);
						} else {
							if (this.gp.girl.canObainItem(this.npc.inventory.get(itemIndex)) == true) {
								this.gp.girl.coin -= this.npc.inventory.get(itemIndex).price;
							} else {
								this.subState = 0;
								this.npc.startDialogue(this.npc, 3);
							}
						}
					}

				}

			}
		}
	}

	public int getXforCenteredText(String text) {
		int length = (int) this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		int x = this.gp.screenWidth / 2 - length / 2;
		return x;
	}

	public int getXforAlignedToRightText(String text, int tailX) {
		int length = (int) this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
