package com.arcade;

import com.game.MyGameLoop;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.KeyRequestType;

public class Launch
{
	public static final KeyRequestType	KEY_REQUEST_TYPE	= KeyRequestType.Keyboard;
	public static final CtrlFactory		CTRL_FACTORY		= CtrlFactory.newCtrlFactory(KEY_REQUEST_TYPE);

	public static void main(final String[] args)
	{

		// final Window window = MyWindow.getInstance();

		CTRL_FACTORY.keyRequestInstance();

		// window.addKeyListener((KeyListener) keys);

		new MyGameLoop(new Menu(CTRL_FACTORY));
	}
}
