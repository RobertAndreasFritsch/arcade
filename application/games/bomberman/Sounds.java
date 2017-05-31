package games.bomberman;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public enum Sounds
{
	explosion0("res/games/bomberman/explosion0.wav"), explosion1("res/games/bomberman/explosion1.wav"), explosion2(
	      "res/games/bomberman/explosion2.wav"), scream0("res/games/bomberman/scream0.wav");

	private String	filename;
	private Clip	sfx;

	Sounds(final String f)
	{
		this.filename = f;
	}

	void play()
	{
		this.sfx = null;
		final File file = new File(this.filename);
		try
		{
			final AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			final AudioFormat format = stream.getFormat();
			final int size = (int) (format.getFrameSize() * stream.getFrameLength());
			final byte[] audio = new byte[size];
			final DataLine.Info info = new DataLine.Info(Clip.class, format, size);
			stream.read(audio, 0, size);
			this.sfx = (Clip) AudioSystem.getLine(info);
			this.sfx.open(format, audio, 0, size);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		if (this.sfx != null)
		{
			this.sfx.start();
		}
	}

}
