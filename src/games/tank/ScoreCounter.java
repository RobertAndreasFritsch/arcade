package games.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import games.utils.Seat;

public class ScoreCounter implements Drawable {

	Image[][] scores = new Image[4][10];

	public ScoreCounter() {
		for (int j = 0; j < this.scores.length; j++) {
			for (int i = 0; i < this.scores[0].length; i++) {
				this.scores[j][i] = Toolkit.getDefaultToolkit().getImage("img//Score//" + j + i + ".png");
			}
		}
	}

	@Override
	public void draw(final Graphics2D g) {

		g.drawImage(this.scores[0][Seat.P1.getScore()], 19, 19, null);

		g.drawImage(this.scores[1][Seat.P2.getScore()], 940, 19, null);

		g.drawImage(this.scores[2][Seat.P3.getScore()], 940, 943, null);

		g.drawImage(this.scores[3][Seat.P4.getScore()], 19, 943, null);

	}
}
