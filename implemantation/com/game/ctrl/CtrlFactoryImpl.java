package com.game.ctrl;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class CtrlFactoryImpl implements CtrlFactory
{
	private final JFrame jFrame = new JFrame();
	
	KeyRequest keyRequest;
	GraphicsController graphicsController = new GraphicsController(jFrame);

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
		
		jFrame.addKeyListener((KeyListener) this.keyRequest);
	}

	@Override
	public KeyRequest keyRequestInstance()
	{
		return this.keyRequest;
	}

	@Override
	public Graphics2D getGraphics()
	{
		return (Graphics2D) graphicsController.getBufferGraphics();
	}

	@Override
	public GraphicsController graphicsControllerInstance()
	{return graphicsController;
	}
}
