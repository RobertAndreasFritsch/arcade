package com.arcade;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.arcade.presentation.Presentation;
import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.Game;
import com.game.MyGame;
import com.game.ProceedsInput;
import com.game.Updateable;
import com.game.ctrl.KeyRequest;

class GameSlider implements ProceedsInput, Updateable, Drawable
{

	private static int				activePresentation	= 0;

	private static final long		KeyBufferTime			= 400l;

	private final Presentation[]	presentations			= Presentation.values();
	private final Menu				parent;

	private final KeyRequest		KEYS;
	private long						bufferTime				= 0l;

	public GameSlider(final Menu parent, final KeyRequest KEYS)
	{
		this.parent = parent;
		this.KEYS = KEYS;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.BLACK);
		this.presentations[GameSlider.activePresentation].draw(g);
	}

	@Override
	public void processInput()
	{
		if (this.bufferTime > 0) { return; }

		if (this.KEYS.isPressed(Seat.P1.RIGHT))
		{
			if (GameSlider.activePresentation >= this.presentations.length - 1)
			{

				GameSlider.activePresentation = 0;
			}
			else
			{

				GameSlider.activePresentation++;
			}
			this.bufferTime = GameSlider.KeyBufferTime;

		}
		else
			if (this.KEYS.isPressed(Seat.P1.LEFT))
			{
				if (GameSlider.activePresentation <= 0)
				{

					GameSlider.activePresentation = this.presentations.length - 1;
				}
				else
				{

					GameSlider.activePresentation--;
				}
				this.bufferTime = GameSlider.KeyBufferTime;

			}
			else
				if (this.KEYS.isPressed(KeyEvent.VK_N))
				{
					this.parent.setNextGame(this.presentations[GameSlider.activePresentation]);
					this.parent.setRunning(false);
				}
	}

	@Override
	public void update(final long elapsed)
	{
		if (this.bufferTime > 0)
		{
			this.bufferTime -= elapsed;

			if (this.bufferTime < 0)
			{
				this.bufferTime = 0;
			}
		}
	}
}

public class Menu extends MyGame
{

	/**
	 *
	 */
	private static final long	serialVersionUID	= 72535390847737153L;
	private Presentation			nextGame;

	public Menu(final JPanel panel, final KeyRequest KEYS, final String... args)
	{
		super(panel, KEYS, args);
		this.add(new GameSlider(this, KEYS));
	}

	@Override
	public Game getNextGame()
	{
		return new PlayerSelection(this.PANEL, this.KEYS, this.nextGame);
	}

	public void setNextGame(final Presentation nextGame)
	{
		this.nextGame = nextGame;
	}
}
