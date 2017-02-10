package games.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import environment.model.gameobject.Drawable;

public class Field implements Drawable {

	public static final String[] settings = { "Classic", "Forest", "Desert", "Cave", "Moon", "Graveyard", "Factory" };

	// Spielfeld
	private Feld[][] spielfeld = new Feld[32][32];
	private int[][] Kacheln = new int[10][10];

	public Image wall1Image;
	public Image wall2Image;
	public Image floor1Image;
	public Image floor2Image;
	public Image floor3Image;

	Field(int setting) {

		// Spielfeld genereieren
		generate();

		// SpielfeldTexturen
		floor1Image = Toolkit.getDefaultToolkit().getImage("img/" + settings[setting] + "/floor1.gif");
		floor2Image = Toolkit.getDefaultToolkit().getImage("img/" + settings[setting] + "/floor2.gif");
		floor3Image = Toolkit.getDefaultToolkit().getImage("img/" + settings[setting] + "/floor3.gif");

		wall1Image = Toolkit.getDefaultToolkit().getImage("img/" + settings[setting] + "/wall1.gif");
		wall2Image = Toolkit.getDefaultToolkit().getImage("img/" + settings[setting] + "/wall2.gif");
	}

	@Override
	public void draw(Graphics2D g) {
		for (int y = 0; y < 32; y++) {
			for (int x = 0; x < 32; x++) {

				if (spielfeld[x][y].typ == 0) {
					if (spielfeld[x][y].textur == 0) {

						g.drawImage(floor1Image, 32 * x, 32 * y, null);

					} else {
						if (spielfeld[x][y].textur == 1) {
							g.drawImage(floor2Image, 32 * x, 32 * y, null);
						} else {
							g.drawImage(floor3Image, 32 * x, 32 * y, null);
						}
					}

				} else {
					if (spielfeld[x][y].textur == 0) {
						g.drawImage(wall1Image, 32 * x, 32 * y, null);
					} else {
						g.drawImage(wall2Image, 32 * x, 32 * y, null);
					}

				}
			}

		}
	}

	public void generate() {
		// Zufallsgenerator
		Random r = new Random();
		int p1 = 10;
		int p2 = 30;

		// Spielfeld generieren
		int x;
		int y;
		for (x = 0; x < 32; x++) {
			for (y = 0; y < 32; y++) {
				spielfeld[x][y] = new Feld();
			}
		}

		// alle mit Leerr�umen ausf�llen
		for (x = 0; x < 32; x++) {
			for (y = 0; y < 32; y++) {
				spielfeld[x][y].typ = 0;
				int p = r.nextInt(100);
				if (p < p1) {
					spielfeld[x][y].textur = 2;
				} else {
					spielfeld[x][y].textur = r.nextInt(2);
				}

			}
		}

		// R�nder als Wall
		for (x = 0; x < 32; x++) {
			spielfeld[x][0].typ = 1;
		}
		for (y = 0; y < 32; y++) {
			spielfeld[0][y].typ = 1;
		}
		for (x = 0; x < 32; x++) {
			spielfeld[x][31].typ = 1;
		}
		for (y = 0; y < 32; y++) {
			spielfeld[31][y].typ = 1;
		}

		// festlegen der Kacheln

		for (y = 0; y < 10; y++) {
			for (x = 0; x < 10; x++) {
				int p = r.nextInt(100);
				if (p < p2) {
					Kacheln[x][y] = r.nextInt(8);
				} else {
					Kacheln[x][y] = r.nextInt(4);
				}
			}
		}

		// G�nge im Spielfeld generieren
		for (y = 0; y < 10; y++) {
			for (x = 0; x < 10; x++) {
				if (Kacheln[x][y] == 0) {
					spielfeld[3 * (x + 1)][3 * (y + 1) - 1].typ = 1;
					spielfeld[3 * (x + 1)][3 * (y + 1)].typ = 1;
					spielfeld[3 * (x + 1)][3 * (y + 1) - 2].typ = 1;

					spielfeld[3 * (x + 1)][3 * (y + 1) - 1].textur = r.nextInt(2);
					spielfeld[3 * (x + 1)][3 * (y + 1)].textur = r.nextInt(2);
					spielfeld[3 * (x + 1)][3 * (y + 1) - 2].textur = r.nextInt(2);

				}
				if (Kacheln[x][y] == 4) {
					spielfeld[3 * (x + 1)][3 * (y + 1)].typ = 1;
					spielfeld[3 * (x + 1)][3 * (y + 1)].textur = r.nextInt(2);
				}
				if (Kacheln[x][y] == 7) {
					spielfeld[3 * (x + 1)][3 * (y + 1) - 2].typ = 1;
					spielfeld[3 * (x + 1)][3 * (y + 1) - 2].textur = r.nextInt(2);
				}
				if (Kacheln[x][y] == 2) {

					spielfeld[3 * (x + 1) - 1][3 * (y + 1)].typ = 1;
					spielfeld[3 * (x + 1) - 2][3 * (y + 1)].typ = 1;
					spielfeld[3 * (x + 1)][3 * (y + 1)].typ = 1;

					spielfeld[3 * (x + 1) - 1][3 * (y + 1)].textur = r.nextInt(2);
					spielfeld[3 * (x + 1) - 2][3 * (y + 1)].textur = r.nextInt(2);
					spielfeld[3 * (x + 1)][3 * (y + 1)].textur = r.nextInt(2);

				}
				if (Kacheln[x][y] == 3) {

					spielfeld[3 * (x + 1)][3 * (y + 1) - 2].typ = 1;
					spielfeld[3 * (x + 1) - 1][3 * (y + 1) - 2].typ = 1;
					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].typ = 1;

					spielfeld[3 * (x + 1)][3 * (y + 1) - 2].textur = r.nextInt(2);
					spielfeld[3 * (x + 1) - 1][3 * (y + 1) - 2].textur = r.nextInt(2);
					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].textur = r.nextInt(2);

				}
				if (Kacheln[x][y] == 1) {

					spielfeld[3 * (x + 1) - 2][3 * (y + 1)].typ = 1;
					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 1].typ = 1;
					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].typ = 1;

					spielfeld[3 * (x + 1) - 2][3 * (y + 1)].textur = r.nextInt(2);
					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 1].textur = r.nextInt(2);
					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].textur = r.nextInt(2);

				}
				if (Kacheln[x][y] == 5) {
					spielfeld[3 * (x + 1) - 2][3 * (y + 1)].typ = 1;

					spielfeld[3 * (x + 1) - 2][3 * (y + 1)].textur = r.nextInt(2);
				}
				if (Kacheln[x][y] == 6) {
					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].typ = 1;

					spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].textur = r.nextInt(2);
				}
			}
		}

		// Antiblock
		for (x = 1; x < 31; x++) {
			spielfeld[x][1].typ = 0;
		}
		for (y = 1; y < 31; y++) {
			spielfeld[1][y].typ = 0;
		}
		for (x = 1; x < 31; x++) {
			spielfeld[x][30].typ = 0;
		}
		for (y = 1; y < 31; y++) {
			spielfeld[30][y].typ = 0;
		}
	}

	public Feld[][] getSpielfeld() {
		return spielfeld;
	}

	public void setSpielfeld(Feld[][] spielfeld) {
		this.spielfeld = spielfeld;
	}

}
