package environment.implementation;

import javax.swing.JPanel;

import environment.model.KeyRequest;

public interface MyGameConstructor {
	MyGame MyGame(JPanel panel, KeyRequest keys, String... args);
}
