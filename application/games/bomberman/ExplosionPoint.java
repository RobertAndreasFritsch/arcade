package games.bomberman;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.game.deprecated.Drawable;
import com.game.deprecated.Updateable;

public class ExplosionPoint implements Drawable, Updateable
{

	private int				x, y;
	private Bomberman		game;
	private Player			Dropper;
	private boolean		gone	= false;
	private final Image	i		= Toolkit.getDefaultToolkit().createImage("res/games/bomberman/explosion.gif");;

	public ExplosionPoint(final Bomberman game, final int x, final int y, final Player Dropper, final long timeout)
	{
		this.x = x;
		this.y = y;
		this.game = game;
		this.Dropper = Dropper;

		if (x / 32 >= 0 & x / 32 < 32 & y / 32 >= 0 & y / 32 < 32)
		{
			if (game.getField().ground[x / 32][y / 32] == 1)
			{
				game.getField().ground[x / 32][y / 32] = 0;
				Dropper.setScore(Dropper.getScore() + Bomberman.BLOCK_DESTROY_SCORE);
			}
		}
		else
		{
			this.disappear();
			return;
		}

		for (final Bomb b : new ArrayList<>(Bomb.bombs))
		{
			if (b.getX() == x & b.getY() == y)
			{
				b.explode();
			}
		}

		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(timeout);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
				ExplosionPoint.this.disappear();
			}
		}.start();
	}

	private void disappear()
	{
		if (this.gone) { return; }
		this.game.remove(this);
		this.gone = true;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		if (this.gone) { return; }
		g.drawImage(this.i, this.x, this.y, 32, 32, null);
	}

	@Override
	public void update(final long elapsed)
	{
		if (this.gone) { return; }
		if (this.game.Player1 != null)
		{
			if (this.game.Player1.getX() == this.x && this.game.Player1.getY() == this.y & !this.game.Player1.isDead())
			{
				this.game.Player1.die();
				final ArrayList<Sounds> s = new ArrayList<>();
				for (final Sounds c : Sounds.class.getEnumConstants())
				{
					if (c.name().contains("scream"))
					{
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (this.Dropper == this.game.Player1)
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				}
				else
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
		if (this.game.Player2 != null)
		{
			if (this.game.Player2.getX() == this.x && this.game.Player2.getY() == this.y & !this.game.Player2.isDead())
			{
				this.game.Player2.die();
				final ArrayList<Sounds> s = new ArrayList<>();
				for (final Sounds c : Sounds.class.getEnumConstants())
				{
					if (c.name().contains("scream"))
					{
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (this.Dropper == this.game.Player2)
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				}
				else
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
		if (this.game.Player3 != null)
		{
			if (this.game.Player3.getX() == this.x && this.game.Player3.getY() == this.y & !this.game.Player3.isDead())
			{
				this.game.Player3.die();
				final ArrayList<Sounds> s = new ArrayList<>();
				for (final Sounds c : Sounds.class.getEnumConstants())
				{
					if (c.name().contains("scream"))
					{
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (this.Dropper == this.game.Player3)
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				}
				else
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
		if (this.game.Player4 != null)
		{
			if (this.game.Player4.getX() == this.x && this.game.Player4.getY() == this.y & !this.game.Player4.isDead())
			{
				this.game.Player4.die();
				final ArrayList<Sounds> s = new ArrayList<>();
				for (final Sounds c : Sounds.class.getEnumConstants())
				{
					if (c.name().contains("scream"))
					{
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (this.Dropper == this.game.Player4)
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				}
				else
				{
					this.Dropper.setScore(this.Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
	}

}
