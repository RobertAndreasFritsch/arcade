package com.game;

import java.awt.Graphics2D;

import com.arcade.Launch;

/**
 * @author r.fritsch2511
 *
 */
public interface Drawable extends GOAdapter
{

	/**
	 * @param g
	 */
	public void draw(Graphics2D g);

	@Override
	default void output()
	{
		draw(Launch.CTRL_FACTORY.getGraphics());
	}

}
