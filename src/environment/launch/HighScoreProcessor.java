package environment.launch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Arrays;
import java.util.TreeMap;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.Seat;

class Bg implements Drawable {

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1024, 1024);
	}

}

public class HighScoreProcessor extends MyGame {

	public static TreeMap<String, Integer> sortByValue(TreeMap<String, Integer> map) {
		TreeMap<String, Integer> result = new TreeMap<String, Integer>();

		String[] l = map.keySet().toArray(new String[] {});
		Arrays.sort(l, (s1, s2) -> map.get(s1) > map.get(s2) ? 1 : -1);
		for (String s : l) {
			result.put(s, map.get(s));
		}

		return result;
	}

	public HighScoreProcessor(JPanel PANEL, KeyRequest KEYS, String... gameName) {
		super(PANEL, KEYS);

		TreeMap<String, Integer> map = new TreeMap<String, Integer>();

		if (Seat.P1.isPlaying()) {
			map.put(Seat.P1.getName(), Seat.P1.getScore());
		}
		if (Seat.P2.isPlaying()) {
			map.put(Seat.P2.getName(), Seat.P2.getScore());
		}
		if (Seat.P3.isPlaying()) {
			map.put(Seat.P3.getName(), Seat.P3.getScore());
		}
		if (Seat.P4.isPlaying()) {
			map.put(Seat.P4.getName(), Seat.P4.getScore());
		}

		XMLGameScore g = new XMLGameScore(new File("res/scores/" + gameName[0] + ".xml"));
		map = sortByValue(map);
		String[][] table = new String[2][map.keySet().size()];

		int counter = 0;
		for (String s : map.keySet()) {
			table[0][counter] = s;
			table[1][counter] = Integer.toString(map.get(s));
			counter++;
			g.addEntry(s, map.get(s));
		}
		g.save();

		add(new Bg());
		add(new Table("letzte Runde", table, new Font("Courier", Font.BOLD, 20), 100, 100));
		add(new Table("Alle Runden", g.getAsTable(), new Font("Courier", Font.BOLD, 20), 100, 200));

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setRunning(false);
			}
		}.start();

		Seat.P1.setScore(0);
		Seat.P2.setScore(0);
		Seat.P3.setScore(0);
		Seat.P4.setScore(0);
	}

	@Override
	public Game getNextGame() {
		return new Menu(PANEL, KEYS);
	}

}

class Table implements Drawable {
	private String[][] values;
	private Font f;
	private int x, y;
	private String title;

	public Table(String title, String[][] values, Font f, int x, int y) {
		this.values = values;
		this.f = f;
		this.x = x;
		this.y = y;
		this.title = title;
	}

	@Override
	public void draw(Graphics2D g) {
		int xCursor = x, yCursor = y;
		g.setColor(Color.WHITE);
		g.setFont(new Font(f.getName(), Font.BOLD, f.getSize() + 2));

		g.drawString(title, xCursor, yCursor);
		yCursor += g.getFont().getSize() + 3;

		g.setFont(f);
		for (int x = 0; x < values.length; x++) {
			String longest = "";
			for (int y = 0; y < values[x].length; y++) {
				if (g.getFontMetrics().stringWidth(values[x][y] + " ") > g.getFontMetrics().stringWidth(longest)) {
					longest = values[x][y] + " ";
				}
			}

			for (int y = 0; y < values[x].length; y++) {
				g.drawString(values[x][y], xCursor, yCursor);
				yCursor += g.getFont().getSize();

			}

			if (x != values.length - 1) {
				g.drawLine(xCursor + g.getFontMetrics().stringWidth(longest), this.y + 5,
						xCursor + g.getFontMetrics().stringWidth(longest), yCursor - g.getFont().getSize());
			}
			xCursor += g.getFontMetrics().stringWidth(longest + " ");
			yCursor = this.y + f.getSize() + 5;
		}
	}

}
