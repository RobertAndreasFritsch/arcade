package com.game.ctrl;

import java.awt.Graphics2D;

public class CtrlFactoryImpl implements CtrlFactory
{
	KeyRequest keyRequest;

	CtrlFactoryImpl(final KeyRequestType keyRequestType)
	{
		switch (keyRequestType)
		{
		case Microcontroller:
			this.keyRequest = new MicrocontrollerKeyRequest();
			break;

		default: // <- Keyboard
			this.keyRequest = new KeboardKeyRequest();
			break;
		}
	}

	@Override
	public KeyRequest keyRequestInstance()
	{
		return this.keyRequest;
	}

	@Override
	public Graphics2D getGraphics()
	{
		return null;
	}
}
