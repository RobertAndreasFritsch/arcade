package games.zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.arcade.utils.Direction;
import com.arcade.utils.Seat;
import com.game.deprecated.Drawable;
import com.game.deprecated.Updateable;

import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Player extends CollisionBox implements Drawable, Updateable {

	private static final long serialVersionUID = 7151425924474408977L;
	private final static float ROTATION_SPEED = 200;
	private final static float WALKING_SPEED = 200;
	private final static float WALKING_SPEED_BACK = 100;

	private boolean collNorth = false;
	private boolean collEast = false;
	private boolean collSouth = false;
	private boolean collWest = false;
	
	private long waitPerShot = 500;
	private int dmgPerShot = 5;
	private float bulletSpeed = 500f;

	private long shotTimer = 0;
	private float x, y;
	private float rotation = 0;
	private final Zombies game;
	private final Seat s;
	private final Image texture = Toolkit.getDefaultToolkit()
			.createImage("res/games/zombies/textures/citizenplayer_handgun.png");

	public Player(final int x, final int y, final Seat s, final Zombies game) {
		super(x - 10, y - 10, 20, 20);
		this.x = x;
		this.y = y;
		this.game = game;
		this.s = s;
		game.addCollisionBox(this);
	}
	
	public long getWaitPerShot() {
		return waitPerShot;
	}

	public void setWaitPerShot(long waitPerShot) {
		this.waitPerShot = waitPerShot;
	}

	public int getDmgPerShot() {
		return dmgPerShot;
	}

	public void setDmgPerShot(int dmgPerShot) {
		this.dmgPerShot = dmgPerShot;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.rotate(Math.toRadians(this.rotation + 90), this.x, this.y);
		g.drawImage(this.texture, (int) this.x - 32, (int) this.y - 54, null);
		g.rotate(-Math.toRadians(this.rotation + 90), this.x, this.y);

		super.draw(g);
	}

	@Override
	public void update(final long elapsed) {
		this.shotTimer -= elapsed;

		final float scalar = elapsed / 1000f;

		float newX = this.x, newY = this.y;

		if (this.game.getKEYS().isPressed(this.s.LEFT)) {
			this.rotation -= Player.ROTATION_SPEED * scalar;
		}
		if (this.game.getKEYS().isPressed(this.s.RIGHT)) {
			this.rotation += Player.ROTATION_SPEED * scalar;
		}
		if (this.game.getKEYS().isPressed(this.s.UP)) {
			final float length = Player.WALKING_SPEED * scalar;

			newX += Math.sin(Math.toRadians(this.rotation + 90)) * length;
			newY -= Math.cos(Math.toRadians(this.rotation + 90)) * length;
		}
		if (this.game.getKEYS().isPressed(this.s.DOWN)) {
			final float length = Player.WALKING_SPEED_BACK * scalar;

			newX -= Math.sin(Math.toRadians(this.rotation + 90)) * length;
			newY += Math.cos(Math.toRadians(this.rotation + 90)) * length;
		}
		if (this.game.getKEYS().isPressed(this.s.BTN1)) {
			if (this.shotTimer <= 0) {
				this.game.add(new Bullet(this.x, this.y, dmgPerShot, this.rotation, 500f, this.game));
				this.shotTimer = waitPerShot;
			}
		}

		if (newY < this.y && this.collNorth) {
			newY = this.y;
		}
		if (newY > this.y && this.collSouth) {
			newY = this.y;
		}
		if (newX > this.x && this.collEast) {
			newX = this.x;
		}
		if (newX < this.x && this.collWest) {
			newX = this.x;
		}

		this.x = newX;
		this.y = newY;
		this.setX((int) this.x - 10);
		this.setY((int) this.y - 10);

		this.collNorth = false;
		this.collEast = false;
		this.collSouth = false;
		this.collWest = false;
	}

	@Override
	public void onCollision(final CollisionBox with, final Direction dir) {
		if (!(with instanceof Blockade)) {
			return;
		}

		switch (dir) {
		case NORTH:
			this.collNorth = true;
			break;
		case EAST:
			this.collEast = true;
			break;
		case SOUTH:
			this.collSouth = true;
			break;
		case WEST:
			this.collWest = true;
			break;
		}
	}

	@Override
	public String toString() {
		return "PLAYER";
	}

}
