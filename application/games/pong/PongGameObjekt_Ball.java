package games.pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.Game;
import com.game.GameObject;
import com.game.Updateable;
import com.game.ctrl.KeyCtrl;

import games.pong.Tiles.Tile;

//03.02.2017
/**
 * @author default
 *
 */
public class PongGameObjekt_Ball implements Updateable, Drawable
{
	static int						i						= 0;
	static int						tz						= 0;
	static int						pl						= 4;
	static int						end					= 0;
	static int						tb						= 300;
	static int						th						= 50;
	static int						pkt					= 0;
	static int						a						= 0;
	static int						b						= 0;
	static int						c						= 0;
	static int						d						= 0;
	static int						e						= 0;
	static int						p						= 5;
	static double					pBx					= 0;
	static double					pBy					= 0;
	static int						Zaehler				= 0;
	int								Ballstartrichtung	= 0;
	// TODO hard coded
	File								F						= new File("res/games/pong/sounds/Pong.wav");

	/**
	 * <h1>not documented yet</h1>
	 *
	 * @author Mathis Kautz
	 *
	 */
	double							x						= 910;

	/**
	 *
	 */
	double							y						= 490;
	/**
	 *
	 */
	Rectangle						rect					= new Rectangle((int) this.x, (int) this.y, 30, 30);
	/**
	 *
	 */
	ArrayList<GameObject>		tiles					= new ArrayList<>();

	// PongGameObjekt_Blocker A = new PongGameObjekt_Blocker();
	/**
	 * <h1>not documented yet</h1>
	 *
	 * @author Mathis Kautz
	 *
	 */
	boolean							state1, state2, state3, state4, state5;
	boolean							YisDeath				= false;
	boolean							XisDeath				= false;
	boolean							blockerlimit		= false;
	/**
	 *
	 */

	/**
	 *
	 */
	/**
	 *
	 */
	double							dx, dy;
	/**
	 *
	 */
	PongGameObjekt_Blocker[]	blockers;

