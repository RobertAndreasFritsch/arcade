package com.game.ctrl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import static java.awt.RenderingHints.*;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.arcade.Launch;

public class GraphicsCtrlImpl implements GraphicsCtrl
{
	private final JPanel				jPanel	= new JPanel();

	private final Graphics2D		graphics;
	private final Image				bufferImage;
	private final Graphics2D		bufferGraphics;

	private final AffineTransform	defaultAffineTransform;
	private final Color				defaultBackgrounfColor;
	private final Color				defaultColor;

	public GraphicsCtrlImpl(final JFrame jFrame)
	{
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setUndecorated(true);
		jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		jFrame.setVisible(true);

		jFrame.getContentPane().add(this.jPanel);

		final BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		final Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
		jFrame.getContentPane().setCursor(cursor);

		jFrame.revalidate();

		this.graphics = (Graphics2D) this.jPanel.getGraphics();
		this.bufferImage = this.jPanel.createImage(1024, 1024);
		this.bufferGraphics = (Graphics2D) this.bufferImage.getGraphics();

		this.bufferGraphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
		this.bufferGraphics.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);

		defaultAffineTransform = bufferGraphics.getTransform();
		defaultBackgrounfColor = Color.WHITE;
		defaultColor = Color.BLACK;
	}

	@Override
	public Graphics2D getBufferGraphics()
	{
		return this.bufferGraphics;
	}

	@Override
	public void dispose()
	{
		this.graphics.drawImage(this.bufferImage, (this.jPanel.getWidth() >> 1) - 512,
		      (this.jPanel.getHeight() >> 1) - 512, 1024, 1024, this.jPanel);

		Toolkit.getDefaultToolkit().sync();
		bufferGraphics.clearRect(0, 0, 1024, 1024);
	}

	@Override
	public void normalize()
	{
		this.bufferGraphics.setTransform(defaultAffineTransform);
		this.bufferGraphics.setBackground(defaultBackgrounfColor);
		this.bufferGraphics.setColor(defaultColor);
	}
}
