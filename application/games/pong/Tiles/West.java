package games.pong.Tiles;

import java.awt.Rectangle;

import com.arcade.utils.Seat;
import com.game.KeyRequest;
import com.game.MyWindow;

import games.pong.PongGameObjekt_Blocker;

public class West extends Tile
{
	PongGameObjekt_Blocker[] blockers;

	public West(final Seat player, final KeyRequest KEYS, final PongGameObjekt_Blocker[] blockers)
	{
		super(player, KEYS);
		this.setheight(300);
		this.setwidth(50);
		this.init();
		this.blockers = blockers;
	}

	@Override
	public void actionLeft()
	{
		if (this.rect.y > 100 && this.getTor() != 0 && this.rect.y >= this.blockers[0].getY() + 100)
		{
			this.rect.y -= Tile.SPEED;
		}

	}

	@Override
	public void actionRight()
	{
		if (this.rect.y + 300 <= MyWindow.getInstance().getSize().height && this.getTor() != 0
		      && this.rect.y + 300 <= this.blockers[2].getY())
		{
			this.rect.y += Tile.SPEED;
		}

	}

	@Override
	protected void init()
	{
		final int y = (int) (MyWindow.getInstance().getSize().height * .5 - this.getheight() * .5);
		final int x = 0;

		this.rect = new Rectangle(x, y, this.getwidth(), this.getheight());
		// rect = new Rectangle(15, 350, 50, 300);

	}

}
