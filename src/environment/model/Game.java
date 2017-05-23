package environment.model;

import java.util.List;

/**
 * @author r.fritsch2511
 *
 */
public interface Game {

	/**
	 * @param gameObject
	 */
	public void add(Object gameObject);

	/**
	 * @param gameObjects
	 */
	void addAll(List<?> gameObjects);

	/**
	 * gibt das Game zurück was im anschluss gelauncht wird
	 * 
	 * @return
	 */
	public Game getNextGame();

	/**
	 * @return
	 */
	public boolean isRunning();

	/**
	 * @param gameObject
	 */
	public void remove(Object gameObject);

	/**
	 * @param gameObjects
	 */
	void removeAll(List<?> gameObjects);

	/**
	 * @param running
	 */
	public void setRunning(boolean running);

	/**
	 * @param elapsed
	 */
	public void tick(long elapsed);

}
