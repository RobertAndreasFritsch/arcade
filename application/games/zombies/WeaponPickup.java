package games.zombies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import com.arcade.utils.Direction;
import com.game.deprecated.Drawable;

import games.zombies.collision.CollisionBox;

public class WeaponPickup extends CollisionBox implements Drawable{

	private static final int HITBOX_DIM = 20;

	private Weapon weapon;
	private int x, y;
	private Zombies game;

	public WeaponPickup(int x, int y, Weapon weapon, Zombies game) {
		super(x - (HITBOX_DIM >> 1), y - (HITBOX_DIM >> 1), HITBOX_DIM, HITBOX_DIM);
		this.x = x;
		this.y = y;
		this.game = game;
		this.weapon = weapon;
		
		game.addCollisionBox(this);
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(Color.GRAY);
		g.fillRect(x - (HITBOX_DIM >> 1), y - (HITBOX_DIM >> 1), HITBOX_DIM, HITBOX_DIM);
		
		super.draw(g);
	}
	
	@Override
	public void onCollision(final CollisionBox with, final Direction dir)
	{
		if(with instanceof Player){
			Player p = (Player) with;
			p.setWeapon(weapon);
			game.removeCollisionBox(this);
		}
	}

}
