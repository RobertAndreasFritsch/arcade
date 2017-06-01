package games.zombies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Set;

import com.arcade.utils.Direction;
import com.game.ctrl.ImageCtrlImpl;
import com.game.deprecated.Drawable;
import com.game.deprecated.Updateable;

import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Zombie extends CollisionBox implements Drawable, Updateable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1683331211559374151L;

	private static final float WALKING_SPEED = 0;
	public static final ImageCtrlImpl textures = new ImageCtrlImpl();

	private float rotation = 0;
	private final Zombies game;
	private Image texture;

	private int hpBarWidth = 80;
	private int hpBarHeight = 10;

	private boolean collNorth = false;
	private boolean collEast = false;
	private boolean collSouth = false;
	private boolean collWest = false;

	private int hitBoxDim = 40;
	private int fullHp;
	private int hp;

	private double realX, realY;

	public Zombie(final int x, final int y, int hitBoxDim, int hp, final Zombies game) {
		super(x - (hitBoxDim >> 1), y - (hitBoxDim >> 1), hitBoxDim, hitBoxDim);
		this.game = game;
		this.hitBoxDim = hitBoxDim;
		this.realX = x;
		this.realY = y;
		this.fullHp = this.hp = hp;

		game.addCollisionBox(this);

		ArrayList<Image> images = new ArrayList<Image>();
		images.addAll(textures.images.values());
		texture = images.get((int) (Math.random() * images.size()));
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public void update(final long elapsed) {
		final float scalar = elapsed / 1000f;

		if (hp <= 0) {
			game.remove(this);
			game.removeCollisionBox(this);
		}

		Player target = null;
		double distance = -1;
		double difx;
		double dify;

		for (final Player p : this.game.getPlayers()) {
			difx = realX - p.getRealX();
			dify = realY - p.getRealY();
			final double ddistance = Math.sqrt(difx * difx + dify * dify);
			if (ddistance < distance) {
				distance = ddistance;
				target = p;
			} else if (distance == -1) {
				distance = ddistance;
				target = p;
			}
		}

		if (!(distance < hitBoxDim >> 1)) {

			float angle = (float) Math.toDegrees(Math.atan2(target.getY() - this.getY(), target.getX() - this.getX()));
			if (angle < 0) {
				angle += 360;
			}
			this.rotation = angle;

			double newX = realX, newY = realY;

			final float length = Zombie.WALKING_SPEED * scalar;
			newX += Math.sin(Math.toRadians(this.rotation + 90)) * length;
			newY -= Math.cos(Math.toRadians(this.rotation + 90)) * length;

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

			realX = newX;
			realY = newY;
			this.setX((int) (realX - (hitBoxDim >> 1)));
			this.setY((int) (realY - (hitBoxDim >> 1)));
		}

		this.collNorth = false;
		this.collEast = false;
		this.collSouth = false;
		this.collWest = false;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.rotate(Math.toRadians(this.rotation + 90), realX, realY);
		g.drawImage(this.texture, (int) (realX - 35), (int) (realY - 45), null);
		g.setColor(Color.RED);
		g.fillRoundRect((int) (realX - (hpBarWidth >> 1)), (int) (realY - 50), hpBarWidth, hpBarHeight, 5, 5);
		g.setColor(Color.GREEN);
		g.fillRoundRect((int) (realX - 40), (int) (realY - 50), (int) ((float) hp / fullHp * hpBarWidth), hpBarHeight,
				5, 5);
		g.rotate(-Math.toRadians(this.rotation + 90), realX, realY);

		super.draw(g);
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

}
