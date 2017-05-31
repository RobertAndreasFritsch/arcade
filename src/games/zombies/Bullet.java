package games.zombies;

import java.awt.Color;
import java.awt.Graphics2D;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.utils.Direction;
import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Bullet extends CollisionBox implements Drawable, Updateable {
	private static final int MAX_BOUNCES = 5;

	private float x, y;
	private double dXPerSec, dYPerSec;
	private Zombies game;
	private int bounces = 0;

	private boolean collNorth = false;
	private boolean collEast = false;
	private boolean collSouth = false;
	private boolean collWest = false;

	public Bullet(float x, float y, float rotation, float speed, Zombies game) {
		super((int) (x - 4), (int) (y - 4), 8, 8);
		this.x = x;
		this.y = y;
		this.game = game;

		dXPerSec = Math.sin(Math.toRadians(rotation + 90)) * speed;
		dYPerSec = Math.cos(Math.toRadians(rotation + 90)) * speed;

		game.addCollisionBox(this);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillOval((int) (x - 2), (int) (y - 2), 4, 4);

		super.draw(g);
	}

	@Override
	public void update(long elapsed) {
		double scalar = elapsed / 1000f;

		double dX = dXPerSec * scalar;
		double dY = dYPerSec * scalar;

		if (collNorth) {
			dYPerSec *= -1d;
			dY *= -1;
		}
		if (collEast) {
			dXPerSec *= -1d;
			dX *= -1;
		}
		if (collSouth) {
			dYPerSec *= -1d;
			dY *= -1;
		}
		if (collWest) {
			dXPerSec *= -1d;
			dX *= -1;
		}

		if (bounces >= MAX_BOUNCES) {
			game.remove(this);
			game.removeCollisionBox(this);
		}
		if (collNorth | collSouth | collWest | collEast) {
			bounces++;
		}

		x += dX;
		y -= dY;
		setX((int) x - 4);
		setY((int) y - 4);

		collNorth = false;
		collEast = false;
		collSouth = false;
		collWest = false;
	}

	@Override
	public void onCollision(CollisionBox with, Direction dir) {
		if (with instanceof Blockade) {
			switch (dir) {
			case NORTH:
				collNorth = true;
				break;
			case EAST:
				collEast = true;
				break;
			case SOUTH:
				collSouth = true;
				break;
			case WEST:
				collWest = true;
				break;
			}
		}
	}

}
