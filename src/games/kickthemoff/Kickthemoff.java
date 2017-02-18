package games.kickthemoff;

import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;

public class KickThemOff extends MyGame {
	private static int roundsToGo = 5;
	private ArrayList<Player> players = new ArrayList<Player>();

	public KickThemOff(JPanel PANEL, KeyRequest KEYS) {
		super(PANEL, KEYS);

		Ground g = new Ground(512, 512, 450);

		add(new Background());
		add(g);

		if (Seat.P1.isPlaying()) {
			players.add(new Player(this, Seat.P1_PlayerView, 512, 450, KEYS, g));
		}
		if (Seat.P2.isPlaying()) {
			players.add(new Player(this, Seat.P2_PlayerView, 574, 512, KEYS, g));
		}
		if (Seat.P3.isPlaying()) {
			players.add(new Player(this, Seat.P3_PlayerView, 512, 574, KEYS, g));
		}
		if (Seat.P4.isPlaying()) {
			players.add(new Player(this, Seat.P4_PlayerView, 450, 512, KEYS, g));
		}

		addAll(players);
		add(new Exit(this));
		add(new GameOverWaiter(this));
	}
	
	@Override
	public Game getNextGame(){
		if(--roundsToGo <= 0){
			roundsToGo = 5;
			return super.getNextGame();
		}
		return new KickThemOff(getPANEL(),getKEYS());
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

}
