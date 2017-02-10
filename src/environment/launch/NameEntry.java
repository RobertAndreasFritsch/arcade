package environment.launch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.Game;
import environment.model.KeyRequest;
import environment.model.gameobject.Drawable;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Seat;
import environment.model.gameobject.Updateable;

class Background implements Drawable {
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		g.translate(0, 0);
	}
}

abstract class GUIElement {
	private int x, y;
	private boolean isFocused = false;
	private ArrayList<OnClickListener> l;

	public GUIElement(int x, int y) {
		this.x = x;
		this.y = y;
		l = new ArrayList<OnClickListener>();
	}

	public void additionalDraw(Graphics g) {

	}

	public void addOnClickListener(OnClickListener l) {
		this.l.add(l);
	}

	abstract void basicDraw(Graphics2D g);

	public void clicked() {
		for (OnClickListener x : l) {
			x.clicked();
		}
	}

	public void doneFocus() {
		unfocus();
		for (OnClickListener x : l) {
			x.doneFocusing();
			;
		}
	}

	public final void draw(Graphics g) {
		basicDraw((Graphics2D) g);
		additionalDraw(g);
	}

	public void focus() {
		isFocused = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isFocused() {
		return isFocused;
	}

	public void newFocus() {
		focus();
		for (OnClickListener x : l) {
			x.nowFocused();
		}
	}

	public void otherKeyPress(KeyEvent arg0) {
		for (OnClickListener x : l) {
			x.otherKeyPress(arg0);
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void unfocus() {
		isFocused = false;
	}
}

class GUITextInput extends GUIElement {
	protected char[][] chars;
	protected char[] current;
	protected int cursorPos = 0;
	protected Color bg, txt;
	protected boolean[] useUpperCase;
	protected Font f;
	protected int height, width;

	public GUITextInput(int x, int y, char[][] chars, Color bg, Color txt, Font f) {
		super(x + f.getSize(), y);
		this.bg = bg;
		this.txt = txt;
		this.f = f;
		this.chars = chars;
		current = new char[chars.length];
		useUpperCase = new boolean[chars.length];
		for (int h = 0; h < chars.length; h++) {
			current[h] = chars[h][0];
		}
		this.width = f.getSize() * chars.length + (f.getSize() * 2);
		this.height = f.getSize() * 2;
	}

	@Override
	void basicDraw(Graphics2D g) {
		g.setColor(bg);
		g.fillRect(getX() - f.getSize(), getY() - (int) (f.getSize() * 1.5),
				f.getSize() * chars.length + (f.getSize() * 2), f.getSize() * 2);
		g.setFont(f);
		width = f.getSize() * chars.length + (f.getSize() * 2);
		height = f.getSize() * 2;
		for (int x = 0; x < current.length; x++) {
			g.setFont(f);
			g.setColor(txt);
			if (x == cursorPos) {
				g.setColor(new Color(255 - txt.getRed(), 255 - txt.getGreen(), 255 - txt.getBlue()));
			}
			if (useUpperCase[x]) {
				g.drawString(Character.toUpperCase(current[x]) + "", getX() + (f.getSize() * x), getY());
			} else {
				g.drawString(current[x] + "", getX() + (f.getSize() * x), getY());
			}
		}
		g.setColor(txt);
		if (isFocused()) {
			g.drawRect(getX() - f.getSize(), getY() - (int) (f.getSize() * 1.5),
					f.getSize() * chars.length + (f.getSize() * 2), f.getSize() * 2);
		}
	}

	public void down() {
		char m = current[cursorPos];
		int index = indexOf(chars[cursorPos], m);
		if (index == chars[cursorPos].length - 1) {
			index = 0;
		} else {
			index++;
		}
		current[cursorPos] = chars[cursorPos][index];
	}

	public String getCurrent() {
		String res = "";
		for (int x = 0; x < current.length; x++) {
			if (useUpperCase[x]) {
				res += Character.toUpperCase(current[x]);
			} else {
				res += current[x];
			}
		}
		return res;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	private int indexOf(char[] chars, char chr) {
		int index = 0;
		for (int x = 0; x < chars.length; x++) {
			if (chars[x] == chr) {
				index = x;
			}
		}
		return index;
	}

	/**
	 * 
	 */
	public void left() {
		if (this.cursorPos == 0) {
			this.cursorPos = this.chars.length - 1;
		} else {
			this.cursorPos--;
		}
	}

	public void right() {
		if (cursorPos == chars.length - 1) {
			cursorPos = 0;
		} else {
			cursorPos++;
		}
	}

	public void setCurrent(String s) {
		for (int x = 0; x < s.length(); x++) {
			current[x] = s.charAt(x);
		}
	}

	public void up() {
		char m = current[cursorPos];
		int index = indexOf(chars[cursorPos], m);
		if (index == 0) {
			index = chars[cursorPos].length - 1;
		} else {
			index--;
		}
		current[cursorPos] = chars[cursorPos][index];
	}

}

class GUITextInputAdapter extends GUITextInput implements Drawable, ProceedsInput {
	private KeyRequest Keys;
	private Seat s;
	private boolean canPress = true;
	private int rot;
	public boolean isReady = false;

	public GUITextInputAdapter(KeyRequest Keys, Seat s, int x, int y, char[][] chars, Color bg, Color txt, Font f,
			int rot) {
		super(x, y, chars, bg, txt, f);
		this.Keys = Keys;
		this.s = s;
		this.rot = rot;
		super.setX(super.getX() - super.getWidth() / 2);
		setCurrent(s.getName() != null ? s.getName() : "aaa");
	}

	@Override
	public void draw(Graphics2D g) {
		g.rotate(Math.toRadians(rot));
		super.draw(g);

		if (isReady) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(super.getX() - super.f.getSize(), super.getY() + 20, 100, 100);
		g.rotate(Math.toRadians(-rot));
	}

	private void justPressed() {
		canPress = false;
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				canPress = true;
			}
		}.start();
	}

	@Override
	public void processInput() {
		if (isReady) {
			return;
		}
		if (Keys.isPressed(s.UP) & canPress) {
			super.up();
			justPressed();
		}
		if (Keys.isPressed(s.DOWN) & canPress) {
			super.down();
			justPressed();
		}
		if (Keys.isPressed(s.LEFT) & canPress) {
			super.left();
			justPressed();
		}
		if (Keys.isPressed(s.RIGHT) & canPress) {
			super.right();
			justPressed();
		}
		if (Keys.isPressed(s.BTN1)) {
			isReady = true;
		}
	}
}

public class NameEntry extends MyGame {
	private Presentation presentation;
	GUITextInputAdapter[] a = new GUITextInputAdapter[4];

	public NameEntry(JPanel PANEL, KeyRequest KEYS, boolean[] players, Presentation presentation) {
		super(PANEL, KEYS);

		this.presentation = presentation;
		add(new Background());

		char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		if (players[0]) {
			a[0] = new GUITextInputAdapter(KEYS, Seat.P3, 1024 / 2, 1024 - 1024 / 3,
					new char[][] { letters, letters, letters }, Color.gray, Color.WHITE,
					new Font("Courier", Font.PLAIN, 20), 0);
			add(a[0]);
		}
		if (players[1]) {
			a[1] = new GUITextInputAdapter(KEYS, Seat.P4, 1024 / 2, -1024 / 3,
					new char[][] { letters, letters, letters }, Color.gray, Color.WHITE,
					new Font("Courier", Font.PLAIN, 20), 90);
			add(a[1]);
		}
		if (players[2]) {
			a[2] = new GUITextInputAdapter(KEYS, Seat.P1, -1024 / 2, -1024 / 3,
					new char[][] { letters, letters, letters }, Color.gray, Color.WHITE,
					new Font("Courier", Font.PLAIN, 20), 180);
			add(a[2]);
		}
		if (players[3]) {
			a[3] = new GUITextInputAdapter(KEYS, Seat.P2, -1024 / 2, 1024 / 3 * 2,
					new char[][] { letters, letters, letters }, Color.gray, Color.WHITE,
					new Font("Courier", Font.PLAIN, 20), 270);
			add(a[3]);
		}
		add(new waiter(a, players, this));
	}

	@Override
	public Game getNextGame() {
		return presentation.getGame(PANEL, KEYS);
	}
}

interface OnClickListener {

	public abstract void clicked();

	public abstract void doneFocusing();

	public abstract void nowFocused();

	public abstract void otherKeyPress(KeyEvent arg0);
}

class waiter implements Updateable {
	private GUITextInputAdapter[] a;
	private boolean[] players;
	private NameEntry e;

	public waiter(GUITextInputAdapter[] a, boolean[] players, NameEntry e) {
		this.players = players;
		this.a = a;
		this.e = e;
	}

	@Override
	public void update(long elapsed) {
		boolean ready = true;

		if (players[0] & !(a[0] != null ? a[0].isReady : true) | players[1] & !(a[1] != null ? a[1].isReady : true)
				| players[2] & !(a[2] != null ? a[2].isReady : true)
				| players[3] & !(a[3] != null ? a[3].isReady : true)) {
			ready = false;
		}
		if (ready) {
			e.setRunning(false);
			Seat.P3.setPlaying(players[0]);
			Seat.P3.setName(a[0] != null ? a[0].getCurrent() : "");
			Seat.P4.setPlaying(players[1]);
			Seat.P4.setName(a[1] != null ? a[1].getCurrent() : "");
			Seat.P1.setPlaying(players[2]);
			Seat.P1.setName(a[2] != null ? a[2].getCurrent() : "");
			Seat.P2.setPlaying(players[3]);
			Seat.P3.setName(a[3] != null ? a[3].getCurrent() : "");
		}
	}
}