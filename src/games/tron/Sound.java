package games.tron;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sound {
	private Clip sfx;

	public void play(String filename) {
		// Soundeffekt laden und abspielen
		sfx = null;
		File file = new File(filename);
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = stream.getFormat();
			int size = (int) (format.getFrameSize() * stream.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, format, size); // Line
																				// beschaffen
			stream.read(audio, 0, size);
			sfx = (Clip) AudioSystem.getLine(info);
			sfx.open(format, audio, 0, size); // reserviert Ressourcen,
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sfx != null) {
			sfx.start(); // Clip wird einmal abgespielt
		}
	}

	public void stop() {
		sfx.stop();
	}
}
