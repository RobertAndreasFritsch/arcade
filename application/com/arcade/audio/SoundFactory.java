package com.arcade.audio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoundFactory
{
	public List<Sound> sounds = new ArrayList<>();

	public SoundFactory()
	{
	}

	public Sound newSound(final File file, final boolean loop, final SoundType soundType)
	{
		Sound sound;

		try
		{
			switch (soundType)
			{
			case MP3:
				sound = new MP3Sound(file, loop);
				break;
			case WAV:
				sound = new WAVSound(file, loop);
				break;
			default:
				throw new Exception("unknown Type : " + soundType);
			}
		}
		catch (final Exception e)
		{
			return null;
		}

		this.sounds.add(sound);
		return sound;
	}

	public void stop()
	{
		for (final Sound sound : this.sounds)
		{
			sound.stop();
		}
	}
}
