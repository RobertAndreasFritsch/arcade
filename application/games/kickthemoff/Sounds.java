package games.kickthemoff;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * @deprecated
 */
public enum Sounds
{
	water_splash0("res/games/kickthemoff/water_splash.wav"), pling("res/games/kickthemoff/pling.wav"), falling(
	      "res/games/kickthemoff/falling.wav");

	private String filename;

	/**
	 * @deprecated
	 */
	@Deprecated
	Sounds(final String f)
	{
		this.filename = f;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	void play()
	{
		Clip sfx = null;
		final File file = new File(this.filename);
		try
		{
			final AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			final AudioFormat format = stream.getFormat();
			final int size = (int) (format.getFrameSize() * stream.getFrameLength());
			final byte[] audio = new byte[size];
			final DataLine.Info info = new DataLine.Info(Clip.class, format, size);
			stream.read(audio, 0, size);
			sfx = (Clip) AudioSystem.getLine(info);
			sfx.open(format, audio, 0, size);

			if (sfx != null)
			{
				sfx.start();
			}
		}
		catch (final Exception e)
		{
		}
	}

}
