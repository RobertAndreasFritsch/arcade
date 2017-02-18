package games.kickthemoff;

import java.awt.Color;
import java.awt.Graphics2D;

import environment.model.gameobject.Drawable;

public class Background implements Drawable{

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1024, 1024);
	}

}
