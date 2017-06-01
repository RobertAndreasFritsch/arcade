package games.zombies;

import java.util.ArrayList;

import com.arcade.utils.Seat;
import com.game.SimpleGame;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.ImageType;
import com.game.deprecated.ProceedsInput;

import games.zombies.collision.Blockade;
import games.zombies.collision.CollisionBox;

public class Zombies extends SimpleGame implements ProceedsInput
{

	/**
	 *
	 */
	private static final long					serialVersionUID	= 7982550380997988183L;
	private Ground									ground;
	private final ArrayList<CollisionBox>	boxes;
	private final ArrayList<Player>			players;

	public Zombies(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);
		this.boxes = new ArrayList<>();
		this.players = new ArrayList<>();
		
		Zombie.textures.load("res/games/zombies/textures/citizenzombie1.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie2.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie3.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie4.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie5.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie6.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie7.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie8.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie9.png", ImageType.PNG);
		Zombie.textures.load("res/games/zombies/textures/citizenzombie10.png", ImageType.PNG);

		this.add(this);
		this.add(this.ground = new Ground(0, 0, 1024, 1024));
		this.addCollisionBox(new Blockade(0, 0, 10, 1024));
		this.addCollisionBox(new Blockade(0, 0, 1024, 10));
		this.addCollisionBox(new Blockade(1014, 0, 10, 1024));
		this.addCollisionBox(new Blockade(0, 1014, 1024, 10));

		if (Seat.P1.isPlaying())
		{
			final Player p = new Player(100, 100, Seat.P1, this);
			this.add(p);
			this.players.add(p);
		}
		if (Seat.P2.isPlaying())
		{
			final Player p = new Player(100, 100, Seat.P2, this);
			this.add(p);
			this.players.add(p);
		}
		if (Seat.P3.isPlaying())
		{
			final Player p = new Player(100, 100, Seat.P3, this);
			this.add(p);
			this.players.add(p);
		}
		if (Seat.P4.isPlaying())
		{
			final Player p = new Player(100, 100, Seat.P4, this);
			this.add(p);
			this.players.add(p);
		}
		this.add(new Zombie(500, 500, this));

	}

	public Ground getGround()
	{
		return this.ground;
	}

	public void addCollisionBox(final CollisionBox c)
	{
		if (!(c instanceof Player))
		{
			this.add(c);
		}
		this.boxes.add(c);
	}

	public boolean removeCollisionBox(final CollisionBox c)
	{
		this.remove(c);
		return this.boxes.remove(c);
	}

	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}

	@Override
	public void processInput()
	{
		for (final CollisionBox c1 : new ArrayList<>(this.boxes))
		{
			for (final CollisionBox c2 : new ArrayList<>(this.boxes))
			{
				if (c1 != c2)
				{
					if (CollisionBox.doCollide(c1, c2))
					{
						c1.onCollision(c2, CollisionBox.getCollisionDirection(c1, c2));
						c2.onCollision(c1, CollisionBox.getCollisionDirection(c2, c1));
					}
				}
			}
		}
	}

}
