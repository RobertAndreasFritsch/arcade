package games.kickthemoff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class Kickthemoff_Presentation implements Presentation {

	private final Image img;

	public Kickthemoff_Presentation() {
		img = Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/kickthemoff_presentation.png");
	}

	@Override
	public Game getGame(JPanel panel, KeyRequest KEYS) {
		return new Kickthemoff(panel, KEYS);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, 0, 0, 1024,1024, null);
	}

}
