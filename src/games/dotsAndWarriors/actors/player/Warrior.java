package games.dotsAndWarriors.actors.player;

import java.awt.Image;
import java.util.List;

import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;
import games.dotsAndWarriors.actors.GameSubject;

public class Warrior extends GameSubject implements ProceedsInput {

	private final KeyRequest KEYS;
	private Seat player;

	public Warrior(KeyRequest KEYS, int x, int y, Seat player, Image img, List<GameSubject> friends,
			List<GameSubject> enemies, List<GameSubject> subjects, double angle) {
		super(x, y, img, friends, enemies, subjects);

		setAngle(angle);
		setSpeed(startSpeed + 3);

		this.KEYS = KEYS;
		this.player = player;
	}

	@Override
	public void processInput() {
		if (isDead())
			return;

		if (KEYS.isPressed(player.LEFT)) {
			setTurningLeft(true);
		}

		if (KEYS.isPressed(player.RIGHT)) {
			setTurningRight(true);
		}

		if (KEYS.isPressed(player.BTN1)) {
			setAttacking(true);
		}

		if (KEYS.isPressed(player.DOWN)) {
			setRunningBackward(true);
		}

		if (KEYS.isPressed(player.UP)) {
			setRunningForward(true);
		}
	}
}
