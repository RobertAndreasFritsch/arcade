package games.knutzzz.control;

import java.awt.event.KeyEvent;

import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.knutzzz.Knutzzz;
import games.knutzzz.KnutzzzGameObject_Bumper;

public class WinnerControl implements Updateable, ProceedsInput {

	Knutzzz parent;
	private KeyRequest KEYS;

	public WinnerControl(Knutzzz parent, KeyRequest KEYS) {
		this.parent = parent;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput() {
		if (KEYS.isPressed(KeyEvent.VK_M)) {
			parent.setRunning(false);
		}
	}

	@Override
	public void update(long elapsed) {
		if (!parent.isGameRunning()) {
			parent.step++;
			if (parent.step == 100) {
				for (Object go : parent.getPROCEEDINGINPUTS()) {
					if (go instanceof KnutzzzGameObject_Bumper) {
						((KnutzzzGameObject_Bumper) go).reset();
					}
				}
			}
		}
	}
}
