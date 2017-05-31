package games.pong.Tiles;

import java.awt.Rectangle;

import com.arcade.utils.Seat;
import com.game.ctrl.KeyCtrl;

import games.pong.PongGameObjekt_Blocker;

public class North extends Tile
{
	PongGameObjekt_Blocker[] blockers;

	public North(final Seat player, final KeyCtrl KEYS, final PongGameObjekt_Blocker[] blockers)
	{
		super(player, KEYS);
		this.setheight(50);
		this.setwidth(300);
		this.init();
		this.blockers = blockers;
	}

	@Override
	public void actionLeft()
	{
		if (this.rect.x + 300 <= 1048 && this.getTor() != 0 && this.rect.x + 300 <= this.blockers[1].getX())
		{
			this.rect.x += Tile.SPEED;
		}
	}

	@Override
	public void actionRight()
	{
		if (this.rect.x > 100 && this.getTor() != 0 && this.rect.x >= this.blockers[0].getX() + 100)
		{
			this.rect.x -= Tile.SPEED;
		}
	}

	@Override
	protected void init()
	{
		final int x = (int) (1048 * .5 - this.getwidth() * .5), y = 0;

		this.rect = new Rectangle(x, y, this.getwidth(), this.getheight());
		// rect = new Rectangle(775, 15, 300, 50);
	}

}
