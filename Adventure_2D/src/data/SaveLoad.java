package data;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;

public class SaveLoad {
	public GamePanel gp;

	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}

	public void Save() {
		try {
			if (this.gp.currentPlayer != null) {
				if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("saveboy.dat")));
					DataStorage ds = new DataStorage();
					// PLAYER BOY STATUS
					ds.Level = this.gp.boy.level;
					ds.maxLife = this.gp.boy.maxLife;
					ds.Life = this.gp.boy.life;
					ds.Mana = this.gp.boy.mana;
					ds.strength = this.gp.boy.strength;
					ds.Dexterity = this.gp.boy.dex;
					ds.Exp = this.gp.boy.exp;
					ds.nextLevelExp = this.gp.boy.nextLVexp;
					ds.coin = this.gp.boy.coin;
					ds.maxMana = this.gp.boy.Maxmana;

					// PLAYER INVENTORY
					for (int i = 0; i < this.gp.boy.inventory.size(); i++) {
						ds.itemNames.add(this.gp.boy.inventory.get(i).name);
						ds.itemAmounts.add(this.gp.boy.inventory.get(i).amount);

					}

					// PLAYER EQUIPMENT
					ds.currentWeaponSlot = this.gp.boy.getCurrentWeaponSlot();
					ds.currentShieldSlot = this.gp.boy.getCurrentShieldSlot();

					// OBJS ON MAP
					ds.mapObjNames = new String[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJWorldX = new int[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJWorldY = new int[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJLootnames = new String[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJOpened = new boolean[this.gp.maxMap][this.gp.obj[1].length];
					for (int j = 0; j < this.gp.maxMap; j++) {
						for (int k = 0; k < this.gp.obj[1].length; k++) {
							if (this.gp.obj[j][k] == null) {
								ds.mapObjNames[j][k] = "NA";
							} else {
								ds.mapObjNames[j][k] = this.gp.obj[j][k].name;
								ds.mapOBJWorldX[j][k] = this.gp.obj[j][k].worldX;
								ds.mapOBJWorldY[j][k] = this.gp.obj[j][k].worldY;
								if (this.gp.obj[j][k].loot != null) {
									ds.mapOBJLootnames[j][k] = this.gp.obj[j][k].loot.name;

								}
								ds.mapOBJOpened[j][k] = this.gp.obj[j][k].opened;

							}
						}
					}
					// WRITE THE DATASTORAGE OBJECT
					oos.writeObject(ds);
					oos.close();
				}
				if (this.gp.currentPlayer == this.gp.girl
						&& this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("savegirl.dat")));
					DataStorage ds = new DataStorage();
					// PLAYER BOY STATUS
					ds.Level = this.gp.girl.level;
					ds.maxLife = this.gp.girl.maxLife;
					ds.Life = this.gp.girl.life;
					ds.Mana = this.gp.girl.mana;
					ds.strength = this.gp.girl.strength;
					ds.Dexterity = this.gp.girl.dex;
					ds.Exp = this.gp.girl.exp;
					ds.nextLevelExp = this.gp.girl.nextLVexp;
					ds.coin = this.gp.girl.coin;
					ds.maxMana = this.gp.girl.Maxmana;

					// PLAYER INVENTORY
					for (int i = 0; i < this.gp.girl.inventory.size(); i++) {
						ds.itemNames.add(this.gp.girl.inventory.get(i).name);
						ds.itemAmounts.add(this.gp.girl.inventory.get(i).amount);

					}

					// PLAYER EQUIPMENT
					ds.currentWeaponSlot = this.gp.girl.getCurrentWeaponSlot();
					ds.currentShieldSlot = this.gp.girl.getCurrentShieldSlot();

					// OBJS ON MAP
					ds.mapObjNames = new String[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJWorldX = new int[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJWorldY = new int[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJLootnames = new String[this.gp.maxMap][this.gp.obj[1].length];
					ds.mapOBJOpened = new boolean[this.gp.maxMap][this.gp.obj[1].length];
					for (int j = 0; j < this.gp.maxMap; j++) {
						for (int k = 0; k < this.gp.obj[1].length; k++) {
							if (this.gp.obj[j][k] == null) {
								ds.mapObjNames[j][k] = "NA";
							} else {
								ds.mapObjNames[j][k] = this.gp.obj[j][k].name;
								ds.mapOBJWorldX[j][k] = this.gp.obj[j][k].worldX;
								ds.mapOBJWorldY[j][k] = this.gp.obj[j][k].worldY;
								if (this.gp.obj[j][k].loot != null) {
									ds.mapOBJLootnames[j][k] = this.gp.obj[j][k].loot.name;

								}
								ds.mapOBJOpened[j][k] = this.gp.obj[j][k].opened;

							}
						}
					}
					// WRITE THE DATASTORAGE OBJECT
					oos.writeObject(ds);
					oos.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void Load() {
		try {
			if (this.gp.currentPlayer != null) {
				if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("saveboy.dat")));
					// READ THE DATASTORAGE OBJECT
					DataStorage ds = (DataStorage) (ois.readObject());
					this.gp.boy.level = ds.Level;
					this.gp.boy.maxLife = ds.maxLife;
					this.gp.boy.life = ds.Life;
					this.gp.boy.mana = ds.Mana;
					this.gp.boy.Maxmana = ds.maxMana;
					this.gp.boy.coin = ds.coin;
					this.gp.boy.strength = ds.strength;
					this.gp.boy.dex = ds.Dexterity;
					this.gp.boy.exp = ds.Exp;
					this.gp.boy.nextLVexp = ds.nextLevelExp;

					// PLAYER INVENTORY
					this.gp.boy.inventory.clear();
					for (int i = 0; i < ds.itemNames.size(); i++) {
						this.gp.boy.inventory.add(this.gp.egenerator.getObject(ds.itemNames.get(i)));
						this.gp.boy.inventory.get(i).amount = ds.itemAmounts.get(i);
					}

					// PLAYER EQUIPMENT
					this.gp.boy.currentWeapon = this.gp.boy.inventory.get(ds.currentWeaponSlot);
					this.gp.boy.currentShield = this.gp.boy.inventory.get(ds.currentShieldSlot);
					this.gp.boy.getAttack();
					this.gp.boy.getDefense();
					this.gp.boy.getBoyAttackImage();

					// OBJS ON MAP
					if (this.gp.obj == null) {
						this.gp.obj = new Entity[this.gp.maxMap][this.gp.obj[1].length];
					}
					for (int j = 0; j < this.gp.maxMap; j++) {
						for (int k = 0; k < this.gp.obj[1].length; k++) {
							if (ds.mapObjNames[j][k].equals("NA")) {
								this.gp.obj[j][k] = null;
							} else {
								if (this.gp.obj[j][k] == null) {
									this.gp.obj[j][k] = new Entity(this.gp);
									this.gp.obj[j][k] = this.gp.egenerator.getObject(ds.mapObjNames[j][k]);
									this.gp.obj[j][k].worldX = ds.mapOBJWorldX[j][k];
									this.gp.obj[j][k].worldY = ds.mapOBJWorldY[j][k];
									if (ds.mapOBJLootnames[j][k] != null) {
										this.gp.obj[j][k].loot.setLoot(this.gp.egenerator.getObject(ds.mapOBJLootnames[j][k]));
									}
									this.gp.obj[j][k].opened = ds.mapOBJOpened[j][k];
									if (this.gp.obj[j][k].opened == true) {
										this.gp.obj[j][k].down1 = this.gp.obj[j][k].img2;
									}
								}
							}
						}
					}
					ois.close();
				}
				if (this.gp.currentPlayer == this.gp.girl
						&& this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("savegirl.dat")));
					// READ THE DATASTORAGE OBJECT
					DataStorage ds = (DataStorage) (ois.readObject());
					this.gp.girl.level = ds.Level;
					this.gp.girl.maxLife = ds.maxLife;
					this.gp.girl.life = ds.Life;
					this.gp.girl.mana = ds.Mana;
					this.gp.girl.Maxmana = ds.maxMana;
					this.gp.girl.coin = ds.coin;
					this.gp.girl.strength = ds.strength;
					this.gp.girl.dex = ds.Dexterity;
					this.gp.girl.exp = ds.Exp;
					this.gp.girl.nextLVexp = ds.nextLevelExp;

					// PLAYER INVENTORY
					this.gp.girl.inventory.clear();
					for (int i = 0; i < ds.itemNames.size(); i++) {
						this.gp.girl.inventory.add(this.gp.egenerator.getObject(ds.itemNames.get(i)));
						this.gp.girl.inventory.get(i).amount = ds.itemAmounts.get(i);
					}

					// PLAYER EQUIPMENT
					this.gp.girl.currentWeapon = this.gp.girl.inventory.get(ds.currentWeaponSlot);
					this.gp.girl.currentShield = this.gp.girl.inventory.get(ds.currentShieldSlot);
					this.gp.girl.getAttack();
					this.gp.girl.getDefense();
					this.gp.girl.getGirlAttackImage();

					// OBJS ON MAP
					if (this.gp.obj == null) {
						this.gp.obj = new Entity[this.gp.maxMap][this.gp.obj[1].length];
					}
					for (int j = 0; j < this.gp.maxMap; j++) {
						for (int k = 0; k < this.gp.obj[1].length; k++) {
							if (ds.mapObjNames[j][k].equals("NA")) {
								this.gp.obj[j][k] = null;
							} else {
								if (this.gp.obj[j][k] == null) {
									this.gp.obj[j][k] = new Entity(this.gp);
									this.gp.obj[j][k] = this.gp.egenerator.getObject(ds.mapObjNames[j][k]);
									this.gp.obj[j][k].worldX = ds.mapOBJWorldX[j][k];
									this.gp.obj[j][k].worldY = ds.mapOBJWorldY[j][k];
									if (ds.mapOBJLootnames[j][k] != null) {
										this.gp.obj[j][k].loot.setLoot(this.gp.egenerator.getObject(ds.mapOBJLootnames[j][k]));
									}
									this.gp.obj[j][k].opened = ds.mapOBJOpened[j][k];
									if (this.gp.obj[j][k].opened == true) {
										this.gp.obj[j][k].down1 = this.gp.obj[j][k].img2;
									}
								}
							}
						}
					}
					ois.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
