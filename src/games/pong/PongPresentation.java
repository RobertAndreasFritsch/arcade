package games.pong;

import java.io.IOException;

import javax.swing.JPanel;

import environment.model.Game;
import environment.model.Window;

/**
 * @author default
 *
 */
public final class PongPresentation {
	public static Game getGame(JPanel jPanel, Window window) {
		return new Pong(jPanel, window);
	}

	/**
	 * @throws IOException
	 */
	public PongPresentation() throws IOException {

	}
}
