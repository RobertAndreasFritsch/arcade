package com.game.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.Player;

public class MP3SoundImpl implements Runnable, Sound
{
	private final List<Player>	players	= new ArrayList<>();
	private boolean				loop;
	private final File			file;
	private final SoundCtrl		soundCtrl;

	public MP3SoundImpl(final SoundCtrlImpl soundCtrlImpl, final File file, final boolean loop) throws Exception
	{
		this.soundCtrl = soundCtrlImpl;
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
			Player player;
			FileInputStream fileInputStream;

			this.soundCtrl.incrementSoundCounter();
			incremented = true;
			do
			{
				fileInputStream = new FileInputStream(this.file);
				player = new Player(fileInputStream);
				this.players.add(player);
				player.play();
			}
			while (this.loop);
			this.soundCtrl.decrementSoundCounter();
			incremented = false;

			player.close();
			this.players.remove(player);
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
