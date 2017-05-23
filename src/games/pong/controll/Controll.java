package games.pong.controll;

import java.awt.event.KeyEvent;

import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;

public class Controll implements ProceedsInput {

	private Game parent;
	private KeyRequest KEYS;

	public Controll(Game parent, KeyRequest KEYS) {
		this.parent = parent;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput() {
		if (KEYS.isPressed(KeyEvent.VK_ESCAPE)) {
			parent.setRunning(false);
		}
	}

}
