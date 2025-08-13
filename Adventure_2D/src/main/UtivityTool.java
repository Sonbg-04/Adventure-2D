package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtivityTool {
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaleimg = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaleimg.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		return scaleimg;
	}

}
