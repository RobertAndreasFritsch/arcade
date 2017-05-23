package games.knutzzz.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;

public class Goal implements Drawable {

	Image goalImage;

	public Goal() {
		goalImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/goal.png");
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(goalImage, 0, 0, null);
	}
}