	private final Game			game;

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
	PongGameObjekt_Ball(final Game game, final KeyCtrl KEYS, final ArrayList<GameObject> tiles,
	      final PongGameObjekt_Blocker[] blockers)
	{
		this.game = game;

		this.tiles = tiles;
		this.blockers = blockers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see gamelibrary.GameObject#paint(java.awt.Graphics2D)
	 */
	@Override
	public void draw(final Graphics2D GRAPHICS)
	{

		GRAPHICS.fillRoundRect((int) this.x, (int) this.y, 20, 20, 100, 100);
	}

	static long	Roundtime	= 0;
	long			startBuffer	= 2000;

	/*
	 * (non-Javadoc)
	 *
	 * @see gamelibrary.GameObject#update(java.util.List)
	 */
	@Override
	public void update(final long elapsed)
	{
		// System.out.println(startBuffer);
		// System.out.println("-->>"+ ""+elapsed);
		if (this.startBuffer > 0 && PongGameObjekt_Ball.e != 0)
		{
			this.startBuffer -= elapsed;
			return;
		}
		if (this.startBuffer > 0 && PongGameObjekt_Ball.e == 0)
		{
			this.startBuffer -= elapsed;
			PongGameObjekt_Ball.e++;
			PongGameObjekt_Ball.i = 0;
			PongGameObjekt_Ball.tz = 0;
			PongGameObjekt_Ball.end = 0;
			return;
		}
		else
			if (PongGameObjekt_Ball.i == 0)
			{
				this.state5 = true;
				PongGameObjekt_Ball.i++;
			}

		// Blockerrelevant
		final int b = 100;
		final int l = 100;
		final double bx = 25;
		final double by = 25;
		// andere

		this.x += this.dx;
		this.y += this.dy;
		this.rect.x = (int) this.x;
		this.rect.y = (int) this.y;

		final Tile playerNorth = (Tile) this.tiles.get(0);
		final Tile playerEast = (Tile) this.tiles.get(1);
		final Tile playerSouth = (Tile) this.tiles.get(2);
		final Tile playerWest = (Tile) this.tiles.get(3);
		final int pBx2 = (int) (this.blockers[0].getX() + this.x);
		final int pBy2 = (int) (this.blockers[0].getX() + this.y);
		if (PongGameObjekt_Ball.tz == 0)
		{
			// hard coded
			// erste Positionen

			this.starter(playerEast, playerNorth, playerSouth, playerWest, l);

		}
		// Feldgrenze bzw. Torlinien
		if (this.rect.x > 1024)
		{
			if (playerEast.getTor() != 0)
			{
				playerEast.setTor(playerEast.getTor() - 1);
				if (PongGameObjekt_Ball.pkt != 2)
				{
					this.Pkterhalten(PongGameObjekt_Ball.pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerEast.getTor() == 0)
				{
					playerEast.setheight(1024);
					playerEast.setwidth(b);
					playerEast.seti();
					this.state2 = true;
					PongGameObjekt_Ball.end++;
				}
				this.startBuffer = this.startBuffer + 5 + 2000;
				this.state5 = true;
			}
		}
		if (this.rect.x < 0)
		{
			if (playerWest.getTor() != 0)
			{
				playerWest.setTor(playerWest.getTor() - 1);
				if (PongGameObjekt_Ball.pkt != 4)
				{
					this.Pkterhalten(PongGameObjekt_Ball.pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerWest.getTor() == 0)
				{
					playerWest.setheight(1024);
					playerWest.setwidth(b);
					playerWest.seti();
					this.state4 = true;
					PongGameObjekt_Ball.end++;
				}
				this.startBuffer = this.startBuffer + 5 + 2000;
				this.state5 = true;
			}
		}
		if (this.rect.y > 1024)
		{
			if (playerSouth.getTor() != 0)
			{
				playerSouth.setTor(playerSouth.getTor() - 1);
				if (PongGameObjekt_Ball.pkt != 3)
				{
					this.Pkterhalten(PongGameObjekt_Ball.pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerSouth.getTor() == 0)
				{
					playerSouth.setwidth(1024);
					playerSouth.setheight(l);
					playerSouth.seti();
					this.state3 = true;
					PongGameObjekt_Ball.end++;
				}
				this.startBuffer = this.startBuffer + 5 + 2000;
				this.state5 = true;
			}
		}
		if (this.rect.y < 0)
		{
			if (playerNorth.getTor() != 0)
			{
				playerNorth.setTor(playerNorth.getTor() - 1);
				if (PongGameObjekt_Ball.pkt != 1)
				{
					this.Pkterhalten(PongGameObjekt_Ball.pkt, playerEast, playerNorth, playerSouth, playerWest);
				}
				if (playerNorth.getTor() == 0)
				{
					playerNorth.setwidth(1024);
					playerNorth.setheight(l);
					playerNorth.seti();
					this.state1 = true;
					PongGameObjekt_Ball.end++;
				}
				this.startBuffer = this.startBuffer + 5 + 2000;
				this.state5 = true;
			}
		}

		// 2Spieler fehlen auf einer Achse:
		if (this.state2 == true && this.state4 == true && this.XisDeath == false)
		{
			this.dy = 20;
			this.dx = 0;
			this.XisDeath = true;
		}
		if (this.state1 == true && this.state3 == true && this.YisDeath == false)
		{
			this.dx = 20;
			this.dy = 0;
			this.YisDeath = true;
		}
		// Torspeeren:
		if (this.state2 == true && this.x + bx >= this.blockers[1].getX())
		{
			this.dx = -Math.abs(this.dx);
			this.blockers[1].seth(1024);
			PongGameObjekt_Ball.Playsound(this.F);
		}
		if (this.state4 == true && this.x <= this.blockers[0].getX() + l)
		{
			this.dx = Math.abs(this.dx);
			this.blockers[0].seth(1024);
			PongGameObjekt_Ball.Playsound(this.F);
		}
		if (this.state3 == true && this.y + by >= this.blockers[3].getY())
		{
			this.dy = -Math.abs(this.dy);
			this.blockers[3].setb(1240);
			PongGameObjekt_Ball.Playsound(this.F);
		}
		if (this.state1 == true && this.y <= this.blockers[0].getY() + l)
		{
			this.dy = Math.abs(this.dy);
			this.blockers[0].setb(1240);
			PongGameObjekt_Ball.Playsound(this.F);
		}
		// Linker Spieler

		if (this.y <= playerWest.getY() + 300 && this.y + by >= playerWest.getY() && this.x <= playerWest.getX() + 55
		      && this.x + bx >= playerWest.getX())
		{
			if (pBx2 <= playerWest.getX() || pBx2 >= playerWest.getX())
			{
				this.dx = Math.abs(this.dx);
				// dx = 0;
			}
			if (pBy2 <= playerWest.getY() || pBy2 >= playerWest.getY() + 300)
			{

				this.dy = this.dy * -1;
			}

			if (this.y <= playerWest.getY() + 50)
			{
				this.dy = this.dy - 5;

			}
			if (this.y <= playerWest.getY() + 100)
			{
				this.dy = this.dy - 5;
			}
			if (this.y >= playerWest.getY() + 200)
			{
				this.dy = this.dy + 5;
			}
			if (this.y >= playerWest.getY() + 250)
			{
				this.dy = this.dy + 5;
			}
			PongGameObjekt_Ball.Playsound(this.F);
			PongGameObjekt_Ball.pkt = 4;
			PongGameObjekt_Ball.Zaehler = 0;
		}
		// Rechter Spieler
		if (this.y <= playerEast.getY() + 300 && this.y + by >= playerEast.getY() && this.x <= playerEast.getX() + 55
		      && this.x + bx >= playerEast.getX())
		{

			if (pBx2 <= playerEast.getX() || pBx2 >= playerEast.getX())
			{
				this.dx = -Math.abs(this.dx);
				// dx = 0;
			}
			if (pBy2 <= playerEast.getY() || pBy2 >= playerEast.getY() + 300)
			{
				this.dy = this.dy * -1;
			}
			if (this.y <= playerEast.getY() + 50)
			{
				this.dy = this.dy - 5;

			}
			if (this.y <= playerEast.getY() + 100)
			{
				this.dy = this.dy - 5;
			}
			if (this.y >= playerEast.getY() + 200)
			{
				this.dy = this.dy + 5;
			}
			if (this.y >= playerEast.getY() + 250)
			{
				this.dy = this.dy + 5;
			}
			PongGameObjekt_Ball.Playsound(this.F);
			PongGameObjekt_Ball.pkt = 2;
			PongGameObjekt_Ball.Zaehler = 0;
		}
		// oberer Spieler

		if (this.y <= playerNorth.getY() + 55 && this.y + by >= playerNorth.getY() && this.x <= playerNorth.getX() + 300
		      && this.x + bx >= playerNorth.getX())
		{
			if (pBx2 <= playerNorth.getX() || pBx2 >= playerNorth.getX() + 300)
			{
				this.dx = this.dx * -1;
			}
			if (pBy2 <= playerNorth.getY() || pBy2 >= playerNorth.getY())
			{
				this.dy = Math.abs(this.dy);
			}
			if (this.x <= playerNorth.getX() + 50)
			{
				this.dx = this.dx - 5;
			}
			if (this.x <= playerNorth.getX() + 100)
			{
				this.dx = this.dx - 5;
			}
			if (this.x >= playerNorth.getX() + 200)
			{
				this.dx = this.dx + 5;
			}
			if (this.x >= playerNorth.getX() + 250)
			{
				this.dx = this.dx + 5;
			}
			PongGameObjekt_Ball.Playsound(this.F);
			PongGameObjekt_Ball.pkt = 1;
			PongGameObjekt_Ball.Zaehler = 0;
		}

		// Unterer Spieler
		if (this.y <= playerSouth.getY() + 55 && this.y + by >= playerSouth.getY() && this.x <= playerSouth.getX() + 300
		      && this.x + bx >= playerSouth.getX())
		{

			if (pBx2 <= playerSouth.getX() || pBx2 >= playerSouth.getX() + 300)
			{
				this.dx = this.dx * -1;
			}
			if (pBy2 <= playerSouth.getY() || pBy2 >= playerSouth.getY() + 50)
			{
				this.dy = -Math.abs(this.dy);
			}
			if (this.x <= playerSouth.getX() + 50)
			{
				this.dx = this.dx - 5;
			}
			if (this.x <= playerSouth.getX() + 100)
			{
				this.dx = this.dx - 5;
			}
			if (this.x >= playerSouth.getX() + 200)
			{
				this.dx = this.dx + 5;
			}
			if (this.x >= playerSouth.getX() + 250)
			{
				this.dx = this.dx + 5;
			}
			PongGameObjekt_Ball.Playsound(this.F);
			PongGameObjekt_Ball.pkt = 3;
			PongGameObjekt_Ball.Zaehler = 0;
		}

		// Max Geschwindigkeit:
		final int bmax = 20; // TODO hard coded
		PongGameObjekt_Ball.Roundtime = PongGameObjekt_Ball.Roundtime + elapsed;
		if (PongGameObjekt_Ball.Roundtime > 10000)
		{
			this.dx += Math.signum(this.dx) * 2; // Vorzeichen von dx (1/-1)
			                                     // +Faktor bzw. 2mal dx
			this.dy += Math.signum(this.dy) * 2;
			PongGameObjekt_Ball.Roundtime = 0;
		}
		if (this.dy > bmax)
		{
			this.dy = bmax;
		}
		if (this.dy < -bmax)
		{
			this.dy = -bmax;
		}
		if (this.dx > bmax)
		{
			this.dx = bmax;
		}
		if (this.dx < -bmax)
		{
			this.dx = -bmax;
		}

		// Blocker:
		// oben links Z=0
		// oben rechts Z=1
		// unten links Z=2
		// unten links Z=3
		for (int Z = 0; Z <= 3; Z++)
		{
			// Bugfix Unbeabsichtigte Kollision bei Torsperren
			if (playerNorth.getTor() == 0 || playerWest.getTor() == 0)
			{
				if (Z == 0)
				{
					Z++;
				}
			}
			if (playerNorth.getTor() == 0 || playerEast.getTor() == 0)
			{
				if (Z == 1)
				{
					Z++;
				}
			}
			if (playerWest.getTor() == 0 || playerSouth.getTor() == 0)
			{
				if (Z == 2)
				{
					Z++;
				}
			}
			if (playerEast.getTor() == 0 || playerSouth.getTor() == 0)
			{
				if (Z == 3)
				{
					this.blockerlimit = true;
				}
			}
			// Bugfix End + boolean ausserhalb
			if (this.x + bx >= this.blockers[Z].getX() && this.x <= this.blockers[Z].getX() + b
			      && this.y + by >= this.blockers[Z].getY() && this.y <= this.blockers[Z].getY() + l
			      && this.blockerlimit == false)
			{
				if (PongGameObjekt_Ball.pBx <= this.blockers[Z].getX())
				{
					this.dx = -Math.abs(this.dx);
					// System.out.println("1");
				}
				if (PongGameObjekt_Ball.pBx >= this.blockers[Z].getX() + b)
				{
					this.dx = Math.abs(this.dx);
					// System.out.println("2");
				}
				if (PongGameObjekt_Ball.pBy <= this.blockers[Z].getY())
				{
					this.dy = -Math.abs(this.dy);
					// System.out.println("3");
				}
				if (PongGameObjekt_Ball.pBy >= this.blockers[Z].getY() + l)
				{
					this.dy = Math.abs(this.dy);
					// System.out.println("4");
				}
				// System.out.println(pBy);
				PongGameObjekt_Ball.Playsound(this.F);
				PongGameObjekt_Ball.Zaehler++;
			}
			else
			{
				this.blockerlimit = false;
			}
		}
		PongGameObjekt_Ball.pBx = this.x;
		PongGameObjekt_Ball.pBy = this.y;
		if (PongGameObjekt_Ball.Zaehler > 5)
		{// Ballloopbrecher
			this.dx++;
			PongGameObjekt_Ball.Zaehler = 0;
			if (this.dy == 0)
			{
				this.dy++;
			}
		}
		// Endausgabe:
		if (PongGameObjekt_Ball.end == 3)
		{
			if (this.state1 == false)
			{
				System.out.println("Spieler 2 hat gewonnen");
			}
			if (this.state2 == false)
			{
				System.out.println("Spieler 4 hat gewonnen");
			}
			if (this.state3 == false)
			{
				System.out.println("Spieler 3 hat gewonnen");
			}
			if (this.state4 == false)
			{
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
			PongGameObjekt_Ball.tz = 0;
			PongGameObjekt_Ball.e = 0;
			PongGameObjekt_Ball.end++;
			this.game.setRunning(false);

		}
		if (this.state5)
		{
			// reset
			this.Ballstartrichtung = (int) (Math.random() * 4 + 1);
			PongGameObjekt_Ball.pkt = 0;
			this.rect.x = 910;
			this.rect.y = 490;
			this.state5 = false;
			this.x = 512;
			this.y = 512;
			// x=500;
			// y=512;
			// dx=10;
			// dy=0;
			// x = 60;
			// y = 470;
			if (this.YisDeath == false && this.XisDeath == true)
			{
				this.dx = 10;
				this.dy = Math.random() * 11;
			}
			if (this.XisDeath == false && this.YisDeath == true)
			{
				this.dy = 10;
				this.dx = Math.random() * 11;
			}
			else
				if (this.XisDeath == false && this.YisDeath == false)
				{
					if (this.Ballstartrichtung == 1)
					{
						this.dx = Math.random() * 11;
						this.dy = Math.random() * 11;
					}
					if (this.Ballstartrichtung == 2)
					{
						this.dx = -Math.abs(Math.random() * 10 - 11);
						this.dy = Math.random() * 11 + 10;
					}
					if (this.Ballstartrichtung == 3)
					{
						this.dx = Math.random() * 11 + 10;
						this.dy = -Math.abs(Math.random() * 10 - 11);
					}
					if (this.Ballstartrichtung == 4)
					{
						this.dx = -Math.abs(Math.random() * 10 - 11);
						this.dy = -Math.abs(Math.random() * 10 - 11);
					}
				}
		}
	}

	static void Playsound(final File Sound)
	{
		try
		{
			final Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 100000);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	public void Pkterhalten(final int pkt, final Tile playerEast, final Tile playerNorth, final Tile playerSouth,
	      final Tile playerWest)
	{
		if (pkt == 1)
		{
			playerNorth.setPkt();
		}
		if (pkt == 2)
		{
			playerEast.setPkt();
		}
		if (pkt == 3)
		{
			playerSouth.setPkt();
		}
		if (pkt == 4)
		{
			playerWest.setPkt();
		}
	}

	public void starter(final Tile playerEast, final Tile playerNorth, final Tile playerSouth, final Tile playerWest,
	      final int l)
	{
		if (Seat.P1.isPlaying() == true)
		{
			playerNorth.setTor(PongGameObjekt_Ball.p);
			playerNorth.setheight(PongGameObjekt_Ball.th);
			playerNorth.setwidth(PongGameObjekt_Ball.tb);
			playerNorth.seti();
		}
		else
		{
			playerNorth.setTor(0);
			playerNorth.setwidth(1024);
			playerNorth.setheight(l);
			playerNorth.seti();
			this.state1 = true;
			PongGameObjekt_Ball.end++;
		}
		if (Seat.P2.isPlaying() == true)
		{
			playerEast.setTor(PongGameObjekt_Ball.p);
			playerEast.setwidth(PongGameObjekt_Ball.th);
			playerEast.setheight(PongGameObjekt_Ball.tb);
			playerEast.seti();
		}
		else
		{
			playerEast.setTor(0);
			playerEast.setheight(1024);
			playerEast.setwidth(100);
			playerEast.seti();
			this.state2 = true;
			PongGameObjekt_Ball.end++;
		}
		if (Seat.P3.isPlaying() == true)
		{
			playerSouth.setTor(PongGameObjekt_Ball.p);
			playerSouth.setheight(PongGameObjekt_Ball.th);
			playerSouth.setwidth(PongGameObjekt_Ball.tb);
		}
		else
		{
			playerSouth.setTor(0);
			playerSouth.setwidth(1024);
			playerSouth.setheight(l);
			playerSouth.seti();
			this.state3 = true;
			PongGameObjekt_Ball.end++;
		}
		if (Seat.P4.isPlaying() == true)
		{
			playerWest.setTor(PongGameObjekt_Ball.p);
			playerWest.setheight(PongGameObjekt_Ball.tb);
			playerWest.setwidth(PongGameObjekt_Ball.th);
		}
		else
		{
			playerWest.setTor(0);
			playerWest.setheight(1024);
			playerWest.setwidth(100);
			playerWest.seti();
			this.state4 = true;
			PongGameObjekt_Ball.end++;
		}
		playerNorth.setY(0);
		playerEast.setX(1024);
		playerSouth.setY(1024);
		playerWest.setX(0);
		PongGameObjekt_Ball.tz++;
	}

}
