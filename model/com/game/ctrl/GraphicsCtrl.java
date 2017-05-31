package com.game.ctrl;

import java.awt.Graphics2D;

public interface GraphicsCtrl
{

	Graphics2D getBufferGraphics();

	void dispose();
	
	void normalize();

}