package environment.implementation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import environment.model.KeyRequest;

public class MicroControllerKeyRequest implements KeyListener, KeyRequest {

	private static final MicroControllerKeyRequest INSTANCE = new MicroControllerKeyRequest();

	private static final int KEYSLENGHT = 128;
	private boolean[] keys = new boolean[KEYSLENGHT];
	private boolean[] keysBuffer = new boolean[KEYSLENGHT];

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

		if (e.getKeyCode() == KeyEvent.VK_COMMA) {

			boolean[] tempKeys = keys;
			keys = keysBuffer;
			keysBuffer = tempKeys;// new boolean[KEYSLENGHT];

			for (int i = 0; i < this.keys.length; i++)
				this.keysBuffer[i] = false;
		}

		else if (key < this.keys.length)
			this.keysBuffer[key] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Empty
		// managed by keyPressed -> if there is no input
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Empty
	}

	public static MicroControllerKeyRequest getInstance() {
		return INSTANCE;
	}
}
