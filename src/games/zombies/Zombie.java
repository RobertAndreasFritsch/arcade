package games.zombies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import environment.model.gameobject.Drawable;
import environment.model.gameobject.Updateable;
import games.utils.Direction;
import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Zombie extends CollisionBox implements Drawable, Updateable {

	private static final float WALKING_SPEED = 100;
	
	private float rotation = 0;
	private Zombies game;
	private Image texture = Toolkit.getDefaultToolkit().createImage("res/games/zombies/Zombie.png");

	private boolean collNorth = false;
	private boolean collEast = false;
	private boolean collSouth = false;
	private boolean collWest = false;
	
	public Zombie(int x, int y, Zombies game) {
		super(x - 20, y - 20, 40, 40);
		this.game = game;
	}

	@Override
	public void update(long elapsed) {
		float scalar = elapsed / 1000f;
		
		Player target = null;
		double distance = -1;
		double difx;
		double dify;

		for (Player p : game.getPlayers()) {
			difx = getX() - p.getX();
			dify = getY() - p.getY();
			double ddistance = Math.sqrt((difx * difx) + (dify * dify));
			if (ddistance < distance) {
				distance = ddistance;
				target = p;
			} else if (distance == -1) {
				distance = ddistance;
				target = p;
			}
		}
		
		float angle = (float) Math.toDegrees(Math.atan2(target.getY() - getY(), target.getX() - getX()));
	    if(angle < 0){
	        angle += 360;
	    }
	    rotation = angle;
		
	    
		double newX = x, newY = y;
		
		float length = WALKING_SPEED * scalar;
		newX += Math.sin(Math.toRadians(rotation + 90)) * length;
		newY -= Math.cos(Math.toRadians(rotation + 90)) * length;
		
		if (newY < y && collNorth) {
			newY = y;
		}
		if (newY > y && collSouth) {
			newY = y;
		}
		if (newX > x && collEast) {
			newX = x;
		}
		if (newX < x && collWest) {
			newX = x;
		}

		x = (int) newX;
		y = (int) newY;
		setX((int) x - 20);
		setY((int) y - 20);

		collNorth = false;
		collEast = false;
		collSouth = false;
		collWest = false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.rotate(Math.toRadians(rotation), x, y);
		g.drawImage(texture, (int) x - 15, (int) y - 17, null);
		g.rotate(-Math.toRadians(rotation), x, y);

		super.draw(g);
	}
	
	@Override
	public void onCollision(CollisionBox with, Direction dir) {
		if (!(with instanceof Blockade)) {
			return;
		}

		switch (dir) {
		case NORTH:
			collNorth = true;
			break;
		case EAST:
			collEast = true;
			break;
		case SOUTH:
			collSouth = true;
			break;
		case WEST:
			collWest = true;
			break;
		}
	}
	
}
