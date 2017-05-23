// @Felix Thalmann
// 18.11.2016
// Tron Game

package games.tron;

//import java.awt.Color;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import games.utils.Seat;

public final class Tron extends MyGame {

	private final int	floor[][];							//
	private final int	tileSize			= 2;				// Felder groesse
	private final int	width				= 512;			// breite
	private final int	height			= 512;			// hoehe

	Sound					sound				= new Sound();

	boolean				wartenFuerEnde	= false;			// End sequfenz warteschlage

	// ---------------------------------------------------------------------------------------------------------------------------
	// Main (music anfang, KEYS einfuegen, hintergung einfuegen, erstellung der
	// spieler, feld deklarieren, winner Implimentierung)
	public Tron(final JPanel panel, final KeyRequest KEYS) {
		super(panel, KEYS);

		this.sound.play("res/games/tron/TronSong.wav");

		this.add(new Controll(this, KEYS));
		this.add(new Background());

		this.floor = new int[this.width][this.height];

		final int halfwidth = this.width >> 1;
		final int halfheight = this.height >> 1;

		// Logisch Spieler
		final Player P1 = new Player(KEYS, halfwidth, 0, 0, 1, this.floor, 1, this.tileSize, this.width, this.height, Seat.P1_PlayerView); // Player1
		                                                                                                                                   // erstellen
		final Player P2 = new Player(KEYS, this.width, halfheight, -1, 0, this.floor, 2, this.tileSize, this.width, this.height, Seat.P2_PlayerView);
		final Player P3 = new Player(KEYS, halfwidth, this.height, 0, -1, this.floor, 3, this.tileSize, this.width, this.height, Seat.P3_PlayerView);
		final Player P4 = new Player(KEYS, 0, halfheight, 1, 0, this.floor, 4, this.tileSize, this.width, this.height, Seat.P4_PlayerView);

		this.add(P1);
		this.add(P2);
		this.add(P3);
		this.add(P4);

		// Graphische Darstellung der Spieler
		this.add(new Field(this.floor, this.tileSize, this.width, this.height));

		this.add(new WinnerControll(P1, P2, P3, P4, this, this.sound));
	}
}