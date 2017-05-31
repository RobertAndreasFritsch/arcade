package games.bomberman;

import com.arcade.utils.Seat;
import com.game.MyGame;
import com.game.ctrl.CtrlFactory;

//Alex war hier 30.02.2017
//Luca war auch hier 1.2.2017

public class Bomberman extends MyGame
{
	/**
	 *
	 */
	private static final long	serialVersionUID		= 7854511732333935062L;
	public static final int		BLOCK_DESTROY_SCORE	= 1;
	public static final int		PLAYER_KILL_SCORE		= 5;
	public static final int		PLAYER_SUICIDE_SCORE	= -1;

	private final Field			f;
	public Player					Player1, Player2, Player3, Player4;

	public Bomberman(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);

		this.f = new Field();

		if (Seat.P3.isPlaying())
		{
			this.Player3 = new Player(this, this.getKEYS(), Seat.P3_PlayerView, this.f, 512, 768);
		}
		if (Seat.P4.isPlaying())
		{
			this.Player4 = new Player(this, this.getKEYS(), Seat.P4_PlayerView, this.f, 256, 512);
		}
		if (Seat.P1.isPlaying())
		{
			this.Player1 = new Player(this, this.getKEYS(), Seat.P1_PlayerView, this.f, 512, 256);
		}
		if (Seat.P2.isPlaying())
		{
			this.Player2 = new Player(this, this.getKEYS(), Seat.P2_PlayerView, this.f, 768, 512);
		}

		this.add(new Background());
		this.add(this.f);
		if (Seat.P1.isPlaying())
		{
			this.add(this.Player1);
		}
		if (Seat.P2.isPlaying())
		{
			this.add(this.Player2);
		}
		if (Seat.P3.isPlaying())
		{
			this.add(this.Player3);
		}
		if (Seat.P4.isPlaying())
		{
			this.add(this.Player4);
		}

		this.add(new GameOverWaiter(this, this.getKEYS()));

	}

	public Field getField()
	{
		return this.f;
	}

}
