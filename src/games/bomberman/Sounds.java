package games.bomberman;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public enum Sounds {
	explosion0("res/games/bomberman/explosion0.wav"), explosion1("res/games/bomberman/explosion1.wav"), explosion2(
			"res/games/bomberman/explosion2.wav"), scream0("res/games/bomberman/scream0.wav");

	private String filename;
	private Clip sfx;

	Sounds(String f) {
		this.filename = f;
	}

	void play() {
		sfx = null;
		File file = new File(filename);
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = stream.getFormat();
			int size = (int) (format.getFrameSize() * stream.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, format, size);
			stream.read(audio, 0, size);
			sfx = (Clip) AudioSystem.getLine(info);
			sfx.open(format, audio, 0, size);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sfx != null) {
			sfx.start();
		}
	}

}
