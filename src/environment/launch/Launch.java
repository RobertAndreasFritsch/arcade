package environment.launch;

import java.awt.event.KeyListener;

import environment.implementation.ComputerKeyRequest;
import environment.implementation.MicroControllerKeyRequest;
import environment.implementation.MyGameLoop;
import environment.implementation.MyWindow;
import environment.model.KeyRequest;
import environment.model.Window;
import environment.model.gameobject.Seat;

public class Launch {

	public static final boolean debugMode = true;

	public static void main(String[] args) {

		final Window window = MyWindow.getInstance();
		final KeyRequest keys = debugMode ? ComputerKeyRequest.getInstance() : MicroControllerKeyRequest.getInstance();

		window.addKeyListener((KeyListener) keys);

		final Menu menu = new Menu(window.getJPanel(), keys);

		new MyGameLoop(menu);
	}
}
