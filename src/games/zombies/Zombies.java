package games.zombies;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import games.utils.Seat;

public class Zombies extends MyGame{

	public Zombies(JPanel PANEL, KeyRequest KEYS, String... args) {
		super(PANEL, KEYS, args);
		
		add(new Ground());
		add(new Player(100, 100, Seat.P1, this));
		add(new Player(100, 100, Seat.P2, this));
		add(new Player(100, 100, Seat.P3, this));
		add(new Player(100, 100, Seat.P4, this));
	}

}
