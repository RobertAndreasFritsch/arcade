package com.arcade.audio;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3Sound implements Runnable, Sound
{

	private Player						player;
	private boolean					loop;
	private final FileInputStream	fileInputStream;

	public MP3Sound(final File file, final boolean loop) throws Exception
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
