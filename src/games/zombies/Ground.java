package games.zombies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;

public class Ground implements Drawable {

	private Image ground = Toolkit.getDefaultToolkit().createImage("res/games/zombies/ground.png");
	private int x = 0, y = 0, width, height;

	public Ground(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1024, 1024);

		g.drawImage(ground, x, y, x + width, y + height, 0, 0, ground.getWidth(null), ground.getHeight(null), null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
