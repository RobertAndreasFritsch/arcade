package games.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.Game;
import com.game.Updateable;

public class Scores implements Updateable, Drawable
{

	public static final Image[][] scoreImages = new Image[4][10];

	static
	{
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				try
				{
					Scores.scoreImages[i][j] = Toolkit.getDefaultToolkit()
					      .getImage(new File("res/games/tankies/img/score/" + i + "" + j + ".png").toURI().toURL());
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	int						cp						= 0;
	int						c						= 0;

	Image[]					ScoreImageArray	= new Image[4];
	Image						score;
	Image						landrover;
	int						maxScore				= 9;
	int						WinnerPlayer		= -1;
	boolean					winner				= false;
	boolean					matchwinner			= false;
	boolean					draw					= false;
	boolean					RoundOver			= false;

	Tank[]					TankArray;
	BulletFactory			bulletFactory;
	Player[]					PlayerArray;
	private final Game	game;

	public Scores(final Tank[] tankArray, final Player[] player, final BulletFactory bulletFactory, final Game game)
	{

		super();
		this.TankArray = tankArray;
		this.bulletFactory = bulletFactory;
		this.PlayerArray = player;
		this.game = game;

		try
		{
			this.landrover = Toolkit.getDefaultToolkit()
			      .getImage(new File("res\\games\\tankies\\img\\Score\\Rover.png").toURI().toURL());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void draw(final Graphics2D g)
	{
		final AffineTransform originalTransform = g.getTransform();

		if (this.RoundOver)
		{

			g.rotate(Math.toRadians(this.buffertime * .05 % 360), 516, 516);
			g.drawImage(this.landrover, 516 - 400 /* TODO get image width */,
			      516 - 200/* TODO get image height */, null);
			g.setTransform(originalTransform);
		}

		for (int i = 0; i < 4; i++)
		{

			final Seat seat = Seat.values()[i];
			final int playerScore = this.PlayerArray[i].score % 10;

			g.rotate(seat.getTransform(), 516, 516);
			g.drawImage(Scores.scoreImages[i][playerScore], 0, 1048 - 64, null);
			g.setTransform(originalTransform);
		}
	}

	long	MAX_BUFFER_TIME	= 6000;
	long	buffertime			= 0;

	@Override
	public void update(final long elapsed)
	{// Testen ob noch jmd am Leben

		if (this.RoundOver)
		{
			this.buffertime += elapsed;

			if (this.buffertime >= this.MAX_BUFFER_TIME)
			{
				this.game.setRunning(false);
			}

			return;
		}

		int dead = 0;
		for (int c = 0; c < 4; c++)
		{
			if (this.TankArray[c] == null)
			{
				dead++;
			}
		}

		// Testen ob noch Kugeln fliegen
		// counter = ;

		if (this.bulletFactory.size() == 0 && dead >= 3)
		{
			// Ende der Runde Testen

			// Gewinner
			if (dead == 3)
			{
				// Gewinner finden
				for (int i = 0; i < 4; i++)
				{
					if (this.TankArray[i] != null)
					{
						this.PlayerArray[i].score = this.PlayerArray[i].score + 2;
						this.WinnerPlayer = i;
					}
				}
			}

			this.RoundOver = true;
		}
	}
}
