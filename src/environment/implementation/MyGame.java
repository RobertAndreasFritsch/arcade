package environment.implementation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import environment.launch.HighScoreProcessor;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;

public class MyGame implements Game {

	public static final MyGameConstructor DefaultGame = HighScoreProcessor::new;

	protected final List<Drawable> DRAWABLES = new ArrayList<Drawable>();
	protected final List<Updateable> UPDATEABLES = new ArrayList<Updateable>();
	protected final List<ProceedsInput> PROCEEDINGINPUTS = new ArrayList<ProceedsInput>();

	protected final JPanel PANEL;
	protected final KeyRequest KEYS;

	private boolean running = false;

	Image img;

	Graphics2D g;
	private MyGameConstructor nextGame = DefaultGame;

	public MyGame(final JPanel PANEL, final KeyRequest KEYS, String... args) {
		this.PANEL = PANEL;
		this.KEYS = KEYS;
	}

	@Override
	public void add(Object gameObject) {
		if (gameObject instanceof ProceedsInput) {
			this.PROCEEDINGINPUTS.add((ProceedsInput) gameObject);
		}
		if (gameObject instanceof Updateable) {
			this.UPDATEABLES.add((Updateable) gameObject);
		}
		if (gameObject instanceof Drawable) {
			this.DRAWABLES.add((Drawable) gameObject);
		}
	}

	@Override
	public void addAll(List<?> gameObjects) {
		for (Object o : gameObjects) {
			add(o);
		}
	}

	public void exitGame() {
		setNextGame(HighScoreProcessor::new);
		setRunning(false);
	}

	public List<Drawable> getDRAWABLES() {
		return DRAWABLES;
	}

	public KeyRequest getKEYS() {
		return KEYS;
	}

	@Override
	public Game getNextGame() {
		return nextGame.MyGame(PANEL, KEYS, this.getClass().getSimpleName());
	}

	public JPanel getPANEL() {
		return PANEL;
	}

	public List<ProceedsInput> getPROCEEDINGINPUTS() {
		return PROCEEDINGINPUTS;
	}

	public List<Updateable> getUPDATEABLES() {
		return UPDATEABLES;
	}

	@Override
	public boolean isRunning() {
		return this.running;
	}

	@Override
	public void remove(Object gameObject) {
		if (gameObject instanceof ProceedsInput) {
			this.PROCEEDINGINPUTS.remove(gameObject);
		}
		if (gameObject instanceof Updateable) {
			this.UPDATEABLES.remove(gameObject);
		}
		if (gameObject instanceof Drawable) {
			this.DRAWABLES.remove(gameObject);
		}
	}

	@Override
	public void removeAll(List<?> gameObjects) {
		for (Object o : gameObjects) {
			remove(o);
		}
	}

	private void setNextGame(MyGameConstructor nextGame) {
		this.nextGame = nextGame;
	}

	@Override
	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void tick(long elapsed) {

		for (ProceedsInput i : new ArrayList<ProceedsInput>(this.PROCEEDINGINPUTS)) {
			i.processInput();
		}
		for (Updateable u : new ArrayList<Updateable>(this.UPDATEABLES)) {
			u.update(elapsed);
		}

		img = this.getPANEL().createImage(MyWindow.getInstance().getWidth(), MyWindow.getInstance().getHeight());
		g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Drawable d : new ArrayList<Drawable>(this.DRAWABLES)) {
			d.draw(g);
		}

		this.getPANEL().getGraphics().drawImage(img, 0, 0, null);
	}
}
