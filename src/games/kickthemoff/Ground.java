package games.kickthemoff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public class Ground implements Drawable, Updateable {
	private final Image	tex							= Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/platform.png");
	private final int		x, y;
	private float			r;
	private final float	SIZE_DECREASE_PER_SEC	= 5;

	public Ground(final int x, final int y, final int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.setClip(new Ellipse2D.Float(this.x - this.r, this.y - this.r, 2 * this.r, 2 * this.r));
		g.drawImage(this.tex, 0, 0, null);
		g.setClip(null);
	}

	public void setR(final int r) {
		this.r = r;
	}

	public float getR() {
		return this.r;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public void update(final long elapsed) {
		float factor = (float) elapsed / 1000;
		factor = this.SIZE_DECREASE_PER_SEC * factor;
		this.r -= factor;
	}

}
