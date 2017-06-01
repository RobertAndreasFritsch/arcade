package games.bomberman;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.deprecated.Drawable;

public class Background implements Drawable
{

	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1048, 1048);
	}

}
