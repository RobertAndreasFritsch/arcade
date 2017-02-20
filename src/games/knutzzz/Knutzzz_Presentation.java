package games.knutzzz;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

import environment.implementation.MyWindow;
import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

/**
 * @author default
 *
 */
public final class Knutzzz_Presentation implements Presentation {

	Image img;

	/**
	 * @throws IOException
	 */
	public Knutzzz_Presentation() {
		img = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/knutzzzwallpaper.png");
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, 0, 0, MyWindow.getInstance().getSize().width, MyWindow.getInstance().getSize().height, null);
	}

	@Override
	public Game getGame(JPanel panel, KeyRequest KEYS) {
		return new Knutzzz(panel, KEYS);
	}
}
