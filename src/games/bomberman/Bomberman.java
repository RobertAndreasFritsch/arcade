package games.bomberman;

import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.Seat;

//Alex war hier 30.02.2017
//Luca war auch hier 1.2.2017

public class Bomberman extends MyGame {
	public static final int BLOCK_DESTROY_SCORE = 1;
	public static final int PLAYER_KILL_SCORE = 5;
	public static final int PLAYER_SUICIDE_SCORE = -1;

	private Field f;
	public Player Player1, Player2, Player3, Player4;

	public Bomberman(JPanel PANEL, KeyRequest KEYS) {
		super(PANEL, KEYS);

		f = new Field();

		if (Seat.P3.isPlaying()) {
			Player3 = new Player(this, KEYS, Seat.P3_PlayerView, f, 512, 768);
		}
		if (Seat.P4.isPlaying()) {
			Player4 = new Player(this, KEYS, Seat.P4_PlayerView, f, 256, 512);
		}
		if (Seat.P1.isPlaying()) {
			Player1 = new Player(this, KEYS, Seat.P1_PlayerView, f, 512, 256);
		}
		if (Seat.P2.isPlaying()) {
			Player2 = new Player(this, KEYS, Seat.P2_PlayerView, f, 768, 512);
		}

		add(new Background());
		add(f);
		if (Seat.P1.isPlaying()) {
			add(Player1);
		}
		if (Seat.P2.isPlaying()) {
			add(Player2);
		}
		if (Seat.P3.isPlaying()) {
			add(Player3);
		}
		if (Seat.P4.isPlaying()) {
			add(Player4);
		}

		if (Seat.P3.isPlaying()) {
			add(new ScoreBar(this, 512 - 125, 900, 250, 15, 0));
		}
		if (Seat.P4.isPlaying()) {
			add(new ScoreBar(this, 100 + 15, 512 - 125, 250, 15, 90));
		}
		if (Seat.P1.isPlaying()) {
			add(new ScoreBar(this, 512 + 125, 100 + 15, 250, 15, 180));
		}
		if (Seat.P2.isPlaying()) {
			add(new ScoreBar(this, 900, 512 + 125, 250, 15, 270));
		}

		add(new GameOverWaiter(this, KEYS));

	}

	public ArrayList<Drawable> getDrawables() {
		return (ArrayList<Drawable>) DRAWABLES;
	}

	public Field getField() {
		return f;
	}

}
