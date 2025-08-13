package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sounds {

	public Clip cl;
	public URL soundUrl[] = new URL[999];
	public FloatControl fc;
	public int volumeScale = 3;
	public float volume;

	public Sounds() {
		this.soundUrl[0] = getClass().getResource("/sound/adventure.wav");

		this.soundUrl[1] = getClass().getResource("/sound/blocked.wav");
		this.soundUrl[2] = getClass().getResource("/sound/burning.wav");

		this.soundUrl[3] = getClass().getResource("/sound/chipwall.wav");
		this.soundUrl[4] = getClass().getResource("/sound/coin.wav");
		this.soundUrl[5] = getClass().getResource("/sound/cursor.wav");
		this.soundUrl[6] = getClass().getResource("/sound/cuttree.wav");

		this.soundUrl[7] = getClass().getResource("/sound/dooropen.wav");
		this.soundUrl[8] = getClass().getResource("/sound/dungeon.wav");

		this.soundUrl[9] = getClass().getResource("/sound/fanfare.wav");
		this.soundUrl[10] = getClass().getResource("/sound/finalbattle.wav");

		this.soundUrl[11] = getClass().getResource("/sound/gameover.wav");

		this.soundUrl[12] = getClass().getResource("/sound/hitmonster.wav");

		this.soundUrl[13] = getClass().getResource("/sound/levelup.wav");

		this.soundUrl[14] = getClass().getResource("/sound/merchant.wav");

		this.soundUrl[15] = getClass().getResource("/sound/parry.wav");
		this.soundUrl[16] = getClass().getResource("/sound/powerup.wav");

		this.soundUrl[17] = getClass().getResource("/sound/receivedamage.wav");

		this.soundUrl[18] = getClass().getResource("/sound/sleep.wav");
		this.soundUrl[19] = getClass().getResource("/sound/speak.wav");
		this.soundUrl[20] = getClass().getResource("/sound/stairs.wav");

		this.soundUrl[21] = getClass().getResource("/sound/unlock.wav");
	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(this.soundUrl[i]);
			this.cl = AudioSystem.getClip();
			this.cl.open(ais);
			this.fc = (FloatControl) this.cl.getControl(FloatControl.Type.MASTER_GAIN);
			this.checkVolume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Play() {
		this.cl.start();
	}

	public void loop() {
		this.cl.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void Stop() {
		this.cl.stop();
	}

	public void checkVolume() {
		switch (this.volumeScale) {
		case 0:
			this.volume = -80f;
			break;
		case 1:
			this.volume = -20f;
			break;
		case 2:
			this.volume = -12f;
			break;
		case 3:
			this.volume = -5f;
			break;
		case 4:
			this.volume = 1f;
			break;
		case 5:
			this.volume = 6f;
			break;
		}
		this.fc.setValue(this.volume);
	}
}
