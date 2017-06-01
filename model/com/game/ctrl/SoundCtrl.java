package com.game.ctrl;

public interface SoundCtrl
{
	static final int SOUNDLIMIT = 16;

	Sound newSound(String path, boolean loop, SoundType soundType);

	void closeSounds();

	boolean limit();

	void incrementSoundCounter();

	void decrementSoundCounter();
}
