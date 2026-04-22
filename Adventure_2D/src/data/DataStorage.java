package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
	// PLAYER STATUS
	public int Level;
	public int maxLife;
	public int Life;
	public int Mana;
	public int maxMana;
	public int strength;
	public int Dexterity;
	public int Exp;
	public int nextLevelExp;
	public int coin;

	// PLAYER INVENTORY
	public ArrayList<String> itemNames = new ArrayList<>();
	public ArrayList<Integer> itemAmounts = new ArrayList<>();
	public int currentWeaponSlot;
	public int currentShieldSlot;

	// OBJECT ON MAP
	public String mapObjNames[][];
	public int mapOBJWorldX[][];
	public int mapOBJWorldY[][];
	public String mapOBJLootnames[][];
	public boolean mapOBJOpened[][];

}
