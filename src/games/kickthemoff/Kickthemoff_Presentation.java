package games.kickthemoff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class Kickthemoff_Presentation implements Presentation {

	private final Image img;

	/**
	 * @throws IOException
	 */
	public Kickthemoff_Presentation() {
		this.img = Toolkit.getDefaultToolkit().getImage("res/games/kickthemoff/kickthemoff_Presentation.png");
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawImage(this.img, 0, 0, 1024, 1024, null);
	}

	@Override
	public Game getGame(final JPanel panel, final KeyRequest KEYS) {
		return new Kickthemoff(panel, KEYS);
	}

}
