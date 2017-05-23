package games.tron;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;

//---------------------------------------------------------------------------------------------------------------------------
// Floor hintergrung einfuegen und setzten 
public class Background implements Drawable {
	Image floorImage;

	public Background() {
		//floorImage = Toolkit.getDefaultToolkit().getImage("res/games/tron/tile().png");	// wird momentan nicht genutz
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(floorImage, 0, 0, null);
	}

}
