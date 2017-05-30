package games.zombies.collision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import environment.model.gameobject.Drawable;
import games.utils.Direction;

public class CollisionBox extends Rectangle implements Drawable {

	public CollisionBox(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public static Direction getCollisionDirection(CollisionBox c1, CollisionBox c2) {
		Rectangle res = new Rectangle();
		Rectangle.intersect(c1, c2, res);

		double c1r = c1.getHeight() / c1.getWidth();
		double resr = res.getHeight() / res.getWidth();

		if (resr < c1r) {
			double midyres = res.getY() + res.getHeight() / 2d;
			double midyc = c1.getY() + c1.getHeight() / 2d;
			if (midyres >= midyc) {
				return Direction.SOUTH;
			} else {
				return Direction.NORTH;
			}
		} else {
			double midxres = res.getX() + res.getWidth() / 2d;
			double midxc = c1.getX() + c1.getWidth() / 2d;
			if (midxres >= midxc) {
				return Direction.EAST;
			} else {
				return Direction.WEST;
			}
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.drawRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	public void setX(int x) {
		setBounds(x, (int) getY(), (int) getWidth(), (int) getHeight());
	}

	public void setY(int y) {
		setBounds((int) getX(), y, (int) getWidth(), (int) getHeight());
	}

	public void setWidth(int width) {
		setBounds((int) getX(), (int) getY(), width, (int) getHeight());
	}

	public void setHeight(int height) {
		setBounds((int) getX(), (int) getY(), (int) getWidth(), height);
	}

	public static boolean doCollide(CollisionBox c1, CollisionBox c2) {
		return c1.intersects(c2);
	}

	public void onCollision(CollisionBox with, Direction dir) {
	}

}