package games.bomberman;

import java.awt.Graphics2D;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Seat;

public class ScoreBar implements Drawable {
	private Bomberman game;
	private int x, y, width, height, rotation;

	public ScoreBar(Bomberman game, int x, int y, int width, int height, int rotation) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
	}

	@Override
	public void draw(Graphics2D g) {
		int s1 = Seat.P1.isPlaying() ? game.Player1.getScore() : 0;
		int s2 = Seat.P2.isPlaying() ? game.Player2.getScore() : 0;
		int s3 = Seat.P3.isPlaying() ? game.Player3.getScore() : 0;
		int s4 = Seat.P4.isPlaying() ? game.Player4.getScore() : 0;
		int sum = s1 + s2 + s3 + s4;

		g.translate(x, y);
		g.rotate(Math.toRadians(rotation));
		g.translate(-x, -y);

		int cursor = 0;
		g.setColor(Seat.P1.getColor());
		g.fillRect(x, y, (int) ((float) s1 / sum * width), height);
		cursor += (int) ((float) s1 / sum * width);

		g.setColor(Seat.P2.getColor());
		g.fillRect(x + cursor, y, (int) ((float) s2 / sum * width), height);
		cursor += (int) ((float) s2 / sum * width);

		g.setColor(Seat.P3.getColor());
		g.fillRect(x + cursor, y, (int) ((float) s3 / sum * width), height);
		cursor += (int) ((float) s3 / sum * width);

		g.setColor(Seat.P4.getColor());
		g.fillRect(x + cursor, y, (int) ((float) s4 / sum * width), height);
		cursor += (int) ((float) s4 / sum * width);

		g.translate(x, y);
		g.rotate(Math.toRadians(-rotation));
		g.translate(-x, -y);
	}

}
