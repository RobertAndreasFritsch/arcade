package com.game.ctrl;

import java.awt.Graphics2D;

public interface CtrlFactory
{
	static CtrlFactory newCtrlFactory(final KeyRequestType keyRequestType)
	{
		return new CtrlFactoryImpl(keyRequestType);
	}

	KeyRequest keyRequestInstance();

	Graphics2D getGraphics();
}