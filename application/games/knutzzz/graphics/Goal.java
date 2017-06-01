package games.knutzzz.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import com.game.deprecated.Drawable;

public class Goal implements Drawable
{

	Image goalImage;

	public Goal()
	{
		this.goalImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/goal.png");
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.drawImage(this.goalImage, 0, 0, null);
	}
}
