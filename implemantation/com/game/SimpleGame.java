package com.game;

import java.util.ArrayList;

import com.arcade.Menu;
import com.arcade.presentation.GameConstructor;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.KeyCtrl;

public class SimpleGame extends ArrayList<GameObject> implements Game
{
	private static final long				serialVersionUID	= -564173017564334081L;
	public static final GameConstructor	DefaultGame			= Menu::new;

	private boolean							running				= false;
	private GameConstructor					nextGame				= SimpleGame.DefaultGame;
	private final CtrlFactory				ctrlFactory;

	public SimpleGame(final CtrlFactory ctrlFactory)
	{
		this.ctrlFactory = ctrlFactory;
	}

	public void exitGame()
	{
		this.setNextGame(Menu::new);
		this.setRunning(false);
	}

	public KeyCtrl getKEYS()
	{
		return this.ctrlFactory.keyRequestInstance();
	}

	@Override
	public Game getNextGame()
	{
		return this.nextGame.newGame(this.getCtrlFactory());
	}

	@Override
	public boolean isRunning()
	{
		return this.running;
	}

	private void setNextGame(final GameConstructor nextGame)
	{
		this.nextGame = nextGame;
	}

	@Override
	public void setRunning(final boolean running)
	{
		this.running = running;
	}

	@Override
	public void tick(final long elapsed)
	{
		final ArrayList<GameObject> temp = new ArrayList<>(this);

		ctrlFactory.keyRequestInstance().takeFrame();
		for (final GameObject i : temp)
		{
			i.input();
		}

		for (final GameObject u : temp)
		{
			u.update(elapsed);
		}
		
		for (final GameObject d : temp)
		{
			d.output();
		}
		ctrlFactory.graphicsControllerInstance().dispose();
	}

	public CtrlFactory getCtrlFactory()
	{
		return this.ctrlFactory;
	}
}
