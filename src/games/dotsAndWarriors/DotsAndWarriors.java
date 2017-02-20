package games.dotsAndWarriors;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.implementation.MyWindow;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;
import games.dotsAndWarriors.actors.GameSubject;
import games.dotsAndWarriors.actors.bosses.Boss;
import games.dotsAndWarriors.actors.player.Warrior;

public class DotsAndWarriors extends MyGame {

	private final List<GameSubject> subjects = new ArrayList<GameSubject>();
	private final List<GameSubject> warriors = new ArrayList<GameSubject>();
	private final List<GameSubject> bosses = new ArrayList<GameSubject>();

	private final int halfWidth = (int) (MyWindow.getInstance().getSize().width * .5);
	private final int halfHeight = (int) (MyWindow.getInstance().getSize().height * .5);

	private final int top = (int) (Warrior.startSize * .5);
	private final int right = MyWindow.getInstance().getSize().width - (int) (Warrior.startSize * .5);
	private final int bottom = MyWindow.getInstance().getSize().height - (int) (Warrior.startSize * .5);
	private final int left = (int) (Warrior.startSize * .5);

	public DotsAndWarriors(JPanel PANEL, KeyRequest KEYS) {
		super(PANEL, KEYS);

		// creating a background object that prints out the background image
		add(new Background());
		add(new Control(this, KEYS, warriors, bosses));

		// creating all players that are playing
		if (Seat.P1.isPlaying()) {
			Image img = Toolkit.getDefaultToolkit().getImage("res/games/daw/graphics/player/player1.png");
			warriors.add(new Warrior(KEYS, halfWidth, top, Seat.P1, img, warriors, bosses, subjects, 90));
		}

		if (Seat.P2.isPlaying()) {
			Image img = Toolkit.getDefaultToolkit().getImage("res/games/daw/graphics/player/player2.png");
			warriors.add(new Warrior(KEYS, right, halfHeight, Seat.P2, img, warriors, bosses, subjects, 180));
		}

		if (Seat.P3.isPlaying()) {
			Image img = Toolkit.getDefaultToolkit().getImage("res/games/daw/graphics/player/player3.png");
			warriors.add(new Warrior(KEYS, halfWidth, bottom, Seat.P3, img, warriors, bosses, subjects, 270));
		}

		if (Seat.P4.isPlaying()) {
			Image img = Toolkit.getDefaultToolkit().getImage("res/games/daw/graphics/player/player4.png");
			warriors.add(new Warrior(KEYS, left, halfHeight, Seat.P4, img, warriors, bosses, subjects, 0));
		}

		// creating the boss
		Image bossImg = Toolkit.getDefaultToolkit().getImage("res/games/daw/graphics/bosses/boss1.png");
		bosses.add(new Boss(halfWidth, halfHeight, bossImg, bosses, warriors, subjects));

		subjects.addAll(bosses);
		subjects.addAll(warriors);

		addAll(subjects);
	}
}
