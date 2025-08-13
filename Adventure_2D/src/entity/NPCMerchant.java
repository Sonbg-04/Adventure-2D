package entity;

import main.GamePanel;
import objects.OBJNormalAxe;
import objects.OBJBoots;
import objects.OBJChest;
import objects.OBJDiamondAxe;
import objects.OBJDiamondKnife;
import objects.OBJKey;
import objects.OBJNormalKnife;
import objects.OBJNormalShield;
import objects.OBJPotionRed;
import objects.OBJPremiumShield;
import objects.OBJDiamondShield;
import objects.OBJDiamondSword;
import objects.OBJGoldenAxe;
import objects.OBJGoldenKnife;
import objects.OBJGoldenShield;
import objects.OBJGoldenSword;
import objects.OBJNormalSword;
import objects.OBJPotionBlue;

public class NPCMerchant extends Entity {

	public NPCMerchant(GamePanel gp) {
		super(gp);
		this.direction = "down";
		this.speed = 1;

		this.getNPCImage();
		this.setDialogue();
		this.setItem();

	}

	public void getNPCImage() {
		this.up1 = Setup("/npc/merchant/merchant_1", this.gp.tileSize, this.gp.tileSize);
		this.up2 = Setup("/npc/merchant/merchant_2", this.gp.tileSize, this.gp.tileSize);
		this.down1 = Setup("/npc/merchant/merchant_1", this.gp.tileSize, this.gp.tileSize);
		this.down2 = Setup("/npc/merchant/merchant_2", this.gp.tileSize, this.gp.tileSize);
		this.left1 = Setup("/npc/merchant/merchant_1", this.gp.tileSize, this.gp.tileSize);
		this.left2 = Setup("/npc/merchant/merchant_2", this.gp.tileSize, this.gp.tileSize);
		this.right1 = Setup("/npc/merchant/merchant_1", this.gp.tileSize, this.gp.tileSize);
		this.right2 = Setup("/npc/merchant/merchant_2", this.gp.tileSize, this.gp.tileSize);
	}

	public void setDialogue() {
		this.dialogues[0][0] = "Hehe, so you found me. I have some good stuff.\nDo you want to trade?";
		this.dialogues[1][0] = "Come again, hehe!";
		this.dialogues[2][0] = "You need more coin to buy that!";
		this.dialogues[3][0] = "You can't carry any more!";
		this.dialogues[4][0] = "You can't sell an equipped item!";

	}

	public void setItem() {
		this.inventory.add(new OBJPotionRed(this.gp));
		this.inventory.add(new OBJBoots(this.gp));
		this.inventory.add(new OBJChest(this.gp));
		this.inventory.add(new OBJKey(this.gp));
		this.inventory.add(new OBJDiamondShield(this.gp));
		this.inventory.add(new OBJGoldenShield(this.gp));
		this.inventory.add(new OBJNormalShield(this.gp));
		this.inventory.add(new OBJPremiumShield(this.gp));
		this.inventory.add(new OBJNormalSword(this.gp));
		this.inventory.add(new OBJPotionBlue(this.gp));
		this.inventory.add(new OBJGoldenSword(this.gp));
		this.inventory.add(new OBJDiamondSword(this.gp));
		this.inventory.add(new OBJDiamondKnife(this.gp));
		this.inventory.add(new OBJNormalKnife(this.gp));
		this.inventory.add(new OBJGoldenKnife(this.gp));
		this.inventory.add(new OBJNormalAxe(this.gp));
		this.inventory.add(new OBJGoldenAxe(this.gp));
		this.inventory.add(new OBJDiamondAxe(this.gp));

	}

	public void Speak() {
		this.facePlayer();
		this.gp.gameState = this.gp.tradeState;
		this.gp.ui.npc = this;
	}
}
