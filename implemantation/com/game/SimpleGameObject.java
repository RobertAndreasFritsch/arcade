package com.game;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import com.game.ctrl.CtrlFactory;
import com.game.ctrl.ImageType;
import com.game.ctrl.Sound;
import com.game.ctrl.SoundType;

public abstract class SimpleGameObject implements GameObject
{
	private final CtrlFactory ctrlFactory;

	// supported delegations:

	// private ImageCtrl imageCtrl;
	// private GraphicsCtrl graphicsCtrl;
	// private Graphics graphics;
	// private KeyCtrl keyCtrl;
	// private SoundCtrl soundCtrl;

	public SimpleGameObject(final CtrlFactory ctrlFactory)
	{
		this.ctrlFactory = ctrlFactory;
	}

	public void normalize()
	{
		this.ctrlFactory.graphicsControllerInstance().normalize();
	}

	public boolean limit()
	{
		return this.ctrlFactory.soundCtrlInstance().limit();
	}

	public Image load(final String path, final ImageType imageType)
	{
		return this.ctrlFactory.imageCtrlInstance().load(path, imageType);
	}

	public BufferedImage loadBuffered(final String path, final ImageType imageType)
	{
		return this.ctrlFactory.imageCtrlInstance().loadBuffered(path, imageType);
	}

	public Sound newSound(final String path, final boolean loop, final SoundType soundType)
	{
		return this.ctrlFactory.soundCtrlInstance().newSound(path, loop, soundType);
	}

	public void closeSounds()
	{
		this.ctrlFactory.soundCtrlInstance().closeSounds();
	}

	public boolean isPressed(final int keyCode)
	{
		return this.ctrlFactory.keyRequestInstance().isPressed(keyCode);
	}

	public void keyPressed(final KeyEvent arg0)
	{
		this.ctrlFactory.keyRequestInstance().keyPressed(arg0);
	}

	public void keyReleased(final KeyEvent arg0)
	{
		this.ctrlFactory.keyRequestInstance().keyReleased(arg0);
	}

	public void keyTyped(final KeyEvent arg0)
	{
		this.ctrlFactory.keyRequestInstance().keyTyped(arg0);
	}

	public void takeFrame()
	{
		this.ctrlFactory.keyRequestInstance().takeFrame();
	}

	public void addRenderingHints(final Map<?, ?> arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().addRenderingHints(arg0);
	}

	public void clearRect(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().clearRect(arg0, arg1, arg2, arg3);
	}

	public void clip(final Shape arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().clip(arg0);
	}

	public void clipRect(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().clipRect(arg0, arg1, arg2, arg3);
	}

	public void copyArea(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().copyArea(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	public Graphics create()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().create();
	}

	public Graphics create(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().create(arg0, arg1, arg2, arg3);
	}

	public void dispose()
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().dispose();
	}

	public void draw(final Shape arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().draw(arg0);
	}

