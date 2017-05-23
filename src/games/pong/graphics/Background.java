package games.pong.graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import environment.implementation.MyWindow;
import environment.model.gameobject.Drawable;

public class Background implements Drawable {

	@Override
	public void draw(final Graphics2D g) {
		g.setColor(Color.GRAY);

		g.fillRect(0, 0, MyWindow.getInstance().getSize().width, MyWindow.getInstance().getSize().height);

		g.setColor(Color.BLACK);
	}

}
