package com.arcade;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.arcade.presentation.Presentation;
import com.arcade.utils.Seat;
import com.game.Game;
import com.game.SimpleGame;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.KeyCtrl;
import com.game.deprecated.Drawable;
import com.game.deprecated.ProceedsInput;
import com.game.deprecated.Updateable;

class GameSlider implements ProceedsInput, Updateable, Drawable
{

	private static int				activePresentation	= 0;

	private static final long		KeyBufferTime			= 400l;

	private final Presentation[]	presentations			= Presentation.values();
	private final Menu				parent;

	private final KeyCtrl			KEYS;
	private long						bufferTime				= 0l;

	public GameSlider(final Menu parent, final KeyCtrl KEYS)
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

public class Menu extends SimpleGame
{

	private static final long	serialVersionUID	= 72535390847737153L;
	private Presentation			nextGame;

	public Menu(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);
		this.add(new GameSlider(this, this.getKEYS()));
	}

	@Override
	public Game getNextGame()
	{
		return new PlayerSelection(this.getCtrlFactory(), this.nextGame);
	}

	public void setNextGame(final Presentation nextGame)
	{
		this.nextGame = nextGame;
	}
}
