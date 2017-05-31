package games.bomberman;

import java.util.ArrayList;

public class MazeGenerator
{

	static class Point
	{
		Integer	r;
		Integer	c;
		Point		parent;

		public Point(final int x, final int y, final Point p)
		{
			this.r = x;
			this.c = y;
			this.parent = p;
		}

		// compute opposite node given that it is in the other direction from
		// the parent
		public Point opposite()
		{
			if (this.r.compareTo(
			      this.parent.r) != 0) { return new Point(this.r + this.r.compareTo(this.parent.r), this.c, this); }
			if (this.c.compareTo(
			      this.parent.c) != 0) { return new Point(this.r, this.c + this.c.compareTo(this.parent.c), this); }
			return null;
		}
	}

	public static int[][] generate(final int w, final int h)
	{
		// dimensions of generated maze
		final int r = w, c = h;

		// build maze and initialize with only walls
		final StringBuilder s = new StringBuilder(c);
		for (int x = 0; x < c; x++)
		{
			s.append('*');
		}
		final char[][] maz = new char[r][c];
		for (int x = 0; x < r; x++)
		{
			maz[x] = s.toString().toCharArray();
		}

		// select random point and open as start node
		final Point st = new Point((int) (Math.random() * r), (int) (Math.random() * c), null);
		maz[st.r][st.c] = 'S';

		// iterate through direct neighbors of node
		final ArrayList<Point> frontier = new ArrayList<>();
		for (int x = -1; x <= 1; x++)
		{
			for (int y = -1; y <= 1; y++)
			{
				if (x == 0 && y == 0 || x != 0 && y != 0)
				{
					continue;
				}
				try
				{
					if (maz[st.r + x][st.c + y] == '.')
					{
						continue;
					}
				}
				catch (final Exception e)
				{ // ignore ArrayIndexOutOfBounds
					continue;
				}
				// add eligible points to frontier
				frontier.add(new Point(st.r + x, st.c + y, st));
			}
		}

		Point last = null;
		while (!frontier.isEmpty())
		{

			// pick current node at random
			final Point cu = frontier.remove((int) (Math.random() * frontier.size()));
			final Point op = cu.opposite();
			try
			{
				// if both node and its opposite are walls
				if (maz[cu.r][cu.c] == '*')
				{
					if (maz[op.r][op.c] == '*')
					{

						// open path between the nodes
						maz[cu.r][cu.c] = '.';
						maz[op.r][op.c] = '.';

						// store last node in order to mark it later
						last = op;

						// iterate through direct neighbors of node, same as
						// earlier
						for (int x = -1; x <= 1; x++)
						{
							for (int y = -1; y <= 1; y++)
							{
								if (x == 0 && y == 0 || x != 0 && y != 0)
								{
									continue;
								}
								try
								{
									if (maz[op.r + x][op.c + y] == '.')
									{
										continue;
									}
								}
								catch (final Exception e)
								{
									continue;
								}
								frontier.add(new Point(op.r + x, op.c + y, op));
							}
						}
					}
				}
			}
			catch (final Exception e)
			{ // ignore NullPointer and
			  // ArrayIndexOutOfBounds
			}

			// if algorithm has resolved, mark end node
			if (frontier.isEmpty())
			{
				maz[last.r][last.c] = 'E';
			}
		}

		// print final maze
		final int[][] res = new int[w][h];
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				switch (maz[i][j])
				{
				case '.':
					res[i][j] = 0;
					break;
				case '*':
					res[i][j] = 1;
				}
			}
		}
		return res;
	}

	public static void main(final String[] args)
	{
		final int[][] m = MazeGenerator.generate(32, 32);

		for (int x = 0; x < 32; x++)
		{
			for (int y = 0; y < 32; y++)
			{
				System.out.print(m[x][y]);
			}
			System.out.println();
		}
	}

}
