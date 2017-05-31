package com.game;

public interface GOAdapter extends GameObject
{
	@Override
	default void input()
	{
	}

	@Override
	default void update(final long elapsed)
	{
	}

	@Override
	default void output()
	{
	}
}
