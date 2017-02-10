package games.dotsAndWarriors;

import java.awt.event.KeyEvent;
import java.util.List;

import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.dotsAndWarriors.actors.GameSubject;

public class Control implements Updateable, ProceedsInput {

	private final Game GAME;
	private List<GameSubject> warriors;
	private List<GameSubject> enemies;
	private KeyRequest KEYS;

	public Control(final Game GAME, KeyRequest KEYS, List<GameSubject> warriors, List<GameSubject> enemies) {
		this.GAME = GAME;
		this.KEYS = KEYS;
		this.warriors = warriors;
		this.enemies = enemies;
	}

	@Override
	public void processInput() {
		if (KEYS.isPressed(KeyEvent.VK_M)) {
			GAME.setRunning(false);
		}
	}

	@Override
	public void update(long elapsed) {

		// look up if everyone is dead
		boolean allDeath = true;

		for (GameSubject w : warriors) {
			if (w.getHealth() > 0) {
				allDeath = false;
				break;
			}
		}

		if (allDeath) {
			GAME.setRunning(false);
		}

		// look up if the enemies is dead
		boolean enemiesDeath = true;

		for (GameSubject g : enemies) {
			if (g.getHealth() > 0) {
				enemiesDeath = false;
				break;
			}
		}

		if (enemiesDeath) {
			GAME.setRunning(false);
		}
	}
}