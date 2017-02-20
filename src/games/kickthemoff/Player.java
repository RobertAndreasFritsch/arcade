package games.kickthemoff;

import java.awt.Graphics2D;

import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

public class Player implements Drawable, ProceedsInput, Updateable {
	private static final float SPEED_INCREASE_PER_PRESS = 0.05f;
	private static final float SPEED_DECREASE_PER_SECOND = 2f;

	private Seat s;
	private float x, y;
	private float accelerationX, accelerationY;
	private KeyRequest keys;
	private boolean dead = false, dying = false;
	private float scale = 1;
	private Ground g;
	private Player[] otherPlayers;
	private KickThemOff game;
	private Player lastKickedBy = this;

	public Player(KickThemOff game, Seat s, int x, int y, KeyRequest keys, Ground g, Player... otherPlayers) {
		this.s = s;
		this.x = x;
		this.y = y;
		accelerationX = 0;
		accelerationY = 0;
		this.keys = keys;
		this.g = g;
		this.otherPlayers = otherPlayers;
		this.game = game;
	}

	@Override
	public void processInput() {
		if (dead | dying) {
			return;
		}
		if (keys.isPressed(s.UP)) {
			accelerationY -= SPEED_INCREASE_PER_PRESS;
		}
		if (keys.isPressed(s.DOWN)) {
			accelerationY += SPEED_INCREASE_PER_PRESS;
		}
		if (keys.isPressed(s.LEFT)) {
			accelerationX -= SPEED_INCREASE_PER_PRESS;
		}
		if (keys.isPressed(s.RIGHT)) {
			accelerationX += SPEED_INCREASE_PER_PRESS;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (dead) {
			return;
		}
		g.setColor(s.getColor());
		g.fillOval((int) (x - 25 * scale), (int) (y - 25 * scale), (int) (50 * scale), (int) (50 * scale));
	}

	@Override
	public void update(long elapsed) {
		if (dead) {
			return;
		}
		x += accelerationX;
		y += accelerationY;

		accelerationX -= Math.abs(accelerationX) > 0
				? accelerationX > 0 ? SPEED_DECREASE_PER_SECOND * ((float) elapsed / 1000)
						: -SPEED_DECREASE_PER_SECOND * ((float) elapsed / 1000)
				: 0;
		accelerationY -= Math.abs(accelerationY) > 0
				? accelerationY > 0 ? SPEED_DECREASE_PER_SECOND * ((float) elapsed / 1000)
						: -SPEED_DECREASE_PER_SECOND * ((float) elapsed / 1000)
				: 0;

		float dx = x - g.getX();
		float dy = y - g.getY();
		float len = (float) Math.sqrt(dx * dx + dy * dy);
		if (len > g.getR() & !dying) {
			dying = true;
			lastKickedBy.getSeat().setScore(lastKickedBy.getSeat().getScore() + 50);
			Sounds.falling.play();
			new Thread() {
				public void run() {
					while (scale > 0) {
						scale -= 0.05;
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					dead = true;
					waterSplash w = new waterSplash(game, (int) x - 25, (int) y - 25);
					game.getDRAWABLES().add(1, w);
				}
			}.start();
		}

		for (Player p : game.getPlayers()) {
			if (p != this & !isDead() & !isDying() & !p.isDead() & !p.isDying()) {
				doCollision(this, p);
			}
		}
	}

	public float getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(float accelerationX) {
		this.accelerationX = accelerationX;
	}

	public float getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(float accelerationY) {
		this.accelerationY = accelerationY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean isDying() {
		return dying;
	}

	public Seat getSeat() {
		return s;
	}

	public Player getLastKickedBy() {
		return lastKickedBy;
	}

	public void setLastKickedBy(Player lastKickedBy) {
		this.lastKickedBy = lastKickedBy;
	}

	public static void doCollision(Player p1, Player p2) {
		// Normale
		float nx = p2.getX() - p1.getX();
		float ny = p2.getY() - p1.getY();
		if (nx * nx + ny * ny < 2500) {
			// Normalengeschwindigkeit
			double rvx = p2.getAccelerationX() - p1.getAccelerationX();
			double rvy = p2.getAccelerationY() - p1.getAccelerationY();
			// Normale normieren
			double absn = Math.sqrt(nx * nx + ny * ny);
			nx /= absn;
			ny /= absn;

			double vn = rvx * nx + rvy * ny;
			if (vn < 0) {
				// Kollisionspartner bewegen sich aufeinander zu
				// Elastizitaet e=1
				double e = 1;
				double j = -(1 + e) * vn;
				// Massen Player m=4, Ball m=1
				j = j * 2;
				// Impuls
				double impx = j * nx;
				double impy = j * ny;
				p1.setAccelerationX((float) (p1.getAccelerationX() - 0.25 * impx));
				p1.setAccelerationY((float) (p1.getAccelerationY() - 0.25 * impy));
				p2.setAccelerationX((float) (p2.getAccelerationX() + 0.25 * impx));
				p2.setAccelerationY((float) (p2.getAccelerationY() + 0.25 * impy));
			}
			Sounds.pling.play();
		}

		p1.setLastKickedBy(p2);
		p2.setLastKickedBy(p1);
	}
}