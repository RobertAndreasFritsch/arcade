package com.deprecated;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.arcade.presentation.Presentation;
import com.arcade.utils.Seat;
import com.game.Drawable;
import com.game.Game;
import com.game.MyGame;
import com.game.ProceedsInput;
import com.game.Updateable;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.KeyCtrl;

/**
 * @deprecated not used anymore
 */
@Deprecated
class Background implements Drawable
{
	@Override
	public void draw(final Graphics2D g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1048, 1048);
		g.translate(0, 0);
	}
}

/**
 * @deprecated not used anymore
 */
@Deprecated
abstract class GUIElement
{
	private int											x, y;
	private boolean									isFocused	= false;
	private final ArrayList<OnClickListener>	l;

	/**
	 * @param x
	 * @param y
	 * @deprecated not used anymore
	 */
	@Deprecated
	public GUIElement(final int x, final int y)
	{
		this.x = x;
		this.y = y;
		this.l = new ArrayList<>();
	}

	/**
	 * @param g
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void additionalDraw(final Graphics g)
	{

	}

	/**
	 * @param l
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void addOnClickListener(final OnClickListener l)
	{
		this.l.add(l);
	}

	/**
	 * @param g
	 * @deprecated not used anymore
	 */
	@Deprecated
	abstract void basicDraw(Graphics2D g);

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void clicked()
	{
		for (final OnClickListener x : this.l)
		{
			x.clicked();
		}
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void doneFocus()
	{
		this.unfocus();
		for (final OnClickListener x : this.l)
		{
			x.doneFocusing();
			;
		}
	}

	/**
	 * @param g
	 * @deprecated not used anymore
	 */
	@Deprecated
	public final void draw(final Graphics g)
	{
		this.basicDraw((Graphics2D) g);
		this.additionalDraw(g);
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void focus()
	{
		this.isFocused = true;
	}

	/**
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	public int getX()
	{
		return this.x;
	}

	/**
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	public int getY()
	{
		return this.y;
	}

	/**
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	public boolean isFocused()
	{
		return this.isFocused;
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void newFocus()
	{
		this.focus();
		for (final OnClickListener x : this.l)
		{
			x.nowFocused();
		}
	}

	/**
	 * @param arg0
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void otherKeyPress(final KeyEvent arg0)
	{
		for (final OnClickListener x : this.l)
		{
			x.otherKeyPress(arg0);
		}
	}

	/**
	 * @param x
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void setX(final int x)
	{
		this.x = x;
	}

	/**
	 * @param y
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void setY(final int y)
	{
		this.y = y;
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void unfocus()
	{
		this.isFocused = false;
	}
}

/**
 * @deprecated not used anymore
 */
@Deprecated
class GUITextInput extends GUIElement
{
	protected char[][]	chars;
	protected char[]		current;
	protected int			cursorPos	= 0;
	protected Color		bg, txt;
	protected boolean[]	useUpperCase;
	protected Font			f;
	protected int			height, width;

	/**
	 * @param x
	 * @param y
	 * @param chars
	 * @param bg
	 * @param txt
	 * @param f
	 * @deprecated not used anymore
	 */
	@Deprecated
	public GUITextInput(final int x, final int y, final char[][] chars, final Color bg, final Color txt, final Font f)
	{
		super(x + f.getSize(), y);
		this.bg = bg;
		this.txt = txt;
		this.f = f;
		this.chars = chars;
		this.current = new char[chars.length];
		this.useUpperCase = new boolean[chars.length];
		for (int h = 0; h < chars.length; h++)
		{
			this.current[h] = chars[h][0];
		}
		this.width = f.getSize() * chars.length + f.getSize() * 2;
		this.height = f.getSize() * 2;
	}

	@Override
	void basicDraw(final Graphics2D g)
	{
		g.setColor(this.bg);
		g.fillRect(this.getX() - this.f.getSize(), this.getY() - (int) (this.f.getSize() * 1.5),
		      this.f.getSize() * this.chars.length + this.f.getSize() * 2, this.f.getSize() * 2);
		g.setFont(this.f);
		this.width = this.f.getSize() * this.chars.length + this.f.getSize() * 2;
		this.height = this.f.getSize() * 2;
		for (int x = 0; x < this.current.length; x++)
		{
			g.setFont(this.f);
			g.setColor(this.txt);
			if (x == this.cursorPos)
			{
				g.setColor(new Color(255 - this.txt.getRed(), 255 - this.txt.getGreen(), 255 - this.txt.getBlue()));
			}
			if (this.useUpperCase[x])
			{
				g.drawString(Character.toUpperCase(this.current[x]) + "", this.getX() + this.f.getSize() * x, this.getY());
			}
			else
			{
				g.drawString(this.current[x] + "", this.getX() + this.f.getSize() * x, this.getY());
			}
		}
		g.setColor(this.txt);
		if (this.isFocused())
		{
			g.drawRect(this.getX() - this.f.getSize(), this.getY() - (int) (this.f.getSize() * 1.5),
			      this.f.getSize() * this.chars.length + this.f.getSize() * 2, this.f.getSize() * 2);
		}
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void down()
	{
		final char m = this.current[this.cursorPos];
		int index = this.indexOf(this.chars[this.cursorPos], m);
		if (index == this.chars[this.cursorPos].length - 1)
		{
			index = 0;
		}
		else
		{
			index++;
		}
		this.current[this.cursorPos] = this.chars[this.cursorPos][index];
	}

	/**
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	public String getCurrent()
	{
		String res = "";
		for (int x = 0; x < this.current.length; x++)
		{
			if (this.useUpperCase[x])
			{
				res += Character.toUpperCase(this.current[x]);
			}
			else
			{
				res += this.current[x];
			}
		}
		return res;
	}

	/**
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * @param chars
	 * @param chr
	 * @return
	 * @deprecated not used anymore
	 */
	@Deprecated
	private int indexOf(final char[] chars, final char chr)
	{
		int index = 0;
		for (int x = 0; x < chars.length; x++)
		{
			if (chars[x] == chr)
			{
				index = x;
			}
		}
		return index;
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void left()
	{
		if (this.cursorPos == 0)
		{
			this.cursorPos = this.chars.length - 1;
		}
		else
		{
			this.cursorPos--;
		}
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void right()
	{
		if (this.cursorPos == this.chars.length - 1)
		{
			this.cursorPos = 0;
		}
		else
		{
			this.cursorPos++;
		}
	}

	/**
	 * @param s
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void setCurrent(final String s)
	{
		for (int x = 0; x < s.length(); x++)
		{
			this.current[x] = s.charAt(x);
		}
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public void up()
	{
		final char m = this.current[this.cursorPos];
		int index = this.indexOf(this.chars[this.cursorPos], m);
		if (index == 0)
		{
			index = this.chars[this.cursorPos].length - 1;
		}
		else
		{
			index--;
		}
		this.current[this.cursorPos] = this.chars[this.cursorPos][index];
	}

}

/**
 * @deprecated not used anymore
 */
@Deprecated
class GUITextInputAdapter extends GUITextInput implements Drawable, ProceedsInput
{
	private final KeyCtrl	Keys;
	private final Seat			s;
	private boolean				canPress	= true;
	private final int				rot;
	public boolean					isReady	= false;

	/**
	 * @param Keys
	 * @param s
	 * @param x
	 * @param y
	 * @param chars
	 * @param bg
	 * @param txt
	 * @param f
	 * @param rot
	 * @deprecated not used anymore
	 */
	@Deprecated
	public GUITextInputAdapter(final KeyCtrl Keys, final Seat s, final int x, final int y, final char[][] chars,
	      final Color bg, final Color txt, final Font f, final int rot)
	{
		super(x, y, chars, bg, txt, f);
		this.Keys = Keys;
		this.s = s;
		this.rot = rot;
		super.setX(super.getX() - super.getWidth() / 2);
		this.setCurrent(s.getName() != null ? s.getName() : "aaa");
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.rotate(Math.toRadians(this.rot));
		super.draw(g);

		if (this.isReady)
		{
			g.setColor(Color.GREEN);
		}
		else
		{
			g.setColor(Color.RED);
		}
		g.fillRect(super.getX() - super.f.getSize(), super.getY() + 20, 100, 100);
		g.rotate(Math.toRadians(-this.rot));
	}

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	private void justPressed()
	{
		this.canPress = false;
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(100);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
				GUITextInputAdapter.this.canPress = true;
			}
		}.start();
	}

	@Override
	public void processInput()
	{
		if (this.isReady) { return; }
		if (this.Keys.isPressed(this.s.UP) & this.canPress)
		{
			super.up();
			this.justPressed();
		}
		if (this.Keys.isPressed(this.s.DOWN) & this.canPress)
		{
			super.down();
			this.justPressed();
		}
		if (this.Keys.isPressed(this.s.LEFT) & this.canPress)
		{
			super.left();
			this.justPressed();
		}
		if (this.Keys.isPressed(this.s.RIGHT) & this.canPress)
		{
			super.right();
			this.justPressed();
		}
		if (this.Keys.isPressed(this.s.BTN1))
		{
			this.isReady = true;
		}
	}
}

/**
 * @deprecated not used anymore
 */
@Deprecated
public class NameEntry extends MyGame
{
	/**
	 *
	 */
	private static final long	serialVersionUID	= -5382653777454931952L;
	private final Presentation	presentation;
	GUITextInputAdapter[]		a						= new GUITextInputAdapter[4];

	public NameEntry(final CtrlFactory ctrlFactory, final boolean[] players, final Presentation presentation)
	{
		super(ctrlFactory);

		this.presentation = presentation;
		this.add(new Background());

		final char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		      's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		if (players[0])
		{
			this.a[0] = new GUITextInputAdapter(this.getKEYS(), Seat.P3, 1024 / 2, 1024 - 1024 / 3,
			      new char[][] { letters, letters, letters }, Color.gray, Color.WHITE, new Font("Courier", Font.PLAIN, 20),
			      0);
			this.add(this.a[0]);
		}
		if (players[1])
		{
			this.a[1] = new GUITextInputAdapter(this.getKEYS(), Seat.P4, 1024 / 2, -1024 / 3,
			      new char[][] { letters, letters, letters }, Color.gray, Color.WHITE, new Font("Courier", Font.PLAIN, 20),
			      90);
			this.add(this.a[1]);
		}
		if (players[2])
		{
			this.a[2] = new GUITextInputAdapter(this.getKEYS(), Seat.P1, -1024 / 2, -1024 / 3,
			      new char[][] { letters, letters, letters }, Color.gray, Color.WHITE, new Font("Courier", Font.PLAIN, 20),
			      180);
			this.add(this.a[2]);
		}
		if (players[3])
		{
			this.a[3] = new GUITextInputAdapter(this.getKEYS(), Seat.P2, -1024 / 2, 1024 / 3 * 2,
			      new char[][] { letters, letters, letters }, Color.gray, Color.WHITE, new Font("Courier", Font.PLAIN, 20),
			      270);
			this.add(this.a[3]);
		}
		this.add(new waiter(this.a, players, this));
	}

	@Override
	public Game getNextGame()
	{
		return this.presentation.getGame(this.getCtrlFactory());
	}
}

/**
 * @deprecated not used anymore
 */
@Deprecated
interface OnClickListener
{

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public abstract void clicked();

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public abstract void doneFocusing();

	/**
	 * @deprecated not used anymore
	 */
	@Deprecated
	public abstract void nowFocused();

	/**
	 * @param arg0
	 * @deprecated not used anymore
	 */
	@Deprecated
	public abstract void otherKeyPress(KeyEvent arg0);
}

/**
 * @deprecated not used anymore
 */
@Deprecated
class waiter implements Updateable
{
	private final GUITextInputAdapter[]	a;
	private final boolean[]					players;
	private final NameEntry					e;

	/**
	 * @param a
	 * @param players
	 * @param e
	 * @deprecated not used anymore
	 */
	@Deprecated
	public waiter(final GUITextInputAdapter[] a, final boolean[] players, final NameEntry e)
	{
		this.players = players;
		this.a = a;
		this.e = e;
	}

	@Override
	public void update(final long elapsed)
	{
		boolean ready = true;

		if (this.players[0] & !(this.a[0] != null ? this.a[0].isReady : true)
		      | this.players[1] & !(this.a[1] != null ? this.a[1].isReady : true)
		      | this.players[2] & !(this.a[2] != null ? this.a[2].isReady : true)
		      | this.players[3] & !(this.a[3] != null ? this.a[3].isReady : true))
		{
			ready = false;
		}

		if (ready)
		{
			new Thread()
			{
				@Override
				public void run()
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (final InterruptedException e1)
					{
						e1.printStackTrace();
					}

					waiter.this.e.setRunning(false);
					Seat.P3.setPlaying(waiter.this.players[0]);
					Seat.P3.setName(waiter.this.a[0] != null ? waiter.this.a[0].getCurrent() : "");
					Seat.P4.setPlaying(waiter.this.players[1]);
					Seat.P4.setName(waiter.this.a[1] != null ? waiter.this.a[1].getCurrent() : "");
					Seat.P1.setPlaying(waiter.this.players[2]);
					Seat.P1.setName(waiter.this.a[2] != null ? waiter.this.a[2].getCurrent() : "");
					Seat.P2.setPlaying(waiter.this.players[3]);
					Seat.P3.setName(waiter.this.a[3] != null ? waiter.this.a[3].getCurrent() : "");

					Seat.P3_PlayerView.setPlaying(waiter.this.players[0]);
					Seat.P3_PlayerView.setName(waiter.this.a[0] != null ? waiter.this.a[0].getCurrent() : "");
					Seat.P4_PlayerView.setPlaying(waiter.this.players[1]);
					Seat.P4_PlayerView.setName(waiter.this.a[1] != null ? waiter.this.a[1].getCurrent() : "");
					Seat.P1_PlayerView.setPlaying(waiter.this.players[2]);
					Seat.P1_PlayerView.setName(waiter.this.a[2] != null ? waiter.this.a[2].getCurrent() : "");
					Seat.P2_PlayerView.setPlaying(waiter.this.players[3]);
					Seat.P3_PlayerView.setName(waiter.this.a[3] != null ? waiter.this.a[3].getCurrent() : "");
				}
			}.start();
		}
	}
}