package games.zombies;

import java.util.ArrayList;

import javax.swing.JPanel;

import environment.implementation.MyGame;
import environment.model.KeyRequest;
import environment.model.gameobject.ProceedsInput;
import environment.model.gameobject.Updateable;
import games.utils.Direction;
import games.utils.Seat;
import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Zombies extends MyGame implements ProceedsInput {

	private Ground ground;
	private ArrayList<CollisionBox> boxes;

	public Zombies(JPanel PANEL, KeyRequest KEYS, String... args) {
		super(PANEL, KEYS, args);
		boxes = new ArrayList<CollisionBox>();

		add(this);
		add((ground = new Ground(0, 0, 1024, 1024)));
		addCollisionBox(new Blockade(0, 0, 10, 1024));
		addCollisionBox(new Blockade(0, 0, 1024, 10));
		addCollisionBox(new Blockade(1014, 0, 10, 1024));
		addCollisionBox(new Blockade(0, 1014, 1024, 10));

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
		if (!(c instanceof Player)) {
			add(c);
		}
		boxes.add(c);
	}

	public boolean removeCollisionBox(CollisionBox c) {
		remove(c);
		return boxes.remove(c);
	}

	@Override
	public void processInput() {
		for (CollisionBox c1 : new ArrayList<CollisionBox>(boxes)) {
			for (CollisionBox c2 : new ArrayList<CollisionBox>(boxes)) {
				if (c1 != c2) {
					if (CollisionBox.doCollide(c1, c2)) {
						c1.onCollision(c2, CollisionBox.getCollisionDirection(c1, c2));
						c2.onCollision(c1, CollisionBox.getCollisionDirection(c2, c1));
					}
				}
			}
		}
	}

}
