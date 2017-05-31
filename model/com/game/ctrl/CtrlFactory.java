package com.game.ctrl;

import java.io.File;

public interface CtrlFactory
{
	static CtrlFactory newCtrlFactory(final KeyCtrlType keyCtrlType)
	{
		return new CtrlFactoryImpl(keyCtrlType);
	}

	KeyCtrl keyRequestInstance();

	GraphicsCtrl graphicsControllerInstance();

	SoundCtrl newSound(final File file, final boolean loop, final SoundType soundType);

	void closeSounds();

}