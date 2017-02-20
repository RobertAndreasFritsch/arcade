package games.kickthemoff;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public enum Sounds {
	water_splash0("res/games/kickthemoff/water_splash.wav"),
	pling("res/games/kickthemoff/pling.wav"),
	falling("res/games/kickthemoff/falling.wav");

	private String filename;
	private Clip sfx;

	Sounds(String f) {
		this.filename = f;
	}

	void play() {
		Clip sfx = null;
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
