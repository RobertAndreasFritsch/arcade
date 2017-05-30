package games.zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.utils.Seat;
import games.zombies.collision.CollisionBox;

public class Player extends CollisionBox implements Drawable, Updateable {
	private final static float ROTATION_SPEED = 250;
	private final static float WALKING_SPEED = 250;
	private final static float WALKING_SPEED_BACK = 100;

	private boolean collNorth = false;
	private boolean collEast = false;
	private boolean collSouth = false;
	private boolean collWest = false;
	
	private float x, y;
	private float rotation = 0;
	private Zombies game;
	private Seat s;
	private Image texture = Toolkit.getDefaultToolkit().createImage("res/games/zombies/player_top.png");

	public Player(int x, int y, Seat s, Zombies game) {
		super(x - 5, y - 5, 10, 10);
		this.x = x;
		this.y = y;
		this.game = game;
		this.s = s;
		game.addCollisionBox(this);
	}

	@Override
	public void draw(Graphics2D g) {
		g.rotate(Math.toRadians(rotation), x, y);
		g.drawImage(texture, (int) x - 23, (int) y - 30, null);
		g.rotate(-Math.toRadians(rotation), x, y);
	}

	@Override
	public void update(long elapsed) {
		float scalar = elapsed / 1000f;

		float newX = x, newY = y;

		if (game.getKEYS().isPressed(s.LEFT)) {
			rotation -= ROTATION_SPEED * scalar;
		}
		if (game.getKEYS().isPressed(s.RIGHT)) {
			rotation += ROTATION_SPEED * scalar;
		}
		if (game.getKEYS().isPressed(s.UP)) {
			float length = WALKING_SPEED * scalar;

			newX += Math.sin(Math.toRadians(rotation + 90)) * length;
			newY -= Math.cos(Math.toRadians(rotation + 90)) * length;
		}
		if (game.getKEYS().isPressed(s.DOWN)) {
			float length = WALKING_SPEED_BACK * scalar;

			newX -= Math.sin(Math.toRadians(rotation + 90)) * length;
			newY += Math.cos(Math.toRadians(rotation + 90)) * length;
		}
		
		x = newX;
		y = newY;
		setX((int) x - 5);
		setY((int) y - 5);
		
		collNorth = false;
		collEast = false;
		collSouth = false;
		collWest = false;
	}

	@Override
	public void onCollision(CollisionBox with, boolean north, boolean east, boolean south, boolean west) {
		System.out.println("COLLISION DETECTED! " + north + "  " + east + "  " + south + "  " + west);
		collNorth = north;
		collEast = east;
		collSouth = south;
		collWest = west;
	}

	public String toString() {
		return "PLAYER";
	}

}
