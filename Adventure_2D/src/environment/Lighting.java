package environment;

import java.awt.AlphaComposite;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {
	public GamePanel gp;
	public BufferedImage darknessFilter;
	public int dayCounter;
	public float filterAlpha = 0F;

	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	public int dayState = day;

	public Lighting(GamePanel gp) {
		this.gp = gp;
		this.setLightSource();
	}

	public void setLightSource() {
		this.darknessFilter = new BufferedImage(this.gp.screenWidth, this.gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) (this.darknessFilter.getGraphics());
		if (this.gp.currentPlayer != null) {
			if (this.gp.currentPlayer == this.gp.boy && this.gp.boy.type == this.gp.currentPlayer.type_player_boy) {
				if (this.gp.boy.currentLight == null) {
					g2.setColor(new Color(0, 0, 0, 0.97F));
				} else {
					int centerX = this.gp.boy.screenX + (this.gp.tileSize) / 2;
					int centerY = this.gp.boy.screenY + (this.gp.tileSize) / 2;

					Color cl[] = new Color[12];
					float fraction[] = new float[12];

					cl[0] = new Color(0, 0, 0.1F, 0.1F);
					cl[1] = new Color(0, 0, 0.1F, 0.42F);
					cl[2] = new Color(0, 0, 0.1F, 0.52F);
					cl[3] = new Color(0, 0, 0.1F, 0.61F);
					cl[4] = new Color(0, 0, 0.1F, 0.69F);
					cl[5] = new Color(0, 0, 0.1F, 0.76F);
					cl[6] = new Color(0, 0, 0.1F, 0.82F);
					cl[7] = new Color(0, 0, 0.1F, 0.87F);
					cl[8] = new Color(0, 0, 0.1F, 0.91F);
					cl[9] = new Color(0, 0, 0.1F, 0.92F);
					cl[10] = new Color(0, 0, 0.1F, 0.93F);
					cl[11] = new Color(0, 0, 0.1F, 0.94F);

					fraction[0] = 0F;
					fraction[1] = 0.4F;
					fraction[2] = 0.5F;
					fraction[3] = 0.6F;
					fraction[4] = 0.65F;
					fraction[5] = 0.7F;
					fraction[6] = 0.75F;
					fraction[7] = 0.8F;
					fraction[8] = 0.85F;
					fraction[9] = 0.9F;
					fraction[10] = 0.95F;
					fraction[11] = 1F;

					RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY,
							this.gp.boy.currentLight.lightRadius, fraction, cl);

					g2.setPaint(gPaint);
				}
				g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
			}
			if (this.gp.currentPlayer == this.gp.girl && this.gp.girl.type == this.gp.currentPlayer.type_player_girl) {
				if (this.gp.girl.currentLight == null) {
					g2.setColor(new Color(0, 0, 0.1F, 0.97F));
				} else {

					int centerX = this.gp.girl.screenX + (this.gp.tileSize) / 2;
					int centerY = this.gp.girl.screenY + (this.gp.tileSize) / 2;

					Color cl[] = new Color[12];
					float fraction[] = new float[12];

					cl[0] = new Color(0, 0, 0.1F, 0.1F);
					cl[1] = new Color(0, 0, 0.1F, 0.42F);
					cl[2] = new Color(0, 0, 0.1F, 0.52F);
					cl[3] = new Color(0, 0, 0.1F, 0.61F);
					cl[4] = new Color(0, 0, 0.1F, 0.69F);
					cl[5] = new Color(0, 0, 0.1F, 0.76F);
					cl[6] = new Color(0, 0, 0.1F, 0.82F);
					cl[7] = new Color(0, 0, 0.1F, 0.87F);
					cl[8] = new Color(0, 0, 0.1F, 0.91F);
					cl[9] = new Color(0, 0, 0.1F, 0.92F);
					cl[10] = new Color(0, 0, 0.1F, 0.93F);
					cl[11] = new Color(0, 0, 0.1F, 0.94F);

					fraction[0] = 0F;
					fraction[1] = 0.4F;
					fraction[2] = 0.5F;
					fraction[3] = 0.6F;
					fraction[4] = 0.65F;
					fraction[5] = 0.7F;
					fraction[6] = 0.75F;
					fraction[7] = 0.8F;
					fraction[8] = 0.85F;
					fraction[9] = 0.9F;
					fraction[10] = 0.95F;
					fraction[11] = 1F;

					RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY,
							this.gp.girl.currentLight.lightRadius, fraction, cl);
					g2.setPaint(gPaint);
				}
				g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
			}
		}
	}

	public void setDayState() {
		if (this.dayState == this.day) {
			this.dayCounter++;
			if (this.dayCounter > 600) {
				this.dayState = this.dusk;
				this.dayCounter = 0;
			}
		}
		if (this.dayState == this.dusk) {
			this.filterAlpha += 0.001F;
			if (this.filterAlpha > 1F) {
				this.filterAlpha = 1F;
				this.dayState = this.night;
			}
		}
		if (this.dayState == this.night) {
			this.dayCounter++;
			if (this.dayCounter > 600) {
				this.dayState = this.dawn;
				this.dayCounter = 0;
			}
		}
		if (this.dayState == this.dawn) {
			this.filterAlpha -= 0.001F;
			if (this.filterAlpha < 0F) {
				this.filterAlpha = 0;
				this.dayState = this.day;
			}
		}
	}

	public void Update() {
    	this.setDayState();
    	this.setLightSource();
	}

	public void resetDay() {
		this.dayState = day;
		this.filterAlpha = 0F;
	}

	public void draw(Graphics2D g2) {

		if (this.gp.currentArea == this.gp.outside) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.filterAlpha));
		}
		if (this.gp.currentArea == this.gp.outside || this.gp.currentArea == this.gp.dungeon) {
			g2.drawImage(this.darknessFilter, 0, 0, null);
		}

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

		// DEBUG
		String situation = " ";

		switch (this.dayState) {
		case day: {
			situation = "Day!";
			break;
		}
		case dusk: {
			situation = "Dusk!";
			break;
		}
		case night: {
			situation = "Night!";
			break;
		}
		case dawn: {
			situation = "Dawn!";
			break;
		}
		}
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(30F));
		g2.drawString(situation, 800, 500);

	}
}
