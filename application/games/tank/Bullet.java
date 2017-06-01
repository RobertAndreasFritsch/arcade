package games.tank;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.game.deprecated.Drawable;
import com.game.deprecated.Updateable;

public class Bullet implements Drawable, Updateable
{
	// ------------------------------------------------------------------Decs
	int									BulletSpeed	= 7;
	int									type;
	int									timer			= 200;
	int									playerB;

	double								angle;
	double								posX;
	double								posY;

	int									speedAngle	= 0;
	double								speedX;
	double								speedY;

	double[][]							post			= new double[4][2];
	private final Battleground		battleground;

	private final Tank[]				tanks;
	private final Player[]			player;
	private final BulletFactory	bulletFactory;

	// ------------------------------------------------------------------ctor
	public Bullet(final int player, final Battleground battleground, final Tank[] tankArray,
	      final BulletFactory bulletFactory, final Player[] players)
	{
		this.bulletFactory = bulletFactory;
		this.tanks = tankArray;
		this.battleground = battleground;
		this.playerB = player;
		this.player = players;
		this.init();
	}

	public void init()
	{
		this.speedX = Math.cos(Math.toRadians(this.angle)) * this.BulletSpeed;
		this.speedY = Math.sin(Math.toRadians(this.angle)) * this.BulletSpeed;
		this.posX += Math.cos(Math.toRadians(this.angle)) * 18;
		this.posY += Math.sin(Math.toRadians(this.angle)) * 18;
	}

	// ------------------------------------------------------------------Methods
	@Override
	public void draw(final Graphics2D g)
	{

		final int PaintX = (int) this.posX;
		final int PaintY = (int) this.posY;

		// Drehen des Objekts
		final Graphics2D g2d = g;
		final AffineTransform origform = g2d.getTransform();
		final AffineTransform newform = (AffineTransform) origform.clone();
		newform.rotate(Math.toRadians(this.angle), PaintX, PaintY);
		g2d.setTransform(newform);

		g2d.drawImage(Battleground.standardBulletObject, PaintX - 2, PaintY - 2, null);
		g2d.setTransform(origform);
	}

	@Override
	public void update(final long mspf)
	{

		// Kollision------------------------------------------------------------------------------------------------
		// Bulletposition im naechsten Takt im Array
		final int posax = (int) ((this.posX + this.speedX) / 32);
		final int posay = (int) ((this.posY + this.speedY) / 32);

		if (posax < 0 || posax > 31)
		{
			this.bulletFactory.deleteBullet(this, -1);
			return;
		}

		if (posay < 0 || posay > 31)
		{
			this.bulletFactory.deleteBullet(this, -1);
			return;
		}

		// Bulletposition in diesem Takt im Array
		// int aktx = (int) (BulletPosX / 32);
		final int akty = (int) (this.posY / 32);

		// System.out.println("ArrayX"+posax+"BulletX"+BulletPosX);
		// System.out.println("ArrayY"+posay+"BulletY"+BulletPosY);

		// kollision mit der map
		if (this.battleground.spielfeld[posax][posay].typ > 0)
		{
			// BulletPosX = oldx;
			// BulletPosY = oldy;

			if (akty != posay)
			{
				this.speedY = -this.speedY;
			}
			else
			{
				this.speedX = -this.speedX;
			}
			// System.out.println("Kollision an " + posax + ":" + posay + " von
			// " + aktx + ":" + akty);
		}
		// Bewegung
		this.posX = this.posX + this.speedX;
		this.posY = this.posY + this.speedY;

		// Kugel-Tank Kollision
		for (final Tank tank : this.tanks)
		{

			if (tank != null)
			{
				// if (BulletPosX == battleground.tankArray[x].TankPosX &&
				// BulletPosY ==
				// battleground.tankArray[x].TankPosY)
				if ((tank.TankPosX - this.posX) * (tank.TankPosX - this.posX)
				      + (tank.TankPosY - this.posY) * (tank.TankPosY - this.posY) < Tank.size * Tank.size)
				{

					// System.out.println("Tankkollision erkannt");
					// soundPlayer.Playsound(soundPlayer.Explosion); TODO: play
					// sounds

					// Kollision mit eigenem Panzer
					if (this.playerB == tank.player)
					{
						if (this.player[this.playerB].score > 0)
						{
							this.player[this.playerB].score--;
							// System.out.println("Score " + playerB + " --");
						}

						int i = 0;
						for (final Tank tank2 : this.tanks)
						{
							if (tank2 == null)
							{
								i++;
							}
						}

						if (i >= 3)
						{

						}
					}
					// Kollision mit anderem Panzer
					else
					{
						this.player[this.playerB].score++;
					}

					this.bulletFactory.deleteBullet(this, tank.player);
					return;
				}
			}
		}

		this.timer--;

		if (this.timer <= 0)
		{
			this.bulletFactory.deleteBullet(this, -1);
		}

	}

}
