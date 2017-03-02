package environment.implementation;

import environment.model.Game;
import environment.model.GameLoop;

public class MyGameLoop implements GameLoop {

	public static final long MSPF = 15; // TODO hard coded
	protected Game activeGame;

	public MyGameLoop(Game game) {
		this.setActiveGame(game);
	}

	@Override
	public Game getActiveGame() {
		return this.activeGame;
	}

	@Override
	public void run() {
		
		long now = System.currentTimeMillis();
		long next;

		long elapsed = 0;

		while (this.activeGame.isRunning()) {

			next = System.currentTimeMillis();
			elapsed = next - now;
			now = next;

			if (elapsed < MyGameLoop.MSPF) {

				now -= elapsed;

				try {
					Thread.sleep(MyGameLoop.MSPF - elapsed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				this.activeGame.tick(elapsed);
			}
		}
		
		this.setActiveGame(activeGame.getNextGame());
	}

	@Override
	public void setActiveGame(Game activeGame) {
		if (this.activeGame != null)
			this.activeGame.setRunning(false);

		this.activeGame = activeGame;
		this.start();
	}

	@Override
	public void start() {
		if (this.activeGame.isRunning())
			return;

		this.activeGame.setRunning(true);
		new Thread(this).start();
	}
}
