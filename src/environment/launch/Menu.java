package environment.launch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.utils.Seat;

class GameSlider implements ProceedsInput, Updateable, Drawable {

	private static int					activePresentation	= 0;

	private static final long			KeyBufferTime			= 400l;

	private final List<Presentation>	presentations			= Presentations.getPresentations();
	private final Menu					parent;

	private final KeyRequest			KEYS;
	private long							bufferTime				= 0l;

	public GameSlider(final Menu parent, final KeyRequest KEYS) {
		this.parent = parent;
		this.KEYS = KEYS;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.setColor(Color.BLACK);
		this.presentations.get(GameSlider.activePresentation).draw(g);
	}

	@Override
	public void processInput() {
		if (this.bufferTime > 0) { return; }

		if (this.KEYS.isPressed(Seat.P1.RIGHT)) {
			if (GameSlider.activePresentation >= this.presentations.size() - 1) {

				GameSlider.activePresentation = 0;
			}
			else {

				GameSlider.activePresentation++;
			}
			this.bufferTime = GameSlider.KeyBufferTime;

		}
		else
			if (this.KEYS.isPressed(Seat.P1.LEFT)) {
				if (GameSlider.activePresentation <= 0) {

					GameSlider.activePresentation = this.presentations.size() - 1;
				}
				else {

					GameSlider.activePresentation--;
				}
				this.bufferTime = GameSlider.KeyBufferTime;

			}
			else
				if (this.KEYS.isPressed(KeyEvent.VK_N)) {
					this.parent.setNextGame(new PlayerSelection_Presentation(this.presentations.get(GameSlider.activePresentation)));
					// parent.setNextGame(presentations.get(acticePresentation));
					this.parent.setRunning(false);
				}
	}

	@Override
	public void update(final long elapsed) {
		if (this.bufferTime > 0) {
			this.bufferTime -= elapsed;

			if (this.bufferTime < 0) {
				this.bufferTime = 0;
			}
		}
	}
}

public class Menu extends MyGame {

	private Presentation nextGame;

	public Menu(final JPanel panel, final KeyRequest KEYS, final String... args) {
		super(panel, KEYS, args);
		this.add(new GameSlider(this, KEYS));
	}

	@Override
	public Game getNextGame() {
		return this.nextGame.getGame(this.getPANEL(), this.getKEYS());
	}

	public void setNextGame(final Presentation nextGame) {
		this.nextGame = nextGame;
	}
}
