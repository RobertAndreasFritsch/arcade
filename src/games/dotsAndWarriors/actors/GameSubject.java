package games.dotsAndWarriors.actors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.List;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public abstract class GameSubject implements Updateable, Drawable {

	/*
	 * start Values for Attributes
	 */
	public static final int startSize = 64;
	public static final int startHealth = 9000;
	public static final int startSpeed = 1;
	public static final int startTurningSpeed = 6;
	public static final int startPower = 12;

	public static final double startAngle = 0;
	public static final double startRange = 16;
	public static final double startHitAngle = 45;
	public static final double startProtection = 9;

	/*
	 * Action
	 */
	public boolean runningForward = false;
	public boolean runningBackward = false;
	public boolean attacking = false;
	public boolean dead = false;
	public boolean turningLeft = false;
	public boolean turningRight = false;

	/*
	 * Attribute
	 */
	private int size = startSize;
	private int health = startHealth;
	private int maxHealth = startHealth;
	private int speed = startSpeed;
	private int turningSpeed = startTurningSpeed;

	private double angle = startAngle;
	private double range = startRange;
	private double hitAngle = startHitAngle;
	private double power = startPower;
	private double protection = startProtection;

	/*
	 * Effects
	 */
	private long blocking = 0;

	/*
	 * other informations
	 */
	private double x;
	private double y;

	private Image image;
	private Image deathImage;
	private final List<GameSubject> enemies;
	private final List<GameSubject> friends;
	private final List<GameSubject> subjects;

	public GameSubject(int x, int y, Image img, List<GameSubject> friends, List<GameSubject> enemies,
			List<GameSubject> subjects) {
		setX(x);
		setY(y);

		setImage(img);
		setDeathImage(img);

		this.friends = friends;
		this.enemies = enemies;
		this.subjects = subjects;
	}

	public void attack() {
		for (GameSubject w : getEnemies()) {
			if (!w.isDead()) {
				if (GameSubjects.distance(w, this) <= ((w.getSize() + getSize()) >> 1) + getRange()) {
					if (Math.abs(
							(Math.abs(GameSubjects.angle(this, w) - getAngle() + 360) % 360) - 180) <= getHitAngle()) {

						double damage = getPower() - Math.sqrt(w.getProtection());
						damage = damage < 1 ? 1 : damage;

						w.setHealth(w.getHealth() - (int) damage);
					}
				}
			}
		}
		setAttacking(false);
	}

	@Override
	public void draw(Graphics2D g) {
		// shameless copied from "Jörg Kuhle" on 01.2017
		// referencing on project "Knutzzz"

		// turn image
		AffineTransform origXform = g.getTransform();
		AffineTransform newXform = (AffineTransform) (origXform.clone());

		// rotation midpoint
		newXform.rotate(Math.toRadians(getAngle()), getX(), getY());
		g.setTransform(newXform);

		// draw image
		g.drawImage(image, (int) (getX() - getHalfSize()), (int) (getY() - getHalfSize()), getSize(), getSize(), null);

		g.setColor(Color.RED);
		g.fillRect((int) (getX() - getHalfSize()), (int) (getY() - getHalfSize()) - (getSize() >> 3), getSize(),
				(getSize() >> 3));
		g.setColor(Color.GREEN);
		g.fillRect((int) (getX() - getHalfSize()), (int) (getY() - getHalfSize()) - (getSize() >> 3),
				getSize() * getHealth() / getMaxHealth(), (getSize() >> 3));

		g.setTransform(origXform);
	}

	public double getHitAngle() {
		return hitAngle;
	}

	public void setHitAngle(double hitAngle) {
		this.hitAngle = hitAngle;
	}

	public double getAngle() {
		return angle;
	}

	public Image getDeathImage() {
		return deathImage;
	}

	public double getHalfSize() {
		return size >> 1;
	}

	public int getHealth() {
		return health;
	}

	public Image getImage() {
		return image;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public double getPower() {
		return power;
	}

	public double getProtection() {
		return protection;
	}

	public double getRange() {
		return range;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public int getTurningSpeed() {
		return turningSpeed;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean isRunningBackward() {
		return runningBackward;
	}

	public boolean isRunningForward() {
		return runningForward;
	}

	public boolean isTurningLeft() {
		return turningLeft;
	}

	public boolean isTurningRight() {
		return turningRight;
	}

	public void setAngle(double angle) {
		if (angle < 0)
			angle += 360;
		else if (angle >= 360)
			angle -= 360;

		this.angle = angle;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setDeathImage(Image deathImage) {
		this.deathImage = deathImage;
	}

	public long getBlocking() {
		return blocking;
	}

	public void setBlocking(long blocking) {
		this.blocking = blocking;
	}

	public void setHealth(int health) {
		if (getBlocking() > 0)
			return;

		this.health = health;

		if (this.health <= 0) {
			this.health = 0;
			setDead(true);
		} else if (this.health > this.getMaxHealth()) {
			this.health = this.getMaxHealth();
		}
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public void setProtection(double protection) {
		this.protection = protection;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public void setRunningBackward(boolean runningBackward) {
		this.runningBackward = runningBackward;
	}

	public void setRunningForward(boolean runningForward) {
		this.runningForward = runningForward;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTurningLeft(boolean turningLeft) {
		this.turningLeft = turningLeft;
	}

	public void setTurningRight(boolean turningRight) {
		this.turningRight = turningRight;
	}

	public void setTurningSpeed(int turningSpeed) {
		this.turningSpeed = turningSpeed;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public List<GameSubject> getEnemies() {
		return enemies;
	}

	public List<GameSubject> getFriends() {
		return friends;
	}

	public List<GameSubject> getSubjects() {
		return subjects;
	}

	@Override
	public void update(long elapsed) {
		if (isDead())
			return;

		if (isTurningLeft())
			turnLeft();

		if (isTurningRight())
			turnRight();

		if (isAttacking())
			attack();

		else {
			if (isRunningForward())
				runForward();

			if (isRunningBackward())
				runBackward();

			// checking side collision
			if (x < size * .5) {
				x = size * .5;
			} else if (x > Toolkit.getDefaultToolkit().getScreenSize().width - (int) (size * .5)) {
				x = Toolkit.getDefaultToolkit().getScreenSize().width - (int) (size * .5);
			}

			if (y < size * .5) {
				y = size * .5;
			} else if (y > Toolkit.getDefaultToolkit().getScreenSize().height - (int) (size * .5)) {
				y = Toolkit.getDefaultToolkit().getScreenSize().height - (int) (size * .5);
			}
		}

		// manage Effects:

		setBlocking(getBlocking() - elapsed < 0 ? 0 : getBlocking() - elapsed);
	}

	public void runForward() {
		x += Math.cos(Math.toRadians(angle)) * speed;
		y += Math.sin(Math.toRadians(angle)) * speed;

		for (GameSubject g : getSubjects()) {
			if (g != this && !g.isDead()) {
				if (GameSubjects.distance(this, g) < (this.getSize() + g.getSize()) >> 1) {
					x -= Math.cos(Math.toRadians(angle)) * speed;
					y -= Math.sin(Math.toRadians(angle)) * speed;
					break;
				}
			}
		}

		setRunningForward(false);
	}

	public void runBackward() {
		x -= Math.cos(Math.toRadians(angle)) * speed;
		y -= Math.sin(Math.toRadians(angle)) * speed;

		for (GameSubject g : getSubjects()) {
			if (g != this && !g.isDead()) {
				if (GameSubjects.distance(this, g) < (this.getSize() + g.getSize()) >> 1) {
					x += Math.cos(Math.toRadians(angle)) * speed;
					y += Math.sin(Math.toRadians(angle)) * speed;
					break;

				}
			}
		}

		setRunningBackward(false);
	}

	public void turnLeft() {
		setAngle(getAngle() - getTurningSpeed());
		setTurningLeft(false);
	}

	public void turnRight() {
		setAngle(getAngle() + getTurningSpeed());
		setTurningRight(false);
	}
}