package games.zombies.collision;

import games.utils.Direction;

public abstract class CollisionBox {
	private int x, y, width, height;

	public CollisionBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public static Direction doCollideOnX(CollisionBox c1, CollisionBox c2) {
		if(c1.getX() >= c2.getX() && c1.getX() <= c2.getX() + c2.getWidth()) {
			return Direction.WEST;
		}
		if(c2.getX() >= c1.getX() && c2.getX() <= c1.getX() + c1.getWidth()) {
			return Direction.EAST;
		}
		return null;
	}
	
	public static Direction doCollideOnY(CollisionBox c1, CollisionBox c2) {
		if(c1.getY() >= c2.getY() && c1.getY() <= c2.getY() + c2.getHeight()) {
			return Direction.SOUTH;
		}
		if(c2.getY() >= c1.getY() && c2.getY() <= c1.getY() + c1.getHeight()) {
			return Direction.NORTH;
		}
		return null;
	}
	
	public abstract void onCollision(CollisionBox with, boolean north, boolean east, boolean south, boolean west);
	
}