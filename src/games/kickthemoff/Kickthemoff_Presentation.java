package games.kickthemoff;

import javax.swing.JPanel;

import environment.launch.Presentation;
import environment.model.Game;
import environment.model.KeyRequest;

public class Kickthemoff_Presentation implements Presentation{

	@Override
	public Game getGame(JPanel panel, KeyRequest KEYS) {
		return new KickThemOff(panel,KEYS);
	}

}
