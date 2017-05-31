package com.game.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.Player;

public class MP3SoundCtrlImpl implements Runnable, SoundCtrl
{

	private List<Player>	players	= new ArrayList<>();
	private boolean		loop;
	private final File	file;

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
				fileInputStream = new FileInputStream(file);
				player = new Player(fileInputStream);
				players.add(player);
				player.play();
			}
			while (this.loop);

			player.close();
			players.remove(player);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void stop()
	{
		for (Player player : players)
			if (player != null) player.close();
		this.loop = false;
	}

	@Override
	public void start()
	{
		new Thread(this).start();
	}
}
