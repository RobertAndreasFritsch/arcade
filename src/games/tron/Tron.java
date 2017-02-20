// @Felix Thalmann
// 18.11.2016
// Tron Game

package games.tron;

//import java.awt.Color;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import environment.model.gameobject.Seat;

public final class Tron extends MyGame {

	private int floor[][]; //
	private final int tileSize = 2; //Felder groesse
	private int width = 512; // breite
	private int height = 512; // hoehe
	
	Sound sound=new Sound();

	boolean wartenFuerEnde = false; // End sequfenz warteschlage
	
//---------------------------------------------------------------------------------------------------------------------------
//Main (music anfang, KEYS einfuegen, hintergung einfuegen, erstellung der spieler, feld deklarieren, winner Implimentierung)
	public Tron(JPanel panel, KeyRequest KEYS) {
		super(panel, KEYS);
		
		sound.play("res/games/tron/TronSong.wav");
		
		add(new Controll(this, KEYS));
		add(new Background());
		
		floor = new int[width][height];
		
		int halfwidth = width >> 1;
		int halfheight = height >> 1;
		
		// Logisch Spieler
		Player P1 = new Player(KEYS, halfwidth, 0, 0, 1, floor, 1, tileSize, width, height, Seat.P1_PlayerView); // Player1 erstellen
		Player P2 = new Player(KEYS, width, halfheight, -1, 0, floor, 2, tileSize, width, height, Seat.P2_PlayerView); 
		Player P3 = new Player(KEYS, halfwidth, height, 0, -1, floor, 3, tileSize, width,height, Seat.P3_PlayerView);
		Player P4 = new Player(KEYS, 0, halfheight, 1, 0, floor, 4, tileSize, width, height, Seat.P4_PlayerView);

		add(P1);
		add(P2);
		add(P3);
		add(P4);
		
		// Graphische Darstellung der Spieler
		add(new Field(floor, tileSize, (width), (height)));
		
		add( new WinnerControll(P1, P2, P3, P4, this, sound));
	}
}