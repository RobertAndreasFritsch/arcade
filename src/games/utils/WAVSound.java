package games.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WAVSound implements Runnable, Sound {

	File file;
	private Clip clip;
	private boolean loop;

	public WAVSound(File file, boolean loop) {
		this.file = file;
		this.loop = loop;
		new Thread(this).start();
	}

	@Override
	public void run() {
		do {
		try {
			
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 100000);
			
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}while (loop);
	}
	
	public void stop() {
		clip.close();
		loop = false;
	}
}
