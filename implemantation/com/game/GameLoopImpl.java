package com.game;

public class GameLoopImpl implements GameLoop
{

	public static final long	MSPF	= 15;	// TODO hard coded
	protected Game					activeGame;

	public GameLoopImpl(final Game game)
	{
		this.setActiveGame(game);
	}

	@Override
	public Game getActiveGame()
	{
		return this.activeGame;
	}

	@Override
	public void run()
	{

		long now = System.currentTimeMillis();
		long elapsed;

		while (this.activeGame.isRunning())
		{

			elapsed = System.currentTimeMillis() - now;

			if (elapsed >= GameLoopImpl.MSPF)
			{
				this.activeGame.tick(elapsed);
				now += elapsed;

			}
			else
			{
				try
				{
					Thread.sleep(GameLoopImpl.MSPF - elapsed);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

		this.setActiveGame(this.activeGame.getNextGame());
	}

	@Override
	public void setActiveGame(final Game activeGame)
	{
		if (this.activeGame != null)
		{
			this.activeGame.setRunning(false);
		}

		this.activeGame = activeGame;
		this.start();
	}

	@Override
	public void start()
	{
		if (this.activeGame.isRunning()) { return; }

		this.activeGame.setRunning(true);
		new Thread(this).start();
	}
}
