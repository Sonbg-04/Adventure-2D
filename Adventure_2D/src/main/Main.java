package main;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

public class Main {

	public static JFrame wd;

	public static void main(String[] args) {
		wd = new JFrame();
		wd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wd.setResizable(false);
		wd.setTitle("Adventure's 2D");
		Main m = new Main();
		m.setIcon();
		GamePanel gp = new GamePanel();
		wd.add(gp);
		gp.cfg.loadConfig();
		if (gp.fullScreenOn == true) {
			wd.setUndecorated(true);
		}
		wd.pack();
		wd.setLocationRelativeTo(null);
		wd.setVisible(true);

		gp.SetupGame();
		gp.StartgameThread();
	}

	public void setIcon() {
		
		String sourceImg = "player/dichuyen/boy/boy_down_1.png";
		final ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(sourceImg));
		wd.setIconImage(icon.getImage());
	}
}
