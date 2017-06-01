package games.knutzzz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import com.game.deprecated.Drawable;

public class KnutzzzGameObject_ScoreDisplay implements Drawable
{
	int			x, y;
	int			player;
	public int	score;
	Color			digitColor	= new Color(200, 150, 0);
	Color			darkColor	= new Color(25, 25, 25);
	Image			glassImage0, glassImage1;
	Knutzzz		parent;

	public KnutzzzGameObject_ScoreDisplay(final Knutzzz parent, final int player)
	{
		this.player = player;
		this.parent = parent;
		this.glassImage0 = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/glass0.png");
		this.glassImage1 = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/glass1.png");
	}

	@Override
	public void draw(final Graphics2D g)
	{
		// Bilder drehen
		final AffineTransform origXform = g.getTransform();
		final AffineTransform newXform = (AffineTransform) origXform.clone();

		// Rotationsmittelpunkt
		final double currentAngle = Math.toRadians(this.player * 90);
		newXform.rotate(currentAngle, 512, 512);
		g.setTransform(newXform);

		// Bild zeichnen
		this.paintScore(g);
		g.drawImage(this.glassImage0, 478, 294, null);
		g.setTransform(origXform);
	}

	public void paintDigit(final Graphics g, final int x, final int y, final int num)
	{
		if (num != 1 && num != 4 && num != 7)
		{
			g.setColor(this.digitColor);
		}
		else
		{
			g.setColor(this.darkColor);
		}
		// oben
		g.drawLine(x + 1, y, x + 9, y);
		g.drawLine(x + 2, y + 1, x + 8, y + 1);

		if (num != 2)
		{
			g.setColor(this.digitColor);
		}
		else
		{
			g.setColor(this.darkColor);
		}
		// links oben
		g.drawLine(x, y + 1, x, y + 9);
		g.drawLine(x + 1, y + 2, x + 1, y + 8);

		if (num != 1 && num != 3 && num != 4 && num != 5 && num != 7 && num != 9)
		{
			g.setColor(this.digitColor);
		}
		else
		{
			g.setColor(this.darkColor);
		}
		// rechts oben
		g.drawLine(x + 10, y + 1, x + 10, y + 9);
		g.drawLine(x + 9, y + 2, x + 9, y + 8);

		if (num != 0 && num != 1 && num != 7)
		{
			g.setColor(this.digitColor);
		}
		else
		{
			g.setColor(this.darkColor);
		}
		// Mitte
		g.drawLine(x + 2, y + 9, x + 8, y + 9);
		g.drawLine(x + 1, y + 10, x + 9, y + 10);
		g.drawLine(x + 2, y + 11, x + 8, y + 11);

		if (num != 5 && num != 6)
		{
			g.setColor(this.digitColor);
		}
		else
		{
			g.setColor(this.darkColor);
		}
		// links unten
		g.drawLine(x, y + 11, x, y + 19);
		g.drawLine(x + 1, y + 12, x + 1, y + 18);

		if (num != 1 && num != 2 && num != 3 && num != 7)
		{
			g.setColor(this.digitColor);
		}
		else
		{
			g.setColor(this.darkColor);
		}
		// rechts unten
		g.drawLine(x + 10, y + 11, x + 10, y + 19);
		g.drawLine(x + 9, y + 12, x + 9, y + 18);

		if (num != 1 && num != 4)
		{
			g.setColor(this.digitColor);
		}
		else
		{
			g.setColor(this.darkColor);
		}
		// unten
		g.drawLine(x + 1, y + 20, x + 9, y + 20);
		g.drawLine(x + 2, y + 19, x + 8, y + 19);
	}

	public void paintScore(final Graphics g)
	{
		this.paintDigit(g, 514, 302, this.score / 10);
		this.paintDigit(g, 500, 302, this.score % 10);
	}
}
