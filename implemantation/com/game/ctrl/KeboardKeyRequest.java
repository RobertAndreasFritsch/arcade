package com.game.ctrl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeboardKeyRequest implements KeyListener, KeyCtrl
{
	public static final int	KEYSLENGHT	= 524;

	private final boolean[]	keys			= new boolean[KeboardKeyRequest.KEYSLENGHT];
	private final boolean[]	frame			= new boolean[KeboardKeyRequest.KEYSLENGHT];

	KeboardKeyRequest()
	{
	}

	@Override
	public boolean isPressed(final int keyCode)
	{
		if (keyCode < this.frame.length) return this.frame[keyCode];
		return false;
	}

	@Override
	public void keyPressed(final KeyEvent e)
	{
		if (e.getKeyCode() < this.keys.length)
		{
			this.keys[e.getKeyCode()] = true;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e)
	{
		if (e.getKeyCode() < this.keys.length)
		{
			this.keys[e.getKeyCode()] = false;
		}
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
