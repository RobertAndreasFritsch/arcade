package games.kickthemoff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;

public class Ground implements Drawable {
	private Image tex = Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/platform.png");
	private int x, y, r;

	public Ground(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(tex, x - r, y - r, x + r, y + r, 0, 0, tex.getWidth(null), tex.getHeight(null), null);
	}

	public int getR() {
		return r;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
