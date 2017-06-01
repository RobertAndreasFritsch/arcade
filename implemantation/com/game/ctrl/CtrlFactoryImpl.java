package com.game.ctrl;

import javax.swing.JFrame;

public class CtrlFactoryImpl implements CtrlFactory
{
	private final JFrame			jFrame				= new JFrame();

	private KeyCtrl				keyCtrl;
	private final GraphicsCtrl	graphicsCtrlImpl	= new GraphicsCtrlImpl(this.jFrame);
	private ImageCtrl				imageCtrl			= new ImageCtrlImpl();
	private SoundCtrl				soundCtrl			= new SoundCtrlImpl();

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
		return this.soundCtrl;
	}

	@Override
	public ImageCtrl imageCtrlInstance()
	{
		return this.imageCtrl;
	}
}
