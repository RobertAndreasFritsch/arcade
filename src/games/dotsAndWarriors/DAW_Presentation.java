package games.dotsAndWarriors;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class DAW_Presentation implements Presentation {
	@Override
	public Game getGame(JPanel PANEL, KeyRequest KEYS) {
		return new DotsAndWarriors(PANEL, KEYS);
	}
}
