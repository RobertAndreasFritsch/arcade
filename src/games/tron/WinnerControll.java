package games.tron;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import environment.model.Game;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

public class WinnerControll implements Updateable, Drawable {
	
	// gewinner erfassung
	private Seat derGewinner; // Uebergiebt den genauen Namen des
	boolean Winner = false;
	private Player Gewinner = null; // zum Toeten des letzten Spielers
	private int winner = 0;
	private boolean fertig = false; 
	private Sound sound;	// fuer die music 
	
	private Player P1, P2, P3, P4;
	private Game game;

	public WinnerControll(Player p1, Player p2, Player p3, Player p4, Game game, Sound sound) {
		this.P1 = p1;
		this.P2 = p2;
		this.P3 = p3;
		this.P4 = p4;
		
		this.sound = sound;
		this.game = game;
	}

//---------------------------------------------------------------------------------------------------------------------------
//	abfrage wer der Gewinner ist 
	@Override
	public void update(long elapsed) {
		if (!P1.dead) {
			winner += 1;
			derGewinner = Seat.P1;
			Gewinner = P1;
		}
		if (!P2.dead) {
			winner += 1;
			derGewinner = Seat.P2;
			Gewinner = P2;
		}
		if (!P3.dead) {
			winner += 1;
			derGewinner = Seat.P3;
			Gewinner = P3;
		}
		if (!P4.dead) {
			winner += 1;
			derGewinner = Seat.P4;
			Gewinner = P4;
		}
		if (winner == 1) {
			Winner = true;
			Gewinner.dead = true; // Gewinner ist eingefrore
			derGewinner.setScore(derGewinner.getScore()+1);
		}
		winner = 0;
	}
	
//---------------------------------------------------------------------------------------------------------------------------
//	Rotator fuer die end sequenfz "GAME OVER"
	private int Grad = 0, fontSize = 10;;
	private Thread rotator = new Thread() { // zaehler fuer rotation der end
		// sequefnz
		public void run() {
			while (true) {
				try {
					Thread.sleep((long) 8); // warte zeit | schnellichkeit der
					// rutation
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Grad += 1;
				fontSize += 1;
			}
		}
	};

//---------------------------------------------------------------------------------------------------------------------------
//	Gewinner ausgebae in Schwarz und weiss abwaechselnt 
	private int ColorCounter = 0; // Zaehler
	private Color WhiteBlack() {
		if (ColorCounter++ % 2 == 0) { // Gerade
			return Color.WHITE;
		} else { // Ungerade
			return Color.BLACK;
		}
	}

//---------------------------------------------------------------------------------------------------------------------------
//	Spiel Stand aktuell anzeigen (Gewinner und Verlierer)
	@Override
	public void draw(Graphics2D g) {
		g.setFont(new Font("Courier", Font.BOLD, 20)); // font =Schrieft/Bold =schriftart/Farbe / 20 =groesse/dicke = Courier

		if (Winner == true && Seat.P1 == derGewinner) {
			g.rotate(Math.toRadians(180));
			g.setColor(WhiteBlack());
			g.drawString("Gewinner " + derGewinner.getName(), -(1024 / 2) - ((g.getFontMetrics().stringWidth("Gewinner " + derGewinner.getName())) / 2), 0); // p1
			g.rotate(Math.toRadians(-180));
		} else {
			if (P1.dead == true) {
				g.rotate(Math.toRadians(180));
				g.setColor(P1.player.getColor());
				g.drawString("GAME OVER "+ Seat.P1.getName(), -(1024 / 2) - ((g.getFontMetrics().stringWidth("GAME OVER "+ Seat.P1.getName())) / 2),
						-10); // p1
				g.rotate(Math.toRadians(-180));
			}
		}

		if (Winner == true && Seat.P2 == derGewinner) {
			g.rotate(Math.toRadians(270)); // drehen
			g.setColor(WhiteBlack());
			g.drawString("Gewinner " + derGewinner.getName(), -1024 / 2 - ((g.getFontMetrics().stringWidth("Gewinner " + derGewinner.getName())) / 2), 1024 - 10);
			g.rotate(Math.toRadians(-270));
		} else {
			if (P2.dead == true) {
				g.rotate(Math.toRadians(270)); // drehen
				g.setColor(P2.player.getColor());
				g.drawString("GAME OVER "+ Seat.P2.getName(), -1024 / 2 - ((g.getFontMetrics().stringWidth("GAME OVER "+ Seat.P2.getName())) / 2),
						1024 - 10); // p2
				g.rotate(Math.toRadians(-270));
			}
		}

		if (Winner == true && Seat.P3 == derGewinner) {
			g.setColor(WhiteBlack());
			g.drawString("Gewinner " + derGewinner.getName(), 1024 / 2 - ((g.getFontMetrics().stringWidth("Gewinner " + derGewinner.getName())) / 2), 1024 - 10);

		} else {
			if (P3.dead == true) {
				g.setColor(P3.player.getColor());
				g.drawString("GAME OVER "+ Seat.P3.getName(), 1024 / 2 - ((g.getFontMetrics().stringWidth("GAME OVER "+ Seat.P3.getName())) / 2),
						1024 - 10); // p3
			}
		}

		if (Winner == true && Seat.P4 == derGewinner) {
			g.rotate(Math.toRadians(90));
			g.setColor(WhiteBlack());
			g.drawString("Gewinner " + derGewinner.getName(), (1024 / 2) - ((g.getFontMetrics().stringWidth("Gewinner " + derGewinner.getName())) / 2), -10);
			g.rotate(Math.toRadians(-90));
		} else {
			if (P4.dead == true) {
				g.rotate(Math.toRadians(90));
				g.setColor(P4.player.getColor());
				g.drawString("GAME OVER "+ Seat.P4.getName(), (1024 / 2) - ((g.getFontMetrics().stringWidth("GAME OVER "+ Seat.P4.getName())) / 2),
						-10); // p4
				g.rotate(Math.toRadians(-90));
			}
		}

		// End ausgabe mit rutation
		if (P1.dead == true && P2.dead == true && P3.dead == true && P4.dead == true) {
			if (!rotator.isAlive()) { // lebt der runner? , wenn nicht
				rotator.start(); // erwaecke ihn
			}
			g.setFont(new Font("Courier", Font.BOLD, fontSize));
			g.setColor(Color.white);
			g.translate((1024 / 2), (1024 / 2)); // Position
			g.rotate(Math.toRadians(Grad));
			g.drawString("GAME OVER", -((g.getFontMetrics().stringWidth("GAME OVER")) / 2), 0);
			end();
		}
	}
	
//---------------------------------------------------------------------------------------------------------------------------
//	warte schleife fuer die anzeige des highscor 
	public void end() {		
		if (!fertig) {
			new Thread() {
				public void run(){
					try {
						Thread.sleep(5000);                 // 5 sec. Pause
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
						System.out.println("Exception caught");
					}
					game.setRunning(false);	// Spiel vorbei -> sound off 
					sound.stop();
				}
			}.start();
			fertig = true;
		}
	}
}