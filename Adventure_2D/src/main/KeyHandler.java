package main;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;

	// DEBUG VARIABLES
	public boolean showDebugText = false;
	public boolean godmode = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		// TITLE STATE
		if (this.gp.gameState == this.gp.titleState) {
			this.titleState(code);
		}

		// PLAY STATE
		else if (this.gp.gameState == this.gp.playstate) {
			this.playState(code);
		}

		// PAUSE STATE
		else if (this.gp.gameState == this.gp.pausestate) {
			this.pauseState(code);
		}

		// DIALOGUE STATE
		else if (this.gp.gameState == this.gp.dialogueState || this.gp.gameState == this.gp.cutsceneState) {
			this.dialogueState(code);
		}

		// CHARACTER STATE
		else if (this.gp.gameState == this.gp.CharacterState) {
			this.characterState(code);
		}

		// OPTIONS STATE
		else if (this.gp.gameState == this.gp.optionState) {
			this.optionstate(code);
		}

		// GAME OVER STATE
		else if (this.gp.gameState == this.gp.gameOverState) {
			this.gameoverstate(code);
		}
		// TRADE STATE
		else if (this.gp.gameState == this.gp.tradeState) {
			this.tradeState(code);
		}

		// MAP MINI STATE
		else if (this.gp.gameState == this.gp.mapState) {
			this.mapState(code);
		}

	}

	public void mapState(int code) {
		if (code == KeyEvent.VK_M) {
			this.gp.gameState = this.gp.playstate;
		}
	}

	public void gameoverstate(int code) {
		if (code == KeyEvent.VK_UP) {
			this.gp.ui.commandNum--;
			if (this.gp.ui.commandNum < 0) {
				this.gp.ui.commandNum = 1;
			}
			this.gp.playSE(5);
		}
		if (code == KeyEvent.VK_DOWN) {
			this.gp.ui.commandNum++;
			if (this.gp.ui.commandNum > 1) {
				this.gp.ui.commandNum = 0;
			}
			this.gp.playSE(5);
		}
		if (code == KeyEvent.VK_ENTER) {
			if (this.gp.ui.commandNum == 0) {
				this.gp.gameState = this.gp.playstate;
				this.gp.resetGame(false);
			}
			if (this.gp.ui.commandNum == 1) {
				this.gp.gameState = this.gp.titleState;
				this.gp.ui.titleScreenState = 0;
				this.gp.resetGame(true);
			}
		}
	}

	public void titleState(int code) {
		if (this.gp.ui.titleScreenState == 0) {
			if (code == KeyEvent.VK_UP) {
				this.gp.ui.commandNum--;
				if (this.gp.ui.commandNum < 0) {
					this.gp.ui.commandNum = 2;
				}
				this.gp.playSE(5);
			}
			if (code == KeyEvent.VK_DOWN) {
				this.gp.ui.commandNum++;
				if (this.gp.ui.commandNum > 2) {
					this.gp.ui.commandNum = 0;
				}
				this.gp.playSE(5);
			}
			if (code == KeyEvent.VK_ENTER) {
				if (this.gp.ui.commandNum == 0) {
					this.gp.ui.titleScreenState = 1;
				}
				if (this.gp.ui.commandNum == 1) {
					this.gp.ui.titleScreenState = 2;
				}
				if (this.gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}

		} else if (this.gp.ui.titleScreenState == 1) {
			if (code == KeyEvent.VK_UP) {
				this.gp.ui.commandNum--;
				if (this.gp.ui.commandNum < 0) {
					this.gp.ui.commandNum = 2;
				}
				this.gp.playSE(5);
			}
			if (code == KeyEvent.VK_DOWN) {
				this.gp.ui.commandNum++;
				if (this.gp.ui.commandNum > 2) {
					this.gp.ui.commandNum = 0;
				}
				this.gp.playSE(5);
			}
			if (code == KeyEvent.VK_ENTER) {
				if (this.gp.ui.commandNum == 0) {
					this.gp.gameState = this.gp.playstate;
					this.gp.currentPlayer = this.gp.boy;
					this.gp.boy.type = this.gp.currentPlayer.type_player_boy;

				}
				if (this.gp.ui.commandNum == 1) {
					this.gp.gameState = this.gp.playstate;
					this.gp.currentPlayer = this.gp.girl;
					this.gp.girl.type = this.gp.currentPlayer.type_player_girl;

				}
				if (this.gp.ui.commandNum == 2) {
					this.gp.ui.titleScreenState = 0;
				}
			}
		} else if (this.gp.ui.titleScreenState == 2) {
			if (code == KeyEvent.VK_UP) {
				this.gp.ui.commandNum--;
				if (this.gp.ui.commandNum < 1) {
					this.gp.ui.commandNum = 3;
				}
				this.gp.playSE(5);

			}
			if (code == KeyEvent.VK_DOWN) {
				this.gp.ui.commandNum++;
				if (this.gp.ui.commandNum > 3) {
					this.gp.ui.commandNum = 1;

				}
				this.gp.playSE(5);
			}
			if (code == KeyEvent.VK_ENTER) {
				if (this.gp.ui.commandNum == 1) {
					this.gp.currentPlayer = this.gp.boy;
					this.gp.boy.type = this.gp.currentPlayer.type_player_boy;
					this.gp.saveload.Load();
					this.gp.gameState = this.gp.playstate;

				}
				if (this.gp.ui.commandNum == 2) {
					this.gp.currentPlayer = this.gp.girl;
					this.gp.girl.type = this.gp.currentPlayer.type_player_girl;
					this.gp.saveload.Load();
					this.gp.gameState = this.gp.playstate;

				}
				if (this.gp.ui.commandNum == 3) {
					this.gp.ui.titleScreenState = 0;
				}
			}
		}
	}

	public void playState(int code) {
		if (code == KeyEvent.VK_UP) {
			this.upPressed = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			this.downPressed = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			this.leftPressed = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			this.rightPressed = true;
		}
		if (code == KeyEvent.VK_P) {
			this.gp.gameState = this.gp.pausestate;
		}
		if (code == KeyEvent.VK_C) {
			this.gp.gameState = this.gp.CharacterState;
		}
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}
		if (code == KeyEvent.VK_F) {
			this.shotKeyPressed = true;
			this.gp.playSE(2);
		}
		if (code == KeyEvent.VK_F1) {
			this.gp.gameState = this.gp.optionState;
		}
		if (code == KeyEvent.VK_M) {
			this.gp.gameState = this.gp.mapState;
		}
		if (code == KeyEvent.VK_S) {
			this.spacePressed = true;
		}

		if (code == KeyEvent.VK_X) {
			if (this.gp.map.onMiniMap == false) {
				this.gp.map.onMiniMap = true;
			} else {
				this.gp.map.onMiniMap = false;
			}
		}

		// DEBUG
		if (code == KeyEvent.VK_T) {
			if (this.showDebugText == false) {
				this.showDebugText = true;
			} else {
				this.showDebugText = false;
			}
		}
		if (code == KeyEvent.VK_L) {
			if (this.godmode == false) {
				this.godmode = true;
			} else {
				this.godmode = false;
			}
		}
		if (code == KeyEvent.VK_R) {
			switch (this.gp.currentMap) {
			case 0:
				this.gp.tmg.Loadmap("/map/ground.txt", 0);
				break;
			case 1:
				this.gp.tmg.Loadmap("/map/house.txt", 1);
				break;
			case 2:
				this.gp.tmg.Loadmap("/map/dungeon01.txt", 2);
				break;
			case 3:
				this.gp.tmg.Loadmap("/map/dungeon02.txt", 3);
				break;
			}
		}
	}

	public void pauseState(int code) {
		if (code == KeyEvent.VK_P) {
			this.gp.gameState = this.gp.playstate;
		}
	}

	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}
	}

	public void characterState(int code) {
		if (code == KeyEvent.VK_C) {
			this.gp.gameState = this.gp.playstate;
		}
		if (code == KeyEvent.VK_ENTER) {
			if (this.gp.currentPlayer != null) {
				if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
					this.gp.boy.selectItem();
				}
				if (this.gp.currentPlayer == this.gp.girl
						&& this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
					this.gp.girl.selectItem();
				}
			}
		}
		this.playerInventory(code);
	}

	public void optionstate(int code) {
		if (code == KeyEvent.VK_F1) {
			this.gp.gameState = this.gp.playstate;
		}
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}
		int maxCommandNum = 0;
		switch (this.gp.ui.subState) {
		case 0:
			maxCommandNum = 5;
			break;
		case 3:
			maxCommandNum = 1;
			break;
		}
		if (code == KeyEvent.VK_UP) {
			this.gp.ui.commandNum--;
			if (this.gp.ui.commandNum < 0) {
				this.gp.ui.commandNum = maxCommandNum;
			}
			this.gp.playSE(5);
		}
		if (code == KeyEvent.VK_DOWN) {
			this.gp.ui.commandNum++;
			if (this.gp.ui.commandNum > maxCommandNum) {
				this.gp.ui.commandNum = 0;
			}
			this.gp.playSE(5);
		}
		if (code == KeyEvent.VK_LEFT) {
			if (this.gp.ui.subState == 0) {
				if (this.gp.ui.commandNum == 1 && this.gp.music.volumeScale > 0) {
					this.gp.music.volumeScale--;
					this.gp.music.checkVolume();
					this.gp.playSE(5);
				}
				if (this.gp.ui.commandNum == 2 && this.gp.s.volumeScale > 0) {
					this.gp.s.volumeScale--;
					this.gp.playSE(5);
				}
			}
		}
		if (code == KeyEvent.VK_RIGHT) {
			if (this.gp.ui.subState == 0) {
				if (this.gp.ui.commandNum == 1 && this.gp.music.volumeScale < 5) {
					this.gp.music.volumeScale++;
					this.gp.music.checkVolume();
					this.gp.playSE(5);

				}
				if (this.gp.ui.commandNum == 2 && this.gp.s.volumeScale < 5) {
					this.gp.s.volumeScale++;
					this.gp.playSE(5);
				}
			}
		}
	}

	public void tradeState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = true;
		}

		if (this.gp.ui.subState == 0) {
			if (code == KeyEvent.VK_UP) {
				this.gp.ui.commandNum--;
				if (this.gp.ui.commandNum < 0) {
					this.gp.ui.commandNum = 2;
				}
				this.gp.playSE(5);
			}
			if (code == KeyEvent.VK_DOWN) {
				this.gp.ui.commandNum++;
				if (this.gp.ui.commandNum > 2) {
					this.gp.ui.commandNum = 0;
				}
				this.gp.playSE(5);
			}
		}
		if (this.gp.ui.subState == 1) {
			this.npcInventory(code);
			if (code == KeyEvent.VK_F1) {
				this.gp.ui.subState = 0;
			}
		}
		if (this.gp.ui.subState == 2) {
			this.playerInventory(code);
			if (code == KeyEvent.VK_F1) {
				this.gp.ui.subState = 0;
			}
		}
	}

	public void playerInventory(int code) {
		if (code == KeyEvent.VK_UP) {
			if (this.gp.ui.playerSlotRow != 0) {
				this.gp.ui.playerSlotRow--;
				this.gp.playSE(5);
			}
		}
		if (code == KeyEvent.VK_DOWN) {
			if (this.gp.ui.playerSlotRow != 3) {
				this.gp.ui.playerSlotRow++;
				this.gp.playSE(5);

			}
		}
		if (code == KeyEvent.VK_LEFT) {
			if (this.gp.ui.playerSlotCol != 0) {
				this.gp.ui.playerSlotCol--;
				this.gp.playSE(5);
			}
		}
		if (code == KeyEvent.VK_RIGHT) {
			if (this.gp.ui.playerSlotCol != 4) {
				this.gp.ui.playerSlotCol++;
				this.gp.playSE(5);
			}
		}
	}

	public void npcInventory(int code) {
		if (code == KeyEvent.VK_UP) {
			if (this.gp.ui.npcSlotRow != 0) {
				this.gp.ui.npcSlotRow--;
				this.gp.playSE(5);

			}
		}
		if (code == KeyEvent.VK_DOWN) {
			if (this.gp.ui.npcSlotRow != 3) {
				this.gp.ui.npcSlotRow++;
				this.gp.playSE(5);

			}
		}
		if (code == KeyEvent.VK_LEFT) {
			if (this.gp.ui.npcSlotCol != 0) {
				this.gp.ui.npcSlotCol--;
				this.gp.playSE(5);

			}
		}
		if (code == KeyEvent.VK_RIGHT) {
			if (this.gp.ui.npcSlotCol != 4) {
				this.gp.ui.npcSlotCol++;
				this.gp.playSE(5);

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			this.upPressed = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			this.downPressed = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			this.leftPressed = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			this.rightPressed = false;
		}
		if (code == KeyEvent.VK_F) {
			this.shotKeyPressed = false;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (code == KeyEvent.VK_ENTER) {
			this.enterPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			this.spacePressed = false;
		}

	}
}
