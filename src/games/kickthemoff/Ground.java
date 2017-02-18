package games.kickthemoff;

import java.awt.Color;
import java.awt.Graphics2D;

import environment.model.gameobject.Drawable;

public class Ground implements Drawable {
	private int x, y, r;

	public Ground(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(153, 77, 0));
		g.fillOval(x - r, y - r, r << 1, r << 1);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getR() {
		return r;
	}

}
