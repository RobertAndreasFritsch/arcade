package games.pong.Tiles;

import java.awt.Rectangle;

import environment.implementation.MyWindow;
import environment.model.KeyRequest;
import games.utils.Seat;

public class West extends Tile {

	public West(final Seat player, final KeyRequest KEYS) {
		super(player, KEYS);
		this.setheight(300);
		this.setwidth(50);
		this.init();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionLeft() {
		if (this.rect.y > 100 && this.getTor() != 0) {
			this.rect.y -= Tile.SPEED;
		}

	}

	@Override
	public void actionRight() {
		if (this.rect.y + 300 <= MyWindow.getInstance().getSize().height && this.getTor() != 0) {
			this.rect.y += Tile.SPEED;
		}

	}

	@Override
	protected void init() {
		final int y = (int) (MyWindow.getInstance().getSize().height * .5 - this.getheight() * .5);
		final int x = 0;

		this.rect = new Rectangle(x, y, this.getwidth(), this.getheight());
		// rect = new Rectangle(15, 350, 50, 300);

	}

}
