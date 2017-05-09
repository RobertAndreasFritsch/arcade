package environment.launch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.implementation.MyWindow;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

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

class ProgressAnimation implements Drawable, ProceedsInput,Updateable {
	boolean[] players = new boolean[4]; 
	private long progress = 0;
	private long timeout = 5000;
	private double angle = 0;
	private KeyRequest Keys;
	private Game game;
	int blub=0;
	
	public ProgressAnimation(KeyRequest Keys, final Game game) {
		this.Keys = Keys;
		this.game = game;
	}
	

	@Override
	public void update(long elapsed) {
		progress += elapsed;
		if(progress >= timeout){
			progress = timeout;
			
			game.setRunning(false);
		}
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, MyWindow.getInstance().getSize().width, MyWindow.getInstance().getSize().height);		

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

		angle = (double) ( progress ) / timeout * 360;

		Shape oval = new Ellipse2D.Double(-70, -70, 140, 140);

		g.translate(512, 512);
		g.setClip(oval);
		g.fillPolygon(new int[] { x1, x2, x3, x4, x5, 0 }, new int[] { y1, y2, y3, y4, y5, 0 }, 6);
		g.translate(-512, -512);
		g.setClip(new Rectangle(0,0,1024,1024));
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