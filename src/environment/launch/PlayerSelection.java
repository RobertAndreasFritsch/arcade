package environment.launch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;

public class PlayerSelection extends MyGame {
	private ProgressAnimation anim;
	private Presentation presentation;

	public PlayerSelection(JPanel PANEL, KeyRequest KEYS, Presentation presentation) {
		super(PANEL, KEYS);

		this.presentation = presentation;
		anim = new ProgressAnimation(KEYS, this);
		add(anim);
	}

	@Override
	public Game getNextGame() {
		int counter = 0;
		for (boolean b : anim.players) {
			if (b) {
				counter++;
			}
		}
		if (counter < 1) {
			return new Menu(PANEL, KEYS);
		}
		return new NameEntry(PANEL, KEYS, anim.players, presentation);
	}

}

class ProgressAnimation implements Drawable, ProceedsInput {
	boolean[] players = new boolean[4];
	int leftOffset = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 512;
	float progress = 0;
	long timeout = 5000;
	private float angle = 0;
	private KeyRequest Keys;

	public ProgressAnimation(KeyRequest Keys, final Game game) {
		this.Keys = Keys;
		new Thread() {
			@Override
			public void run() {
				while (progress <= 100) {
					progress += 0.25;
					try {
						Thread.sleep((long) (timeout / (100 / .25)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				game.setRunning(false);
			}
		}.start();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		g.translate(0, 0);

		if (players[0]) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(1024 / 2 - 50, 1024 - 1024 / 3 + 50, 100, 100);

		if (players[1]) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(1024 / 3 - 150, 1024 / 2 - 50, 100, 100);

		if (players[2]) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(1024 / 2 - 50, 1024 / 3 - 150, 100, 100);

		if (players[3]) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(1024 - 1024 / 3 + 50, 1024 / 2 - 50, 100, 100);

		g.setColor(Color.GRAY);
		int x1 = 0, y1 = 100, x2 = 100, y2 = 0, x3 = 0, y3 = -100, x4 = -100, y4 = 0, x5 = x1, y5 = y1;
		int tx1 = 0, ty1 = 100, tx2 = 100, ty2 = 0, tx3 = 0, ty3 = -100, tx4 = -100, ty4 = 0, tx5 = 0, ty5 = 100;
		x1 = (int) (tx1 * Math.cos(Math.toRadians(angle * 0)) - ty1 * Math.sin(Math.toRadians(angle * 0)));
		y1 = (int) (tx1 * Math.sin(Math.toRadians(angle * 0)) + ty1 * Math.cos(Math.toRadians(angle * 0)));
		x2 = (int) (tx2 * Math.cos(Math.toRadians(angle * 0.25)) - ty2 * Math.sin(Math.toRadians(angle * 0.25)));
		y2 = (int) (tx2 * Math.sin(Math.toRadians(angle * 0.25)) + ty2 * Math.cos(Math.toRadians(angle * 0.25)));
		x3 = (int) (tx3 * Math.cos(Math.toRadians(angle * 0.5)) - ty3 * Math.sin(Math.toRadians(angle * 0.5)));
		y3 = (int) (tx3 * Math.sin(Math.toRadians(angle * 0.5)) + ty3 * Math.cos(Math.toRadians(angle * 0.5)));
		x4 = (int) (tx4 * Math.cos(Math.toRadians(angle * 0.75)) - ty4 * Math.sin(Math.toRadians(angle * 0.75)));
		y4 = (int) (tx4 * Math.sin(Math.toRadians(angle * 0.75)) + ty4 * Math.cos(Math.toRadians(angle * 0.75)));
		x5 = (int) (tx5 * Math.cos(Math.toRadians(angle)) - ty5 * Math.sin(Math.toRadians(angle)));
		y5 = (int) (tx5 * Math.sin(Math.toRadians(angle)) + ty5 * Math.cos(Math.toRadians(angle)));

		angle = progress / 100 * 360;

		Shape oval = new Ellipse2D.Double(-70, -70, 140, 140);

		g.translate(512, 512);
		g.setClip(oval);
		g.fillPolygon(new int[] { x1, x2, x3, x4, x5, 0 }, new int[] { y1, y2, y3, y4, y5, 0 }, 6);
	}

	@Override
	public void processInput() {
		if (Keys.isPressed(Seat.P3.BTN1)) {
			players[0] = true;
		}
		if (Keys.isPressed(Seat.P4.BTN1)) {
			players[1] = true;
		}
		if (Keys.isPressed(Seat.P1.BTN1)) {
			players[2] = true;
		}
		if (Keys.isPressed(Seat.P2.BTN1)) {
			players[3] = true;
		}
	}

}