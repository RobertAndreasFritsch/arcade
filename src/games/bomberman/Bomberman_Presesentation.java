package games.bomberman;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class Bomberman_Presesentation implements Presentation {

	@Override
	public Game getGame(JPanel panel, KeyRequest KEYS) {
		return new Bomberman(panel, KEYS);
	}

}
