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

	Image									toadStoolImage;
	KnutzzzGameObject_Dispenser	dispenser;
	private final Knutzzz			parent;
	int									ballCount	= 0;

	public Dispenser(final Knutzzz parent, final KnutzzzGameObject_Dispenser dispenser) {
		this.parent = parent;
		this.dispenser = dispenser;
		this.toadStoolImage = Toolkit.getDefaultToolkit().getImage("res/games/knutzzz/img/toadstool.png");
	}

	@Override
	public void draw(final Graphics2D g) {
		if (this.dispenser.showToadstool) {
			g.drawImage(this.toadStoolImage, 480, 480, null);
		}

	}

	@Override
	public void update(final long elapsed) {
		if (this.dispenser.createBall) {
			// Dispenser gibt einen Ball aus -> Ballobjekt erzeugen
			final int angle = (int) (Math.random() * 360);
			final KnutzzzGameObject_Ball ball = new KnutzzzGameObject_Ball(this.parent);
			ball.vx = Math.cos(Math.toRadians(angle)) * 4;
			ball.vy = Math.sin(Math.toRadians(angle)) * 4;
			this.parent.add(ball); // TODO ???
			this.parent.ball = ball;
			this.parent.setGameRunning(true);
			this.parent.step = 0;
			this.ballCount++;
		}

		if (this.parent.ball != null && this.parent.ball.goal) {
			// Ball ist im Tor -> Ballobjekt vernichten
			this.parent.remove(this.parent.ball);
			this.parent.ball = null;

			if (this.ballCount >= 21) {
				// Max. Anzahl Baelle -> Spielende
				this.dispenser.setGameEnd();
			}
			else {
				this.dispenser.reset();
			}
			this.parent.setGameRunning(false);
		}

	}

}
