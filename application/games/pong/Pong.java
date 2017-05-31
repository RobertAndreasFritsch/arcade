package games.pong;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.arcade.utils.Seat;
import com.game.GameObject;
import com.game.MyGame;
import com.game.MyWindow;
import com.game.ctrl.KeyRequest;

import games.pong.controll.Controll;
import games.pong.graphics.Background;

/**
 * @author default
 *
 */
public final class Pong extends MyGame
{

	/**
	 *
	 */
	private static final long serialVersionUID = -3610479023387369082L;

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
	public Pong(final JPanel panel, final KeyRequest KEYS)
	{
		super(panel, KEYS);

		this.add(new Controll(this, KEYS));
		this.add(new Background());

		final ArrayList<GameObject> tiles = new ArrayList<>();

		// this.add(new PongGameObjekt_Blocker());

		final int b = 100, h = 100;

		final PongGameObjekt_Blocker B1 = new PongGameObjekt_Blocker(0, 0, b, h);
		final PongGameObjekt_Blocker B2 = new PongGameObjekt_Blocker(MyWindow.getInstance().getSize().width - b, 0, b, h);
		final PongGameObjekt_Blocker B3 = new PongGameObjekt_Blocker(0, MyWindow.getInstance().getSize().height - h, b,
		      h);
		final PongGameObjekt_Blocker B4 = new PongGameObjekt_Blocker(MyWindow.getInstance().getSize().width - b,
		      MyWindow.getInstance().getSize().height - h, b, h);

		this.add(B1);
		this.add(B2);
		this.add(B3);
		this.add(B4);

		final PongGameObjekt_Blocker[] blockers = { B1, B2, B3, B4 };

		tiles.add(new games.pong.Tiles.North(Seat.P1, KEYS, blockers));
		tiles.add(new games.pong.Tiles.East(Seat.P2, KEYS, blockers));
		tiles.add(new games.pong.Tiles.South(Seat.P3, KEYS, blockers));
		tiles.add(new games.pong.Tiles.West(Seat.P4, KEYS, blockers));
		this.add(new PongGameObjekt_Ball(this, KEYS, tiles, blockers));
		this.add(tiles.get(0));
		this.add(tiles.get(1));
		this.add(tiles.get(2));
		this.add(tiles.get(3));
	}
}
