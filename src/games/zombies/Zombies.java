package games.zombies;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import games.utils.Seat;

public class Zombies extends MyGame {

	public Zombies(final JPanel PANEL, final KeyRequest KEYS, final String... args) {
		super(PANEL, KEYS, args);

		this.add(new Ground());
		this.add(new Player(100, 100, Seat.P1, this));
		this.add(new Player(100, 100, Seat.P2, this));
		this.add(new Player(100, 100, Seat.P3, this));
		this.add(new Player(100, 100, Seat.P4, this));
	}

}
