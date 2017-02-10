package games.dotsAndWarriors.actors.bosses;

import java.awt.Image;
import java.util.List;

import games.dotsAndWarriors.actors.GameSubject;
import games.dotsAndWarriors.actors.GameSubjects;

public class Boss extends GameSubject {
	private static final long minFocusTime = 5000l;

	private GameSubject focused;
	private long currentFocustime = 0;

	public Boss(int x, int y, Image img, List<GameSubject> friends, List<GameSubject> enemies,
			List<GameSubject> subjects) {
		super(x, y, img, friends, enemies, subjects);

		setFocused(enemies.get(0));
		setSize(128);
		setRange(20);
	}

	private double calculateAgro(GameSubject g) {
		return g.getPower() + g.getProtection() * 30 + 60 / GameSubjects.distance(g, this);
	}

	private GameSubject calculateMaxAgroPlayer() {

		GameSubject maxAgroPlayer = null;
		double maxAgro = -2;

		for (GameSubject g : getEnemies()) {
			double wAgro = g.isDead() ? -1 : calculateAgro(g);

			if (wAgro > maxAgro) {
				maxAgroPlayer = g;
				maxAgro = wAgro;
			}
		}
		return maxAgroPlayer;
	}

	public GameSubject getFocused() {
		return focused;
	}

	public void setFocused(GameSubject focused) {
		this.focused = focused;
	}

	@Override
	public void update(long elapsed) {
		if (isDead())
			return;

		if (currentFocustime <= 0 | focused.isDead()) {

			focused = calculateMaxAgroPlayer();
			currentFocustime = minFocusTime;
		} else {
			currentFocustime -= elapsed;
		}

		if (focused.isDead())
			return;

		double distX = focused.getX() - getX();
		double distY = focused.getY() - getY();

		setAngle(GameSubjects.angle(distY, distX));

		if (GameSubjects.distance(distX, distY) <= ((focused.getSize() + getSize()) >> 1) + getRange()) {
			setAttacking(true);
		} else {
			setRunningForward(true);
		}

		super.update(elapsed);
	}
}
