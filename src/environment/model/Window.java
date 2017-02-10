package environment.model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/**
 * @author r.fritsch2511
 *
 */
public interface Window {
	
	/**
	 * @return
	 */
	public JPanel getJPanel();
	
	/**
	 * delegation method for JPanels getWidth()
	 * 
	 * @return
	 */
	default int getWidth() {
		return getJPanel().getWidth();
	}
	
	/**
	 * delegation method for JPanels getHeight()
	 * 
	 * @return
	 */
	default int getHeight() {
		return getJPanel().getHeight();
	}
	
	/**
	 * delegation method for JPanels getSize()
	 * 
	 * @return
	 */
	default Dimension getSize() {
		return getJPanel().getSize();
	}
	
	default void addKeyListener(KeyListener keyListener) {
		this.getJFrame().addKeyListener(keyListener);
	}

	/**
	 * @return
	 */
	public Component getJFrame();
}
