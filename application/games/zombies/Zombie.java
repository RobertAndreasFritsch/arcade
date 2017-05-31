package games.zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.arcade.utils.Direction;
import com.game.Drawable;
import com.game.Updateable;

import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Zombie extends CollisionBox implements Drawable, Updateable
{

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1683331211559374151L;

	private static final float	WALKING_SPEED		= 100;

	private float					rotation				= 0;
	private final Zombies		game;
	private final Image			texture				= Toolkit.getDefaultToolkit()
	      .createImage("res/games/zombies/Zombie.png");

	private boolean				collNorth			= false;
	private boolean				collEast				= false;
	private boolean				collSouth			= false;
	private boolean				collWest				= false;

	public Zombie(final int x, final int y, final Zombies game)
	{
		super(x - 20, y - 20, 40, 40);
		this.game = game;
	}

	@Override
	public void update(final long elapsed)
	{
		final float scalar = elapsed / 1000f;

		Player target = null;
		double distance = -1;
		double difx;
		double dify;

		for (final Player p : this.game.getPlayers())
		{
			difx = this.getX() - p.getX();
			dify = this.getY() - p.getY();
			final double ddistance = Math.sqrt(difx * difx + dify * dify);
			if (ddistance < distance)
			{
				distance = ddistance;
				target = p;
			}
			else
				if (distance == -1)
				{
					distance = ddistance;
					target = p;
				}
		}

		float angle = (float) Math.toDegrees(Math.atan2(target.getY() - this.getY(), target.getX() - this.getX()));
		if (angle < 0)
		{
			angle += 360;
		}
		this.rotation = angle;

		double newX = this.x, newY = this.y;

		final float length = Zombie.WALKING_SPEED * scalar;
		newX += Math.sin(Math.toRadians(this.rotation + 90)) * length;
		newY -= Math.cos(Math.toRadians(this.rotation + 90)) * length;

		if (newY < this.y && this.collNorth)
		{
			newY = this.y;
		}
		if (newY > this.y && this.collSouth)
		{
			newY = this.y;
		}
		if (newX > this.x && this.collEast)
		{
			newX = this.x;
		}
		if (newX < this.x && this.collWest)
		{
			newX = this.x;
		}

		this.x = (int) newX;
		this.y = (int) newY;
		this.setX(this.x - 20);
		this.setY(this.y - 20);

		this.collNorth = false;
		this.collEast = false;
		this.collSouth = false;
		this.collWest = false;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.rotate(Math.toRadians(this.rotation), this.x, this.y);
		g.drawImage(this.texture, this.x - 15, this.y - 17, null);
		g.rotate(-Math.toRadians(this.rotation), this.x, this.y);

		super.draw(g);
	}

	@Override
	public void onCollision(final CollisionBox with, final Direction dir)
	{
		if (!(with instanceof Blockade)) { return; }

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
