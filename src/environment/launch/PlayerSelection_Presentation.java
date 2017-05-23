package environment.launch;

import javax.swing.JPanel;

import environment.model.Game;
import environment.model.KeyRequest;

public class PlayerSelection_Presentation implements Presentation {

	private final Presentation presentation;

	public PlayerSelection_Presentation(final Presentation presentation) {

		this.presentation = presentation;
	}

	@Override
	public Game getGame(final JPanel panel, final KeyRequest KEYS) {
		return new PlayerSelection(panel, KEYS, this.presentation);
	}
}
