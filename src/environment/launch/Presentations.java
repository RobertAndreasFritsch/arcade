package environment.launch;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import environment.model.Game;
import environment.model.KeyRequest;
import games.bomberman.Bomberman_Presesentation;
import games.kickthemoff.Kickthemoff_Presentation;
import games.knutzzz.Knutzzz_Presentation;
import games.pong.Pong_Presentation;
import games.tank.Tankies;
//import games.tank.TankPresentation;
import games.tron.Tron_Presentation;
import games.zombies.Zombies_Presentation;

public abstract class Presentations {

	public static List<Presentation> getPresentations() {
		final List<Presentation> presentations = new ArrayList<>();

		try { // temp presentation
			presentations.add(new Presentation() {
				
				private Image img = ImageIO.read(new File("res/games/tank/img/tank_Presesentation.jpg"));
				
				@Override
				public void draw(Graphics2D g) {
					g.drawImage(img, 0, 0, 1048, 1048, null);
				}
				@Override
				public Game getGame(JPanel panel, KeyRequest KEYS) {
					return new Tankies(panel, KEYS, null);
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
