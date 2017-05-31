package games.tank;

import javax.swing.JPanel;

import com.arcade.audio.SoundFactory;
import com.game.Game;
import com.game.MyGame;
import com.game.ctrl.KeyRequest;

public class Tankies extends MyGame
{

	Player[]			player			= new Player[4];
	SoundFactory	soundFactory	= new SoundFactory();

	public Tankies(final JPanel PANEL, final KeyRequest keys)
	{
		super(PANEL, keys);

		Battleground battleground = null;
		BulletFactory bulletFactory = null;

		final Tank[] tankArray = new Tank[4];

		this.player[0] = new Player();
		this.player[1] = new Player();
		this.player[2] = new Player();
		this.player[3] = new Player();

		battleground = new Battleground((int) (Math.random() * Battleground.settings.length), this.soundFactory);
		bulletFactory = new BulletFactory(this, battleground, tankArray, this.player, this.soundFactory);

		tankArray[0] = new Tank(0, battleground, bulletFactory, keys);
		tankArray[1] = new Tank(1, battleground, bulletFactory, keys);
		tankArray[2] = new Tank(2, battleground, bulletFactory, keys);
		tankArray[3] = new Tank(3, battleground, bulletFactory, keys);

		this.add(battleground);

		this.add(tankArray[0]);
		this.add(tankArray[1]);
		this.add(tankArray[2]);
		this.add(tankArray[3]);

		this.add(new Scores(tankArray, this.player, bulletFactory, this));
	}

	public Tankies(final JPanel PANEL, final KeyRequest keys, final Player[] player)
	{
		super(PANEL, keys);

		Battleground battleground = null;
		BulletFactory bulletFactory = null;

		final Tank[] tankArray = new Tank[4];

		this.player = player;

		battleground = new Battleground((int) (Math.random() * Battleground.settings.length), this.soundFactory);
		bulletFactory = new BulletFactory(this, battleground, tankArray, player, this.soundFactory);

		tankArray[0] = new Tank(0, battleground, bulletFactory, keys);
		tankArray[1] = new Tank(1, battleground, bulletFactory, keys);
		tankArray[2] = new Tank(2, battleground, bulletFactory, keys);
		tankArray[3] = new Tank(3, battleground, bulletFactory, keys);

		this.add(battleground);

		this.add(tankArray[0]);
		this.add(tankArray[1]);
		this.add(tankArray[2]);
		this.add(tankArray[3]);

		this.add(new Scores(tankArray, player, bulletFactory, this));
	}

	@Override
	public Game getNextGame()
	{

		this.soundFactory.stop();

		for (final Player p : this.player)
		{
			if (p.score >= 9) { return super.getNextGame(); }
		}
		return new Tankies(super.getPANEL(), super.getKEYS(), this.player);
	}
}
