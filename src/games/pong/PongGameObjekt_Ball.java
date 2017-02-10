package games.pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;
import games.pong.Tiles.Tile;

//03.02.2017
/**
 * @author default
 *
 */
public class PongGameObjekt_Ball implements Updateable, Drawable {
	static int i = 0;
	static int tz = 0;
	static int pl = 4;
	static int end = 0;
	static int tb = 300;
	static int th = 50;
	static int pkt = 0;
	static int a = 0;
	static int b = 0;
	static int c = 0;
	static int d = 0;
	static int p = 5; // TODO hard coded

	/**
	 * <h1>not documented yet</h1>
	 * 
	 * @author Mathis Kautz
	 * 
	 */
	double x = 910;

	/**
	 * 
	 */
	double y = 490;
	/**
	 * 
	 */
	Rectangle rect = new Rectangle((int) x, (int) y, 30, 30);
	/**
	 * 
	 */
	ArrayList<Object> tiles = new ArrayList<Object>();

	// PongGameObjekt_Blocker A = new PongGameObjekt_Blocker();
	/**
	 * <h1>not documented yet</h1>
	 * 
	 * @author Mathis Kautz
	 * 
	 */
	boolean state1, state2, state3, state4, state5, stateX, stateY;
	/**
	 * 
	 */
	final int VK_Button3, VK_Button4, VK_Button5, VK_Button6, VK_Button7, VK_Button8;

	/**
	 * 
	 */
	/**
	 * 
	 */
	double dx, dy;
	/**
	 * 
	 */
	PongGameObjekt_Blocker[] blockers;

	private KeyRequest KEYS;
	private Game game;

	/**
	 * @param keyCode3
	 * @param keyCode4
	 * @param keyCode5
	 * @param keyCode6
	 * @param keyCode7
	 * @param keyCode8
	 * @param tiles
	 * @param blockers
	 * @throws Exception
	 */
	PongGameObjekt_Ball(Game game, KeyRequest KEYS, int keyCode3, int keyCode4, int keyCode5, int keyCode6,
			int keyCode7, int keyCode8, ArrayList<Object> tiles, PongGameObjekt_Blocker[] blockers) {
		this.game = game;
		this.KEYS = KEYS;
		VK_Button3 = keyCode3;
		VK_Button4 = keyCode4;
		VK_Button5 = keyCode5;
		VK_Button6 = keyCode6;
		VK_Button7 = keyCode7;
		VK_Button8 = keyCode8;// Resett
		this.tiles = tiles;
		this.blockers = blockers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gamelibrary.GameObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D GRAPHICS) {

		GRAPHICS.fillRoundRect((int) x, (int) y, 20, 20, 100, 100);
	}

	long startBuffer = 20000;

