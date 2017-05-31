package games.pong.Tiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.ProceedsInput;
import com.game.Updateable;
import com.game.ctrl.KeyRequest;

public abstract class Tile implements Updateable, Drawable, ProceedsInput
{

	public static final int		SPEED	= 30;					// TODO hard coded

	static int						i		= 0;
	public final Seat				player;
	private int						Tor;
	private int						width;
	private int						height;
	private int						Pkt;
	/**
	 *
	 */
	Rectangle						rect;

	/**
	 *
	 */
	boolean							stateLeft, stateRight;

	private final KeyRequest	KEYS;

	/**
	 * @param keyCode1
	 * @param keyCode2
	 * @throws Exception
	 */
	public Tile(final Seat player, final KeyRequest KEYS)
	{
		this.player = player;
		this.KEYS = KEYS;
		this.init();
	}

	/**
	 *
	 */
	public abstract void actionLeft();

	/**
	 *
	 */
	public abstract void actionRight();

	/*
	 * (non-Javadoc)
	 *
	 * @see gamelibrary.GameObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void draw(final Graphics2D GRAPHICS)
	{
		GRAPHICS.setColor(this.player.getColor());
		GRAPHICS.fill(this.rect);
		GRAPHICS.setFont(new Font("Courier", Font.PLAIN, 20));
		GRAPHICS.drawString(Integer.toString(this.getTor()), this.getX(), this.getY());
		GRAPHICS.setColor(Color.BLACK);
	}

	public int getheight()
	{
		return this.height;
	}

	public int getPkt()
	{
		return this.Pkt;
	}

	/**
	 * @return
	 */
	public boolean getstateLeft()
	{
		return this.stateLeft;
	}

	/**
	 * @return
	 */
	public boolean getstateRight()
	{
		return this.stateRight;
	}

	public int getTor()
	{
		return this.Tor;
	}

	public int getwidth()
	{
		return this.width;
	}

	/**
	 * @return
	 */
	public int getX()
	{
		return this.rect.x;
	}

	public void setX(final int x)
	{
		this.rect.x = x;
	}

	/**
	 * @return
	 */
	public int getY()
	{
		return this.rect.y;
	}

	public void setY(final int y)
	{
		this.rect.y = y;
	}

	protected abstract void init();

	/*
	 * (non-Javadoc)
	 *
	 * @see gamelibrary.GameObject#processInput(gamelibrary.FrameBasedGame)
	 */
	@Override
	public void processInput()
	{
		if (this.KEYS.isPressed(this.player.LEFT))
		{

			this.stateLeft = true;

		}
		if (this.KEYS.isPressed(this.player.RIGHT))
		{
			this.stateRight = true;
		}

	}

	public void setheight(final int height)
	{
		this.height = height;
	}

	public void seti()
	{
		Tile.i = 0;
	}

	public void setPkt()
	{
		this.Pkt = this.Pkt + 100;
	}

	public void setTor(final int Tor)
	{
		this.Tor = Tor;
	}

	public void setwidth(final int width)
	{
		this.width = width;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see gamelibrary.GameObject#update(java.util.List)
	 */
	@Override

	public void update(final long elapsed)
	{

		if (Tile.i <= 3)
		{
			this.init();
			Tile.i++;
		}
		if (this.stateLeft)
		{
			this.actionLeft();
			this.stateLeft = false;
		}
		if (this.stateRight)
		{
			this.actionRight();
			this.stateRight = false;
		}
	}
}
