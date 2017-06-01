package games.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.deprecated.Drawable;

/**
 * @author default
 *
 */
public class PongGameObjekt_Blocker implements Drawable
{
	/**
	 *
	 */
	int			x1, y1, b1, h1;
	/**
	 *
	 */
	Rectangle	rect;

	/*
	 * Rectangle rect2 = new Rectangle(x2, y2, b, h); Rectangle rect3 = new
	 * Rectangle(x3, y3, b, h); Rectangle rect4 = new Rectangle(x4, y4, b, h);
	 */
	/**
	 * @param x
	 * @param y
	 * @param b
	 * @param h
	 * @throws Exception
	 */
	public PongGameObjekt_Blocker(final int x, final int y, final int b, final int h)
	{
		this.rect = new Rectangle(x, y, b, h);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see gamelibrary.GameObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void draw(final Graphics2D GRAPHICS)
	{
		GRAPHICS.setColor(Color.BLACK);
		GRAPHICS.fill(this.rect);
		/*
		 * GRAPHICS.fill(rect2); GRAPHICS.fill(rect3); GRAPHICS.fill(rect4);
		 */

	}

	/**
	 * @return
	 */
	public int getb()
	{
		return this.b1;
	}

	public void setb(final int b)
	{
		this.b1 = b;
	}

	/**
	 * @return
	 */
	public int geth()
	{
		return this.h1;
	}

	public void seth(final int h)
	{
		this.b1 = h;
	}

	/**
	 * @return
	 */
	public int getX()
	{
		return this.rect.x;
	}

	/**
	 * @return
	 */
	public int getY()
	{
		return this.rect.y;
	}

	/**
	 * @param x
	 * @param y
	 * @param b
	 * @param h
	 */
	// public void setKor(int x, int y, int b, int h) {
	// rect.x = x;
	// x1 = x;
	// rect.y = y;
	// y1 = y;
	// rect.width = b;
	// rect.height = h;
	// System.out.println("HI");
	// }
}
