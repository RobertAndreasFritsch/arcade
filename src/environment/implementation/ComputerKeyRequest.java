package environment.implementation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import environment.model.KeyRequest;

public class ComputerKeyRequest implements KeyListener, KeyRequest {

	public static final ComputerKeyRequest INSTANCE = new ComputerKeyRequest();

	private ComputerKeyRequest() {
	}

	private static final int KEYSLENGHT = 128;
	private boolean[] keys = new boolean[KEYSLENGHT];

	@Override
	public boolean isPressed(int keyCode) {
		if (keyCode < this.keys.length) {
			return this.keys[keyCode];
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key < this.keys.length)
			this.keys[key] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key < this.keys.length)
			this.keys[key] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Empty
	}

	public static ComputerKeyRequest getInstance() {
		return INSTANCE;
	}
}
