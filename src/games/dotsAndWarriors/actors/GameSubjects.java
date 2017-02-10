package games.dotsAndWarriors.actors;

public final class GameSubjects {

	public static final double angle(double from, double to) {
		return Math.toDegrees(Math.atan2(from, to));
	}

	public static final double angle(GameSubject from, GameSubject to) {
		double distX = from.getX() - to.getX();
		double distY = from.getY() - to.getY();

		return Math.toDegrees(Math.atan2(distY, distX));
	}

	public static final double distance(double distX, double distY) {
		return Math.sqrt(distX * distX + distY * distY);
	}

	public static final double distance(GameSubject go1, GameSubject go2) {
		double distX = go1.getX() - go2.getX();
		double distY = go1.getY() - go2.getY();

		return Math.sqrt(distX * distX + distY * distY);
	}
}
