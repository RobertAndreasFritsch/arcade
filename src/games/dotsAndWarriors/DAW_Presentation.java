package games.dotsAndWarriors;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class DAW_Presentation implements Presentation {
	
	Image img;

	/**
	 * @throws IOException
	 */
	public DAW_Presentation() {
		img = Toolkit.getDefaultToolkit().getImage("res/games/daw/daw_Presesentation.png");
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, 0, 0, 1024,1024, null);
	}
	
	@Override
	public Game getGame(JPanel PANEL, KeyRequest KEYS) {
		return new DotsAndWarriors(PANEL, KEYS);
	}
}
