package games.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.utils.Seat;

public class Tank implements Updateable, Drawable, ProceedsInput {
	// ------------------------------------------------------------------Decs
	public static final int size = 16;

	double speed = .0;
	double TankPosX = .0;
	double TankPosY = .0;

	int player;
	Seat seat;

	double angle = .0;
	double maxAngle = 360;
	double anglespeed = 0;

	Image TankObject;
	private final Battleground battleground;
	private final BulletFactory bulletFactory;
	private final KeyRequest keys;

	// ------------------------------------------------------------------Constructor
	public Tank(final int player, final Battleground battleground, final BulletFactory bulletFactory,
			final KeyRequest keys) {

		this.keys = keys;
		this.bulletFactory = bulletFactory;
		this.battleground = battleground;
		this.player = player;

		// Spawns
		if (player == 0) {
			this.seat = Seat.P1_PlayerView;
			this.TankPosX = 1024 - 80;
			this.TankPosY = 80;
			this.angle = 135;

			try {
				this.TankObject = ImageIO.read(new File("res/games/tankies/img/Tankred.png"));
			} catch (final IOException e) {
				e.printStackTrace();
			}

		} else if (player == 1) {
			this.seat = Seat.P2_PlayerView;
			this.TankPosX = 1024 - 80;
			this.TankPosY = 1024 - 80;
			this.angle = 225;

			try {
				this.TankObject = ImageIO.read(new File("res/games/tankies/img/Tankblue.png"));
			} catch (final IOException e) {
				e.printStackTrace();
			}

		} else if (player == 2) {
			this.seat = Seat.P3_PlayerView;
			this.TankPosX = 80;
			this.TankPosY = 1024 - 80;
			this.angle = 315;

			try {
				this.TankObject = ImageIO.read(new File("res/games/tankies/img/Tankpurple.png"));
			} catch (final IOException e) {
				e.printStackTrace();
			}

		} else if (player == 3) {
			this.seat = Seat.P4_PlayerView;
			this.TankPosX = 80;
			this.TankPosY = 80;
			this.angle = 45;

			try {
				this.TankObject = ImageIO.read(new File("res/games/tankies/img/Tankorange.png"));
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ------------------------------------------------------------------Methods

	boolean forward0 = false;
	boolean forward1 = false;
	boolean backward = false;
	boolean left = false;
	boolean right = false;
	boolean fire = false;

	@Override
	public void processInput() {
		if (this.keys == null) {
			return;
		}
		
		this.forward0 = this.keys.isPressed(this.seat.UP);
		this.forward1 = this.keys.isPressed(this.seat.BTN2);
		
		this.backward = this.keys.isPressed(this.seat.DOWN);
		this.left = this.keys.isPressed(this.seat.LEFT);
		this.right = this.keys.isPressed(this.seat.RIGHT);
		this.fire = this.keys.isPressed(this.seat.BTN1);
	}

	@Override
	public void draw(final Graphics2D g) {

		// Drehen des Objekt

		final AffineTransform origform = g.getTransform();

		g.rotate(Math.toRadians(this.angle), (int) this.TankPosX, (int) this.TankPosY);
		g.drawImage(this.TankObject, (int) this.TankPosX - 16, (int) this.TankPosY - 16, null);

		g.setTransform(origform);
	}

	private int bulletTimeBuffer = 0;
	public static final int MAX_BULLET_TIME = 400;

	@Override
	public void update(final long elapsed) {

		if (this.forward0) {
			this.speed = .15 * elapsed;
		}
		if (this.forward1) {
			this.speed += .11 * elapsed;
		}

		if (this.backward) {
			this.speed -= .08 * elapsed;
		}
		if (this.left) {
			this.anglespeed += .3 * elapsed;
		}
		if (this.right) {
			this.anglespeed -= .3 * elapsed;
		}

		this.bulletTimeBuffer += elapsed;
		if (this.fire) {
			if (this.bulletTimeBuffer >= Tank.MAX_BULLET_TIME) {
				this.bulletFactory.newBullet(this);
				this.bulletTimeBuffer = 0;
			}
		}

		this.angle += this.anglespeed;
		if (this.angle >= 360) {
			this.angle -= 360;
		}
		if (this.angle < 0) {
			this.angle += 360;
		}

		// Position
		final double oldx = this.TankPosX;
		final double oldy = this.TankPosY;

		// Bewegen
		this.TankPosX = this.TankPosX + Math.cos(Math.toRadians(this.angle)) * this.speed;
		this.TankPosY = this.TankPosY + Math.sin(Math.toRadians(this.angle)) * this.speed;

		// Kollision
		final int posx = (int) (this.TankPosX + Math.cos(Math.toRadians(this.angle)) * 16 * Math.signum(this.speed))
				/ 32;
		final int posy = (int) (this.TankPosY + Math.sin(Math.toRadians(this.angle)) * 16 * Math.signum(this.speed))
				/ 32;

		if (this.battleground.spielfeld[posx][posy].typ > 0) {
			// System.out.println("Crash an "+posx+":"+posy+" mit
			// "+TankPosX+":"+TankPosY);
			this.TankPosX = oldx;
			this.TankPosY = oldy;
		}

		this.speed = 0;
		this.anglespeed = 0;
	}
}
