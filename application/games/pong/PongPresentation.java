package games.pong;

import java.io.IOException;

import javax.swing.JPanel;

import com.game.Game;
import com.game.KeyRequest;
import com.game.Window;

/**
 * @author default
 *
 */
public final class PongPresentation
{
	public static Game getGame(final JPanel jPanel, final Window window)
	{
		return new Pong(jPanel, (KeyRequest) window);
	}

	/**
	 * @throws IOException
	 */
	public PongPresentation() throws IOException
	{

	}
}
