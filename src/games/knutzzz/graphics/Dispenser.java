package games.knutzzz.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.knutzzz.Knutzzz;
import games.knutzzz.KnutzzzGameObject_Ball;
import games.knutzzz.KnutzzzGameObject_Dispenser;

public class Dispenser implements Drawable, Updateable {

	Image toadStoolImage;
	KnutzzzGameObject_Dispenser dispenser;
	private Knutzzz parent;

	public Dispenser(Knutzzz parent, KnutzzzGameObject_Dispenser dispenser) {
		this.parent = parent;
		this.dispenser = dispenser;
		toadStoolImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/toadstool.png");
	}

	@Override
	public void draw(Graphics2D g) {
		if (dispenser.showToadstool)
			g.drawImage(toadStoolImage, 480, 480, null);

	}

	@Override
	public void update(long elapsed) {
		if (dispenser.createBall) {
			int angle = (int) (Math.random() * 360);
			KnutzzzGameObject_Ball ball = new KnutzzzGameObject_Ball(parent);
			ball.vx = Math.cos(Math.toRadians(angle)) * 4;
			ball.vy = Math.sin(Math.toRadians(angle)) * 4;
			parent.add(ball); // TODO ???
			parent.ball = ball;
			parent.setGameRunning(true);
			parent.step = 0;
		}

		if (parent.ball != null && parent.ball.goal) {
			parent.remove(parent.ball);
			parent.ball = null;
			dispenser.reset();
			parent.setGameRunning(false);
		}
	}

}
