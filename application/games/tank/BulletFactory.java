package games.tank;

import java.io.File;

import com.game.Game;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.SoundType;

public class BulletFactory
{

	private final Battleground	battleground;
	private final Tank[]			tankArray;
	private final Game			game;

	// KugelArray
	int[]								bulletcounter	= { 0, 0, 0, 0 };
	private final Player[]		player;
	private final CtrlFactory	ctrlFactoryImpl;

	public BulletFactory(final Game game, final Battleground battleground, final Tank[] tankArray, final Player[] player,
	      final CtrlFactory ctrlFactory)
	{
		this.game = game;
		this.battleground = battleground;
		this.tankArray = tankArray;
		this.player = player;
		this.ctrlFactoryImpl = ctrlFactory;
	}

	public void newBullet(final Tank tank)
	{

		// ------------------------------------------------

		// Testen ob Platz im Array

		if (this.bulletcounter[tank.player] < 5)
		{
			this.bulletcounter[tank.player]++;

			final Bullet bullet = new Bullet(tank.player, this.battleground, this.tankArray, this, this.player);

			bullet.posX = tank.TankPosX;
			bullet.posY = tank.TankPosY;
			bullet.angle = tank.angle;
			bullet.init();

			this.game.add(bullet);

			this.ctrlFactoryImpl.newSound(new File("res/games/tankies/sounds/Samples/shoot.wav"), false, SoundType.WAV).start();
		}
	}

	public void deleteBullet(final Bullet bullet, final int x)
	{
		this.bulletcounter[bullet.playerB]--;

		if (x != -1)
		{
			this.game.remove(this.tankArray[x]);
			this.tankArray[x] = null;
		}

		this.game.remove(bullet);
	}

	public int size()
	{
		int sum = 0;
		for (final int i : this.bulletcounter)
		{
			sum += i;
		}
		return sum;
	}
}
