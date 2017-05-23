package games.bomberman;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public class ExplosionPoint implements Drawable, Updateable {

	private int x, y;
	private Bomberman game;
	private Player Dropper;
	private boolean gone = false;
	private Image i = Toolkit.getDefaultToolkit().createImage("res/games/bomberman/explosion.gif");;

	public ExplosionPoint(Bomberman game, int x, int y, Player Dropper, final long timeout) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.Dropper = Dropper;

		if ((x / 32 >= 0 & x / 32 < 32) & (y / 32 >= 0 & y / 32 < 32)) {
			if (game.getField().ground[x / 32][y / 32] == 1) {
				game.getField().ground[x / 32][y / 32] = 0;
				Dropper.setScore(Dropper.getScore() + Bomberman.BLOCK_DESTROY_SCORE);
			}
		} else {
			disappear();
			return;
		}

		for (Bomb b : new ArrayList<Bomb>(Bomb.bombs)) {
			if (b.getX() == x & b.getY() == y) {
				b.explode();
			}
		}

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				disappear();
			}
		}.start();
	}

	private void disappear() {
		if (gone) {
			return;
		}
		game.remove(this);
		gone = true;
	}

	@Override
	public void draw(Graphics2D g) {
		if (gone) {
			return;
		}
		g.drawImage(i, x, y, 32, 32, null);
	}

	@Override
	public void update(long elapsed) {
		if (gone) {
			return;
		}
		if (game.Player1 != null) {
			if (game.Player1.getX() == x && game.Player1.getY() == y & !game.Player1.isDead()) {
				game.Player1.die();
				ArrayList<Sounds> s = new ArrayList<Sounds>();
				for (Sounds c : Sounds.class.getEnumConstants()) {
					if (c.name().contains("scream")) {
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (Dropper == game.Player1) {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				} else {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
		if (game.Player2 != null) {
			if (game.Player2.getX() == x && game.Player2.getY() == y & !game.Player2.isDead()) {
				game.Player2.die();
				ArrayList<Sounds> s = new ArrayList<Sounds>();
				for (Sounds c : Sounds.class.getEnumConstants()) {
					if (c.name().contains("scream")) {
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (Dropper == game.Player2) {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				} else {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
		if (game.Player3 != null) {
			if (game.Player3.getX() == x && game.Player3.getY() == y & !game.Player3.isDead()) {
				game.Player3.die();
				ArrayList<Sounds> s = new ArrayList<Sounds>();
				for (Sounds c : Sounds.class.getEnumConstants()) {
					if (c.name().contains("scream")) {
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (Dropper == game.Player3) {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				} else {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
		if (game.Player4 != null) {
			if (game.Player4.getX() == x && game.Player4.getY() == y & !game.Player4.isDead()) {
				game.Player4.die();
				ArrayList<Sounds> s = new ArrayList<Sounds>();
				for (Sounds c : Sounds.class.getEnumConstants()) {
					if (c.name().contains("scream")) {
						s.add(c);
					}
				}
				s.get((int) (Math.random() * s.size())).play();
				if (Dropper == game.Player4) {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_SUICIDE_SCORE);
				} else {
					Dropper.setScore(Dropper.getScore() + Bomberman.PLAYER_KILL_SCORE);
				}
			}
		}
	}

}
