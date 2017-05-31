package com.game.ctrl;

import java.io.File;
import java.io.FileInputStream;

import com.arcade.audio.SoundCtrl;

import javazoom.jl.player.Player;

public class MP3SoundCtrlImpl implements Runnable, SoundCtrl
{

	private Player						player;
	private boolean					loop;
	private final FileInputStream	fileInputStream;

	public MP3SoundCtrlImpl(final File file, final boolean loop) throws Exception
	{
		this.fileInputStream = new FileInputStream(file);
		this.loop = loop;
	}

	@Override
	public void run()
	{
		try
		{
			do
			{
				this.player = new Player(this.fileInputStream);
				this.player.play();
			}
			while (this.loop);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		this.player.close();
	}

	@Override
	public void stop()
	{
		this.player.close();
		this.loop = false;
	}

	@Override
	public void start()
	{
		new Thread(this).start();
	}
}
