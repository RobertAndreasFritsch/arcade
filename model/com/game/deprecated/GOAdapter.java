package com.game.deprecated;

import com.game.GameObject;

/**
 * @author user
 * @deprecated
 * 
 *             implement GameObject or extends SimpleGameObject
 * 
 * @see com.game.GameObject
 * @see com.game.SimpleGameObject
 */
@Deprecated
public interface GOAdapter extends GameObject
{
	@Override
	default void input()
	{
	}

	@Override
	default void update(final long elapsed)
	{
	}

	@Override
	default void output()
	{
	}
}
