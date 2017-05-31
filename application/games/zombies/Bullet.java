package games.zombies;

import java.awt.Color;
import java.awt.Graphics2D;

import com.arcade.utils.Direction;
import com.game.Drawable;
import com.game.Updateable;

import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Bullet extends CollisionBox implements Drawable, Updateable
{
	/**
	 *
	 */
	private static final long	serialVersionUID	= -4345454809916370881L;

	private static final int	MAX_BOUNCES			= 5;

	private float					x, y;
	private double					dXPerSec, dYPerSec;
	private final Zombies		game;
	private int						bounces				= 0;

	private boolean				collNorth			= false;
	private boolean				collEast				= false;
	private boolean				collSouth			= false;
	private boolean				collWest				= false;

	public Bullet(final float x, final float y, final float rotation, final float speed, final Zombies game)
	{
		super((int) (x - 4), (int) (y - 4), 8, 8);
		this.x = x;
		this.y = y;
		this.game = game;

		this.dXPerSec = Math.sin(Math.toRadians(rotation + 90)) * speed;
		this.dYPerSec = Math.cos(Math.toRadians(rotation + 90)) * speed;

		game.addCollisionBox(this);
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillOval((int) (this.x - 2), (int) (this.y - 2), 4, 4);

		super.draw(g);
	}

	@Override
	public void update(final long elapsed)
	{
		final double scalar = elapsed / 1000f;

		double dX = this.dXPerSec * scalar;
		double dY = this.dYPerSec * scalar;

		if (this.collNorth)
		{
			this.dYPerSec *= -1d;
			dY *= -1;
		}
		if (this.collEast)
		{
			this.dXPerSec *= -1d;
			dX *= -1;
		}
		if (this.collSouth)
		{
			this.dYPerSec *= -1d;
			dY *= -1;
		}
		if (this.collWest)
		{
			this.dXPerSec *= -1d;
			dX *= -1;
		}

		if (this.bounces >= Bullet.MAX_BOUNCES)
		{
			this.game.remove(this);
			this.game.removeCollisionBox(this);
		}
		if (this.collNorth | this.collSouth | this.collWest | this.collEast)
		{
			this.bounces++;
		}

		this.x += dX;
		this.y -= dY;
		this.setX((int) this.x - 4);
		this.setY((int) this.y - 4);

		this.collNorth = false;
		this.collEast = false;
		this.collSouth = false;
		this.collWest = false;
	}

	@Override
	public void onCollision(final CollisionBox with, final Direction dir)
	{
		if (with instanceof Blockade)
		{
			switch (dir)
			{
			case NORTH:
				this.collNorth = true;
				break;
			case EAST:
				this.collEast = true;
				break;
			case SOUTH:
				this.collSouth = true;
				break;
			case WEST:
				this.collWest = true;
				break;
			}
		}
	}

}
