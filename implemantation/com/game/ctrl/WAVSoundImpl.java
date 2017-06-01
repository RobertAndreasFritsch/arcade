package com.game.ctrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class WAVSoundImpl implements Runnable, Sound
{
	private final List<Clip>	clips	= new ArrayList<>();
	private boolean				loop;
	private final File			file;

	private final SoundCtrl		soundCtrl;

	public WAVSoundImpl(final SoundCtrl soundCtrl, final File file, final boolean loop) throws Exception
	{
		this.soundCtrl = soundCtrl;
		this.file = file;
		this.loop = loop;

	}

	@Override
	public void run()
	{
		if (this.soundCtrl.limit()) { return; }

		boolean incremented = false;
		try
		{
			Clip clip;
			AudioInputStream audioInputStream;

			this.soundCtrl.incrementSoundCounter();
			incremented = true;
			do
			{
				audioInputStream = AudioSystem.getAudioInputStream(this.file);
				clip = AudioSystem.getClip();
				this.clips.add(clip);
				clip.open(audioInputStream);
				clip.start();
				Thread.sleep(clip.getMicrosecondLength() / 100000);
			}
			while (this.loop);
			this.soundCtrl.decrementSoundCounter();
			incremented = false;

			clip.close(); // TODO not sure
			this.clips.remove(clip);
		}
		catch (final Exception e)
		{
			if (incremented)
			{
				this.soundCtrl.decrementSoundCounter();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{
		for (final Clip clip : new ArrayList<>(this.clips))
		{
			if (clip != null)
			{
				clip.close();
			}
		}
		this.loop = false;
	}

	@Override
	public void play()
	{
		new Thread(this).start();
	}
}
