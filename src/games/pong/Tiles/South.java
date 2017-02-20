package games.pong.Tiles;

import java.awt.Rectangle;

import environment.implementation.MyWindow;
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
		if (rect.x + 300 <= MyWindow.getInstance().getSize().width && getTor() != 0) {
			rect.x += SPEED;
		}

	}

	@Override
	protected void init() {

		int x = (int) (MyWindow.getInstance().getSize().width * .5 - getwidth() * .5),
				y = MyWindow.getInstance().getSize().height - getheight();

		rect = new Rectangle(x, y, getwidth(), getheight());
		// rect = new Rectangle(775, 1000, 300, 50);

	}

}
