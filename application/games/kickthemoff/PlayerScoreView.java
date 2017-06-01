package games.kickthemoff;

import java.awt.Color;
import java.awt.Graphics2D;

import com.game.deprecated.Drawable;

public class PlayerScoreView implements Drawable
{
	private static final int	POINT_WIDTH	= 25;

	private final int				x, y, rotation, max;
	private final Player			player;

	public PlayerScoreView(final int max, final int x, final int y, final int rotation, final Player player)
	{
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.player = player;
		this.max = max;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		final Graphics2D g2 = g;

		g2.translate(this.x, this.y);
		g2.rotate(Math.toRadians(this.rotation));

		final int topY = PlayerScoreView.POINT_WIDTH << 1;

		g.setColor(Color.GREEN);
		for (int xc = -(this.max * PlayerScoreView.POINT_WIDTH >> 1), c = 0; xc < this.max
		      * PlayerScoreView.POINT_WIDTH >> 1; xc += PlayerScoreView.POINT_WIDTH, c++)
		{
			if (c >= this.player.getWins())
			{
				g.setColor(Color.RED);
			}
			g.fillOval(xc, topY, PlayerScoreView.POINT_WIDTH, PlayerScoreView.POINT_WIDTH);
		}

		g2.rotate(-Math.toRadians(this.rotation));
		g2.translate(-this.x, -this.y);
	}

}
