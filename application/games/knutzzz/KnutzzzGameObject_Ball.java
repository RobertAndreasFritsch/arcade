package games.knutzzz;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.game.Drawable;
import com.game.Updateable;

public class KnutzzzGameObject_Ball implements Updateable, Drawable
{
	static final int	START			= 0;
	static final int	GAME			= 1;
	static final int	GOAL			= 2;

	double				x, y;
	public double		vx;
	public double		vy;
	Image					ballImage;
	Sound					sound;
	// int state = GAME;
	public boolean		goal			= false;
	Knutzzz				parent;
	int					lastTouch	= 0;

	public KnutzzzGameObject_Ball(final Knutzzz parent)
	{
		this.parent = parent;
		this.ballImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/ball.png");
		this.sound = new Sound();
		// sound.load("res/games/knutzzz/sfx/thud.wav");
		this.init();
	}

	public void collide(final KnutzzzGameObject_Ball ball, final KnutzzzGameObject_Bumper player)
	{
		double nx = ball.x - player.x;
		double ny = ball.y - player.y;
		if (nx * nx + ny * ny < 576)
		{
			// Ball und Player auf richtigen Abstand bringen
			// double
			// inset=24-Math.sqrt((ball.x-player.x)*(ball.x-player.x)+(ball.y-player.y)*(ball.y-player.y));
			// double
			// k=inset/(Math.sqrt(ball.vx*ball.vx+ball.vy*ball.vy)+Math.sqrt(player.vx*player.vx+player.vy*player.vy));
			// ball.vx-=k*ball.vx;
			// ball.vx-=k*ball.vy;
			// player.vx-=k*player.vx;
			// player.vx-=k*player.vy;

			// Normalengeschwindigkeit
			final double rvx = ball.vx - player.vx;
			final double rvy = ball.vy - player.vy;
			// Normale normieren
			final double absn = Math.sqrt(nx * nx + ny * ny);
			nx /= absn;
			ny /= absn;

			final double vn = rvx * nx + rvy * ny;
			if (vn < 0)
			{
				// Kollisionspartner bewegen sich aufeinander zu
				// Elastizitaet e=1
				final double e = 1;
				double j = -(1 + e) * vn;
				// Massen Player m=4, Ball m=1
				j = j / (1 / 4 + 1 / 1);
				// Impuls
				final double impx = j * nx;
				final double impy = j * ny;
				player.vx -= 1 / 4 * impx;
				player.vy -= 1 / 4 * impy;
				ball.vx += impx;
				ball.vy += impy;
			}

			// for (int i=0; i<4; i++)
			// if (players.get(i)==player)
			this.lastTouch = player.ID;
		}
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.drawImage(this.ballImage, (int) this.x - 8, (int) this.y - 8, null);
	}

	public void init()
	{
		this.x = 512;
		this.y = 512;
		this.vx = 0;
		this.vy = 0;
		// state = GAME;
		this.goal = false;
	}

	@Override
	public void update(final long elapsed)
	{
		this.x += this.vx;
		this.y += this.vy;

		// Ball abbremsen (Reibung)
		this.vx *= 0.99;
		this.vy *= 0.99;

		// Ball stoppen, wenn er zu langsam ist
		if (this.vx * this.vx + this.vy * this.vy < 0.01)
		{
			this.vx = 0;
			this.vy = 0;
		}

		// Maximale Geschwindigkeit begrenzen
		final double rootv = Math.sqrt(this.vx * this.vx + this.vy * this.vy);
		if (rootv > 24)
		{
			this.vx /= rootv * 24.0;
			this.vy /= rootv * 24.0;
		}

		if (this.x < 40)
		{
			// Ball prallt links ab oder fliegt ins Tor
			if (this.y < 440 || this.y > 582)
			{
				this.x = 40;
				this.vx = -this.vx;
				// sound.play();
				this.sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			}
			else
				if (this.y < 448 || this.y > 574)
				{
					this.vy = -this.vy;
				}
		}
		if (this.y < 40)
		{
			// Ball prallt oben ab oder fliegt ins Tor
			if (this.x < 440 || this.x > 582)
			{
				this.y = 40;
				this.vy = -this.vy;
				// sound.play();
				this.sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			}
			else
				if (this.x < 448 || this.x > 574)
				{
					this.vx = -this.vx;
				}

		}
		if (this.x > 984)
		{
			// Ball prallt rechts ab oder fliegt ins Tor
			if (this.y < 440 || this.y > 582)
			{
				this.x = 984;
				this.vx = -this.vx;
				// sound.play();
				this.sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			}
			else
				if (this.y < 448 || this.y > 574)
				{
					this.vy = -this.vy;
				}
		}

		if (this.y > 984)
		{
			// Ball prallt unten ab oder fliegt ins Tor
			if (this.x < 440 || this.x > 582)
			{
				this.y = 984;
				this.vy = -this.vy;
				// sound.play();
				this.sound.instantPlay("res/games/knutzzz/sfx/thud.wav");
			}
			else
				if (this.x < 448 || this.x > 574)
				{
					this.vx = -this.vx;
				}
		}

		if (!this.goal && (this.x < 20 || this.y < 20 || this.x > 1004 || this.y > 1004))
		{
			this.goal = true;
			this.sound.instantPlay("res/games/knutzzz/sfx/horn.wav");

			// Punkte vergeben:
			// - Fuer das gegenueberliegende Tor
			// - Fuer den Spieler, der den Ball gespielt hat
			// - Abzug fuer Eigentore
			if (this.y < 20)
			{
				// Ball fliegt ins obere Tor
				this.parent.scoreDisplays[2].score++;
				if (this.lastTouch != 0)
				{
					this.parent.scoreDisplays[this.lastTouch].score++;
				}
				else
					if (this.parent.scoreDisplays[0].score > 0)
					{
						this.parent.scoreDisplays[0].score--;
					}
			}
			if (this.y > 1004)
			{
				// Ball fliegt ins untere Tor
				this.parent.scoreDisplays[0].score++;
				if (this.lastTouch != 2)
				{
					this.parent.scoreDisplays[this.lastTouch].score++;
				}
				else
					if (this.parent.scoreDisplays[1].score > 0)
					{
						this.parent.scoreDisplays[1].score--;
					}
			}
			if (this.x < 20)
			{
				// Ball fliegt ins linke Tor
				this.parent.scoreDisplays[1].score++;
				if (this.lastTouch != 3)
				{
					this.parent.scoreDisplays[this.lastTouch].score++;
				}
				else
					if (this.parent.scoreDisplays[3].score > 0)
					{
						this.parent.scoreDisplays[3].score--;
					}
			}
			if (this.x > 1004)
			{
				// Ball fliegt ins rechte Tor
				this.parent.scoreDisplays[3].score++;
				if (this.lastTouch != 1)
				{
					this.parent.scoreDisplays[this.lastTouch].score++;
				}
				else
					if (this.parent.scoreDisplays[1].score > 0)
					{
						this.parent.scoreDisplays[1].score--;
					}
			}
		}

		for (final Object go : this.parent.getPROCEEDINGINPUTS())
		{
			if (go instanceof KnutzzzGameObject_Bumper)
			{
				this.collide(this, (KnutzzzGameObject_Bumper) go);
			}
		}
	}
}
