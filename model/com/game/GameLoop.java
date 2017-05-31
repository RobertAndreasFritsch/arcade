package com.game;

/**
 * @author r.fritsch2511
 *
 */
public interface GameLoop extends Runnable
{

	/**
	 * <h1>getActiveGame()</h1>
	 *
	 * some discr
	 *
	 * @author r.fritsch2511
	 * @return
	 */
	public Game getActiveGame();

	/**
	 * @param activeGame
	 */
	public void setActiveGame(Game activeGame);

	/**
	 *
	 */
	public void start();

}
