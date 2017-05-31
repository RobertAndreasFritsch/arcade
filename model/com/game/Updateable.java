package com.game;

/**
 * @author r.fritsch2511
 *
 */
public interface Updateable extends GOAdapter
{

	/**
	 * @param elapsed
	 */
	@Override
	public void update(long elapsed);

}
