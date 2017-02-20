package games.kickthemoff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;

public class Background implements Drawable {
	private static Image tex = Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/background.png");

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(tex, 0, 0, 1024, 1024, 0, 0, tex.getWidth(null), tex.getHeight(null), null);
	}

}
