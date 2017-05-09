package games.tron;

//import java.awt.Color;
import java.awt.Graphics2D;

import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

public class Player implements Drawable, Updateable, ProceedsInput {
	int x, y; // Aktuelle Position
	int dx, dy; // Gewuenschte Richtung
	int floor[][]; // Aktuelle position P
	int spielerID = 0;
	boolean dead = false;
	private int tileSize;
	private final int width;
	private final int height;
	private KeyRequest KEYS;
	public Seat player;
	Sound sound = new Sound();

	public Player(KeyRequest KEYS, int x, int y, int dx, int dy, int floor[][], int spielerID, int tileSize, int width,
			int height, Seat player) {

		this.player = player;
		this.KEYS = KEYS;
		this.x = x;
		this.y = y;
		this.floor = floor;
		this.spielerID = spielerID;
		this.tileSize = tileSize;
		this.height = height;
		this.width = width;

		this.dx = dx; // Start nach Rechts fuer P
		this.dy = dy;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	//
	public int getX() { // rueckgabe wert ist vom typ int
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// schleife zum gehen | abfrage ob feld belegt/ zu ende/ zusammenstoss mit
	// anderem spieler
	@Override
	public void update(long elapsed) {
		if (dead | !player.isPlaying()) { // nur noch einer Lebt -> brich ab
			dead = true;
			return;
		}

		player.setScore(player.getScore() + 1); // pro zurueckgelektes feld
												// einen punkt

		x = x + dx;
		y = y + dy;

		if (x < 0 || y < 0 || x > (width) || y > (height)) {
			// Spieler ausserhalb des Spielfeldes? -> Tod!
			for (int y = 0; y < (width); y++) {
				for (int x = 0; x < (height); x++) {
					if (floor[x][y] == spielerID) {
						floor[x][y] = 5;
					}
				}
			}
			dead = true;
			sound.play("res/games/tron/LaserBoom.wav");
		} else {
			// Kachel schon belegt? -> Tod!
			try {
				if (floor[x][y] == 0)
					;
			} catch (Exception e) {
				dx = 0;
				dy = 0;
				dead = true;
				sound.play("res/games/tron/LaserBoom.wav");

				for (int y = 0; y < (width); y++) {
					for (int x = 0; x < (height); x++) {
						if (floor[x][y] == spielerID) {
							floor[x][y] = 5;
						}
					}
				}
				return;
			}
			if (floor[x][y] == 0)
				floor[x][y] = spielerID;

			else {
				// Crash
				dx = 0;
				dy = 0;
				dead = true;
				sound.play("res/games/tron/LaserBoom.wav");

				for (int y = 0; y < (width); y++) {
					for (int x = 0; x < (height); x++) {
						if (floor[x][y] == spielerID) {
							floor[x][y] = 5;
						}
					}
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	//
	@Override
	public void draw(Graphics2D g) {
		g.setColor(player.getColor());
		g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// Key uebergabe (oben, unten, rechts, links) | verhindern von eigen toetung
	// und ob man noch lebt
	@Override
	public void processInput() {
		if (KEYS.isPressed(player.UP))
			if (!dead && dy == 0 && player.isPlaying()) { // boolean abfrage ob
															// spieler Tot ist
				dx = 0;
				dy = -1;
			}

		if (KEYS.isPressed(player.DOWN))
			if (!dead && dy == 0 && player.isPlaying()) { // abfrage ob er sich
															// selbt toetet
				dx = 0;
				dy = 1;
			}

		if (KEYS.isPressed(player.LEFT))
			if (!dead && dx == 0 && player.isPlaying()) {
				dx = -1;
				dy = 0;
			}

		if (KEYS.isPressed(player.RIGHT))
			if (!dead && dx == 0 && player.isPlaying()) {
				dx = 1;
				dy = 0;
			}
	}
}