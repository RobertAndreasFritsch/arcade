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
	private CtrlFactoryImpl		ctrlFactory;

	public MP3SoundCtrlImpl(CtrlFactoryImpl ctrlFactory, final File file, final boolean loop) throws Exception
	{
		this.ctrlFactory = ctrlFactory;
		this.file = file;
		this.loop = loop;
	}

	@Override
	public void run()
	{
		if (ctrlFactory.limit())
			return;
		
		boolean incremented = false;
		try
		{
			Player player;
			FileInputStream fileInputStream;
			
			ctrlFactory.incrementSoundCounter();
			incremented = true;
			do
			{
				fileInputStream = new FileInputStream(this.file);
				player = new Player(fileInputStream);
				this.players.add(player);
				player.play();
			}
			while (this.loop);
			ctrlFactory.incrementSoundCounter();
			incremented = false;

			player.close();
			this.players.remove(player);
		}
		catch (final Exception e)
		{
			if(incremented) 
				ctrlFactory.decrementSoundCounter();
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
	public void play()
	{
		new Thread(this).start();
	}
}
