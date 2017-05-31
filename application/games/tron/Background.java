package games.tron;

import java.awt.Graphics2D;
import java.awt.Image;

import com.game.Drawable;

//---------------------------------------------------------------------------------------------------------------------------
// Floor hintergrung einfuegen und setzten
public class Background implements Drawable
{
	Image floorImage;

	public Background()
	{
		// floorImage =
		// Toolkit.getDefaultToolkit().getImage("res/games/tron/tile().png"); //
		// wird momentan nicht genutz
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.drawImage(this.floorImage, 0, 0, null);
	}

}
