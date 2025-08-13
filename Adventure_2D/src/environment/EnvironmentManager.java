package environment;

import java.awt.Graphics2D;

import main.GamePanel;

public class EnvironmentManager {
	public GamePanel gp;
	public Lighting lighting;

	public EnvironmentManager(GamePanel gp) {
		this.gp = gp;
	}

	public void setup() {
		this.lighting = new Lighting(this.gp);
	}

	public void Update() {
		this.lighting.Update();
	}

	public void draw(Graphics2D g2) {
		this.lighting.draw(g2);
	}
}
