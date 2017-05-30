package games.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class TankiesStage implements Presentation {

	Image img;

	/**
	 * @throws IOException
	 */
	public TankiesStage() {
		try {
			this.img = ImageIO.read(new File("res/games/tankies/img/Menu/cover1.png"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawImage(this.img, 0, 0, 1024, 1024, null);
	}

	@Override
	public Game getGame(final JPanel panel, final KeyRequest KEYS) {
		return new Tankies(panel, KEYS, null);
	}

}
