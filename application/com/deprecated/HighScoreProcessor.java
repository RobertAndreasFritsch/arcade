package com.deprecated;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Arrays;
import java.util.TreeMap;

import com.arcade.Menu;
import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.Game;
import com.game.MyGame;
import com.game.ctrl.CtrlFactory;

class Bg implements Drawable
{

	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1024, 1024);
	}

}

/**
 * @deprecated not used anymore
 */
@Deprecated
public class HighScoreProcessor extends MyGame
{

	/**
	 *
	 */
	private static final long serialVersionUID = -1939415195551352099L;

	/**
	 * @param map
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	public static TreeMap<String, Integer> sortByValue(final TreeMap<String, Integer> map)
	{
		final TreeMap<String, Integer> result = new TreeMap<>();

		final String[] l = map.keySet().toArray(new String[] {});
		Arrays.sort(l, (s1, s2) -> map.get(s1) > map.get(s2) ? 1 : -1);
		for (final String s : l)
		{
			result.put(s, map.get(s));
		}

		return result;
	}

	/**
	 * @param PANEL
	 * @param KEYS
	 * @param gameName
	 * @deprecated not used anymore
	 */
	@Deprecated
	public HighScoreProcessor(final CtrlFactory ctrlFactory, final String... gameName)
	{
		super(ctrlFactory);

		TreeMap<String, Integer> map = new TreeMap<>();

		if (Seat.P1.isPlaying())
		{
			map.put(Seat.P1.getName(), Seat.P1.getScore());
		}
		if (Seat.P2.isPlaying())
		{
			map.put(Seat.P2.getName(), Seat.P2.getScore());
		}
		if (Seat.P3.isPlaying())
		{
			map.put(Seat.P3.getName(), Seat.P3.getScore());
		}
		if (Seat.P4.isPlaying())
		{
			map.put(Seat.P4.getName(), Seat.P4.getScore());
		}

		final XMLGameScore g = new XMLGameScore(new File("res/scores/" + gameName[0] + ".xml"));
		map = HighScoreProcessor.sortByValue(map);
		final String[][] table = new String[2][map.keySet().size()];

		int counter = 0;
		for (final String s : map.keySet())
		{
			table[0][counter] = s;
			table[1][counter] = Integer.toString(map.get(s));
			counter++;
			g.addEntry(s, map.get(s));
		}
		g.save();

		this.add(new Bg());
		this.add(new Table("letzte Runde", table, new Font("Courier", Font.BOLD, 20), 100, 100));
		this.add(new Table("Alle Runden", g.getAsTable(), new Font("Courier", Font.BOLD, 20), 100, 200));

		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(5000);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
				HighScoreProcessor.this.setRunning(false);
			}
		}.start();

		Seat.P1.setScore(0);
		Seat.P2.setScore(0);
		Seat.P3.setScore(0);
		Seat.P4.setScore(0);
	}

	@Override
	public Game getNextGame()
	{
		return new Menu(this.getCtrlFactory());
	}

}

/**
 * @deprecated not used anymore
 */
@Deprecated
class Table implements Drawable
{
	private final String[][]	values;
	private final Font			f;
	private final int				x, y;
	private final String			title;

	/**
	 * @param title
	 * @param values
	 * @param f
	 * @param x
	 * @param y
	 * @deprecated not used anymore
	 */
	@Deprecated
	public Table(final String title, final String[][] values, final Font f, final int x, final int y)
	{
		this.values = values;
		this.f = f;
		this.x = x;
		this.y = y;
		this.title = title;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		int xCursor = this.x, yCursor = this.y;
		g.setColor(Color.WHITE);
		g.setFont(new Font(this.f.getName(), Font.BOLD, this.f.getSize() + 2));

		g.drawString(this.title, xCursor, yCursor);
		yCursor += g.getFont().getSize() + 3;

		g.setFont(this.f);
		for (int x = 0; x < this.values.length; x++)
		{
			String longest = "";
			for (int y = 0; y < this.values[x].length; y++)
			{
				if (g.getFontMetrics().stringWidth(this.values[x][y] + " ") > g.getFontMetrics().stringWidth(longest))
				{
					longest = this.values[x][y] + " ";
				}
			}

			for (int y = 0; y < this.values[x].length; y++)
			{
				g.drawString(this.values[x][y], xCursor, yCursor);
				yCursor += g.getFont().getSize();

			}

			if (x != this.values.length - 1)
			{
				g.drawLine(xCursor + g.getFontMetrics().stringWidth(longest), this.y + 5,
				      xCursor + g.getFontMetrics().stringWidth(longest), yCursor - g.getFont().getSize());
			}
			xCursor += g.getFontMetrics().stringWidth(longest + " ");
			yCursor = this.y + this.f.getSize() + 5;
		}
	}
}
