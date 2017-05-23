package environment.implementation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import environment.model.KeyRequest;

public class MicroControllerKeyRequest implements KeyListener, KeyRequest {

	private static final MicroControllerKeyRequest	INSTANCE		= new MicroControllerKeyRequest();

	private static final int								KEYSLENGHT	= 128;

	public static MicroControllerKeyRequest getInstance() {
		return MicroControllerKeyRequest.INSTANCE;
	}

	private boolean[]	keys			= new boolean[MicroControllerKeyRequest.KEYSLENGHT];

	private boolean[]	keysBuffer	= new boolean[MicroControllerKeyRequest.KEYSLENGHT];

	@Override
	public boolean isPressed(final int keyCode) {
		if (keyCode < this.keys.length) { return this.keys[keyCode]; }
		return false;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		final int key = e.getKeyCode();

		if (e.getKeyCode() == KeyEvent.VK_COMMA) {

			final boolean[] tempKeys = this.keys;
			this.keys = this.keysBuffer;
			this.keysBuffer = tempKeys;// new boolean[KEYSLENGHT];

			for (int i = 0; i < this.keys.length; i++) {
				this.keysBuffer[i] = false;
			}
		}

		else
			if (key < this.keys.length) {
				this.keysBuffer[key] = true;
			}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		// Empty
		// managed by keyPressed -> if there is no input
	}

	@Override
	public void keyTyped(final KeyEvent e) {
		// Empty
	}
}
