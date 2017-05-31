package com.arcade;

import java.awt.event.KeyListener;

import com.game.MyGameLoop;
import com.game.MyWindow;
import com.game.Window;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.KeyRequest;
import com.game.ctrl.KeyRequestType;

public class Launch
{
	public static final KeyRequestType	KEY_REQUEST_TYPE	= KeyRequestType.Keyboard;
	public static final CtrlFactory		CTRL_FACTORY		= CtrlFactory.newCtrlFactory(KEY_REQUEST_TYPE);

	public static void main(final String[] args)
	{

		final Window window = MyWindow.getInstance();

		final KeyRequest keys = CTRL_FACTORY.keyRequestInstance();

		window.addKeyListener((KeyListener) keys);

		new MyGameLoop(new Menu(window.getJPanel(), keys));
	}
}
