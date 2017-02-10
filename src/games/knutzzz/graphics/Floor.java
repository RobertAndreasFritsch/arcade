package games.knutzzz.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;

public class Floor implements Drawable {

	Image floorImage;

	public Floor() {
		floorImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/backdrop.png");
	}

	@Override
	public void draw(Graphics2D g) {

		g.drawImage(floorImage, 0, 0, null);

	}

}
