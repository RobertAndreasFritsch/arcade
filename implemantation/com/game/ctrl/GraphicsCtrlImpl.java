package com.game.ctrl;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsCtrlImpl
{
	private final JPanel			PANEL	= new JPanel();

	private final Graphics2D	graphics;
	private final Image			bufferImage;
	private final Graphics2D	bufferGraphics;

	public GraphicsCtrlImpl(final JFrame FRAME)
	{
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setUndecorated(true);
		FRAME.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		FRAME.setVisible(true);

		FRAME.getContentPane().add(this.PANEL);

		final BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		final Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0),
		      "blank cursor");
		FRAME.getContentPane().setCursor(blankCursor);
		FRAME.revalidate();

		this.graphics = (Graphics2D) this.PANEL.getGraphics();
		this.bufferImage = this.PANEL.createImage(1024, 1024);
		this.bufferGraphics = (Graphics2D) this.bufferImage.getGraphics();

		this.bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.bufferGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		      RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	public Graphics2D getBufferGraphics()
	{
		return this.bufferGraphics;
	}

	public void dispose()
	{
		this.graphics.drawImage(this.bufferImage, (this.PANEL.getWidth() >> 1) - 512, (this.PANEL.getHeight() >> 1) - 512,
		      1024, 1024, this.PANEL);

		Toolkit.getDefaultToolkit().sync();
		bufferGraphics.clearRect(0, 0, 1024, 1024);
	}
}
