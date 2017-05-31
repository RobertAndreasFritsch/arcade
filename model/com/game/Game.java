package com.game;

import java.util.List;

public interface Game extends List<GameObject>
{

	// public void add(Object gameObject);

	// void addAll(List<?> gameObjects);

	public Game getNextGame();

	public boolean isRunning();

	// public void remove(Object gameObject);

	// void removeAll(List<?> gameObjects);

	public void setRunning(boolean running);

	public void tick(long elapsed);
}
