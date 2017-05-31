package com.game.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.Player;

public class MP3SoundCtrlImpl implements Runnable, SoundCtrl
{

	private final List<Player>	players	= new ArrayList<>();
	private boolean				loop;
	private final File			file;

	public MP3SoundCtrlImpl(final File file, final boolean loop) throws Exception
	{
		this.file = file;
		this.loop = loop;

	}

	@Override
	public void run()
	{
		try
		{
			Player player;
			FileInputStream fileInputStream;
			do
			{
				fileInputStream = new FileInputStream(this.file);
				player = new Player(fileInputStream);
				this.players.add(player);
				player.play();
			}
			while (this.loop);

			player.close();
			this.players.remove(player);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void stop()
	{
		for (final Player player : this.players)
		{
			if (player != null)
			{
				player.close();
			}
		}
		this.loop = false;
	}

	@Override
	public void start()
	{
		new Thread(this).start();
	}
}
