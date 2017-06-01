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
public interface ProceedsInput extends GOAdapter
{

	/**
	 * @deprecated
	 *
	 *             implement GameObject or extends SimpleGameObject
	 *
	 * @see com.game.GameObject
	 * @see com.game.SimpleGameObject
	 */
	@Deprecated
	public void processInput();

	@Override
	default void input()
	{
		processInput();
	}
}
