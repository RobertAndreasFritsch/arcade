package com.game.deprecated;

import java.awt.Graphics2D;

import com.arcade.Launch;

/**
 * @author r.fritsch2511
 * @deprecated
 * 
 *             implement GameObject or extends SimpleGameObject
 * 
 * @see com.game.GameObject
 * @see com.game.SimpleGameObject
 */
@Deprecated
public interface Drawable extends GOAdapter
{

	/**
	 * @param g
	 * @deprecated
	 *
	 *             implement GameObject or extends SimpleGameObject
	 *
	 * @see com.game.GameObject
	 * @see com.game.SimpleGameObject
	 */
	@Deprecated
	public void draw(Graphics2D g);

	@Override
	default void output()
	{
		draw(Launch.CTRL_FACTORY.graphicsControllerInstance().getBufferGraphics());
	}

}
