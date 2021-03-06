package games.pong;

import java.util.ArrayList;

import com.arcade.utils.Seat;
import com.game.GameObject;
import com.game.SimpleGame;
import com.game.ctrl.CtrlFactory;

import games.pong.controll.Controll;
import games.pong.graphics.Background;

/**
 * @author default
 *
 */
public final class Pong extends SimpleGame
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
	public Pong(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);

		this.add(new Controll(this, this.getKEYS()));
		this.add(new Background());

		final ArrayList<GameObject> tiles = new ArrayList<>();

		// this.add(new PongGameObjekt_Blocker());

		final int b = 100, h = 100;

		final PongGameObjekt_Blocker B1 = new PongGameObjekt_Blocker(0, 0, b, h);
		final PongGameObjekt_Blocker B2 = new PongGameObjekt_Blocker(1048 - b, 0, b, h);
		final PongGameObjekt_Blocker B3 = new PongGameObjekt_Blocker(0, 1048 - h, b, h);
		final PongGameObjekt_Blocker B4 = new PongGameObjekt_Blocker(1048 - b, 1048 - h, b, h);

		this.add(B1);
		this.add(B2);
		this.add(B3);
		this.add(B4);

		final PongGameObjekt_Blocker[] blockers = { B1, B2, B3, B4 };

		tiles.add(new games.pong.Tiles.North(Seat.P1, this.getKEYS(), blockers));
		tiles.add(new games.pong.Tiles.East(Seat.P2, this.getKEYS(), blockers));
		tiles.add(new games.pong.Tiles.South(Seat.P3, this.getKEYS(), blockers));
		tiles.add(new games.pong.Tiles.West(Seat.P4, this.getKEYS(), blockers));
		this.add(new PongGameObjekt_Ball(this, this.getKEYS(), tiles, blockers));
		this.add(tiles.get(0));
		this.add(tiles.get(1));
		this.add(tiles.get(2));
		this.add(tiles.get(3));
	}
}
