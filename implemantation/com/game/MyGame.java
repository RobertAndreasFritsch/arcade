package com.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.arcade.Menu;
import com.arcade.presentation.GameConstructor;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.KeyRequest;

public class MyGame extends ArrayList<GameObject> implements Game
{
	private static final long				serialVersionUID	= -564173017564334081L;

	public static final GameConstructor	DefaultGame			= Menu::new;

	// protected final List<Drawable> DRAWABLES = new ArrayList<>();
	// protected final List<Updateable> UPDATEABLES = new ArrayList<>();
	// protected final List<ProceedsInput> PROCEEDINGINPUTS = new ArrayList<>();

	private boolean							running				= false;

	Image											img;

	Graphics2D									g;
	private GameConstructor					nextGame				= MyGame.DefaultGame;

	private final int							offsetX;
	private final int							offsetY;

	private CtrlFactory ctrlFactory;

	public MyGame(CtrlFactory ctrlFactory)
	{
		this.ctrlFactory = ctrlFactory;
		
		this.offsetX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 1024) >> 1;
		this.offsetY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 1024) >> 1;
	}

	// @Override
	// public void add(final Object gameObject)
	// {
	// if (gameObject instanceof ProceedsInput)
	// {
	// this.PROCEEDINGINPUTS.add((ProceedsInput) gameObject);
	// }
	// if (gameObject instanceof Updateable)
	// {
	// this.UPDATEABLES.add((Updateable) gameObject);
	// }
	// if (gameObject instanceof Drawable)
	// {
	// this.DRAWABLES.add((Drawable) gameObject);
	// }
	// }

	// @Override
	// public void addAll(final List<?> gameObjects)
	// {
	// for (final Object o : gameObjects)
	// {
	// this.add(o);
	// }
	// }

	public void exitGame()
	{
		this.setNextGame(Menu::new);
		this.setRunning(false);
	}

	// public List<Drawable> getDRAWABLES()
	// {
	// return this.DRAWABLES;
	// }

	public KeyRequest getKEYS()
	{
		return this.ctrlFactory.keyRequestInstance();
	}

	@Override
	public Game getNextGame()
	{
		return this.nextGame.newGame(getCtrlFactory());
	}

//	public JPanel getPANEL()
//	{
//		return this.ctrlFactory.getGraphics();
//	}

	// /**
	// * @return
	// * @deprecated
	// */
	// public List<ProceedsInput> getPROCEEDINGINPUTS()
	// {
	//// return this.PROCEEDINGINPUTS;
	// return null;
	// }

	// /**
	// * @return
	// * @deprecated
	// */
	// public List<Updateable> getUPDATEABLES()
	// {
	// // return this.UPDATEABLES;
	// return null;
	// }

	@Override
	public boolean isRunning()
	{
		return this.running;
	}

	// @Override
	// public boolean remove(final Object gameObject)
	// {
	// remove(gameObject);
	// if (gameObject instanceof ProceedsInput)
	// {
	// this.PROCEEDINGINPUTS.remove(gameObject);
	// }
	// if (gameObject instanceof Updateable)
	// {
	// this.UPDATEABLES.remove(gameObject);
	// }
	// if (gameObject instanceof Drawable)
	// {
	// this.DRAWABLES.remove(gameObject);
	// }
	// }

	// @Override
	// public void removeAll(final List<?> gameObjects)
	// {
	// for (final Object o : gameObjects)
	// {
	// this.remove(o);
	// }
	// }

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

//		this.img = this.getPANEL().createImage(MyWindow.getInstance().getWidth(), MyWindow.getInstance().getHeight());
//		this.g = (Graphics2D) this.img.getGraphics();
//
//		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		this.g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//
//		this.g.setColor(Color.BLACK);
//		this.g.fillRect(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
//		      (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
//		this.g.translate(this.offsetX, this.offsetY);
//
//		this.g.setColor(Color.WHITE);
//		this.g.fillRect(0, 0, 1024, 1024);

		for (final GameObject d : this)
		{
			d.output();
		}
		
		getCtrlFactory().graphicsControllerInstance().dispose();

//		this.getPANEL().getGraphics().drawImage(this.img, 0, 0, null);
//		Toolkit.getDefaultToolkit().sync();
	}

	public CtrlFactory getCtrlFactory()
	{
		return ctrlFactory;
	}
}
