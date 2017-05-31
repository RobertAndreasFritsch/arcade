package com.arcade;

import java.awt.event.KeyListener;

import com.game.ComputerKeyRequest;
import com.game.KeyRequest;
import com.game.MicroControllerKeyRequest;
import com.game.MyGameLoop;
import com.game.MyWindow;
import com.game.Window;

public class Launch
{

	public static final boolean debugMode = true;

	public static void main(final String[] args)
	{

		final Window window = MyWindow.getInstance();
		final KeyRequest keys = Launch.debugMode ? ComputerKeyRequest.getInstance()
		      : MicroControllerKeyRequest.getInstance();

		window.addKeyListener((KeyListener) keys);

		new MyGameLoop(new Menu(window.getJPanel(), keys));
	}
}
