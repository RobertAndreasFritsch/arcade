package games.pong.Tiles;

import java.awt.Rectangle;
import java.awt.Toolkit;

import environment.model.KeyRequest;
import environment.model.gameobject.Seat;

public class South extends Tile {

	public South(Seat player, KeyRequest KEYS) {
		super(player, KEYS);
		setheight(50);
		setwidth(300);
		init();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionLeft() {
		if (rect.x > 100 && getTor() != 0) {
			rect.x -= SPEED;
		}

	}

	@Override
	public void actionRight() {
		if (rect.x + 300 <= Toolkit.getDefaultToolkit().getScreenSize().width && getTor() != 0) {
			rect.x += SPEED;
		}

	}

	@Override
	protected void init() {

		int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * .5 - getwidth() * .5),
				y = Toolkit.getDefaultToolkit().getScreenSize().height - getheight();

		rect = new Rectangle(x, y, getwidth(), getheight());
		// rect = new Rectangle(775, 1000, 300, 50);

	}

}
