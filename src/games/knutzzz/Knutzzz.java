package games.knutzzz;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;
import games.knutzzz.control.WinnerControl;
import games.knutzzz.graphics.Dispenser;
import games.knutzzz.graphics.Floor;
import games.knutzzz.graphics.Goal;

/**
 * 
 * @author Joerg (Yogi) Kuhle
 * @version 1.1
 *
 */

public class Knutzzz extends MyGame {

	public int step = 0;
	KnutzzzGameObject_Dispenser dispenser;
	public KnutzzzGameObject_Ball ball;
	Sound sound = new Sound();
	KnutzzzGameObject_ScoreDisplay scoreDisplays[];

	private boolean gameRunning = false;

	// private static Knutzzz instance;

	public Knutzzz(JPanel panel, KeyRequest KEYS) {
		super(panel, KEYS);

		add(new Floor());

		// instance = this;
		dispenser = new KnutzzzGameObject_Dispenser(this);
		add(dispenser);
		scoreDisplays = new KnutzzzGameObject_ScoreDisplay[4];
		scoreDisplays[0] = new KnutzzzGameObject_ScoreDisplay(this, 0); // oben
		scoreDisplays[1] = new KnutzzzGameObject_ScoreDisplay(this, 1); // rechts
		scoreDisplays[2] = new KnutzzzGameObject_ScoreDisplay(this, 2); // unten
		scoreDisplays[3] = new KnutzzzGameObject_ScoreDisplay(this, 3); // links
		add(scoreDisplays[0]);
		add(scoreDisplays[1]);
		add(scoreDisplays[2]);
		add(scoreDisplays[3]);

		add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_4, KeyEvent.VK_6, KeyEvent.VK_7, 0, KEYS, Seat.P1.isPlaying()));
		add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_Q, 1, KEYS, Seat.P2.isPlaying()));
		add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_U, 2, KEYS, Seat.P3.isPlaying()));
		add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_F, KeyEvent.VK_H, KeyEvent.VK_R, 3, KEYS, Seat.P4.isPlaying()));

		add(new Dispenser(this, dispenser));
		add(new Goal());

		add(new WinnerControl(this, KEYS));

	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

}
