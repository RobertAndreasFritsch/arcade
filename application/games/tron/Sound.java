package games.tron;

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
	private Clip sfx;

	/**
	 * @deprecated
	 */
	@Deprecated
	public void play(final String filename)
	{
		// Soundeffekt laden und abspielen
		this.sfx = null;
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

			if (this.sfx != null)
			{
				this.sfx.start(); // Clip wird einmal abgespielt
			}
		}
		catch (final Exception e)
		{
		}
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void stop()
	{
		this.sfx.stop();
	}
}
