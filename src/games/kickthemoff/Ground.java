package games.kickthemoff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public class Ground implements Drawable, Updateable {
	private Image tex = Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/platform.png");
	private int x, y;
	private float r;
	private float SIZE_DECREASE_PER_SEC = 5;

	public Ground(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setClip(new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r));
		g.drawImage(tex, 0, 0, null);
		g.setClip(null);
	}

	public void setR(int r) {
		this.r = r;
	}

	public float getR() {
		return r;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void update(long elapsed) {
		float factor = (float) elapsed / 1000;
		factor = SIZE_DECREASE_PER_SEC * factor;
		r -= factor;
	}

}
