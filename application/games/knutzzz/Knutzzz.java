package games.knutzzz;

import java.awt.event.KeyEvent;

import com.arcade.utils.Seat;
import com.game.SimpleGame;
import com.game.ctrl.CtrlFactory;

import games.knutzzz.control.WinnerControl;
import games.knutzzz.graphics.Dispenser;
import games.knutzzz.graphics.Floor;
import games.knutzzz.graphics.Goal;

/**
 *
 * @author Joerg (Yogi) Kuhle
 * @version 1.1
 *
 */

public class Knutzzz extends SimpleGame
{

	/**
	 *
	 */
	private static final long			serialVersionUID	= -8174198072253621177L;
	public int								step					= 0;
	KnutzzzGameObject_Dispenser		dispenser;
	public KnutzzzGameObject_Ball		ball;
	Sound										sound					= new Sound();
	KnutzzzGameObject_ScoreDisplay	scoreDisplays[];

	private boolean						gameRunning			= false;

	// private static Knutzzz instance;

	public Knutzzz(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);

		this.add(new Floor());

		// instance = this;
		this.dispenser = new KnutzzzGameObject_Dispenser(this);
		this.add(this.dispenser);
		this.scoreDisplays = new KnutzzzGameObject_ScoreDisplay[4];
		this.scoreDisplays[0] = new KnutzzzGameObject_ScoreDisplay(this, 0); // oben
		this.scoreDisplays[1] = new KnutzzzGameObject_ScoreDisplay(this, 1); // rechts
		this.scoreDisplays[2] = new KnutzzzGameObject_ScoreDisplay(this, 2); // unten
		this.scoreDisplays[3] = new KnutzzzGameObject_ScoreDisplay(this, 3); // links
		this.add(this.scoreDisplays[0]);
		this.add(this.scoreDisplays[1]);
		this.add(this.scoreDisplays[2]);
		this.add(this.scoreDisplays[3]);

		if (Seat.P1.isPlaying())
		{
			this.add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_4, KeyEvent.VK_6, KeyEvent.VK_7, 0, this.getKEYS()));
		}
		if (Seat.P2.isPlaying())
		{
			this.add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_Q, 1, this.getKEYS()));
		}
		if (Seat.P3.isPlaying())
		{
			this.add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_U, 2, this.getKEYS()));
		}
		if (Seat.P4.isPlaying())
		{
			this.add(new KnutzzzGameObject_Bumper(this, KeyEvent.VK_F, KeyEvent.VK_H, KeyEvent.VK_R, 3, this.getKEYS()));
		}

		this.add(new Dispenser(this, this.dispenser));
		this.add(new Goal());

		this.add(new WinnerControl(this, this.getKEYS()));

	}

	public boolean isGameRunning()
	{
		return this.gameRunning;
	}

	public void setGameRunning(final boolean gameRunning)
	{
		this.gameRunning = gameRunning;
	}

}
