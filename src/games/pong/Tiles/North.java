package games.pong.Tiles;

import java.awt.Rectangle;

import environment.implementation.MyWindow;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;
import games.pong.PongGameObjekt_Blocker;

public class North extends Tile {
	PongGameObjekt_Blocker[] blockers;
	public North(Seat player, KeyRequest KEYS, PongGameObjekt_Blocker[] blockers) {
		super(player, KEYS);
		setheight(50);
		setwidth(300);
		init();
		this.blockers = blockers;
		}

	@Override
	public void actionLeft() {
		if (rect.x + 300 <= MyWindow.getInstance().getSize().width && getTor() != 0 && rect.x+300 <= blockers[1].getX()) {
			rect.x += SPEED;
		}
	}

	@Override
	public void actionRight() {
		if (rect.x > 100 && getTor() != 0 && rect.x >= blockers[0].getX()+100) {
			rect.x -= SPEED;
		}
	}

	@Override
	protected void init() {
		int x = (int) (MyWindow.getInstance().getSize().width * .5 - getwidth() * .5), y = 0;

		rect = new Rectangle(x, y, getwidth(), getheight());
		// rect = new Rectangle(775, 15, 300, 50);
	}

}
