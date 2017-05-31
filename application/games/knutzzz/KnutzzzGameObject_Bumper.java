package games.knutzzz;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.Drawable;
import com.game.ProceedsInput;
import com.game.Updateable;
import com.game.ctrl.KeyRequest;

public class KnutzzzGameObject_Bumper implements Drawable, Updateable, ProceedsInput
{
	final int						VK_ButtonLeft, VK_ButtonRight, VK_ButtonThrust;

	double							x, y;
	double							vx, vy;
	double							ax, ay;
	int								angle;
	Image								image;
	Image								carImage, driverImage, spotlightImage;
	boolean							throttle	= false;
	int								turn		= 0;
	Knutzzz							parent;
	Sound								sound;
	int								startx, starty, startangle;
	int								ID			= 0;

	private final KeyRequest	KEYS;

	KnutzzzGameObject_Bumper(final Knutzzz parent, final int keyCodeLeft, final int keyCodeRight,
	      final int keyCodeThrust, final int ID, final KeyRequest KEYS)
	{
		this.parent = parent;
		this.KEYS = KEYS;

		this.VK_ButtonLeft = keyCodeLeft;
		this.VK_ButtonRight = keyCodeRight;
		this.VK_ButtonThrust = keyCodeThrust;
		this.ID = ID;

		switch (ID)
		{
		case 0:
			this.init(512, 128, 90);
			break;
		case 1:
			this.init(896, 512, 180);
			break;
		case 2:
			this.init(512, 896, 270);
			break;
		default:
			this.init(128, 512, 0);
		}
		this.spotlightImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/spotlight.png");
		this.loadImages(ID);

		this.sound = new Sound();
		this.sound.load("res/games/knutzzz/sfx/pling.wav");
	}

	public void collide(final KnutzzzGameObject_Bumper player, final KnutzzzGameObject_Bumper player2)
	{
		// Normale
		double nx = player2.x - player.x;
		double ny = player2.y - player.y;
		if (nx * nx + ny * ny < 1024)
		{

			// Beide Player auf richtigen Abstand bringen
			// double
			// inset=32-Math.sqrt((player2.x-player.x)*(player2.x-player.x)+(player2.y-player.y)*(player2.y-player.y));
			// double
			// k=inset/(Math.sqrt(player2.vx*player2.vx+player2.vy*player2.vy)+Math.sqrt(player.vx*player.vx+player.vy*player.vy));
			// player2.vx-=k*player2.vx;
			// player2.vx-=k*player2.vy;
			// player.vx-=k*player.vx;
			// player.vx-=k*player.vy;

			// Normalengeschwindigkeit
			final double rvx = player2.vx - player.vx;
			final double rvy = player2.vy - player.vy;
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
				j = j * 2;
				// Impuls
				final double impx = j * nx;
				final double impy = j * ny;
				player.vx -= 0.25 * impx;
				player.vy -= 0.25 * impy;
				player2.vx += 0.25 * impx;
				player2.vy += 0.25 * impy;
				this.sound.play();
			}
		}
	}

	@Override
	public void draw(final Graphics2D g)
	{
		// Bilder drehen
		final AffineTransform origXform = g.getTransform();
		final AffineTransform newXform = (AffineTransform) origXform.clone();

		// Rotationsmittelpunkt
		final double currentAngle = Math.toRadians(this.angle);
		newXform.rotate(currentAngle, this.x, this.y);
		g.setTransform(newXform);

		// Bild zeichnen
		g.drawImage(this.driverImage, (int) this.x - 16, (int) this.y - 16, null);
		g.drawImage(this.spotlightImage, (int) this.x - 16, (int) this.y - 16, null);
		g.setTransform(origXform);
	}

	public void init(final int x, final int y, final int angle)
	{
		this.x = x;
		this.y = y;
		this.angle = angle;

		this.startx = x;
		this.starty = y;
		this.startangle = angle;
	}

	private void loadImages(final int playerID)
	{
		// Bilder fuer Animation laden
		BufferedImage shapes = null;
		try
		{
			/* TODO imageIO is deprecated */
			shapes = ImageIO.read(new File("res/games/knutzzz/img/bumpershapes.png").toURI().toURL());
		}
		catch (final IOException e)
		{
		}
		// Laedt die Bilder f�r die Animation in das Array
		this.carImage = shapes.getSubimage(playerID * 32, 0, 32, 32);
		this.driverImage = shapes.getSubimage(playerID * 32, 32, 32, 32);
	}

	@Override
	public void processInput()
	{
		if (this.parent.isGameRunning())
		{
			// Tastaturabfrage: Left/Right - Drehung, Button1 - Gas geben
			if (this.KEYS.isPressed(this.VK_ButtonLeft))
			{
				this.turn = -4;
			}
			else
				if (this.KEYS.isPressed(this.VK_ButtonRight))
				{
					this.turn = 4;
				}
				else
				{
					this.turn = 0;
				}
			this.throttle = this.KEYS.isPressed(this.VK_ButtonThrust);
		}
		else
		{
			this.throttle = false;
			this.turn = 0;
		}
	}

	public void reset()
	{
		this.x = this.startx;
		this.y = this.starty;
		this.angle = this.startangle;
	}

	@Override
	public void update(final long elapsed)
	{
		this.angle += this.turn;
		if (this.angle < 0)
		{
			this.angle += 360;
		}
		if (this.angle >= 360)
		{
			this.angle -= 360;
		}

		if (this.throttle)
		{
			this.ax = Math.cos(Math.toRadians(this.angle)) * 0.25;
			this.ay = Math.sin(Math.toRadians(this.angle)) * 0.25;

			// Geschwindigkeit aendern
			this.vx += this.ax;
			this.vy += this.ay;
		}
		this.vx *= 0.95;
		this.vy *= 0.95;

		if (this.vx < 0.1 && this.vx > -0.1)
		{
			this.vx = 0.0;
		}
		if (this.vy < 0.1 && this.vy > -0.1)
		{
			this.vy = 0.0;
		}

		// Position aendern
		this.x += this.vx;
		this.y += this.vy;

		if (this.x < 48)
		{
			this.x = 48;
			this.vx = -this.vx;
			this.sound.play();
		}
		if (this.y < 48)
		{
			this.y = 48;
			this.vy = -this.vy;
			this.sound.play();
		}
		if (this.x > 976)
		{
			this.x = 976;
			this.vx = -this.vx;
			this.sound.play();
		}
		if (this.y > 976)
		{
			this.y = 976;
			this.vy = -this.vy;
			this.sound.play();
		}

		for (final Object go : this.parent.getPROCEEDINGINPUTS())
		{
			if (go instanceof KnutzzzGameObject_Bumper && go != this)
			{
				this.collide(this, (KnutzzzGameObject_Bumper) go);
			}
		}
	}
}
