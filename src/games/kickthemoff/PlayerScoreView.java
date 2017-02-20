package games.kickthemoff;

import java.awt.Color;
import java.awt.Graphics2D;

import environment.model.gameobject.Drawable;

public class PlayerScoreView implements Drawable {
	private static final int POINT_WIDTH = 25;

	private int x, y, rotation, max;
	private Player player;

	public PlayerScoreView(int max, int x, int y, int rotation, Player player) {
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.player = player;
		this.max = max;
	}

	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.translate(x, y);
		g2.rotate(Math.toRadians(rotation));

		int topY = (POINT_WIDTH << 1);
		
		g.setColor(Color.GREEN);
		for (int xc = - ((max * POINT_WIDTH) >> 1), c = 0; xc < (max * POINT_WIDTH) >> 1; xc += POINT_WIDTH, c++) {
			if(c >= player.getWins()){
				g.setColor(Color.RED);
			}
			g.fillOval(xc, topY, POINT_WIDTH, POINT_WIDTH);
		}

		g2.rotate(-Math.toRadians(rotation));
		g2.translate(-x, -y);
	}

}
