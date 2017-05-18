package games.kickthemoff;

import java.awt.Graphics2D;

import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

public class Player implements Drawable, Updateable {
	private static final float SPEED_INCREASE_PER_PRESS = 12f;
	private static final float SPEED_DECREASE_PER_SECOND = 2f;
	private Seat s;
	private float x, y;
	private float accelerationX, accelerationY;
	private KeyRequest keys;
	private boolean dead = false, dying = false;
	private float scale = 1;
	private Ground g;
	private Kickthemoff game;

	private Player lastKickedBy = this;

	public Player(Kickthemoff game, Seat s, int x, int y, Ground g) {
		this.s = s;
		this.x = x;
		this.y = y;
		accelerationX = 0;
		accelerationY = 0;
		this.keys = game.getKEYS();
		this.g = g;
		this.game = game;
	}

	@Override
	public void draw(Graphics2D g) {
		if (dead) {
			return;
		}
		g.setColor(s.getColor());
		g.fillOval((int) (x - 25 * scale), (int) (y - 25 * scale), (int) (50 * scale), (int) (50 * scale));
	}

	public float getAccelerationX() {
		return accelerationX;
	}

	public float getAccelerationY() {
		return accelerationY;
	}

	public Player getLastKickedBy() {
		return lastKickedBy;
	}

	public Seat getSeat() {
		return s;
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

	public int getWins() {
		return (int) (s.getScore() * 0.0001);
	}

	public void setWins(int wins) {
		s.setScore((int) ((s.getScore() * 0.00001 + wins) * 10000));
	}

	public void setAccelerationX(float accelerationX) {
		this.accelerationX = accelerationX;
	}

	public void setAccelerationY(float accelerationY) {
		this.accelerationY = accelerationY;
	}

	public void setLastKickedBy(Player lastKickedBy) {
		this.lastKickedBy = lastKickedBy;
	}

	public void revive() {
		dead = false;
		dying = false;
	}

	@Override
	public void update(long elapsed) {
		if (dead) {
			return;
		}
		float timefactor = ((float) elapsed / 1000);
		
		if (keys.isPressed(s.UP) & !dying) {
			accelerationY -= SPEED_INCREASE_PER_PRESS * timefactor;
		}
		if (keys.isPressed(s.DOWN) & !dying) {
			accelerationY += SPEED_INCREASE_PER_PRESS * timefactor;
		}
		if (keys.isPressed(s.LEFT) & !dying) {
			accelerationX -= SPEED_INCREASE_PER_PRESS * timefactor;
		}
		if (keys.isPressed(s.RIGHT) & !dying) {
			accelerationX += SPEED_INCREASE_PER_PRESS * timefactor;
		}
		
		x += accelerationX;
		y += accelerationY;

		
		accelerationX -= Math.abs(accelerationX) > 0 ? accelerationX > 0 ? SPEED_DECREASE_PER_SECOND * timefactor : -SPEED_DECREASE_PER_SECOND * timefactor : 0;
		accelerationY -= Math.abs(accelerationY) > 0 ? accelerationY > 0 ? SPEED_DECREASE_PER_SECOND * timefactor : -SPEED_DECREASE_PER_SECOND * timefactor : 0;
		
		if(Math.abs(accelerationX) < 0.01f){
			accelerationX = 0;
		}
		if(Math.abs(accelerationY) < 0.01f){
			accelerationY = 0;
		}

		float dx = x - g.getX();
		float dy = y - g.getY();
		float len = (float) Math.sqrt(dx * dx + dy * dy);
		if (len > g.getR() & !dying) {
			dying = true;
			lastKickedBy.getSeat().setScore(lastKickedBy.getSeat().getScore() + 50);
			Sounds.falling.play();
			new Thread() {
				@Override
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
