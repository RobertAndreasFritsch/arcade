package games.kickthemoff;

import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;

public class Kickthemoff extends MyGame {
	private ArrayList<Player> players = new ArrayList<Player>();

	public Kickthemoff(JPanel PANEL, KeyRequest KEYS) {
		super(PANEL, KEYS);

		Ground g = new Ground(512, 512, 450);

		add(new Background());
		add(g);

		if (Seat.P1.isPlaying()) {
			players.add(new Player(this, Seat.P1, 512, 450, KEYS, g));
		}
		if (Seat.P2.isPlaying()) {
			players.add(new Player(this, Seat.P2, 574, 512, KEYS, g));
		}
		if (Seat.P3.isPlaying()) {
			players.add(new Player(this, Seat.P3, 512, 574, KEYS, g));
		}
		if (Seat.P4.isPlaying()) {
			players.add(new Player(this, Seat.P4, 450, 512, KEYS, g));
		}

		addAll(players);
		add(new Exit(this));
		add(new GameOverWaiter(this));
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

}
