package games.dotsAndWarriors.test;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;

public abstract class Subject implements Drawable, Updateable {

	public State activeState;

	// attributes
	double health;
	double protection;

	double attackPower;
	double attackRange;
	double attackSpeed;

	// position attributes
	double x, y;
	double angle;

	// position manipulation attributes
	double movementSpeed;
	double turnSpeed;

	public Subject() {

	}

}
