package games.bomberman;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import com.arcade.utils.Seat;
import com.game.Drawable;

public class Field implements Drawable
{
	int[][]	ground	= new int[32][32];
	Image		Grass1, Grass2, Grass3, SolidBlock, Block;

	public Field()
	{
		this.ground = MazeGenerator.generate(32, 32);

		for (int x = 0; x < 32; x++)
		{
			for (int y = 0; y < 32; y++)
			{
				if (Math.random() < 0.15)
				{
					this.ground[x][y] = 2;
				}
			}
		}

		for (int x = 0; x < 32; x++)
		{
			this.ground[x][0] = 2;
			this.ground[x][31] = 2;
			this.ground[0][x] = 2;
			this.ground[31][x] = 2;
		}

		if (Seat.P3.isPlaying())
		{
			this.ground[16][24] = 0;
			this.ground[16][25] = 0;
			this.ground[17][24] = 0;
		}
		if (Seat.P4.isPlaying())
		{
			this.ground[8][16] = 0;
			this.ground[8][17] = 0;
			this.ground[9][16] = 0;
		}
		if (Seat.P1.isPlaying())
		{
			this.ground[16][8] = 0;
			this.ground[16][9] = 0;
			this.ground[17][8] = 0;
		}
		if (Seat.P2.isPlaying())
		{
			this.ground[24][16] = 0;
			this.ground[25][16] = 0;
			this.ground[24][17] = 0;
		}

		try
		{
			this.Grass1 = Toolkit.getDefaultToolkit().getImage(new File("res/games/bomberman/Floor1.png").toURI().toURL());
			this.Grass2 = Toolkit.getDefaultToolkit().getImage(new File("res/games/bomberman/Floor2.png").toURI().toURL());
			this.Grass3 = Toolkit.getDefaultToolkit().getImage(new File("res/games/bomberman/Floor3.png").toURI().toURL());
			this.Block = Toolkit.getDefaultToolkit()
			      .getImage(new File("res/games/bomberman/BrokenWall.png").toURI().toURL());
			this.SolidBlock = Toolkit.getDefaultToolkit()
			      .getImage(new File("res/games/bomberman/Wall.png").toURI().toURL());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void draw(final Graphics2D g)
	{
		for (int x = 0; x < this.ground.length; x++)
		{
			for (int y = 0; y < this.ground[x].length; y++)
			{
				switch (this.ground[x][y])
				{
				case 0:
					if (x % 3 == 0)
					{
						g.drawImage(this.Grass1, x * 32, y * 32, 32, 32, null);
					}
					else
						if (x % 2 == 0)
						{
							g.drawImage(this.Grass2, x * 32, y * 32, 32, 32, null);
						}
						else
						{
							g.drawImage(this.Grass3, x * 32, y * 32, 32, 32, null);
						}
					break;
				case 1:
					g.drawImage(this.Block, x * 32, y * 32, 32, 32, null);
					break;
				case 2:
					g.drawImage(this.SolidBlock, x * 32, y * 32, 32, 32, null);
					break;
				}
			}
		}
	}

	public void generate(final int[][] field, final int x, final int y)
	{
	}

}
