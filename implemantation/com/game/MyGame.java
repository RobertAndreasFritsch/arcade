package com.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import com.arcade.Menu;
import com.arcade.presentation.GameConstructor;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.KeyRequest;

public class MyGame extends ArrayList<GameObject> implements Game
{
	private static final long				serialVersionUID	= -564173017564334081L;
	public static final GameConstructor	DefaultGame			= Menu::new;

	private boolean							running				= false;
	Image											img;
	Graphics2D									g;
	private GameConstructor					nextGame				= MyGame.DefaultGame;
	private final CtrlFactory				ctrlFactory;

	public MyGame(final CtrlFactory ctrlFactory)
	{
		this.ctrlFactory = ctrlFactory;
	}

	public void exitGame()
	{
		this.setNextGame(Menu::new);
		this.setRunning(false);
	}

	public KeyRequest getKEYS()
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

		for (final GameObject i : temp)
		{
			i.input();
		}
		for (final GameObject u : temp)
		{
			u.update(elapsed);
		}

		// this.img =
		// this.getPANEL().createImage(MyWindow.getInstance().getWidth(),
		// MyWindow.getInstance().getHeight());
		// this.g = (Graphics2D) this.img.getGraphics();
		//
		// this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		// this.g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//
		// this.g.setColor(Color.BLACK);
		// this.g.fillRect(0, 0, (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
		// (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		// this.g.translate(this.offsetX, this.offsetY);
		//
		// this.g.setColor(Color.WHITE);
		// this.g.fillRect(0, 0, 1024, 1024);

		for (final GameObject d : temp)
		{
			d.output();
		}

		this.getCtrlFactory().graphicsControllerInstance().dispose();

		// this.getPANEL().getGraphics().drawImage(this.img, 0, 0, null);
		// Toolkit.getDefaultToolkit().sync();
	}

	public CtrlFactory getCtrlFactory()
	{
		return this.ctrlFactory;
	}
}
