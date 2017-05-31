package com.game.ctrl;

import java.awt.event.KeyListener;

public interface KeyCtrl extends KeyListener
{
	boolean isPressed(int keyCode);
	void takeFrame();
}
