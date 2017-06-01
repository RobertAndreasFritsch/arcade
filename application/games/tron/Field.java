package games.tron;

import java.awt.Color;
import java.awt.Graphics2D;

import com.arcade.utils.Seat;
import com.game.deprecated.Drawable;

public class Field implements Drawable
{

	private final Color[][]	wuermer;

	private final int			floor[][];
	private final int			tileSize;

	private final int			width;		// breite
	private final int			height;		// hoehe

	Field(final int[][] floor2, final int tileSize2, final int width2, final int height2)
	{

		this.floor = floor2;
		this.tileSize = tileSize2;

		this.width = width2;
		this.height = height2;

		this.wuermer = new Color[1024 / this.tileSize][1024 / this.tileSize];
	}

	// ---------------------------------------------------------------------------------------------------------------------------
	// Bild Zentralisiern | wuemer malen mit schweif
	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.WHITE); // f�r den Wei�en ramen
		g.drawRect(0, 0, 1024, 1024); // Position es ramens
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 1023, 1023);
		// Male Boden
		for (int y = 0; y < this.height; y++)
		{
			for (int x = 0; x < this.width; x++)
			{
				if (this.wuermer[x][y] != null)
				{
					g.setColor(this.wuermer[x][y]);
					g.fillRect(x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);
				}
				else
				{
					if (this.floor[x][y] == 0)
					{
						;
					}
					else
					{
						if (this.floor[x][y] == 1)
						{ // spieler
							g.setColor(Seat.P1.getColor());
						}
						else
							if (this.floor[x][y] == 2)
							{
								g.setColor(Seat.P2.getColor());
							}
							else
								if (this.floor[x][y] == 3)
								{
									g.setColor(Seat.P3.getColor());
								}
								else
									if (this.floor[x][y] == 4)
									{
										g.setColor(Seat.P4.getColor());
									}
									else
									{
										g.setColor(Color.DARK_GRAY);
									}
						g.fillRect(x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);
					}
				}
			}
		}
	}

}
