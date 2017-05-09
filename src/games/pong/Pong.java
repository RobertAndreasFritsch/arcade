package games.pong;

import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.implementation.MyWindow;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;
import games.pong.controll.Controll;
import games.pong.graphics.Background;

/**
 * @author default
 *
 */
public final class Pong extends MyGame {

	/**
	 * <h1>Der Constroctor von Pong</h1>
	 * 
	 * Pong ist ein Spiel der Arcade Game-Sammlung und handelt von vermoebeln
	 * deiner Baelle XD
	 * 
	 * @author Mathis Kautz
	 * @throws Exception
	 * 
	 */
	public Pong(JPanel panel, KeyRequest KEYS) {
		super(panel, KEYS);

		this.add(new Controll(this, KEYS));
		this.add(new Background());

		ArrayList<Object> tiles = new ArrayList<Object>();

		tiles.add(new games.pong.Tiles.North(Seat.P1, KEYS));
		tiles.add(new games.pong.Tiles.East(Seat.P2, KEYS));
		tiles.add(new games.pong.Tiles.South(Seat.P3, KEYS));
		tiles.add(new games.pong.Tiles.West(Seat.P4, KEYS));

		this.add(tiles.get(0));
		this.add(tiles.get(1));
		this.add(tiles.get(2));
		this.add(tiles.get(3));
		// this.add(new PongGameObjekt_Blocker());

		int b = 100, h = 100;

		PongGameObjekt_Blocker B1 = new PongGameObjekt_Blocker(0, 0, b, h);
		PongGameObjekt_Blocker B2 = new PongGameObjekt_Blocker(MyWindow.getInstance().getSize().width - b, 0, b, h);
		PongGameObjekt_Blocker B3 = new PongGameObjekt_Blocker(0, MyWindow.getInstance().getSize().height - h, b, h);
		PongGameObjekt_Blocker B4 = new PongGameObjekt_Blocker(MyWindow.getInstance().getSize().width - b,
				MyWindow.getInstance().getSize().height - h, b, h);

		this.add(B1);
		this.add(B2);
		this.add(B3);
		this.add(B4);

		PongGameObjekt_Blocker[] blockers = { B1, B2, B3, B4 };

		this.add(new PongGameObjekt_Ball(this, KEYS, tiles, blockers));
	}
}
