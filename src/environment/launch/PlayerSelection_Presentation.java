package environment.launch;

import javax.swing.JPanel;

import environment.model.Game;
import environment.model.KeyRequest;

public class PlayerSelection_Presentation implements Presentation {

	private Presentation presentation;

	public PlayerSelection_Presentation(Presentation presentation) {

		this.presentation = presentation;
	}

	@Override
	public Game getGame(JPanel panel, KeyRequest KEYS) {
		return new PlayerSelection(panel, KEYS, presentation);
	}
}
