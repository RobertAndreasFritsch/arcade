package com.game.deprecated;

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
public interface Updateable extends GOAdapter
{

	/**
	 * @param elapsed
	 * @deprecated
	 *
	 *             implement GameObject or extends SimpleGameObject
	 *
	 * @see com.game.GameObject
	 * @see com.game.SimpleGameObject
	 */
	@Deprecated
	@Override
	public void update(long elapsed);

}
