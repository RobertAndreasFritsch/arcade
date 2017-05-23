package games.bomberman;

import java.awt.event.KeyEvent;

import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;

public class GameOverWaiter implements Updateable, ProceedsInput {
	private Bomberman game;
	private KeyRequest KEYS;

	public GameOverWaiter(Bomberman game, KeyRequest KEYS) {
		this.game = game;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput() {
		if (KEYS.isPressed(KeyEvent.VK_M)) {
			game.setRunning(false);
		}
	}

	@Override
	public void update(long elapsed) {
		int livingPlayers = 0;
		Player winner;
		if (game.Player1 == null ? false : !game.Player1.isDead()) {
			livingPlayers++;
			winner = game.Player1;
		}
		if (game.Player2 == null ? false : !game.Player2.isDead()) {
			livingPlayers++;
			winner = game.Player2;
		}
		if (game.Player3 == null ? false : !game.Player3.isDead()) {
			livingPlayers++;
			winner = game.Player3;
		}
		if (game.Player4 == null ? false : !game.Player4.isDead()) {
			livingPlayers++;
			winner = game.Player4;
		}

		if (livingPlayers <= 1) {
			game.setRunning(false);
		}
	}

}
