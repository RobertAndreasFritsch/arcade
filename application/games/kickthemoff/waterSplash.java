package games.kickthemoff;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.game.deprecated.Drawable;
import com.game.deprecated.Updateable;

public class waterSplash implements Drawable, Updateable
{
	private final Image			tex;
	private final int				x, y;
	private long					timeout	= 1000;
	private final Kickthemoff	game;

	public waterSplash(final Kickthemoff game, final int x, final int y)
	{
		this.tex = Toolkit.getDefaultToolkit().createImage("res/games/kickthemoff/water_splash.gif");
		this.x = x;
		this.y = y;
		this.game = game;
		Sounds.water_splash0.play();
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.drawImage(this.tex, this.x, this.y, null);
	}

	@Override
	public void update(final long elapsed)
	{
		this.timeout -= elapsed;
		if (this.timeout <= 0)
		{
			this.game.remove(this);
		}
	}

}
