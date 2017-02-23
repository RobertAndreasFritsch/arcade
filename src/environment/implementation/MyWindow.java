package environment.implementation;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import environment.model.Window;

public class MyWindow implements Window {

	private static final Window INSTANCE = new MyWindow();

	public static Window getInstance() {
		return INSTANCE;
	}
	protected final JFrame FRAME = new JFrame();

	protected final JPanel PANEL = new JPanel();

	private MyWindow() {
		this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.FRAME.setUndecorated(true);
		this.FRAME.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.FRAME.setVisible(true);

		int offsetX, offsetY;
		int width, height;

		width = height = 1024;

		this.FRAME.getContentPane().add(PANEL);

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		this.FRAME.getContentPane().setCursor(blankCursor);

		this.FRAME.revalidate();
	}

	@Override
	public Component getJFrame() {
		return this.FRAME;
	}

	@Override
	public JPanel getJPanel() {
		return this.PANEL;
	}
}
