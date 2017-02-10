package games.bomberman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import environment.model.gameobject.Drawable;

public class Bomb implements Drawable {
	public static ArrayList<Bomb> bombs = new ArrayList<Bomb>();

	private long TIMEOUT = 500;
	private Color c;
	private int x, y, width;
	private Player Dropper;
	private Bomberman game;
	private boolean exploded = false;

	public Bomb(Bomberman game, Color c, Player Dropper, int x, int y, int width, final long timeout) {
		this.c = c;
		this.Dropper = Dropper;
		this.x = x;
		this.y = y;
		this.width = width;
		this.game = game;

		bombs.add(this);

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				explode();
			}
		}.start();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x, y, 32, 32, 50, 50);
	}

	public void explode() {
		if (exploded) {
			return;
		}

		ArrayList<Sounds> s = new ArrayList<Sounds>();
		for (Sounds c : Sounds.class.getEnumConstants()) {
			if (c.name().contains("explosion")) {
				s.add(c);
			}
		}
		s.get((int) (Math.random() * s.size())).play();

		bombs.remove(this);
		game.remove(this);
		exploded = true;
		for (int x = this.x; x > this.x - width * 32; x -= 32) {
			if ((x / 32 >= 0 & x / 32 < 32) & (y / 32 >= 0 & y / 32 < 32)) {
				if (game.getField().ground[x / 32][y / 32] != 0) {
					game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
					break;
				}
				game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
			}
		}
		for (int x = this.x + 32; x < this.x + width * 32; x += 32) {
			if ((x / 32 >= 0 & x / 32 < 32) & (y / 32 >= 0 & y / 32 < 32)) {
				if (game.getField().ground[x / 32][y / 32] != 0) {
					game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
					break;
				}
				game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
			}
		}

		for (int y = this.y - 32; y > this.y - width * 32; y -= 32) {
			if ((x / 32 >= 0 & x / 32 < 32) & (y / 32 >= 0 & y / 32 < 32)) {
				if (game.getField().ground[x / 32][y / 32] != 0) {
					game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
					break;
				}
				game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
			}
		}
		for (int y = this.y + 32; y < this.y + width * 32; y += 32) {
			if ((x / 32 >= 0 & x / 32 < 32) & (y / 32 >= 0 & y / 32 < 32)) {
				if (game.getField().ground[x / 32][y / 32] != 0) {
					game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
					break;
				}
				game.add(new ExplosionPoint(game, x, y, Dropper, TIMEOUT));
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
