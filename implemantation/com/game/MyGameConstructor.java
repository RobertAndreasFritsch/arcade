package com.game;

import javax.swing.JPanel;

public interface MyGameConstructor
{
	MyGame MyGame(JPanel panel, KeyRequest keys, String... args);
}
