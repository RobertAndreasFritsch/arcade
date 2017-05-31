package com.arcade.presentation;

import com.game.Game;
import com.game.ctrl.CtrlFactory;

public interface GameConstructor
{
	Game newGame(CtrlFactory ctrlFactory);
}
