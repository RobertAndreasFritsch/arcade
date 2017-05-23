package games.pong.Tiles;

import java.awt.Rectangle;

import environment.implementation.MyWindow;
import environment.model.KeyRequest;
import games.utils.Seat;

public class South extends Tile {

	public South(final Seat player, final KeyRequest KEYS) {
		super(player, KEYS);
		this.setheight(50);
		this.setwidth(300);
		this.init();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionLeft() {
		if (this.rect.x > 100 && this.getTor() != 0) {
			this.rect.x -= Tile.SPEED;
		}

	}

	@Override
	public void actionRight() {
		if (this.rect.x + 300 <= MyWindow.getInstance().getSize().width && this.getTor() != 0) {
			this.rect.x += Tile.SPEED;
		}

	}

	@Override
	protected void init() {

		final int x = (int) (MyWindow.getInstance().getSize().width * .5 - this.getwidth() * .5), y = MyWindow.getInstance().getSize().height - this.getheight();

		this.rect = new Rectangle(x, y, this.getwidth(), this.getheight());
		// rect = new Rectangle(775, 1000, 300, 50);

	}

}
