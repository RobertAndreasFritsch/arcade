package games.kickthemoff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public class waterSplash implements Drawable, Updateable {
	private Image tex;
	private int x, y;
	private long timeout = 1000;
	private KickThemOff game;

	public waterSplash(KickThemOff game, int x, int y) {
		tex = Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/water_splash.gif");
		this.x = x;
		this.y = y;
		this.game = game;
		Sounds.water_splash0.play();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(tex, x, y, null);
	}

	@Override
	public void update(long elapsed) {
		timeout -= elapsed;
		if (timeout <= 0) {
			game.remove(this);
		}
	}

}
