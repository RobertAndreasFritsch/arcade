package com.game.ctrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class WAVSoundCtrl implements Runnable, SoundCtrl
{
	private List<Clip>	clips	= new ArrayList<>();
	private boolean		loop;
	private final File	file;

	public WAVSoundCtrl(final File file, final boolean loop) throws Exception
	{
		this.file = file;
		this.loop = loop;

	}

	@Override
	public void run()
	{
		try
		{
			Clip clip;

			AudioInputStream audioInputStream;
			do
			{
				audioInputStream = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clips.add(clip);
				clip.open(audioInputStream);
				clip.start();
				Thread.sleep(clip.getMicrosecondLength() / 100000);
			}
			while (this.loop);
			
			clip.close(); // TODO not sure
			clips.remove(clip);
		}
		catch (final Exception e)
		{
		}
	}

	@Override
	public void stop()
	{
		for (Clip clip : new ArrayList<>(clips))
			if (clip != null) clip.close();
		this.loop = false;
	}

	@Override
	public void start()
	{
		new Thread(this).start();
	}
}
