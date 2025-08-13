package entity;

import main.KeyHandler;

import objects.OBJFireBall;
import objects.OBJNormalShield;
import objects.OBJNormalSword;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class GirlPlayer extends Entity {
	public KeyHandler khl;
	public int screenX, screenY;
	public int Standcounter = 0;
	public boolean attackCancel = false;
	public boolean lightUpdated = false;

	public GirlPlayer(GamePanel gp, KeyHandler khl) {
		super(gp);

		this.khl = khl;
		this.type = this.type_player_girl;
		this.screenX = this.gp.screenWidth / 2 - this.gp.tileSize / 2;
		this.screenY = this.gp.screenHeight / 2 - this.gp.tileSize / 2;

		// SOLID AREA
		this.solidArea = new Rectangle();
		this.solidArea.x = 8;
		this.solidArea.y = 16;
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
		this.solidArea.height = 32;
		this.solidArea.width = 32;

		this.SetDefaultValue();

	}

	public void SetDefaultValue() {
		this.worldX = this.gp.tileSize * 23;
		this.worldY = this.gp.tileSize * 21;
//				worldX = this.gp.tileSize * 12;
//				worldY = this.gp.tileSize * 12;
//				this.gp.currentMap = 1;
		this.DefaultSpeed = 4;
		this.speed = this.DefaultSpeed;
		this.direction = "down";

		// PLAYER STATUS
		this.ammo = 10;
		this.level = 1;
		this.maxLife = 6;
		this.life = this.maxLife;
		this.strength = 1; // THE MORE STRENGTH YOU HAVE, THE MORE DAMAGE YOU GIVE.
		this.dex = 1; // THE MORE DEX YOU HAVE, THE LESS DAMAGE YOU RECEIVE.
		this.exp = 0;
		this.Maxmana = 4;
		this.mana = this.Maxmana;
		this.nextLVexp = 5;
		this.coin = 500;

		this.currentWeapon = new OBJNormalSword(this.gp);
		this.currentShield = new OBJNormalShield(this.gp);
		this.currentLight = null;
		this.projecttile = new OBJFireBall(this.gp);
		this.attack = getAttack(); // THE TOTAL ATTACK VALUE IS DECIDED BY STRENGTH AND WEAPON
		this.def = getDefense(); // THE TOTAL DEFVALUE VALUE IS DECIDED BY DEXTERITY AND SHIELD
		this.getGirlImage();
		this.getGirlAttackImage();
		this.setItem();
		this.getGirlGuardImage();
		this.setDialogue();
	}

	public void getGirlImage() {
		this.up1 = this.Setup("/player/dichuyen/girl/girl_up_1", this.gp.tileSize, this.gp.tileSize);
		this.up2 = this.Setup("/player/dichuyen/girl/girl_up_2", this.gp.tileSize, this.gp.tileSize);
		this.down1 = this.Setup("/player/dichuyen/girl/girl_down_1", this.gp.tileSize, this.gp.tileSize);
		this.down2 = this.Setup("/player/dichuyen/girl/girl_down_2", this.gp.tileSize, this.gp.tileSize);
		this.left1 = this.Setup("/player/dichuyen/girl/girl_left_1", this.gp.tileSize, this.gp.tileSize);
		this.left2 = this.Setup("/player/dichuyen/girl/girl_left_2", this.gp.tileSize, this.gp.tileSize);
		this.right1 = this.Setup("/player/dichuyen/girl/girl_right_1", this.gp.tileSize, this.gp.tileSize);
		this.right2 = this.Setup("/player/dichuyen/girl/girl_right_2", this.gp.tileSize, this.gp.tileSize);
	}

	public void getSleepingImage(BufferedImage img) {
		this.up1 = img;
		this.up2 = img;
		this.down1 = img;
		this.down2 = img;
		this.left1 = img;
		this.left2 = img;
		this.right1 = img;
		this.right2 = img;

	}

	public void setItem() {
		this.inventory.clear();
		this.inventory.add(this.currentWeapon);
		this.inventory.add(this.currentShield);
	}

	public int getAttack() {
		this.attackArea = this.currentWeapon.attackArea;
		this.motion1_duration = this.currentWeapon.motion1_duration;
		this.motion2_duration = this.currentWeapon.motion2_duration;
		return this.attack = this.strength * this.currentWeapon.attackValue;
	}

	public int getDefense() {
		return this.def = this.strength * this.currentShield.defValue;
	}

	public int getCurrentWeaponSlot() {
		int currentweaponslot = 0;
		for (int i = 0; i < this.inventory.size(); i++) {
			if (this.inventory.get(i) == this.currentWeapon) {
				currentweaponslot = i;
			}
		}
		return currentweaponslot;
	}

	public int getCurrentShieldSlot() {
		int currentshieldslot = 0;
		for (int i = 0; i < this.inventory.size(); i++) {
			if (this.inventory.get(i) == this.currentShield) {
				currentshieldslot = i;
			}
		}
		return currentshieldslot;
	}

	public int searchIteminventory(String itemName) {
		int itemIndex = 999;
		for (int i = 0; i < this.inventory.size(); i++) {
			if (this.inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}

	public boolean canObainItem(Entity item) {
		boolean canObtain = false;
		Entity newitem = this.gp.egenerator.getObject(item.name);

		// CHECK IF STACKABLE
		if (newitem.stackable == true) {
			int index = searchIteminventory(newitem.name);
			if (index != 999) {
				this.inventory.get(index).amount++;
				canObtain = true;
			} else {
				// NEW ITEM SO NEED TO CHECK VACANCY
				if (this.inventory.size() != this.ivtrSizeMax) {
					this.inventory.add(newitem);
					canObtain = true;
				}
			}
		} else {
			// NOT STACKABLE SO CHECK VACANCY
			if (this.inventory.size() != this.ivtrSizeMax) {
				this.inventory.add(newitem);
				canObtain = true;
			}
		}
		return canObtain;
	}

	public void getGirlAttackImage() {
		if (this.currentWeapon.type == this.type_normal_sword) {
			this.attackUp1 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/sword/normal/girl_normal_sword_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_golden_sword) {
			this.attackUp1 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/sword/golden/girl_golden_sword_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_diamond_sword) {
			this.attackUp1 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_right_1",
					this.gp.tileSize * 2, this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/sword/diamond/girl_diamond_sword_right_2",
					this.gp.tileSize * 2, this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_normal_axe) {
			this.attackUp1 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/axe/normal/girl_normal_axe_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_golden_axe) {
			this.attackUp1 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/axe/golden/girl_golden_axe_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_diamond_axe) {
			this.attackUp1 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/axe/diamond/girl_diamond_axe_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_normal_knife) {
			this.attackUp1 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/knife/normal/girl_normal_knife_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_golden_knife) {
			this.attackUp1 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/knife/golden/girl_golden_knife_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_diamond_knife) {
			this.attackUp1 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_up_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_up_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_right_1",
					this.gp.tileSize * 2, this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/knife/diamond/girl_diamond_knife_right_2",
					this.gp.tileSize * 2, this.gp.tileSize);
		}
		if (this.currentWeapon.type == this.type_pickaxe) {
			this.attackUp1 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_up_1", this.gp.tileSize, this.gp.tileSize * 2);
			this.attackUp2 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_up_2", this.gp.tileSize, this.gp.tileSize * 2);
			this.attackDown1 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_down_1", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackDown2 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_down_2", this.gp.tileSize,
					this.gp.tileSize * 2);
			this.attackLeft1 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_left_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackleft2 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_left_2", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight1 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_right_1", this.gp.tileSize * 2,
					this.gp.tileSize);
			this.attackRight2 = this.Setup("/player/tancong/girl/axe/normal/girl_pick_right_2", this.gp.tileSize * 2,
					this.gp.tileSize);
		}
	}

	public void getGirlGuardImage() {
		if (this.currentShield.type == this.type_normal_shield) {
			this.guardUp = this.Setup("/player/phongthu/girl/normal/girl_normal_guard_up", this.gp.tileSize, this.gp.tileSize);
			this.guardDown = this.Setup("/player/phongthu/girl/normal/girl_normal_guard_down", this.gp.tileSize, this.gp.tileSize);
			this.guardLeft = this.Setup("/player/phongthu/girl/normal/girl_normal_guard_left", this.gp.tileSize, this.gp.tileSize);
			this.guardRight = this.Setup("/player/phongthu/girl/normal/girl_normal_guard_right", this.gp.tileSize,
					this.gp.tileSize);
		}
		if (this.currentShield.type == this.type_golden_shield) {
			this.guardUp = this.Setup("/player/phongthu/girl/golden/girl_golden_guard_up", this.gp.tileSize, this.gp.tileSize);
			this.guardDown = this.Setup("/player/phongthu/girl/golden/girl_golden_guard_down", this.gp.tileSize, this.gp.tileSize);
			this.guardLeft = this.Setup("/player/phongthu/girl/golden/girl_golden_guard_left", this.gp.tileSize, this.gp.tileSize);
			this.guardRight = this.Setup("/player/phongthu/girl/golden/girl_golden_guard_right", this.gp.tileSize,
					this.gp.tileSize);
		}
		if (this.currentShield.type == this.type_diamond_shield) {
			this.guardUp = this.Setup("/player/phongthu/girl/diamond/girl_diamond_guard_up", this.gp.tileSize, this.gp.tileSize);
			this.guardDown = this.Setup("/player/phongthu/girl/diamond/girl_diamond_guard_down", this.gp.tileSize,
					this.gp.tileSize);
			this.guardLeft = this.Setup("/player/phongthu/girl/diamond/girl_diamond_guard_left", this.gp.tileSize,
					this.gp.tileSize);
			this.guardRight = this.Setup("/player/phongthu/girl/diamond/girl_diamond_guard_right", this.gp.tileSize,
					this.gp.tileSize);
		}
		if (this.currentShield.type == this.type_premium_shield) {
			this.guardUp = this.Setup("/player/phongthu/girl/premium/girl_premium_guard_up", this.gp.tileSize, this.gp.tileSize);
			this.guardDown = this.Setup("/player/phongthu/girl/premium/girl_premium_guard_down", this.gp.tileSize,
					this.gp.tileSize);
			this.guardLeft = this.Setup("/player/phongthu/girl/premium/girl_premium_guard_left", this.gp.tileSize,
					this.gp.tileSize);
			this.guardRight = this.Setup("/player/phongthu/girl/premium/girl_premium_guard_right", this.gp.tileSize,
					this.gp.tileSize);
		}
	}

	@Override
	public void Update() {
		if (this.knockBack == true) {
			this.collisionOn = false;
			this.gp.ccheck.Checktile(this);
			this.gp.ccheck.CheckObject(this, true);
			this.gp.ccheck.checkEntity(this, this.gp.npc);
			this.gp.ccheck.checkEntity(this, this.gp.mons);
			this.gp.ccheck.checkEntity(this, this.gp.iTile);

			if (this.collisionOn == true) {
				this.knockbackCounter = 0;
				this.knockBack = false;
				this.speed = this.DefaultSpeed;
			} else {
				switch (this.knockbackDirection) {
				case "up":
					this.worldY -= this.speed;
					break;

				case "down":
					this.worldY += this.speed;
					break;

				case "left":
					this.worldX -= this.speed;
					break;

				case "right":
					this.worldX += this.speed;
					break;

				}
			}
			this.knockbackCounter++;
			if (this.knockbackCounter == 10) {
				this.knockbackCounter = 0;
				this.knockBack = false;
				this.speed = this.DefaultSpeed;
			}
		}
		else if (this.attacking == true) {
			this.Attacking();
		}
		else if (this.khl.spacePressed == true) {
			this.guarding = true;
			this.guardCounter++;
		}
		else if (this.khl.upPressed == true || this.khl.downPressed == true || this.khl.leftPressed == true || this.khl.rightPressed == true
				|| this.khl.enterPressed == true) {
			if (this.khl.upPressed == true) {
				this.direction = "up";
			}
			else if (this.khl.downPressed == true) {
				this.direction = "down";
			}
			else if (this.khl.leftPressed == true) {
				this.direction = "left";
			}
			else if (this.khl.rightPressed == true) {
				this.direction = "right";
			}

			// CHECK TILE COLLISION
			this.collisionOn = false;
			this.gp.ccheck.Checktile(this);

			// CHECK OBJECT COLLISION
			int ObjIndex = this.gp.ccheck.CheckObject(this, true);
			this.pickUpObject(ObjIndex);

			// CHECK NPC COLLISION
			int npcIndex = this.gp.ccheck.checkEntity(this, this.gp.npc);
			this.interactNPC(npcIndex);

			// CHECK MONSTER COLLISION
			int monsterIndex = this.gp.ccheck.checkEntity(this, this.gp.mons);
			this.contactMonster(monsterIndex);

			// CHECK INTERACTIVE TILE COLLISION
			this.gp.ccheck.checkEntity(this, this.gp.iTile);

			// CHECK EVENT
			this.gp.evl.checkEvent();

			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (collisionOn == false && this.khl.enterPressed == false) {
				switch (this.direction) {
				case "up":
					this.worldY -= this.speed;
					break;

				case "down":
					this.worldY += this.speed;
					break;

				case "left":
					this.worldX -= this.speed;
					break;

				case "right":
					this.worldX += this.speed;
					break;
				}
			}
			if (this.khl.enterPressed == true && this.attackCancel == false) {
				this.attacking = true;
				this.spriteCounter = 0;
			}

			this.attackCancel = false;
			this.khl.enterPressed = false;
			this.guarding = false;
			this.guardCounter = 0;

			this.spriteCounter++;
			if (this.spriteCounter > 12) {
				if (this.spriteNum == 1) {
					this.spriteNum = 2;
				} else if (this.spriteNum == 2) {
					this.spriteNum = 1;
				}
				this.spriteCounter = 0;
			}
		} else {
			this.Standcounter++;
			if (this.Standcounter == 20) {
				this.spriteNum = 1;
				this.Standcounter = 0;
			}
			this.guarding = false;
			this.guardCounter = 0;
		}

		if (this.gp.khl.shotKeyPressed == true && this.projecttile.alive == false && this.shotAvailableCounter == 30
				&& this.projecttile.haveResources(this) == true) {

			// SET DEFAULT COORDINATES, DIRECTION AND USER
			this.projecttile.Set(this.worldX, this.worldY, this.direction, true, this);

			// SUBTRACT THE COST (MANA, ANMO ETC.)
			this.projecttile.subtractResources(this);

			// CHECK VACANCY
			for (int i = 0; i < this.gp.projectTile[1].length; i++) {
				if (this.gp.projectTile[this.gp.currentMap][i] == null) {
					this.gp.projectTile[this.gp.currentMap][i] = this.projecttile;
					break;
				}
			}
			this.shotAvailableCounter = 0;
		}

		// THIS NEEDS TO BE OUTSIDE OF KEY IF STATEMENT!
		if (this.invincible == true) {
			this.invincibleCount++;
			if (this.invincibleCount > 60) {
				this.invincible = false;
				this.invincibleCount = 0;
				this.transparent = false;
			}
		}

		if (this.shotAvailableCounter < 30) {
			this.shotAvailableCounter++;
		}
		if (this.life > this.maxLife) {
			this.life = this.maxLife;
		}
		if (this.mana > this.Maxmana) {
			this.mana = this.Maxmana;
		}
		if (this.khl.godmode == false) {
			if (this.life <= 0) {
				this.gp.gameState = this.gp.gameOverState;
				this.gp.ui.commandNum = -1;
				this.gp.playSE(11);
			}
		}

	}

	public void DamageProJectTile(int i) {
		if (i != 999) {
			Entity projectile = this.gp.projectTile[this.gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}

	public void DamageInteractiveTile(int i) {
		if (i != 999 && this.gp.iTile[this.gp.currentMap][i].destructible == true
				&& this.gp.iTile[this.gp.currentMap][i].isCorrectItem(this) == true
				&& this.gp.iTile[this.gp.currentMap][i].invincible == false) {
			this.gp.iTile[this.gp.currentMap][i].playSe();
			this.gp.iTile[this.gp.currentMap][i].life--;
			this.gp.iTile[this.gp.currentMap][i].invincible = true;

			// GENERATE PARTICLE
			this.generateParticle(this.gp.iTile[this.gp.currentMap][i], this.gp.iTile[this.gp.currentMap][i]);

			if (this.gp.iTile[this.gp.currentMap][i].life == 0) {
				this.gp.iTile[this.gp.currentMap][i] = this.gp.iTile[this.gp.currentMap][i].getDestroyedForm();
			}
		}
	}

	public void DamageMonster(int i, Entity attacker, int attack, int knockBackPower) {
		if (i != 999) {
			if (this.gp.mons[this.gp.currentMap][i].invincible == false) {
				if (knockBackPower > 0) {
					this.setKnockBack(this.gp.mons[this.gp.currentMap][i], attacker, knockBackPower);
				}
				if (this.gp.mons[this.gp.currentMap][i].offBalance == true) {
					attack *= 5;

				}

				int damage = attack - this.gp.mons[this.gp.currentMap][i].def;
				if (damage < 0) {
					damage = 0;
				}
				this.gp.mons[this.gp.currentMap][i].life -= damage;
				this.gp.playSE(12);
				this.gp.ui.Addmessage(damage + " damage!");
				this.gp.mons[this.gp.currentMap][i].invincible = true;
				this.gp.mons[this.gp.currentMap][i].DamageReaction();
				if (this.gp.mons[this.gp.currentMap][i].life <= 0) {
					this.gp.mons[this.gp.currentMap][i].dead = true;
					this.gp.ui.Addmessage("Killed the " + this.gp.mons[this.gp.currentMap][i].name + "! ");
					this.gp.ui.Addmessage(this.gp.mons[this.gp.currentMap][i].exp + " exp!");
					exp += this.gp.mons[this.gp.currentMap][i].exp;
					this.gp.playSE(17);
					this.CheckLvUp();
				}
			}
		}
	}

	public void CheckLvUp() {
		if (this.exp >= this.nextLVexp) {
			this.gp.playSE(13);
			this.level++;
			this.nextLVexp = this.nextLVexp * 2;
			this.maxLife += 2;
			this.Maxmana += 2;
			this.strength++;
			this.dex++;
			this.attack = this.getAttack();
			this.def = this.getDefense();
			this.setDialogue();
			this.startDialogue(this, 0);

		}
	}

	public void contactMonster(int i) {
		if (i != 999) {
			if (this.invincible == false && this.gp.mons[this.gp.currentMap][i].dead == false) {
				int dmg = this.gp.mons[this.gp.currentMap][i].attack - this.def;
				if (dmg < 1) {
					dmg = 1;
				}
				this.life -= dmg;
				this.invincible = true;
				this.transparent = true;
			}
		}
	}

	public void interactNPC(int i) {
		if (i != 999) {
			if (this.gp.khl.enterPressed == true) {
				this.attackCancel = true;
				this.gp.npc[this.gp.currentMap][i].Speak();
			}

			this.gp.npc[this.gp.currentMap][i].move(this.direction);
		}
	}

	public void pickUpObject(int i) {
		if (i != 999) {

			// PICK UP ONLY ITEMS
			if (this.gp.obj[this.gp.currentMap][i].type == this.type_pickupOnly) {
				this.gp.obj[this.gp.currentMap][i].use(this);
				this.gp.obj[this.gp.currentMap][i] = null;
				this.gp.playSE(4);
			}

			// OBJTACLE
			else if (this.gp.obj[this.gp.currentMap][i].type == this.type_obstacle) {
				if (this.khl.enterPressed == true) {
					this.attackCancel = true;
					this.gp.obj[this.gp.currentMap][i].Interact();
				}
			}

			// INVERTORY ITEMS
			else {
				String t;
				this.collisionOn = true;
				this.gp.playSE(4);
				if (this.canObainItem(this.gp.obj[this.gp.currentMap][i]) == true) {
					t = "Got a " + this.gp.obj[this.gp.currentMap][i].name + "!";
				} else {
					t = "You can't carry any more!";
				}
				this.gp.ui.Addmessage(t);
				this.gp.obj[this.gp.currentMap][i] = null;
			}
		}

	}

	public void draw(Graphics2D g2) {

		BufferedImage img = null;
		int TemScreenX = screenX;
		int TemScreenY = screenY;
		switch (this.direction) {
		case "up":
			if (this.attacking == false) {
				if (this.spriteNum == 1) {
					img = this.up1;
				}
				if (this.spriteNum == 2) {
					img = this.up2;
				}
			}
			if (this.attacking == true) {
				TemScreenY = this.screenY - this.gp.tileSize;
				if (this.spriteNum == 1) {
					img = this.attackUp1;
				}
				if (this.spriteNum == 2) {
					img = this.attackUp2;
				}
			}
			if (this.guarding == true) {
				img = this.guardUp;
			}
			break;
		case "down":
			if (this.attacking == false) {
				if (this.spriteNum == 1) {
					img = this.down1;
				}
				if (this.spriteNum == 2) {
					img = this.down2;
				}
			}
			if (this.attacking == true) {
				if (this.spriteNum == 1) {
					img = this.attackDown1;
				}
				if (this.spriteNum == 2) {
					img = this.attackDown2;
				}
			}
			if (this.guarding == true) {
				img = this.guardDown;
			}
			break;
		case "left":
			if (this.attacking == false) {
				if (this.spriteNum == 1) {
					img = this.left1;
				}
				if (this.spriteNum == 2) {
					img = this.left2;
				}
			}
			if (this.attacking == true) {
				TemScreenX = this.screenX - this.gp.tileSize;
				if (this.spriteNum == 1) {
					img = this.attackLeft1;
				}
				if (this.spriteNum == 2) {
					img = this.attackleft2;
				}
			}
			if (this.guarding == true) {
				img = this.guardLeft;
			}
			break;
		case "right":
			if (this.attacking == false) {
				if (this.spriteNum == 1) {
					img = this.right1;
				}
				if (this.spriteNum == 2) {
					img = this.right2;
				}
			}
			if (this.attacking == true) {
				if (this.spriteNum == 1) {
					img = this.attackRight1;
				}
				if (this.spriteNum == 2) {
					img = this.attackRight2;
				}
			}
			if (this.guarding == true) {
				img = this.guardRight;
			}
			break;
		}
		if (this.transparent == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		if (this.Drawing == true) {
			g2.drawImage(img, TemScreenX, TemScreenY, null);

		}
		// RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}

	public void setDefaultPositions() {
		this.gp.currentMap = 0;
		this.worldX = this.gp.tileSize * 23;
		this.worldY = this.gp.tileSize * 21;
		this.direction = "down";

	}

	public void setDialogue() {
		this.dialogues[0][0] = "You are level: " + level + " now !";
	}

	public void RestoreStatus() {
		this.speed = this.DefaultSpeed;
		this.life = this.maxLife;
		this.mana = this.Maxmana;
		this.invincible = false;
		this.transparent = false;
		this.attacking = false;
		this.guarding = false;
		this.knockBack = false;
		this.lightUpdated = true;
	}

	public void selectItem() {
		int itemIndex = this.gp.ui.getItemsIndexOnSlot(this.gp.ui.playerSlotCol, this.gp.ui.playerSlotRow);
		if (itemIndex < this.inventory.size()) {
			Entity selectedItem = this.inventory.get(itemIndex);
			if (selectedItem.type == this.type_normal_sword || selectedItem.type == this.type_golden_sword
					|| selectedItem.type == this.type_diamond_sword || selectedItem.type == this.type_normal_axe
					|| selectedItem.type == this.type_golden_axe || selectedItem.type == this.type_diamond_axe
					|| selectedItem.type == this.type_normal_knife || selectedItem.type == this.type_golden_knife
					|| selectedItem.type == this.type_diamond_knife || selectedItem.type == this.type_pickaxe) {
				this.currentWeapon = selectedItem;
				this.attack = this.getAttack();
				this.getGirlAttackImage();
				this.gp.playSE(16);

			}
			if (selectedItem.type == this.type_normal_shield || selectedItem.type == this.type_golden_shield
					|| selectedItem.type == this.type_diamond_shield || selectedItem.type == this.type_premium_shield) {
				this.currentShield = selectedItem;
				this.def = this.getDefense();
				this.getGirlGuardImage();
			}
			if (selectedItem.type == this.type_light) {
				this.gp.playSE(16);
				if (this.currentLight == selectedItem) {
					this.currentLight = null;
				} else {
					this.currentLight = selectedItem;
				}
				this.lightUpdated = true;
			}
			if (selectedItem.type == this.type_consumable) {
				if (selectedItem.use(this) == true) {
					if (selectedItem.amount > 1) {
						selectedItem.amount--;
					} else {
						this.inventory.remove(itemIndex);
					}

				}
			}
		}
	}

}
