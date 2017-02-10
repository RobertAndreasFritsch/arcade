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
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

class GameSlider implements ProceedsInput, Updateable, Drawable {

	private static int activePresentation = 0;

	private static final long KeyBufferTime = 400l;

	private List<Presentation> presentations = Presentations.getPresentations();
	private final Menu parent;

	private final KeyRequest KEYS;
	private long bufferTime = 0l;

	public GameSlider(Menu parent, KeyRequest KEYS) {
		this.parent = parent;
		this.KEYS = KEYS;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		presentations.get(activePresentation).draw(g);
	}

	@Override
	public void processInput() {
		if (bufferTime > 0) {
			return;
		}

		if (KEYS.isPressed(Seat.P1.RIGHT)) {
			if (activePresentation >= presentations.size() - 1) {

				activePresentation = 0;
			} else {

				activePresentation++;
			}
			bufferTime = KeyBufferTime;

		} else if (KEYS.isPressed(Seat.P1.LEFT)) {
			if (activePresentation <= 0) {

				activePresentation = presentations.size() - 1;
			} else {

				activePresentation--;
			}
			bufferTime = KeyBufferTime;

		} else if (KEYS.isPressed(KeyEvent.VK_N)) {
			parent.setNextGame(new PlayerSelection_Presentation(presentations.get(activePresentation)));
			// parent.setNextGame(presentations.get(acticePresentation));
			parent.setRunning(false);
		}
	}

	@Override
	public void update(long elapsed) {
		if (bufferTime > 0) {
			bufferTime -= elapsed;

			if (bufferTime < 0) {
				bufferTime = 0;
			}
		}
	}
}

public class Menu extends MyGame {

	private Presentation nextGame;

	public Menu(JPanel panel, KeyRequest KEYS, String... args) {
		super(panel, KEYS, args);
		this.add(new GameSlider(this, KEYS));
	}

	@Override
	public Game getNextGame() {
		return nextGame.getGame(getPANEL(), getKEYS());
	}

	public void setNextGame(Presentation nextGame) {
		this.nextGame = nextGame;
	}
}
