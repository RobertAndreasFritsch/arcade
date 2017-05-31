package com.game.ctrl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MicrocontrollerKeyRequest implements KeyListener, KeyCtrl
{
	public static final int	KEYSLENGHT	= 524;

	private boolean[]			keys			= new boolean[MicrocontrollerKeyRequest.KEYSLENGHT];
	private boolean[]			keysBuffer	= new boolean[MicrocontrollerKeyRequest.KEYSLENGHT];
	private boolean[]			frame			= new boolean[MicrocontrollerKeyRequest.KEYSLENGHT];

	@Override
	public boolean isPressed(final int keyCode)
	{
		if (keyCode < this.frame.length) return this.frame[keyCode];
		return false;
	}

	@Override
	public void keyPressed(final KeyEvent e)
	{
		final int key = e.getKeyCode();

		if (e.getKeyCode() == KeyEvent.VK_COMMA)
		{

			final boolean[] tempKeys = this.keys;
			this.keys = this.keysBuffer;
			this.keysBuffer = tempKeys;

			for (int i = 0; i < this.keys.length; i++)
			{
				this.keysBuffer[i] = false;
			}
		}

		else
			if (key < this.keys.length)
			{
				this.keysBuffer[key] = true;
			}
	}

	@Override
	public void keyReleased(final KeyEvent e)
	{
	}

	@Override
	public void keyTyped(final KeyEvent e)
	{
	}

	@Override
	public void takeFrame()
	{
		for (int i = 0; i < frame.length; i++)
			frame[i] = keys[i];
	}
}
