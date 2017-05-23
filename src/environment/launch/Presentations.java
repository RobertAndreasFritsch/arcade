package environment.launch;

import java.util.ArrayList;
import java.util.List;

import games.bomberman.Bomberman_Presesentation;
import games.kickthemoff.Kickthemoff_Presentation;
import games.knutzzz.Knutzzz_Presentation;
import games.pong.Pong_Presentation;
//import games.tank.TankPresentation;
import games.tron.Tron_Presentation;
import games.zombies.Zombies_Presentation;

public abstract class Presentations {

	public static List<Presentation> getPresentations() {
		final List<Presentation> presentations = new ArrayList<>();

		// presentations.add(new TankPresentation());
		presentations.add(new Bomberman_Presesentation());
		// presentations.add(new DAW_Presentation());
		presentations.add(new Knutzzz_Presentation());
		presentations.add(new Pong_Presentation());
		presentations.add(new Tron_Presentation());
		presentations.add(new Kickthemoff_Presentation());
		presentations.add(new Zombies_Presentation());

		return presentations;
	}
}
