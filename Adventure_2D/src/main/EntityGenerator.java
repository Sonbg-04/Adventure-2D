package main;

import entity.Entity;
import objects.OBJNormalAxe;
import objects.OBJBoots;
import objects.OBJChest;
import objects.OBJDiamondAxe;
import objects.OBJDiamondKnife;
import objects.OBJDoor;
import objects.OBJGoldenAxe;
import objects.OBJGoldenKnife;
import objects.OBJGoldenShield;
import objects.OBJGoldenSword;
import objects.OBJKey;
import objects.OBJLantern;
import objects.OBJNormalKnife;
import objects.OBJPickAxe;
import objects.OBJPotionBlue;
import objects.OBJPotionRed;
import objects.OBJPremiumShield;
import objects.OBJDiamondShield;
import objects.OBJDiamondSword;
import objects.OBJNormalShield;
import objects.OBJNormalSword;
import objects.OBJTent;

public class EntityGenerator {
	public GamePanel gp;

	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}

	public Entity getObject(String itemName) {
		Entity obj = null;
		switch (itemName) {
		case OBJNormalAxe.objName: {
			obj = new OBJNormalAxe(this.gp);
			break;
		}
		case OBJGoldenAxe.objName: {
			obj = new OBJGoldenAxe(this.gp);
			break;
		}
		case OBJDiamondAxe.objName: {
			obj = new OBJDiamondAxe(this.gp);
			break;
		}
		case OBJBoots.objName: {
			obj = new OBJBoots(this.gp);
			break;
		}
		case OBJChest.objName: {
			obj = new OBJChest(this.gp);
			break;
		}
		case OBJKey.objName: {
			obj = new OBJKey(this.gp);
			break;
		}
		case OBJLantern.objName: {
			obj = new OBJLantern(this.gp);
			break;
		}
		case OBJPremiumShield.objName: {
			obj = new OBJPremiumShield(this.gp);
			break;
		}
		case OBJDiamondShield.objName: {
			obj = new OBJDiamondShield(this.gp);
			break;
		}
		case OBJGoldenShield.objName: {
			obj = new OBJGoldenShield(this.gp);
			break;
		}
		case OBJNormalShield.objName: {
			obj = new OBJNormalShield(this.gp);
			break;
		}
		case OBJNormalSword.objName: {
			obj = new OBJNormalSword(this.gp);
			break;
		}
		case OBJGoldenSword.objName: {
			obj = new OBJGoldenSword(this.gp);
			break;
		}
		case OBJDiamondSword.objName: {
			obj = new OBJDiamondSword(this.gp);
			break;
		}
		case OBJTent.objName: {
			obj = new OBJTent(this.gp);
			break;
		}
		case OBJDoor.objName: {
			obj = new OBJDoor(this.gp);
			break;
		}
		case OBJPotionRed.objName: {
			obj = new OBJPotionRed(this.gp);
			break;
		}
		case OBJPotionBlue.objName: {
			obj = new OBJPotionBlue(this.gp);
			break;
		}
		case OBJPickAxe.objName: {
			obj = new OBJPickAxe(this.gp);
			break;
		}
		case OBJGoldenKnife.objName: {
			obj = new OBJGoldenKnife(this.gp);
			break;
		}
		case OBJDiamondKnife.objName: {
			obj = new OBJDiamondKnife(this.gp);
			break;
		}
		case OBJNormalKnife.objName: {
			obj = new OBJNormalKnife(this.gp);
			break;
		}
		}
		return obj;
	}

}
