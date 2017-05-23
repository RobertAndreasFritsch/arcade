package games.zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.utils.Seat;

public class Player implements Drawable, Updateable {
	private final static float	ROTATION_SPEED	= 250;
	private final static float	WALKING_SPEED	= 200;

	private float					x, y;
	private float					rotation			= 0;
	private final Zombies		game;
	private final Seat			s;
	private final Image			texture			= Toolkit.getDefaultToolkit().createImage("res/games/zombies/player_top.png");

	public Player(final int x, final int y, final Seat s, final Zombies game) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.s = s;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.rotate(Math.toRadians(this.rotation), this.x, this.y);
		g.drawImage(this.texture, (int) this.x - 95, (int) this.y - 120, null);
		g.rotate(-Math.toRadians(this.rotation), this.x, this.y);
	}

	@Override
	public void update(final long elapsed) {
		final float scalar = elapsed / 1000f;

		if (this.game.getKEYS().isPressed(this.s.LEFT)) {
			this.rotation -= Player.ROTATION_SPEED * scalar;
		}
		if (this.game.getKEYS().isPressed(this.s.RIGHT)) {
			this.rotation += Player.ROTATION_SPEED * scalar;
		}
		if (this.game.getKEYS().isPressed(this.s.UP)) {
			final float length = Player.WALKING_SPEED * scalar;

			this.x += Math.sin(Math.toRadians(this.rotation + 90)) * length;
			this.y -= Math.cos(Math.toRadians(this.rotation + 90)) * length;
		}
	}

}
