package games.tank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer implements Runnable {

	static void Playsound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 100000);
		} catch (Exception e) {

		}
	}

	String play;
	Thread timerPlayer;

	File Shoot = new File("res/games/tank/sounds/Samples/shoot.wav");

	public MusicPlayer() {
		// Titel
		play = "res/games/tank/music/Classic/Soundtrack.mp3";

		timerPlayer = new Thread(this);
		timerPlayer.start();
	}

	@Override
	public void run() {
		while (timerPlayer != null) {
			try {
				FileInputStream in = new FileInputStream(play);
				Player p1 = new Player(in);
				p1.play();
			} catch (JavaLayerException jle) {
				jle.printStackTrace();
			} catch (FileNotFoundException fnf) {
				fnf.printStackTrace();

				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
				}

			}
		}
	}

}
