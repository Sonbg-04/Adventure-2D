package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {
	public GamePanel gp;

	public Config(GamePanel gp) {
		this.gp = gp;
	}

	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

			// FULL SCREEN
			if (this.gp.fullScreenOn == true) {
				bw.write("On");
			} else {
				bw.write("Off");
			}
			bw.newLine();

			// MUSIC VOLUME
			bw.write(String.valueOf(this.gp.music.volumeScale));
			bw.newLine();

			// SE VOLUME
			bw.write(String.valueOf(this.gp.s.volumeScale));
			bw.newLine();

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			String s = br.readLine();

			// FULL SCREEN
			if (s.equals("On")) {
				this.gp.fullScreenOn = true;
			} else {
				this.gp.fullScreenOn = false;
			}

			// MUSIC VOLUME
			s = br.readLine();
			this.gp.music.volumeScale = Integer.parseInt(s);

			// SE VOLUME
			s = br.readLine();
			this.gp.music.volumeScale = Integer.parseInt(s);

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
