package games.zombies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.game.Drawable;

public class Ground implements Drawable
{

	private final Image	ground	= Toolkit.getDefaultToolkit().createImage("res/games/zombies/ground.png");
	private int				x			= 0, y = 0;
	private final int		width;
	private final int		height;

	public Ground(final int x, final int y, final int width, final int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1024, 1024);

		g.drawImage(this.ground, this.x, this.y, this.x + this.width, this.y + this.height, 0, 0,
		      this.ground.getWidth(null), this.ground.getHeight(null), null);
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public int getHeight()
	{
		return this.height;
	}

	public int getWidth()
	{
		return this.width;
	}

}