	/*
	 * (non-Javadoc)
	 * 
	 * @see gamelibrary.GameObject#update(java.util.List)
	 */
	@Override
	public void update(long elapsed) {
		File F = new File("res/pong/sounds/Pong.wav");

		if (startBuffer > 0) {
			startBuffer -= elapsed;
			i = 0;
			tz = 0;
			end = 0;
			return;
		} else if (i == 0) {
			state5 = true;
			i++;
		}

		// Blockerrelevant
		int b = 100;
		int l = 100;
		double bx = 21;
		double by = 21;
		// andere

		x += dx;
		y += dy;
		rect.x = (int) x;
		rect.y = (int) y;

		Tile playerNorth = (Tile) tiles.get(0);
		Tile playerEast = (Tile) tiles.get(1);
		Tile playerSouth = (Tile) tiles.get(2);
		Tile playerWest = (Tile) tiles.get(3);
		int pBx2 = (int) (blockers[0].getX() + x);
		int pBy2 = (int) (blockers[0].getX() + y);
		if (tz == 0) {
			// hard coded
			// erste Positionen

			if (Seat.P1.isPlaying() == true) {
				playerNorth.setTor(p);
				playerNorth.setheight(th);
				playerNorth.setwidth(tb);
				playerNorth.seti();
			} else {
				playerNorth.setTor(0);
				playerNorth.setwidth(Toolkit.getDefaultToolkit().getScreenSize().width);
				playerNorth.setheight(l);
				playerNorth.seti();
				state4 = true;
				end++;
			}
			if (Seat.P2.isPlaying() == true) {
				playerEast.setTor(p);
				playerEast.setwidth(th);
				playerEast.setheight(tb);
				playerEast.seti();
			} else {
				playerEast.setTor(0);
				playerEast.setheight(Toolkit.getDefaultToolkit().getScreenSize().height);
				playerEast.setwidth(b);
				playerEast.seti();
				state1 = true;
				end++;
			}
			if (Seat.P3.isPlaying() == true) {
				playerSouth.setTor(p);
				playerSouth.setheight(th);
				playerSouth.setwidth(tb);
			} else {
				playerSouth.setTor(0);
				playerSouth.setwidth(Toolkit.getDefaultToolkit().getScreenSize().width);
				playerSouth.setheight(l);
				playerSouth.seti();
				state3 = true;
				end++;
			}
			if (Seat.P4.isPlaying() == true) {
				playerWest.setTor(p);
				playerWest.setheight(tb);
				playerWest.setwidth(th);
			} else {
				playerWest.setTor(0);
				playerWest.setheight(Toolkit.getDefaultToolkit().getScreenSize().height);
				playerWest.setwidth(b);
				playerWest.seti();
				state2 = true;
				end++;
			}
			tz++;
			// playerNorth.seti();
			// playerEast.seti();
			// playerSouth.seti();
			// playerWest.seti();

		}
		// Feldgrenze bzw. Torlinien
		if (rect.x > Toolkit.getDefaultToolkit().getScreenSize().width) {
			if (playerEast.getTor() != 0) {
				playerEast.setTor(playerEast.getTor() - 1);
				if (pkt != 2) {
					Pkterhalten(pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerEast.getTor() == 0) {
					playerEast.setheight(Toolkit.getDefaultToolkit().getScreenSize().height);
					playerEast.setwidth(b);
					playerEast.seti();
					state1 = true;
					end++;
				}
				state5 = true;
			}
		}
		if (rect.x < 0) {
			if (playerWest.getTor() != 0) {
				playerWest.setTor(playerWest.getTor() - 1);
				if (pkt != 4) {
					Pkterhalten(pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerWest.getTor() == 0) {
					playerWest.setheight(Toolkit.getDefaultToolkit().getScreenSize().height);
					playerWest.setwidth(b);
					playerWest.seti();
					state2 = true;
					end++;
				}
				state5 = true;
			}
		}
		if (rect.y > Toolkit.getDefaultToolkit().getScreenSize().height) {
			if (playerSouth.getTor() != 0) {
				playerSouth.setTor(playerSouth.getTor() - 1);
				if (pkt != 3) {
					Pkterhalten(pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerSouth.getTor() == 0) {
					playerSouth.setwidth(Toolkit.getDefaultToolkit().getScreenSize().width);
					playerSouth.setheight(l);
					playerSouth.seti();
					state3 = true;
					end++;
				}
				state5 = true;
			}
		}
		if (rect.y < 0) {
			if (playerNorth.getTor() != 0) {
				playerNorth.setTor(playerNorth.getTor() - 1);
				if (pkt != 1) {
					Pkterhalten(pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerNorth.getTor() == 0) {
					playerNorth.setwidth(Toolkit.getDefaultToolkit().getScreenSize().width);
					playerNorth.setheight(l);
					playerNorth.seti();
					state4 = true;
					end++;
				}
				state5 = true;
			}
		}

		// 2Spieler fehlen auf einer Achse:

		if (state1 == true && state2 == true && stateY == false) {
			dy = 20;
			dx = 0;
			stateY = true;
		}
		if (state3 == true && state4 == true && stateX == false) {
			dx = 20;
			dy = 0;
			stateX = true;
		}
		// Torspeeren:

		if (state1 == true && x + bx >= blockers[1].getX()) {
			dx = dx * -1;
			Playsound(F);
		}
		if (state2 == true && x <= blockers[0].getX() + l) {
			dx = dx * -1;
			Playsound(F);
		}
		if (state3 == true && y + by >= blockers[3].getY()) {
			dy = dy * -1;
			Playsound(F);
		}
		if (state4 == true && y <= blockers[0].getY() + l) {
			dy = dy * -1;
			Playsound(F);
		}
		// Linker Spieler

		if (y <= playerWest.getY() + 300 && y + by >= playerWest.getY() && x <= playerWest.getX() + 50
				&& x + bx >= playerWest.getX()) {
			if (pBx2 <= playerWest.getX() || pBx2 >= playerWest.getX()) {
				dx = dx * -1;
				// dx = 0;
			}
			if (pBy2 <= playerWest.getY() || pBy2 >= playerWest.getY() + 300) {

				dy = dy * -1;
			}

			if (KEYS.isPressed(playerWest.player.RIGHT) && dy < 11 && dy > -11) {
				dy = dy + 5;
				// dx = dx + 5;
			}
			if (KEYS.isPressed(playerWest.player.LEFT) && dy < 11 && dy > -11) {
				dy = dy - 5;
				// dx = dx + 5;
			}
			Playsound(F);
			pkt = 4;
		}
		// Rechter Spieler
		if (y <= playerEast.getY() + 300 && y + by >= playerEast.getY() && x <= playerEast.getX() + 50
				&& x + bx >= playerEast.getX()) {

			if (pBx2 <= playerEast.getX() || pBx2 >= playerEast.getX()) {
				dx = dx * -1;
				// dx = 0;
			}
			if (pBy2 <= playerEast.getY() || pBy2 >= playerEast.getY() + 300) {

				dy = dy * -1;
			}

			if (KEYS.isPressed(playerEast.player.RIGHT) && dy < 11 && dy > -11) {
				dy = dy - 5;
				// dx = dx - 5;
			}
			if (KEYS.isPressed(playerEast.player.LEFT) && dy < 11 && dy > -11) {
				dy = dy + 5;
				// dx = dx - 5;
			}
			Playsound(F);
			pkt = 2;
		}
		// oberer Spieler

		if (y <= playerNorth.getY() + 50 && y + by >= playerNorth.getY() && x <= playerNorth.getX() + 300
				&& x + bx >= playerNorth.getX()) {
			if (pBx2 <= playerNorth.getX() || pBx2 >= playerNorth.getX() + 300) {
				dx = dx * -1;
			}
			if (pBy2 <= playerNorth.getY() || pBy2 >= playerNorth.getY()) {
				dy = dy * -1;
			}

			if (KEYS.isPressed(playerNorth.player.RIGHT) && dx < 11 && dx > -11) {
				// dy = dy - 5;
				dx = dx - 5;
			}
			if (KEYS.isPressed(playerNorth.player.LEFT) && dx < 11 && dx > -11) {
				// dy = dy + 5;
				dx = dx + 5;
			}
			Playsound(F);
			pkt = 1;
		}

		// Unterer Spieler
		if (y <= playerSouth.getY() + 50 && y + by >= playerSouth.getY() && x <= playerSouth.getX() + 300
				&& x + bx >= playerSouth.getX()) {

			if (pBx2 <= playerSouth.getX() || pBx2 >= playerSouth.getX() + 300) {
				dx = dx * -1;
			}
			if (pBy2 <= playerSouth.getY() || pBy2 >= playerSouth.getY() + 50) {
				dy = dy * -1;
			}

			if (KEYS.isPressed(playerSouth.player.RIGHT) && dx < 11 && dx > -11) {
				// dy = dy - 5;
				dx = dx + 5;
			}
			if (KEYS.isPressed(playerSouth.player.LEFT) && dx < 11 && dx > -11) {
				// dy = dy + 5;
				dx = dx - 5;

			}
			Playsound(F);
			pkt = 3;
		}
		// reset Button
		if (KEYS.isPressed(KeyEvent.VK_R)) {
			state5 = true;
		}
		// Max Geschwindigkeit:
		int bmax = 20; // TODO hard coded
		if (dy > bmax) {
			dy = bmax;
		}
		if (dy < -bmax) {
			dy = -bmax;
		}
		if (dx > bmax) {
			dx = bmax;
		}
		if (dx < -bmax) {
			dx = -bmax;
		}

		// Blocker:
		int pBx = (int) (blockers[0].getX() + x);
		int pBy = (int) (blockers[0].getX() + y);
		int Z3 = 0;
		for (int Z = 0; Z <= 3; Z++) {
			System.out.println();
			if (x + bx >= blockers[Z].getX() && x <= blockers[Z].getX() + b && y + by >= blockers[Z].getY()
					&& y <= blockers[Z].getY() + l) {
				if (pBx <= blockers[Z].getX() || pBx >= blockers[Z].getX() + b) {
					dx = dx * -1;
					Z3 = 1;
				}
				if (pBy <= blockers[Z].getY() || pBy >= blockers[Z].getY() + l) {
					dy = dy * -1;
					Z3 = 1;
				}
				if (Z3 != 0) {

					Z = 4;
				}
				Playsound(F);
			}
		}
		// Endausgabe:
		if (end == 3) {
			if (state1 == false) {
				System.out.println("Spieler 2 hat gewonnen");
			}
			if (state2 == false) {
				System.out.println("Spieler 4 hat gewonnen");
			}
			if (state3 == false) {
				System.out.println("Spieler 3 hat gewonnen");
			}
			if (state4 == false) {
				System.out.println("Spieler 1 hat gewonnen");
			}
			Seat.P1.setScore(playerNorth.getPkt());
			Seat.P2.setScore(playerEast.getPkt());
			Seat.P3.setScore(playerSouth.getPkt());
			Seat.P4.setScore(playerWest.getPkt());
			System.out.println("playerNorth --->>> " + playerNorth.getPkt());
			System.out.println("playerEast  --->>> " + playerEast.getPkt());
			System.out.println("playerSouth --->>> " + playerSouth.getPkt());
			System.out.println("playerWest  --->>> " + playerWest.getPkt());
			end++;
			game.setRunning(false);
		}
		if (state5) {
			// reset
			pkt = 0;
			rect.x = 910;
			rect.y = 490;
			state5 = false;
			x = Toolkit.getDefaultToolkit().getScreenSize().width * .5;
			y = Toolkit.getDefaultToolkit().getScreenSize().height * .5;
			// x = 60;
			// y = 470;
			if (stateX == false) {
				dx = 0;
			}
			if (stateY == false) {
				dy = 20;
			}
		}
	}

	static void Playsound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 100000);
		} catch (Exception e) {

		}
	}

	public void Pkterhalten(int pkt, Tile playerEast, Tile playerNorth, Tile playerSouth, Tile playerWest) {
		if (pkt == 1) {
			playerNorth.setPkt();
		}
		if (pkt == 2) {
			playerEast.setPkt();
		}
		if (pkt == 3) {
			playerSouth.setPkt();
		}
		if (pkt == 4) {
			playerWest.setPkt();
		}
	}

}
