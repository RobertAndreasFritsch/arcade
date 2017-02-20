package games.tron;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public final class Tron_Presentation implements Presentation {

	private final Image img;

	public Tron_Presentation() {
		img = Toolkit.getDefaultToolkit().createImage("res/games/tron/tronbild.png");
	}

	@Override
	public Game getGame(JPanel panel, KeyRequest KEYS) {
		return new Tron(panel, KEYS);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height, null);
	}
}
