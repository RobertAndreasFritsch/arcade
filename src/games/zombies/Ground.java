package games.zombies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;

public class Ground implements Drawable{
	
	private Image ground = Toolkit.getDefaultToolkit().createImage("res/games/zombies/ground.png");
	private int offsetX = 0, offsetY = 0;
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1024, 1024);
		
		g.drawImage(ground, offsetX, offsetY, null);
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

}
