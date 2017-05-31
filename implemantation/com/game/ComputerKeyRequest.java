package com.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ComputerKeyRequest implements KeyListener, KeyRequest
{

	public static final ComputerKeyRequest	INSTANCE		= new ComputerKeyRequest();
	public static final int						KEYSLENGHT	= 524;

	public static ComputerKeyRequest getInstance()
	{
		return ComputerKeyRequest.INSTANCE;
	}

	private final boolean[] keys = new boolean[ComputerKeyRequest.KEYSLENGHT];

	private ComputerKeyRequest()
	{
	}

	@Override
	public boolean isPressed(final int keyCode)
	{
		if (keyCode < this.keys.length) { return this.keys[keyCode]; }
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
}
