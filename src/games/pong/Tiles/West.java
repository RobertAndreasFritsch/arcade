package games.pong.Tiles;

import java.awt.Rectangle;

import environment.implementation.MyWindow;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;
import games.pong.PongGameObjekt_Blocker;

public class West extends Tile {
	PongGameObjekt_Blocker[] blockers;
	public West(Seat player, KeyRequest KEYS, PongGameObjekt_Blocker[] blockers) {
		super(player, KEYS);
		setheight(300);
		setwidth(50);
		init();
		this.blockers = blockers;
	}

	@Override
	public void actionLeft() {
		if (rect.y > 100 && getTor() != 0 && rect.y >= blockers[0].getY()+100) {
			rect.y -= SPEED;
		}

	}

	@Override
	public void actionRight() {
		if (rect.y + 300 <= MyWindow.getInstance().getSize().height && getTor() != 0 && rect.y+300 <= blockers[2].getY()) {
			rect.y += SPEED;
		}

	}

	@Override
	protected void init() {
		int y = (int) (MyWindow.getInstance().getSize().height * .5 - getheight() * .5);
		int x = 0;

		rect = new Rectangle(x, y, getwidth(), getheight());
		// rect = new Rectangle(15, 350, 50, 300);

	}

}
