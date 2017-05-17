package games.kickthemoff;

import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;

public class Kickthemoff extends MyGame {
	private static final int WINS_NEEDED = 3;

	private ArrayList<Player> players = new ArrayList<Player>();

	public Kickthemoff(JPanel PANEL, KeyRequest KEYS) {
		super(PANEL, KEYS);

		Ground g = new Ground(512, 512, 425);

		add(new Background());
		add(g);

		if (Seat.P1.isPlaying()) {
			players.add(new Player(this, Seat.P1_PlayerView, 512, 450, g));
			add(new PlayerScoreView(WINS_NEEDED, 512, 25, 0, players.get(players.size() - 1)));
		}
		if (Seat.P2.isPlaying()) {
			players.add(new Player(this, Seat.P2_PlayerView, 574, 512, g));
			add(new PlayerScoreView(WINS_NEEDED, 999, 512, 90, players.get(players.size() - 1)));
		}
		if (Seat.P3.isPlaying()) {
			players.add(new Player(this, Seat.P3_PlayerView, 512, 574, g));
			add(new PlayerScoreView(WINS_NEEDED, 512, 999, 180, players.get(players.size() - 1)));
		}
		if (Seat.P4.isPlaying()) {
			players.add(new Player(this, Seat.P4_PlayerView, 450, 512, g));
			add(new PlayerScoreView(WINS_NEEDED, 25, 512, 270, players.get(players.size() - 1)));
		}

		addAll(players);
		add(new Exit(this));
		add(new GameOverWaiter(this));
	}

	@Override
	public Game getNextGame() {
		for (Player p : players) {
			if (p.getWins() >= WINS_NEEDED) {

				for (Player p2 : players) {
					p2.setWins(0);
				}

				p.getSeat().setScore(p.getSeat().getScore() + 150);
				return super.getNextGame();
			}
		}

		return new Kickthemoff(getPANEL(), getKEYS());
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

}
