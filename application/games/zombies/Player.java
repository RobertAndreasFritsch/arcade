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
	
	private Weapon weapon;
	private long shotTimer = 0;
	private float realX, realY;
	private float rotation = 0;
	private final Zombies game;
	private final Seat s;

	public Player(final int x, final int y, final Seat s, final Zombies game) {
		super(x - 10, y - 10, 20, 20);
		this.realX = x;
		this.realY = y;
		this.game = game;
		this.s = s;
		weapon = Weapon.getPistol(game);
		game.addCollisionBox(this);
	}
	
	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}

	public float getRealX() {
		return realX;
	}

	public float getRealY() {
		return realY;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.rotate(Math.toRadians(this.rotation - 90), this.realX, this.realY);
		g.drawImage(weapon.getImage(), (int) this.realX - 36, (int) this.realY - 23, null);
		g.rotate(-Math.toRadians(this.rotation - 90), this.realX, this.realY);

		super.draw(g);
	}

	@Override
	public void update(final long elapsed) {
		this.shotTimer -= elapsed;

		final float scalar = elapsed / 1000f;

		float newX = this.realX, newY = this.realY;

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
				weapon.fire(this.realX, this.realY, this.rotation);
			}
		}

		if (newY < this.realY && this.collNorth) {
			newY = this.realY;
		}
		if (newY > this.realY && this.collSouth) {
			newY = this.realY;
		}
		if (newX > this.realX && this.collEast) {
			newX = this.realX;
		}
		if (newX < this.realX && this.collWest) {
			newX = this.realX;
		}

		this.realX = newX;
		this.realY = newY;
		this.setX((int) this.realX - 10);
		this.setY((int) this.realY - 10);

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
