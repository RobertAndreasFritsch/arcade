package environment.implementation;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import environment.model.Window;

public class MyWindow implements Window {
	
	private static final Window INSTANCE = new MyWindow();

	protected final JFrame FRAME = new JFrame();
	protected final JPanel PANEL = new JPanel();


	private MyWindow() {
		
		this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.FRAME.setUndecorated(true);
		this.FRAME.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.FRAME.setVisible(true);

		int offsetX, offsetY;
		int width, height;
		
		offsetX = ((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 1024) >> 1);
		offsetY = ((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 1024) >> 1);
		
		width = height = 1024;
		
		PANEL.setBounds(offsetX, offsetY, width, height);
		this.FRAME.getContentPane().add(PANEL);

		this.FRAME.revalidate();
	}

	@Override
	public JPanel getJPanel() {
		return this.PANEL;
	}
	
	public static Window getInstance() {
		return INSTANCE;
	}

	@Override
	public Component getJFrame() {
		return this.FRAME;
	}
	
	
}
