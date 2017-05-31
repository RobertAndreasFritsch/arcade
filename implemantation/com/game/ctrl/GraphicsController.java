package com.game.ctrl;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsController
{
	private final JPanel	PANEL	= new JPanel();

	private Graphics graphics;
	private Image bufferImage;
	private Graphics bufferGraphics;
	
	public GraphicsController(JFrame	FRAME)
	{
		this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.FRAME.setUndecorated(true);
		this.FRAME.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.FRAME.setVisible(true);

		this.FRAME.getContentPane().add(this.PANEL);

		final BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		final Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0),
		      "blank cursor");
		this.FRAME.getContentPane().setCursor(blankCursor);

		this.FRAME.revalidate();
		
		this.graphics = this.PANEL.getGraphics();
		this.bufferImage = this.PANEL.createImage(1024, 1024);
		this.bufferGraphics = bufferImage.getGraphics();
	}

	public Graphics getBufferGraphics()
	{
		return bufferGraphics;
	}

	public void dispose()
	{
		graphics.drawImage(bufferImage, (PANEL.getWidth() >> 1) - 512, (PANEL.getHeight() >> 1) - 512, 1024, 1024, PANEL);
	}
}
