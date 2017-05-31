package com.game.ctrl;

public interface CtrlFactory
{
	static CtrlFactory newCtrlFactory(final KeyCtrlType keyCtrlType)
	{
		return new CtrlFactoryImpl(keyCtrlType);
	}

	KeyCtrl keyRequestInstance();

	GraphicsCtrlImpl graphicsControllerInstance();

}