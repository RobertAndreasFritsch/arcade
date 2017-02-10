package environment.implementation;

import environment.model.Game;
import environment.model.GameLoop;

public class MyGameLoop implements GameLoop {

	public static final long MSPF = 10; // TODO hard coded
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
		long next = 0;
		long elapsed = 0;

		while (activeGame.isRunning()) {

			next = System.currentTimeMillis();
			elapsed = next - now;
			now = next;

			this.activeGame.tick(elapsed);

			try {
				Thread.sleep(MSPF - (elapsed > MSPF ? MSPF : elapsed)); // prognose
																		// für
																		// den
																		// nächsten
																		// wait
			} catch (InterruptedException e) {
				e.printStackTrace();
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
