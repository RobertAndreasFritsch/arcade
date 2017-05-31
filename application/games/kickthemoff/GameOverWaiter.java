package games.kickthemoff;

import com.game.Updateable;

public class GameOverWaiter implements Updateable
{
	private final Kickthemoff game;

	public GameOverWaiter(final Kickthemoff game)
	{
		this.game = game;
	}

	@Override
	public void update(final long elapsed)
	{
		int alive = 0;
		Player winner = null;
		for (final Player p : this.game.getPlayers())
		{
			if (!p.isDead())
			{
				alive++;
				winner = p;
			}
		}
		if (alive == 1)
		{
			this.game.setRunning(false);
			winner.getSeat().setScore(winner.getSeat().getScore() + 100);
			winner.setWins(winner.getWins() + 1);
		}
	}

}
