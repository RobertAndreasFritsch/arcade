package games.knutzzz.control;

import java.awt.event.KeyEvent;

import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.knutzzz.Knutzzz;
import games.knutzzz.KnutzzzGameObject_Bumper;

public class WinnerControl implements Updateable, ProceedsInput {

	Knutzzz							parent;
	private final KeyRequest	KEYS;

	public WinnerControl(final Knutzzz parent, final KeyRequest KEYS) {
		this.parent = parent;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput() {
		if (this.KEYS.isPressed(KeyEvent.VK_M)) {
			this.parent.setRunning(false);
		}
	}

	@Override
	public void update(final long elapsed) {
		if (!this.parent.isGameRunning()) {
			this.parent.step++;
			if (this.parent.step == 100) {
				for (final Object go : this.parent.getPROCEEDINGINPUTS()) {
					if (go instanceof KnutzzzGameObject_Bumper) {
						((KnutzzzGameObject_Bumper) go).reset();
					}
				}
			}
		}
	}
}
