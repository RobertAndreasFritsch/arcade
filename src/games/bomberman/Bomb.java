package games.bomberman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public class Bomb implements Drawable, Updateable {
	public static ArrayList<Bomb>	bombs		= new ArrayList<>();

	private final long				TIMEOUT	= 500;
	private long						tilExplosion;
	private final int					x, y, width;
	private final Player				Dropper;
	private final Bomberman			game;
	private boolean					exploded	= false;

	public Bomb(final Bomberman game, final Color c, final Player Dropper, final int x, final int y, final int width, final long timeout) {
		this.Dropper = Dropper;
		this.x = x;
		this.y = y;
		this.width = width;
		this.game = game;
		this.tilExplosion = timeout;

		Bomb.bombs.add(this);
	}

	@Override
	public void draw(final Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(this.x, this.y, 32, 32, 50, 50);
	}

	public void explode() {
		if (this.exploded) { return; }

		final ArrayList<Sounds> s = new ArrayList<>();
		for (final Sounds c : Sounds.class.getEnumConstants()) {
			if (c.name().contains("explosion")) {
				s.add(c);
			}
		}
		s.get((int) (Math.random() * s.size())).play();

		Bomb.bombs.remove(this);
		this.game.remove(this);
		this.exploded = true;
		for (int x = this.x; x > this.x - this.width * 32; x -= 32) {
			if (x / 32 >= 0 & x / 32 < 32 & this.y / 32 >= 0 & this.y / 32 < 32) {
				if (this.game.getField().ground[x / 32][this.y / 32] != 0) {
					this.game.add(new ExplosionPoint(this.game, x, this.y, this.Dropper, this.TIMEOUT));
					break;
				}
				this.game.add(new ExplosionPoint(this.game, x, this.y, this.Dropper, this.TIMEOUT));
			}
		}
		for (int x = this.x + 32; x < this.x + this.width * 32; x += 32) {
			if (x / 32 >= 0 & x / 32 < 32 & this.y / 32 >= 0 & this.y / 32 < 32) {
				if (this.game.getField().ground[x / 32][this.y / 32] != 0) {
					this.game.add(new ExplosionPoint(this.game, x, this.y, this.Dropper, this.TIMEOUT));
					break;
				}
				this.game.add(new ExplosionPoint(this.game, x, this.y, this.Dropper, this.TIMEOUT));
			}
		}

		for (int y = this.y - 32; y > this.y - this.width * 32; y -= 32) {
			if (this.x / 32 >= 0 & this.x / 32 < 32 & y / 32 >= 0 & y / 32 < 32) {
				if (this.game.getField().ground[this.x / 32][y / 32] != 0) {
					this.game.add(new ExplosionPoint(this.game, this.x, y, this.Dropper, this.TIMEOUT));
					break;
				}
				this.game.add(new ExplosionPoint(this.game, this.x, y, this.Dropper, this.TIMEOUT));
			}
		}
		for (int y = this.y + 32; y < this.y + this.width * 32; y += 32) {
			if (this.x / 32 >= 0 & this.x / 32 < 32 & y / 32 >= 0 & y / 32 < 32) {
				if (this.game.getField().ground[this.x / 32][y / 32] != 0) {
					this.game.add(new ExplosionPoint(this.game, this.x, y, this.Dropper, this.TIMEOUT));
					break;
				}
				this.game.add(new ExplosionPoint(this.game, this.x, y, this.Dropper, this.TIMEOUT));
			}
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public void update(final long elapsed) {
		this.tilExplosion -= elapsed;
		if (this.tilExplosion <= 0) {
			this.explode();
		}
	}

}
