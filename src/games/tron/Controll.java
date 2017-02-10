package games.tron;

import java.awt.event.KeyEvent;

import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;

public class Controll implements Updateable, ProceedsInput {

	private KeyRequest KEYS;
	private Game game;

	public Controll(Game game, KeyRequest KEYS) {
		this.game = game;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput() {
		if (KEYS.isPressed(KeyEvent.VK_M))
			game.setRunning(false);
	}

	@Override
	public void update(long elapsed) {
	}
}
