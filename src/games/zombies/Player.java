package games.zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

public class Player implements Drawable, Updateable {
	private final static float ROTATION_SPEED = 250;
	private final static float WALKING_SPEED = 200;

	private float x, y;
	private float rotation = 0;
	private Zombies game;
	private Seat s;
	private Image texture = Toolkit.getDefaultToolkit().createImage("res/games/zombies/player_top.png");

	public Player(int x, int y, Seat s, Zombies game) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.s = s;
	}

	@Override
	public void draw(Graphics2D g) {
		g.rotate(Math.toRadians(rotation), x, y);
		g.drawImage(texture, (int)x - 95, (int)y - 120, null);
		g.rotate(-Math.toRadians(rotation), x, y);
	}

	@Override
	public void update(long elapsed) {
		float scalar = elapsed / 1000f;

		if (game.getKEYS().isPressed(s.LEFT)) {
			rotation -= ROTATION_SPEED * scalar;
		}
		if (game.getKEYS().isPressed(s.RIGHT)) {
			rotation += ROTATION_SPEED * scalar;
		}
		if (game.getKEYS().isPressed(s.UP)) {
			float length = WALKING_SPEED * scalar;

			x += Math.sin(Math.toRadians(rotation + 90)) * length;
			y -= Math.cos(Math.toRadians(rotation + 90)) * length;
		}
	}

}
