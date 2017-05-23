package games.kickthemoff;

import java.awt.event.KeyEvent;

import environment.model.gameobject.ProceedsInput;

public class Exit implements ProceedsInput {
	private Kickthemoff game;

	public Exit(Kickthemoff game) {
		this.game = game;
	}

	@Override
	public void processInput() {
		if (game.getKEYS().isPressed(KeyEvent.VK_M)) {
			game.setRunning(false);
		}
	}

}
