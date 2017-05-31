package games.kickthemoff;

import java.awt.Graphics2D;

import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.Updateable;
import com.game.ctrl.KeyRequest;

public class Player implements Drawable, Updateable
{
	private static final float	SPEED_INCREASE_PER_PRESS	= 12f;
	private static final float	SPEED_DECREASE_PER_SECOND	= 2f;
	private final Seat			s;
	private float					x, y;
	private float					accelerationX, accelerationY;
	private final KeyRequest	keys;
	private boolean				dead								= false, dying = false;
	private float					scale								= 1;
	private final Ground			g;
	private final Kickthemoff	game;

	private Player					lastKickedBy					= this;

	public Player(final Kickthemoff game, final Seat s, final int x, final int y, final Ground g)
	{
		this.s = s;
		this.x = x;
		this.y = y;
		this.accelerationX = 0;
		this.accelerationY = 0;
		this.keys = game.getKEYS();
		this.g = g;
		this.game = game;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		if (this.dead) { return; }
		g.setColor(this.s.getColor());
		g.fillOval((int) (this.x - 25 * this.scale), (int) (this.y - 25 * this.scale), (int) (50 * this.scale),
		      (int) (50 * this.scale));
	}

	public float getAccelerationX()
	{
		return this.accelerationX;
	}

	public float getAccelerationY()
	{
		return this.accelerationY;
	}

	public Player getLastKickedBy()
	{
		return this.lastKickedBy;
	}

	public Seat getSeat()
	{
		return this.s;
	}

	public float getX()
	{
		return this.x;
	}

	public float getY()
	{
		return this.y;
	}

	public boolean isDead()
	{
		return this.dead;
	}

	public boolean isDying()
	{
		return this.dying;
	}

	public int getWins()
	{
		return (int) (this.s.getScore() * 0.0001);
	}

	public void setWins(final int wins)
	{
		this.s.setScore((int) ((this.s.getScore() * 0.00001 + wins) * 10000));
	}

	public void setAccelerationX(final float accelerationX)
	{
		this.accelerationX = accelerationX;
	}

	public void setAccelerationY(final float accelerationY)
	{
		this.accelerationY = accelerationY;
	}

	public void setLastKickedBy(final Player lastKickedBy)
	{
		this.lastKickedBy = lastKickedBy;
	}

	public void revive()
	{
		this.dead = false;
		this.dying = false;
	}

	@Override
	public void update(final long elapsed)
	{
		if (this.dead) { return; }
		final float timefactor = (float) elapsed / 1000;

		if (this.keys.isPressed(this.s.UP) & !this.dying)
		{
			this.accelerationY -= Player.SPEED_INCREASE_PER_PRESS * timefactor;
		}
		if (this.keys.isPressed(this.s.DOWN) & !this.dying)
		{
			this.accelerationY += Player.SPEED_INCREASE_PER_PRESS * timefactor;
		}
		if (this.keys.isPressed(this.s.LEFT) & !this.dying)
		{
			this.accelerationX -= Player.SPEED_INCREASE_PER_PRESS * timefactor;
		}
		if (this.keys.isPressed(this.s.RIGHT) & !this.dying)
		{
			this.accelerationX += Player.SPEED_INCREASE_PER_PRESS * timefactor;
		}

		this.x += this.accelerationX;
		this.y += this.accelerationY;

		this.accelerationX -= Math.abs(this.accelerationX) > 0
		      ? this.accelerationX > 0 ? Player.SPEED_DECREASE_PER_SECOND * timefactor
		            : -Player.SPEED_DECREASE_PER_SECOND * timefactor
		      : 0;
		this.accelerationY -= Math.abs(this.accelerationY) > 0
		      ? this.accelerationY > 0 ? Player.SPEED_DECREASE_PER_SECOND * timefactor
		            : -Player.SPEED_DECREASE_PER_SECOND * timefactor
		      : 0;

		if (Math.abs(this.accelerationX) < 0.01f)
		{
			this.accelerationX = 0;
		}
		if (Math.abs(this.accelerationY) < 0.01f)
		{
			this.accelerationY = 0;
		}

		final float dx = this.x - this.g.getX();
		final float dy = this.y - this.g.getY();
		final float len = (float) Math.sqrt(dx * dx + dy * dy);
		if (len > this.g.getR() & !this.dying)
		{
			this.dying = true;
			this.lastKickedBy.getSeat().setScore(this.lastKickedBy.getSeat().getScore() + 50);
			Sounds.falling.play();
			new Thread()
			{
				@Override
				public void run()
				{
					while (Player.this.scale > 0)
					{
						Player.this.scale -= 0.05;
						try
						{
							Thread.sleep(50);
						}
						catch (final InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					Player.this.dead = true;
					final waterSplash w = new waterSplash(Player.this.game, (int) Player.this.x - 25,
					      (int) Player.this.y - 25);
					Player.this.game.getDRAWABLES().add(1, w);
				}
			}.start();
		}

		for (final Player p : this.game.getPlayers())
		{
			if (p != this & !this.isDead() & !this.isDying() & !p.isDead() & !p.isDying())
			{
				Player.doCollision(this, p);
			}
		}
	}

	public static void doCollision(final Player p1, final Player p2)
	{
		// Normale
		float nx = p2.getX() - p1.getX();
		float ny = p2.getY() - p1.getY();
		if (nx * nx + ny * ny < 2500)
		{
			// Normalengeschwindigkeit
			final double rvx = p2.getAccelerationX() - p1.getAccelerationX();
			final double rvy = p2.getAccelerationY() - p1.getAccelerationY();
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
				p1.setAccelerationX((float) (p1.getAccelerationX() - 0.25 * impx));
				p1.setAccelerationY((float) (p1.getAccelerationY() - 0.25 * impy));
				p2.setAccelerationX((float) (p2.getAccelerationX() + 0.25 * impx));
				p2.setAccelerationY((float) (p2.getAccelerationY() + 0.25 * impy));
			}
			Sounds.pling.play();
		}

		p1.setLastKickedBy(p2);
		p2.setLastKickedBy(p1);
	}
}
