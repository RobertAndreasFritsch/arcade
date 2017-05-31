package games.bomberman;

import java.awt.Color;
import java.awt.Graphics2D;

import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.ProceedsInput;
import com.game.ctrl.KeyCtrl;

public class Player implements Drawable, ProceedsInput
{
	private final Seat			s;
	private int						x, y;
	private final KeyCtrl	keys;
	private final long			WAIT_UNTIL_NEXT_MOVE	= 100;
	private final long			WAIT_UNTIL_NEXT_BOMB	= 1000;
	private final long			BOMB_TIMEOUT			= 1500;
	private boolean				canMove					= true, canDropBomb = true, dead = false;
	private final Field			f;
	private final Bomberman		game;
	private int						score						= 1;

	public Player(final Bomberman game, final KeyCtrl keys, final Seat s, final Field f, final int x, final int y)
	{
		this.s = s;
		this.x = x;
		this.y = y;
		this.f = f;
		this.game = game;
		this.keys = keys;
	}

	private void bombDropped()
	{
		this.canDropBomb = false;
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(Player.this.WAIT_UNTIL_NEXT_BOMB);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
				Player.this.canDropBomb = true;
			}
		}.start();
	}

	public void die()
	{
		this.dead = true;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		if (!this.dead)
		{
			g.setColor(this.s.getColor());
			g.fillRoundRect(this.x, this.y, 32, 32, 50, 50);
		}
	}

	public Color getColor()
	{
		return this.s.getColor();
	}

	public int getScore()
	{
		return this.score;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public boolean isDead()
	{
		return this.dead;
	}

	private void moved()
	{
		this.canMove = false;
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(Player.this.WAIT_UNTIL_NEXT_MOVE);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
				Player.this.canMove = true;
			}
		}.start();
	}

	@Override
	public void processInput()
	{
		if (this.dead) { return; }
		if (this.canMove)
		{
			if (this.y / 32 - 1 >= 0 & this.y / 32 - 1 < 32)
			{
				if (this.keys.isPressed(this.s.UP) & this.f.ground[this.x / 32][this.y / 32 - 1] == 0)
				{
					this.y -= 32;
					this.moved();
					return;
				}
			}
			if (this.y / 32 + 1 >= 0 & this.y / 32 + 1 < 32)
			{
				if (this.keys.isPressed(this.s.DOWN) & this.f.ground[this.x / 32][this.y / 32 + 1] == 0)
				{
					this.y += 32;
					this.moved();
					return;
				}
			}
			if (this.x / 32 - 1 >= 0 & this.x / 32 - 1 < 32)
			{
				if (this.keys.isPressed(this.s.LEFT) & this.f.ground[this.x / 32 - 1][this.y / 32] == 0)
				{
					this.x -= 32;
					this.moved();
					return;
				}
			}
			if (this.x / 32 + 1 >= 0 & this.x / 32 + 1 < 32)
			{
				if (this.keys.isPressed(this.s.RIGHT) & this.f.ground[this.x / 32 + 1][this.y / 32] == 0)
				{
					this.x += 32;
					this.moved();
					return;
				}
			}
		}
		if (this.canDropBomb)
		{
			if (this.keys.isPressed(this.s.BTN1) & this.canDropBomb)
			{
				// game.getDrawables().add();
				this.game.add(new Bomb(this.game, this.s.getColor(), this, this.x, this.y, 3, this.BOMB_TIMEOUT));
				this.bombDropped();
			}
		}
	}

	public void setScore(final int score)
	{
		this.score = score;
		this.s.setScore(score);
	}

}
