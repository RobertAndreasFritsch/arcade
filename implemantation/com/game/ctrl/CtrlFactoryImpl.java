package com.game.ctrl;

import javax.swing.JFrame;

public class CtrlFactoryImpl implements CtrlFactory
{
	private final JFrame			jFrame				= new JFrame();

	private KeyCtrl				keyCtrl;
	private final GraphicsCtrl	graphicsCtrlImpl	= new GraphicsCtrlImpl(this.jFrame);

	CtrlFactoryImpl(final KeyCtrlType keyCtrlType)
	{
		switch (keyCtrlType)
		{
		case Microcontroller:
			this.keyCtrl = new MicrocontrollerKeyCtrlImpl(this.jFrame);
			break;

		default: // <- Keyboard
			this.keyCtrl = new KeboardKeyCtrlImpl(this.jFrame);
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

	@Override
	public SoundCtrl soundCtrlInstance()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageCtrl imageCtrlInstance()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
