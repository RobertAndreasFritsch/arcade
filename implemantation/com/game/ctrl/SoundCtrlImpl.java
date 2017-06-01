package com.game.ctrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoundCtrlImpl implements SoundCtrl
{
	private final List<Sound>	sounds			= new ArrayList<>();
	private int						soundCounter	= 0;

	@Override
	public Sound newSound(final String path, final boolean loop, final SoundType soundType)
	{
		Sound sound;
		final File file = new File(path);

		try
		{
			switch (soundType)
			{
			case MP3:
				sound = new MP3SoundCtrlImpl(this, file, loop);
				break;
			case WAV:
				sound = new WAVSoundCtrl(this, file, loop);
				break;
			default:
				throw new Exception("unknown Type : " + soundType);
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return null;
		}

		this.sounds.add(sound);
		return sound;
	}

	@Override
	public void closeSounds()
	{
		for (final Sound sound : this.sounds)
		{
			sound.stop();
		}
	}

	@Override
	public void decrementSoundCounter()
	{
		this.soundCounter--;
	}

	@Override
	public void incrementSoundCounter()
	{
		this.soundCounter++;
	}

	@Override
	public boolean limit()
	{
		return this.soundCounter >= SOUNDLIMIT;
	}
}
