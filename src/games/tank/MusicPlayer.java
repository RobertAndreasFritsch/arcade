package games.tank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer implements Runnable {

	static void Playsound(final File Sound) {
		try {
			final Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 100000);
		}
		catch (final Exception e) {

		}
	}

	String	play;
	Thread	timerPlayer;

	File		Shoot	= new File("res/games/tank/sounds/Samples/shoot.wav");

	public MusicPlayer() {
		// Titel
		this.play = "res/games/tank/music/Classic/Soundtrack.mp3";

		this.timerPlayer = new Thread(this);
		this.timerPlayer.start();
	}

	@Override
	public void run() {
		while (this.timerPlayer != null) {
			try {
				final FileInputStream in = new FileInputStream(this.play);
				final Player p1 = new Player(in);
				p1.play();
			}
			catch (final JavaLayerException jle) {
				jle.printStackTrace();
			}
			catch (final FileNotFoundException fnf) {
				fnf.printStackTrace();

				try {
					Thread.sleep(30);
				}
				catch (final InterruptedException e) {
				}

			}
		}
	}

}
