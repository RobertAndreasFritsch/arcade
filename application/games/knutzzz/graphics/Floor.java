package games.knutzzz.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.game.deprecated.Drawable;

public class Floor implements Drawable
{

	Image floorImage;

	public Floor()
	{
		this.floorImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/backdrop.png");
	}

	@Override
	public void draw(final Graphics2D g)
	{

		g.drawImage(this.floorImage, 0, 0, null);

	}

}
