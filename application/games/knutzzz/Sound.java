package games.knutzzz;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * @deprecated
 */
@Deprecated
public class Sound
{
	/**
	 * @deprecated
	 */
	@Deprecated
	Clip sfx = null;

	/**
	 * @deprecated
	 */
	@Deprecated
	public void instantPlay(final String filename)
	{
		// Soundeffekt laden und abspielen
		final File file = new File(filename);
		try
		{
			final AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			final AudioFormat format = stream.getFormat();
			final int size = (int) (format.getFrameSize() * stream.getFrameLength());
			final byte[] audio = new byte[size];
			final DataLine.Info info = new DataLine.Info(Clip.class, format, size); // Line
			// beschaffen
			stream.read(audio, 0, size);
			this.sfx = (Clip) AudioSystem.getLine(info);
			this.sfx.open(format, audio, 0, size); // reserviert Ressourcen,
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		if (this.sfx != null)
		{
			this.sfx.start(); // Clip wird einmal abgespielt
		}
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void load(final String filename)
	{
		// Soundeffekt laden
		final File file = new File(filename);
		try
		{
			final AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			final AudioFormat format = stream.getFormat();
			final int size = (int) (format.getFrameSize() * stream.getFrameLength());
			final byte[] audio = new byte[size];
			final DataLine.Info info = new DataLine.Info(Clip.class, format, size); // Line
			// beschaffen
			stream.read(audio, 0, size);
			this.sfx = (Clip) AudioSystem.getLine(info);
			this.sfx.open(format, audio, 0, size); // reserviert Ressourcen,
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void play()
	{
		if (this.sfx != null)
		{
			this.sfx.setFramePosition(0);
			this.sfx.start(); // Clip wird einmal abgespielt
		}
	}
}
