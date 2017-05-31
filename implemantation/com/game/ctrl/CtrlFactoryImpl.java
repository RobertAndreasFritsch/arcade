package com.game.ctrl;

import javax.swing.JFrame;

public class CtrlFactoryImpl implements CtrlFactory
{
	private final JFrame	jFrame				= new JFrame();

	KeyCtrl					keyCtrl;
	GraphicsCtrl			graphicsCtrlImpl	= new GraphicsCtrlImpl(this.jFrame);

	CtrlFactoryImpl(final KeyCtrlType keyCtrlType)
	{
		switch (keyCtrlType)
		{
		case Microcontroller:
			this.keyCtrl = new MicrocontrollerKeyCtrlImpl(jFrame);
			break;

		default: // <- Keyboard
			this.keyCtrl = new KeboardKeyCtrlImpl(jFrame);
			break;
		}
	}

	@Override
	public KeyCtrl keyRequestInstance()
	{
		return this.keyCtrl;
	}

	@Override
	public GraphicsCtrl graphicsControllerInstance()
	{
		return this.graphicsCtrlImpl;
	}
}
