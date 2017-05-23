package games.zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class Zombies_Presentation implements Presentation {
	Image img;

	/**
	 * @throws IOException
	 */
	public Zombies_Presentation() {
		this.img = Toolkit.getDefaultToolkit().getImage("res/games/zombies/wallpaper.png");
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawImage(this.img, 0, 0, 1024, 1024, null);
	}

	@Override
	public Game getGame(final JPanel panel, final KeyRequest KEYS) {
		return new Zombies(panel, KEYS);
	}
}
