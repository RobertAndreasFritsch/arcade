package com.game;

import javax.swing.JPanel;

import com.game.ctrl.KeyRequest;

public interface MyGameConstructor
{
	MyGame MyGame(JPanel panel, KeyRequest keys, String... args);
}