package games.dotsAndWarriors.dots;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.dotsAndWarriors.actors.GameSubject;

public class Dot extends GameSubject implements Drawable, Updateable {

	public Dot(int x, int y, Image img, List<GameSubject> friends, List<GameSubject> enemies,
			List<GameSubject> subjects) {
		super(x, y, img, friends, enemies, subjects);
	}

	@Override
	public void update(long elapsed) {

	}

	@Override
	public void draw(Graphics2D g) {
		g.drawOval((int) (getX() - getHalfSize()), (int) (getY() - getHalfSize()), getSize(), getSize());
		g.fillOval((int) ((getX() - getHalfSize()) * .5), (int) ((getY() - getHalfSize()) * .5), (int) (getSize() * .5),
				(int) (getSize() * .5));
	}

}
