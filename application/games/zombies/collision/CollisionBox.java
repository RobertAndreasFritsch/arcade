package games.zombies.collision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import com.arcade.utils.Direction;
import com.game.Drawable;

public class CollisionBox extends Rectangle implements Drawable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 418510093470349083L;

	public CollisionBox(final int x, final int y, final int width, final int height)
	{
		super(x, y, width, height);
	}

	public static Direction getCollisionDirection(final CollisionBox c1, final CollisionBox c2)
	{
		final Rectangle res = new Rectangle();
		Rectangle2D.intersect(c1, c2, res);

		final double c1r = c1.getHeight() / c1.getWidth();
		final double resr = res.getHeight() / res.getWidth();

		if (resr < c1r)
		{
			final double midyres = res.getY() + res.getHeight() / 2d;
			final double midyc = c1.getY() + c1.getHeight() / 2d;
			if (midyres >= midyc)
			{
				return Direction.SOUTH;
			}
			else
			{
				return Direction.NORTH;
			}
		}
		else
		{
			final double midxres = res.getX() + res.getWidth() / 2d;
			final double midxc = c1.getX() + c1.getWidth() / 2d;
			if (midxres >= midxc)
			{
				return Direction.EAST;
			}
			else
			{
				return Direction.WEST;
			}
		}
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.CYAN);
		g.drawRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
	}

	public void setX(final int x)
	{
		this.setBounds(x, (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
	}

	public void setY(final int y)
	{
		this.setBounds((int) this.getX(), y, (int) this.getWidth(), (int) this.getHeight());
	}

	public void setWidth(final int width)
	{
		this.setBounds((int) this.getX(), (int) this.getY(), width, (int) this.getHeight());
	}

	public void setHeight(final int height)
	{
		this.setBounds((int) this.getX(), (int) this.getY(), (int) this.getWidth(), height);
	}

	public static boolean doCollide(final CollisionBox c1, final CollisionBox c2)
	{
		return c1.intersects(c2);
	}

	public void onCollision(final CollisionBox with, final Direction dir)
	{
	}

}