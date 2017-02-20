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
	public static Window getInstance() {
		return null;
	}

	default void addKeyListener(KeyListener keyListener) {
		this.getJFrame().addKeyListener(keyListener);
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
	 * @return
	 */
	public Component getJFrame();

	/**
	 * @return
	 */
	public JPanel getJPanel();

	/**
	 * delegation method for JPanels getSize()
	 * 
	 * @return
	 */
	default Dimension getSize() {
		return getJPanel().getSize();
	}

	/**
	 * delegation method for JPanels getWidth()
	 * 
	 * @return
	 */
	default int getWidth() {
		return getJPanel().getWidth();
	}
}
