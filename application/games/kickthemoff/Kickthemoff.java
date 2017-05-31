package games.kickthemoff;

import java.util.ArrayList;

import com.arcade.utils.Seat;
import com.game.Game;
import com.game.SimpleGame;
import com.game.ctrl.CtrlFactory;

public class Kickthemoff extends SimpleGame
{
	/**
	 *
	 */
	private static final long			serialVersionUID	= -5004087972779309022L;

	private static final int			WINS_NEEDED			= 3;

	private final ArrayList<Player>	players				= new ArrayList<>();

	public Kickthemoff(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);

		final Ground g = new Ground(512, 512, 425);

		this.add(new Background());
		this.add(g);

		if (Seat.P1.isPlaying())
		{
			this.players.add(new Player(this, Seat.P1_PlayerView, 512, 450, g));
			this.add(new PlayerScoreView(Kickthemoff.WINS_NEEDED, 512, 25, 0, this.players.get(this.players.size() - 1)));
		}
		if (Seat.P2.isPlaying())
		{
			this.players.add(new Player(this, Seat.P2_PlayerView, 574, 512, g));
			this.add(
			      new PlayerScoreView(Kickthemoff.WINS_NEEDED, 999, 512, 90, this.players.get(this.players.size() - 1)));
		}
		if (Seat.P3.isPlaying())
		{
			this.players.add(new Player(this, Seat.P3_PlayerView, 512, 574, g));
			this.add(
			      new PlayerScoreView(Kickthemoff.WINS_NEEDED, 512, 999, 180, this.players.get(this.players.size() - 1)));
		}
		if (Seat.P4.isPlaying())
		{
			this.players.add(new Player(this, Seat.P4_PlayerView, 450, 512, g));
			this.add(
			      new PlayerScoreView(Kickthemoff.WINS_NEEDED, 25, 512, 270, this.players.get(this.players.size() - 1)));
		}

		this.addAll(this.players);
		this.add(new Exit(this));
		this.add(new GameOverWaiter(this));
	}

	@Override
	public Game getNextGame()
	{
		for (final Player p : this.players)
		{
			if (p.getWins() >= Kickthemoff.WINS_NEEDED)
			{

				for (final Player p2 : this.players)
				{
					p2.setWins(0);
				}

				p.getSeat().setScore(p.getSeat().getScore() + 150);
				return super.getNextGame();
			}
		}

		return new Kickthemoff(this.getCtrlFactory());
	}

	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}

}
