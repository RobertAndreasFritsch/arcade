package games.tron;

//import java.awt.Color;
import java.awt.Graphics2D;

import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.ProceedsInput;
import com.game.Updateable;
import com.game.ctrl.KeyCtrl;

public class Player implements Drawable, Updateable, ProceedsInput
{
	int								x, y;								// Aktuelle Position
	int								dx, dy;							// Gewuenschte Richtung
	int								floor[][];						// Aktuelle position P
	int								spielerID	= 0;

	boolean							dead			= false;
	private final int				tileSize;
	private final int				width;
	private final int				height;
	private final KeyCtrl	KEYS;
	public Seat						player;
	Sound								sound			= new Sound();

	private final long			timeBuffer	= 200;
	private long					currentTime	= 0;

	public Player(final KeyCtrl KEYS, final int x, final int y, final int dx, final int dy, final int floor[][],
	      final int spielerID, final int tileSize, final int width, final int height, final Seat player)
	{

		this.player = player;
		this.KEYS = KEYS;
		this.x = x;
		this.y = y;
		this.floor = floor;
		this.spielerID = spielerID;
		this.tileSize = tileSize;
		this.height = height;
		this.width = width;

		this.dx = dx; // Start nach Rechts fuer P
		this.dy = dy;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	//
	public int getX()
	{ // rueckgabe wert ist vom typ int
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setY(final int y)
	{
		this.y = y;
	}

	public void setX(final int x)
	{
		this.x = x;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// schleife zum gehen | abfrage ob feld belegt/ zu ende/ zusammenstoss mit
	// anderem spieler
	@Override
	public void update(final long elapsed)
	{

		this.currentTime += elapsed;

		if (this.dead | !this.player.isPlaying())
		{ // nur noch einer Lebt -> brich ab
			this.dead = true;
			return;
		}

		this.player.setScore(this.player.getScore() + 1); // pro zurueckgelektes
		                                                  // feld
		// einen punkt

		this.x = this.x + this.dx;
		this.y = this.y + this.dy;

		if (this.x < 0 || this.y < 0 || this.x > this.width || this.y > this.height)
		{
			// Spieler ausserhalb des Spielfeldes? -> Tod!
			for (int y = 0; y < this.width; y++)
			{
				for (int x = 0; x < this.height; x++)
				{
					if (this.floor[x][y] == this.spielerID)
					{
						this.floor[x][y] = 5;
					}
				}
			}
			this.dead = true;
			this.sound.play("res/games/tron/LaserBoom.wav");
		}
		else
		{
			// Kachel schon belegt? -> Tod!
			try
			{
				if (this.floor[this.x][this.y] == 0)
				{
					;
				}
			}
			catch (final Exception e)
			{
				this.dx = 0;
				this.dy = 0;
				this.dead = true;
				this.sound.play("res/games/tron/LaserBoom.wav");

				for (int y = 0; y < this.width; y++)
				{
					for (int x = 0; x < this.height; x++)
					{
						if (this.floor[x][y] == this.spielerID)
						{
							this.floor[x][y] = 5;
						}
					}
				}
				return;
			}
			if (this.floor[this.x][this.y] == 0)
			{
				this.floor[this.x][this.y] = this.spielerID;
			}
			else
			{
				// Crash
				this.dx = 0;
				this.dy = 0;
				this.dead = true;
				this.sound.play("res/games/tron/LaserBoom.wav");

				for (int y = 0; y < this.width; y++)
				{
					for (int x = 0; x < this.height; x++)
					{
						if (this.floor[x][y] == this.spielerID)
						{
							this.floor[x][y] = 5;
						}
					}
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	//
	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(this.player.getColor());
		g.fillRect(this.x * this.tileSize, this.y * this.tileSize, this.tileSize, this.tileSize);
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// Key uebergabe (oben, unten, rechts, links) | verhindern von eigen toetung
	// und ob man noch lebt
	@Override
	public void processInput()
	{

		if (this.currentTime < this.timeBuffer) { return; }

		// Pfeiltasten Controlle
		if (this.KEYS.isPressed(this.player.UP))
		{
			if (!this.dead && this.dy == 0 && this.player.isPlaying())
			{ // boolean abfrage ob
			  // spieler Tot ist
				this.dx = 0;
				this.dy = -1;
			}
		}

		if (this.KEYS.isPressed(this.player.DOWN))
		{
			if (!this.dead && this.dy == 0 && this.player.isPlaying())
			{ // abfrage ob er sich
			  // selbt toetet
				this.dx = 0;
				this.dy = 1;
			}
		}

		if (this.KEYS.isPressed(this.player.LEFT))
		{
			if (!this.dead && this.dx == 0 && this.player.isPlaying())
			{
				this.dx = -1;
				this.dy = 0;
			}
		}

		if (this.KEYS.isPressed(this.player.RIGHT))
		{
			if (!this.dead && this.dx == 0 && this.player.isPlaying())
			{
				this.dx = 1;
				this.dy = 0;
			}
		}

		// Button Controll (Steren der bewegung durch die zwei BTN)
		if (this.KEYS.isPressed(this.player.BTN1))
		{
			if (!this.dead && this.player.isPlaying())
			{
				if (this.dx == 0)
				{
					this.dx = this.dy;
					this.dy = 0;
				}
				else
				{
					this.dy = -this.dx;
					this.dx = 0;
				}
				this.currentTime = 0;
			}
		}

		if (this.KEYS.isPressed(this.player.BTN2))
		{
			if (!this.dead && this.player.isPlaying())
			{
				if (this.dx == 0)
				{
					this.dx = -this.dy;
					this.dy = 0;
				}
				else
				{
					this.dy = this.dx;
					this.dx = 0;
				}
				this.currentTime = 0;
			}
		}
	}
}