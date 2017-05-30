package games.zombies;

import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.utils.Direction;
import games.utils.Seat;
import games.zombies.collision.CollisionBox;

public class Zombies extends MyGame implements ProceedsInput{

	private Ground ground;
	private ArrayList<CollisionBox> boxes;

	public Zombies(JPanel PANEL, KeyRequest KEYS, String... args) {
		super(PANEL, KEYS, args);
		boxes = new ArrayList<CollisionBox>();

		add(this);
		add((ground = new Ground(0, 0, 1024, 1024)));

		if (Seat.P1.isPlaying()) {
			add(new Player(100, 100, Seat.P1, this));
		}
		if (Seat.P2.isPlaying()) {
			add(new Player(100, 100, Seat.P2, this));
		}
		if (Seat.P3.isPlaying()) {
			add(new Player(100, 100, Seat.P3, this));
		}
		if (Seat.P4.isPlaying()) {
			add(new Player(100, 100, Seat.P4, this));
		}
	}

	public Ground getGround() {
		return ground;
	}
	
	public void addCollisionBox(CollisionBox c) {
		boxes.add(c);
	}
	
	public boolean removeCollisionBox(CollisionBox c) {
		return boxes.remove(c);
	}

	@Override
	public void processInput() {
		for(CollisionBox c1: boxes) {
			for(CollisionBox c2: boxes) {
				if(c1 != c2) {
					Direction collideOnX = CollisionBox.doCollideOnX(c1, c2);
					Direction collideOnY = CollisionBox.doCollideOnY(c1, c2);
					
					if(collideOnX == null || collideOnY == null) {
						break;
					}
					
					boolean north = collideOnY == Direction.NORTH;
					boolean south = collideOnY == Direction.SOUTH;
					boolean east = collideOnX == Direction.EAST;
					boolean west = collideOnX == Direction.WEST;
										
					c1.onCollision(c2, north, east, south, west);
					c2.onCollision(c1, south, west, north, east);
				}
			}
		}
	}

}
