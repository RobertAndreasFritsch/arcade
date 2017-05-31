package games.kickthemoff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.game.Drawable;

public class Background implements Drawable
{
	private static Image tex = Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/background.png");

	@Override
	public void draw(final Graphics2D g)
	{
		g.drawImage(Background.tex, 0, 0, 1024, 1024, 0, 0, Background.tex.getWidth(null), Background.tex.getHeight(null),
		      null);
	}

}
