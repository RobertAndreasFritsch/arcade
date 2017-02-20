package games.dotsAndWarriors.dots;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.dotsAndWarriors.actors.GameSubject;

public class DotGenerator extends GameSubject implements Drawable, Updateable {

	public static final long spawnTime = 3000;
	public long lag = 0;

	List<Dot> dots = new ArrayList<Dot>();

	public DotGenerator(int x, int y, Image img, List<GameSubject> friends, List<GameSubject> enemies,
			List<GameSubject> subjects) {
		super(x, y, img, friends, enemies, subjects);
	}

	@Override
	public void draw(Graphics2D g) {

	}

	@Override
	public void update(long elapsed) {
		lag += elapsed;

		if (lag <= spawnTime) {

			lag -= spawnTime;
		}

	}

}
