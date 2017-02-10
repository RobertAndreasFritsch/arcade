package games.pong;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class Pong_Presentation implements Presentation {

	@Override
	public Game getGame(JPanel panel, KeyRequest KEYS) {
		return new Pong(panel, KEYS);
	}
}
