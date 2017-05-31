package games.pong.Tiles;

import java.awt.Rectangle;

import com.arcade.utils.Seat;
import com.game.ctrl.KeyRequest;

import games.pong.PongGameObjekt_Blocker;

public class East extends Tile
{

	PongGameObjekt_Blocker[] blockers;

	public East(final Seat player, final KeyRequest KEYS, final PongGameObjekt_Blocker[] blockers)
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
		if (this.rect.y + 300 <= 1048 && this.getTor() != 0 && this.rect.y + 300 <= this.blockers[3].getY())
		{
			this.rect.y += Tile.SPEED;
		}
	}

	@Override
	public void actionRight()
	{
		if (this.rect.y >= 100 && this.getTor() != 0 && this.rect.y >= this.blockers[1].getY() + 100)
		{
			this.rect.y -= Tile.SPEED;
		}

	}

	@Override
	protected void init()
	{
		final int y = (int) (1048 * .5 - this.getheight() * .5);
		final int x = 1048 - this.getwidth();
		this.rect = new Rectangle(x, y, this.getwidth(), this.getheight());
		// rect = new Rectangle(1850, 350, 50, 300);

	}

}
