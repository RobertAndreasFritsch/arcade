package games.zombies;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import games.utils.Seat;

public class Zombies extends MyGame {

	private Ground ground;
	private ArrayList<Rectangle> rects;

	public Zombies(JPanel PANEL, KeyRequest KEYS, String... args) {
		super(PANEL, KEYS, args);
		rects = new ArrayList<Rectangle>();

		add((ground = new Ground(0, 0, 1024, 1024)));

		if (Seat.P1.isPlaying()) {
			add(new Player(100, 100, Seat.P1, this));
		}
		if (Seat.P2.isPlaying()) {
			add(new Player(100, 100, Seat.P2, this));
		}
		if (Seat.P3.isPlaying()) {
			add(new Player(100, 100, Seat.P3, this));
		}
		if (Seat.P4.isPlaying()) {
			add(new Player(100, 100, Seat.P4, this));
		}
	}

	public Ground getGround() {
		return ground;
	}

}
