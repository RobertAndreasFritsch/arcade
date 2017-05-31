package com.game;

/**
 * @author r.fritsch2511
 *
 */
public interface ProceedsInput extends GOAdapter
{

	public void processInput();

	@Override
	default void input()
	{
		processInput();
	}
}
