package com.game.ctrl;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class WAVSoundCtrl implements Runnable, SoundCtrl
{

	private Clip							clip;
	private boolean						loop;
	private final AudioInputStream	audioInputStream;

	public WAVSoundCtrl(final File file, final boolean loop) throws Exception
	{
		this.audioInputStream = AudioSystem.getAudioInputStream(file);
		this.loop = loop;
	}

	@Override
	public void run()
	{
		try
		{
			do
			{
				this.clip = AudioSystem.getClip();
				this.clip.open(this.audioInputStream);
				this.clip.start();
				Thread.sleep(this.clip.getMicrosecondLength() / 100000);
			}
			while (this.loop);
		}
		catch (final Exception e)
		{
		}
	}

	@Override
	public void stop()
	{
		this.clip.close();
		this.loop = false;
	}

	@Override
	public void start()
	{
		new Thread(this).start();
	}
}