	public void draw3DRect(final int arg0, final int arg1, final int arg2, final int arg3, final boolean arg4)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().draw3DRect(arg0, arg1, arg2, arg3, arg4);
	}

	public void drawArc(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawArc(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	public void drawBytes(final byte[] arg0, final int arg1, final int arg2, final int arg3, final int arg4)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawBytes(arg0, arg1, arg2, arg3, arg4);
	}

	public void drawChars(final char[] arg0, final int arg1, final int arg2, final int arg3, final int arg4)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawChars(arg0, arg1, arg2, arg3, arg4);
	}

	public void drawGlyphVector(final GlyphVector arg0, final float arg1, final float arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawGlyphVector(arg0, arg1, arg2);
	}

	public void drawImage(final BufferedImage arg0, final BufferedImageOp arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2, arg3);
	}

	public boolean drawImage(final Image arg0, final AffineTransform arg1, final ImageObserver arg2)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2);
	}

	public boolean drawImage(final Image arg0, final int arg1, final int arg2, final Color arg3,
	      final ImageObserver arg4)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2, arg3, arg4);
	}

	public boolean drawImage(final Image arg0, final int arg1, final int arg2, final ImageObserver arg3)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2, arg3);
	}

	public boolean drawImage(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4,
	      final Color arg5, final ImageObserver arg6)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2, arg3, arg4,
		      arg5, arg6);
	}

	public boolean drawImage(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4,
	      final ImageObserver arg5)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2, arg3, arg4,
		      arg5);
	}

	public boolean drawImage(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4,
	      final int arg5, final int arg6, final int arg7, final int arg8, final Color arg9, final ImageObserver arg10)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2, arg3, arg4,
		      arg5, arg6, arg7, arg8, arg9, arg10);
	}

	public boolean drawImage(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4,
	      final int arg5, final int arg6, final int arg7, final int arg8, final ImageObserver arg9)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawImage(arg0, arg1, arg2, arg3, arg4,
		      arg5, arg6, arg7, arg8, arg9);
	}

	public void drawLine(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawLine(arg0, arg1, arg2, arg3);
	}

	public void drawOval(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawOval(arg0, arg1, arg2, arg3);
	}

	public void drawPolygon(final int[] arg0, final int[] arg1, final int arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawPolygon(arg0, arg1, arg2);
	}

	public void drawPolygon(final Polygon arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawPolygon(arg0);
	}

	public void drawPolyline(final int[] arg0, final int[] arg1, final int arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawPolyline(arg0, arg1, arg2);
	}

	public void drawRect(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawRect(arg0, arg1, arg2, arg3);
	}

	public void drawRenderableImage(final RenderableImage arg0, final AffineTransform arg1)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawRenderableImage(arg0, arg1);
	}

	public void drawRenderedImage(final RenderedImage arg0, final AffineTransform arg1)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawRenderedImage(arg0, arg1);
	}

	public void drawRoundRect(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4,
	      final int arg5)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawRoundRect(arg0, arg1, arg2, arg3, arg4,
		      arg5);
	}

	public void drawString(final AttributedCharacterIterator arg0, final float arg1, final float arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawString(arg0, arg1, arg2);
	}

	public void drawString(final AttributedCharacterIterator arg0, final int arg1, final int arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawString(arg0, arg1, arg2);
	}

	public void drawString(final String arg0, final float arg1, final float arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawString(arg0, arg1, arg2);
	}

	public void drawString(final String arg0, final int arg1, final int arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().drawString(arg0, arg1, arg2);
	}

	@Override
	public boolean equals(final Object arg0)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().equals(arg0);
	}

	public void fill(final Shape arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fill(arg0);
	}

	public void fill3DRect(final int arg0, final int arg1, final int arg2, final int arg3, final boolean arg4)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fill3DRect(arg0, arg1, arg2, arg3, arg4);
	}

	public void fillArc(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fillArc(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	public void fillOval(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fillOval(arg0, arg1, arg2, arg3);
	}

	public void fillPolygon(final int[] arg0, final int[] arg1, final int arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fillPolygon(arg0, arg1, arg2);
	}

	public void fillPolygon(final Polygon arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fillPolygon(arg0);
	}

	public void fillRect(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fillRect(arg0, arg1, arg2, arg3);
	}

	public void fillRoundRect(final int arg0, final int arg1, final int arg2, final int arg3, final int arg4,
	      final int arg5)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().fillRoundRect(arg0, arg1, arg2, arg3, arg4,
		      arg5);
	}

	@Override
	public void finalize()
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().finalize();
	}

	public Color getBackground()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getBackground();
	}

	public Shape getClip()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getClip();
	}

	public Rectangle getClipBounds()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getClipBounds();
	}

	public Rectangle getClipBounds(final Rectangle arg0)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getClipBounds(arg0);
	}

	public Color getColor()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getColor();
	}

	public Composite getComposite()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getComposite();
	}

	public GraphicsConfiguration getDeviceConfiguration()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getDeviceConfiguration();
	}

	public Font getFont()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getFont();
	}

	public FontMetrics getFontMetrics()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getFontMetrics();
	}

	public FontMetrics getFontMetrics(final Font arg0)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getFontMetrics(arg0);
	}

	public FontRenderContext getFontRenderContext()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getFontRenderContext();
	}

	public Paint getPaint()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getPaint();
	}

	public Object getRenderingHint(final Key arg0)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getRenderingHint(arg0);
	}

	public RenderingHints getRenderingHints()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getRenderingHints();
	}

	public Stroke getStroke()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getStroke();
	}

	public AffineTransform getTransform()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().getTransform();
	}

	@Override
	public int hashCode()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().hashCode();
	}

	public boolean hit(final Rectangle arg0, final Shape arg1, final boolean arg2)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().hit(arg0, arg1, arg2);
	}

	public boolean hitClip(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().hitClip(arg0, arg1, arg2, arg3);
	}

	public void rotate(final double arg0, final double arg1, final double arg2)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().rotate(arg0, arg1, arg2);
	}

	public void rotate(final double arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().rotate(arg0);
	}

	public void scale(final double arg0, final double arg1)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().scale(arg0, arg1);
	}

	public void setBackground(final Color arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setBackground(arg0);
	}

	public void setClip(final int arg0, final int arg1, final int arg2, final int arg3)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setClip(arg0, arg1, arg2, arg3);
	}

	public void setClip(final Shape arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setClip(arg0);
	}

	public void setColor(final Color arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setColor(arg0);
	}

	public void setComposite(final Composite arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setComposite(arg0);
	}

	public void setFont(final Font arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setFont(arg0);
	}

	public void setPaint(final Paint arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setPaint(arg0);
	}

	public void setPaintMode()
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setPaintMode();
	}

	public void setRenderingHint(final Key arg0, final Object arg1)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setRenderingHint(arg0, arg1);
	}

	public void setRenderingHints(final Map<?, ?> arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setRenderingHints(arg0);
	}

	public void setStroke(final Stroke arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setStroke(arg0);
	}

	public void setTransform(final AffineTransform arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setTransform(arg0);
	}

	public void setXORMode(final Color arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().setXORMode(arg0);
	}

	public void shear(final double arg0, final double arg1)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().shear(arg0, arg1);
	}

	@Override
	public String toString()
	{
		return this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().toString();
	}

	public void transform(final AffineTransform arg0)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().transform(arg0);
	}

	public void translate(final double arg0, final double arg1)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().translate(arg0, arg1);
	}

	public void translate(final int arg0, final int arg1)
	{
		this.ctrlFactory.graphicsControllerInstance().getBufferGraphics().translate(arg0, arg1);
	}
}
