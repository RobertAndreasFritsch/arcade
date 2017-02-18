package games.kickthemoff;

import environment.model.gameobject.Updateable;

public class GameOverWaiter implements Updateable {
	private Kickthemoff game;

	public GameOverWaiter(Kickthemoff game) {
		this.game = game;
	}

	@Override
	public void update(long elapsed) {
		int alive = 0;
		Player winner = null;
		for (Player p : game.getPlayers()) {
			if (!p.isDead()) {
				alive++;
				winner = p;
			}
		}
		if (alive == 1) {
			game.setRunning(false);
			winner.getSeat().setScore(winner.getSeat().getScore() + 100);
		}
	}

}
