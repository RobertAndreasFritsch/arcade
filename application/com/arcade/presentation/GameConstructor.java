package com.arcade.presentation;

import javax.swing.JPanel;

import com.game.Game;
import com.game.ctrl.KeyRequest;

public interface GameConstructor
{
	Game newGame(JPanel panel, KeyRequest kEYS);
}
