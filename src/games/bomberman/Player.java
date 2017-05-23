package games.bomberman;

import java.awt.Color;
import java.awt.Graphics2D;

import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import games.utils.Seat;

public class Player implements Drawable, ProceedsInput {
	private Seat s;
	private int x, y;
	private KeyRequest keys;
	private long WAIT_UNTIL_NEXT_MOVE = 100;
	private long WAIT_UNTIL_NEXT_BOMB = 1000;
	private long BOMB_TIMEOUT = 1500;
	private boolean canMove = true, canDropBomb = true, dead = false;
	private Field f;
	private Bomberman game;
	private int score = 1;

	public Player(Bomberman game, KeyRequest keys, Seat s, Field f, int x, int y) {
		this.s = s;
		this.x = x;
		this.y = y;
		this.f = f;
		this.game = game;
		this.keys = keys;
	}

	private void bombDropped() {
		canDropBomb = false;
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(WAIT_UNTIL_NEXT_BOMB);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				canDropBomb = true;
			}
		}.start();
	}

	public void die() {
		dead = true;
	}

	@Override
	public void draw(Graphics2D g) {
		if (!dead) {
			g.setColor(s.getColor());
			g.fillRoundRect(x, y, 32, 32, 50, 50);
		}
	}

	public Color getColor() {
		return s.getColor();
	}

	public int getScore() {
		return score;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isDead() {
		return dead;
	}

	private void moved() {
		canMove = false;
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(WAIT_UNTIL_NEXT_MOVE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				canMove = true;
			}
		}.start();
	}

	@Override
	public void processInput() {
		if (dead) {
			return;
		}
		if (canMove) {
			if (y / 32 - 1 >= 0 & y / 32 - 1 < 32) {
				if (keys.isPressed(s.UP) & f.ground[x / 32][y / 32 - 1] == 0) {
					y -= 32;
					moved();
					return;
				}
			}
			if (y / 32 + 1 >= 0 & y / 32 + 1 < 32) {
				if (keys.isPressed(s.DOWN) & f.ground[x / 32][y / 32 + 1] == 0) {
					y += 32;
					moved();
					return;
				}
			}
			if (x / 32 - 1 >= 0 & x / 32 - 1 < 32) {
				if (keys.isPressed(s.LEFT) & f.ground[x / 32 - 1][y / 32] == 0) {
					x -= 32;
					moved();
					return;
				}
			}
			if (x / 32 + 1 >= 0 & x / 32 + 1 < 32) {
				if (keys.isPressed(s.RIGHT) & f.ground[x / 32 + 1][y / 32] == 0) {
					x += 32;
					moved();
					return;
				}
			}
		}
		if (canDropBomb) {
			if (keys.isPressed(s.BTN1) & canDropBomb) {
//				game.getDrawables().add();
				game.add(new Bomb(game, s.getColor(), this, x, y, 3, BOMB_TIMEOUT));
				bombDropped();
			}
		}
	}

	public void setScore(int score) {
		this.score = score;
		s.setScore(score);
	}

}
