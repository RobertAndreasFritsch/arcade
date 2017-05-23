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

	// Anfangsbild
	public Tron_Presentation() {
		this.img = Toolkit.getDefaultToolkit().createImage("res/games/tron/tronbild.png");
	}

	@Override
	public Game getGame(final JPanel panel, final KeyRequest KEYS) {
		return new Tron(panel, KEYS);
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawImage(this.img, 0, 0, 1024, 1024, null);
	}
}
