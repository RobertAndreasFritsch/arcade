package com.game.ctrl;

import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class CtrlFactoryImpl implements CtrlFactory
{
	private final JFrame	jFrame					= new JFrame();

	KeyCtrl				keyCtrl;
	GraphicsCtrlImpl	graphicsCtrlImpl	= new GraphicsCtrlImpl(this.jFrame);

	CtrlFactoryImpl(final KeyCtrlType keyCtrlType)
	{
		switch (keyCtrlType)
		{
		case Microcontroller:
			this.keyCtrl = new MicrocontrollerKeyRequest();
			break;

		default: // <- Keyboard
			this.keyCtrl = new KeboardKeyRequest();
			break;
		}

		this.jFrame.addKeyListener((KeyListener) this.keyCtrl);
	}

	@Override
	public KeyCtrl keyRequestInstance()
	{
		return this.keyCtrl;
	}

	@Override
	public GraphicsCtrlImpl graphicsControllerInstance()
	{
		return this.graphicsCtrlImpl;
	}
}
