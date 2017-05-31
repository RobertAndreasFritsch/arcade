package games.tron;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.Game;
import com.game.Updateable;

public class WinnerControll implements Updateable, Drawable
{

	// gewinner erfassung
	private Seat			derGewinner;		// Uebergiebt den genauen Namen des
	boolean					Winner	= false;
	private Player			Gewinner	= null;	// zum Toeten des letzten Spielers
	private int				winner	= 0;
	private boolean		fertig	= false;
	private final Sound	sound;				// fuer die music

	private final Player	P1, P2, P3, P4;
	private final Game	game;

	public WinnerControll(final Player p1, final Player p2, final Player p3, final Player p4, final Game game,
	      final Sound sound)
	{
		this.P1 = p1;
		this.P2 = p2;
		this.P3 = p3;
		this.P4 = p4;

		this.sound = sound;
		this.game = game;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// abfrage wer der Gewinner ist
	@Override
	public void update(final long elapsed)
	{
		if (!this.P1.dead)
		{
			this.winner += 1;
			this.derGewinner = Seat.P1;
			this.Gewinner = this.P1;
		}
		if (!this.P2.dead)
		{
			this.winner += 1;
			this.derGewinner = Seat.P2;
			this.Gewinner = this.P2;
		}
		if (!this.P3.dead)
		{
			this.winner += 1;
			this.derGewinner = Seat.P3;
			this.Gewinner = this.P3;
		}
		if (!this.P4.dead)
		{
			this.winner += 1;
			this.derGewinner = Seat.P4;
			this.Gewinner = this.P4;
		}
		if (this.winner == 1)
		{
			this.Winner = true;
			this.Gewinner.dead = true; // Gewinner ist eingefrore
			this.derGewinner.setScore(this.derGewinner.getScore() + 1);
		}
		this.winner = 0;
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// Rotator fuer die end sequenfz "GAME OVER"
	private int				Grad				= 0, fontSize = 10;;
	private final Thread	rotator			= new Thread()
													{																															// zaehler
													                                                                                             // fuer
													                                                                                             // rotation
													                                                                                             // der
													                                                                                             // end
														// sequefnz
														@Override
														public void run()
														{
															while (true)
															{
																try
																{
																	Thread.sleep(8);																						// warte
																	                                                                                 // zeit
																	                                                                                 // |
																	                                                                                 // schnellichkeit
																	                                                                                 // der
																	// rutation
																}
																catch (final InterruptedException e)
																{
																	e.printStackTrace();
																}
																WinnerControll.this.Grad += 1;
																WinnerControll.this.fontSize += 1;
															}
														}
													};

	// ---------------------------------------------------------------------------------------------------------------------------
	// Gewinner ausgebae in Schwarz und weiss abwaechselnt
	private int				ColorCounter	= 0;																														// Zaehler

	private Color WhiteBlack()
	{
		if (this.ColorCounter++ % 2 == 0)
		{ // Gerade
			return Color.WHITE;
		}
		else
		{ // Ungerade
			return Color.BLACK;
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// Spiel Stand aktuell anzeigen (Gewinner und Verlierer)
	@Override
	public void draw(final Graphics2D g)
	{
		g.setFont(new Font("Courier", Font.BOLD, 20)); // font =Schrieft/Bold
		                                               // =schriftart/Farbe / 20
		                                               // =groesse/dicke = Courier

		if (this.Winner == true && Seat.P1 == this.derGewinner)
		{
			g.rotate(Math.toRadians(180));
			g.setColor(this.WhiteBlack());
			g.drawString("Gewinner " + this.derGewinner.getName(),
			      -(1024 / 2) - g.getFontMetrics().stringWidth("Gewinner " + this.derGewinner.getName()) / 2, 0); // p1
			g.rotate(Math.toRadians(-180));
		}
		else
		{
			if (this.P1.dead == true)
			{
				g.rotate(Math.toRadians(180));
				g.setColor(this.P1.player.getColor());
				g.drawString("GAME OVER " + Seat.P1.getName(),
				      -(1024 / 2) - g.getFontMetrics().stringWidth("GAME OVER " + Seat.P1.getName()) / 2, -10); // p1
				g.rotate(Math.toRadians(-180));
			}
		}

		if (this.Winner == true && Seat.P2 == this.derGewinner)
		{
			g.rotate(Math.toRadians(270)); // drehen
			g.setColor(this.WhiteBlack());
			g.drawString("Gewinner " + this.derGewinner.getName(),
			      -1024 / 2 - g.getFontMetrics().stringWidth("Gewinner " + this.derGewinner.getName()) / 2, 1024 - 10);
			g.rotate(Math.toRadians(-270));
		}
		else
		{
			if (this.P2.dead == true)
			{
				g.rotate(Math.toRadians(270)); // drehen
				g.setColor(this.P2.player.getColor());
				g.drawString("GAME OVER " + Seat.P2.getName(),
				      -1024 / 2 - g.getFontMetrics().stringWidth("GAME OVER " + Seat.P2.getName()) / 2, 1024 - 10); // p2
				g.rotate(Math.toRadians(-270));
			}
		}

		if (this.Winner == true && Seat.P3 == this.derGewinner)
		{
			g.setColor(this.WhiteBlack());
			g.drawString("Gewinner " + this.derGewinner.getName(),
			      1024 / 2 - g.getFontMetrics().stringWidth("Gewinner " + this.derGewinner.getName()) / 2, 1024 - 10);

		}
		else
		{
			if (this.P3.dead == true)
			{
				g.setColor(this.P3.player.getColor());
				g.drawString("GAME OVER " + Seat.P3.getName(),
				      1024 / 2 - g.getFontMetrics().stringWidth("GAME OVER " + Seat.P3.getName()) / 2, 1024 - 10); // p3
			}
		}

		if (this.Winner == true && Seat.P4 == this.derGewinner)
		{
			g.rotate(Math.toRadians(90));
			g.setColor(this.WhiteBlack());
			g.drawString("Gewinner " + this.derGewinner.getName(),
			      1024 / 2 - g.getFontMetrics().stringWidth("Gewinner " + this.derGewinner.getName()) / 2, -10);
			g.rotate(Math.toRadians(-90));
		}
		else
		{
			if (this.P4.dead == true)
			{
				g.rotate(Math.toRadians(90));
				g.setColor(this.P4.player.getColor());
				g.drawString("GAME OVER " + Seat.P4.getName(),
				      1024 / 2 - g.getFontMetrics().stringWidth("GAME OVER " + Seat.P4.getName()) / 2, -10); // p4
				g.rotate(Math.toRadians(-90));
			}
		}

		// End ausgabe mit rutation
		if (this.P1.dead == true && this.P2.dead == true && this.P3.dead == true && this.P4.dead == true)
		{
			if (!this.rotator.isAlive())
			{ // lebt der runner? , wenn nicht
				this.rotator.start(); // erwaecke ihn
			}
			g.setFont(new Font("Courier", Font.BOLD, this.fontSize));
			g.setColor(Color.white);
			g.translate(1024 / 2, 1024 / 2); // Position
			g.rotate(Math.toRadians(this.Grad));
			g.drawString("GAME OVER", -(g.getFontMetrics().stringWidth("GAME OVER") / 2), 0);
			this.end();
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// warte schleife fuer die anzeige des highscor
	public void end()
	{
		if (!this.fertig)
		{
			new Thread()
			{
				@Override
				public void run()
				{
					try
					{
						Thread.sleep(5000); // 5 sec. Pause
					}
					catch (final InterruptedException ex)
					{
						Thread.currentThread().interrupt();
						System.out.println("Exception caught");
					}
					WinnerControll.this.game.setRunning(false); // Spiel vorbei ->
					                                            // sound off
					WinnerControll.this.sound.stop();
				}
			}.start();
			this.fertig = true;
		}
	}
}