package games.tank;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.game.SimpleGameObject;
import com.game.ctrl.CtrlFactory;
import com.game.ctrl.ImageType;
import com.game.ctrl.SoundType;

public class Battleground extends SimpleGameObject
{

	// Spielfeld
	Feld[][]								spielfeld	= new Feld[32][32];
	int[][]								Kacheln		= new int[10][10];

	public Image						floor1Image;
	public Image						floor2Image;
	public Image						floor3Image;
	public Image						wall1Image;
	public Image						wall2Image;

	static Image						standardBulletObject;

	public static final String[]	settings		= { "Classic", "Forest", "Desert", "Cave", "Moon", "Graveyard", "Factory",
	      "Lava" };
	String								setting;

	public Battleground(final int scrollpy, final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);

		// setting aus array
		this.setting = Battleground.settings[scrollpy];

		ctrlFactory.soundCtrlInstance()
		      .newSound("res/games/tankies/music/" + Battleground.settings[scrollpy] + "/Soundtrack.mp3", true,
		            SoundType.MP3)
		      .play();

		// Spielfeld genereieren
		this.generate();
		// SpielfeldTexturen

		try
		{
			this.floor1Image = this.load("res/games/tankies/img/" + this.setting + "/floor1.gif", ImageType.GIF);

			this.floor2Image = Toolkit.getDefaultToolkit()
			      .getImage(new File("res/games/tankies/img/" + this.setting + "/floor2.gif").toURI().toURL());
			this.floor3Image = Toolkit.getDefaultToolkit()
			      .getImage(new File("res/games/tankies/img/" + this.setting + "/floor3.gif").toURI().toURL());
			this.wall1Image = Toolkit.getDefaultToolkit()
			      .getImage(new File("res/games/tankies/img/" + this.setting + "/wall1.gif").toURI().toURL());
			this.wall2Image = Toolkit.getDefaultToolkit()
			      .getImage(new File("res/games/tankies/img/" + this.setting + "/wall2.gif").toURI().toURL());
			Battleground.standardBulletObject = Toolkit.getDefaultToolkit()
			      .getImage(new File("res/games/tankies/img/Bullet.png").toURI().toURL());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	private void generate()
	{

		// Zufallsgenerator
		final Random r = new Random();
		final int p1 = 10;
		final int p2 = 30;

		// Spielfeld generieren
		int x;
		int y;
		for (x = 0; x < 32; x++)
		{
			for (y = 0; y < 32; y++)
			{
				this.spielfeld[x][y] = new Feld();
			}
		}

		// alle mit Leerr�umen ausf�llen
		for (x = 0; x < 32; x++)
		{
			for (y = 0; y < 32; y++)
			{
				this.spielfeld[x][y].typ = 0;
				final int p = r.nextInt(100);
				if (p < p1)
				{
					this.spielfeld[x][y].textur = 2;
				}
				else
				{
					this.spielfeld[x][y].textur = r.nextInt(2);
				}

			}
		}

		// R�nder als Wall
		for (x = 0; x < 32; x++)
		{
			this.spielfeld[x][0].typ = 1;
		}
		for (y = 0; y < 32; y++)
		{
			this.spielfeld[0][y].typ = 1;
		}
		for (x = 0; x < 32; x++)
		{
			this.spielfeld[x][31].typ = 1;
		}
		for (y = 0; y < 32; y++)
		{
			this.spielfeld[31][y].typ = 1;
		}

		// festlegen der Kacheln

		for (y = 0; y < 10; y++)
		{
			for (x = 0; x < 10; x++)
			{
				final int p = r.nextInt(100);
				if (p < p2)
				{
					this.Kacheln[x][y] = r.nextInt(8);
				}
				else
				{
					this.Kacheln[x][y] = r.nextInt(4);
				}
			}
		}

		// G�nge im Spielfeld generieren
		for (y = 0; y < 10; y++)
		{
			for (x = 0; x < 10; x++)
			{
				if (this.Kacheln[x][y] == 0)
				{
					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 1].typ = 1;
					this.spielfeld[3 * (x + 1)][3 * (y + 1)].typ = 1;
					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 2].typ = 1;

					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 1].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1)][3 * (y + 1)].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 2].textur = r.nextInt(2);

				}
				if (this.Kacheln[x][y] == 4)
				{
					this.spielfeld[3 * (x + 1)][3 * (y + 1)].typ = 1;
					this.spielfeld[3 * (x + 1)][3 * (y + 1)].textur = r.nextInt(2);
				}
				if (this.Kacheln[x][y] == 7)
				{
					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 2].typ = 1;
					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 2].textur = r.nextInt(2);
				}
				if (this.Kacheln[x][y] == 2)
				{

					this.spielfeld[3 * (x + 1) - 1][3 * (y + 1)].typ = 1;
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1)].typ = 1;
					this.spielfeld[3 * (x + 1)][3 * (y + 1)].typ = 1;

					this.spielfeld[3 * (x + 1) - 1][3 * (y + 1)].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1)].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1)][3 * (y + 1)].textur = r.nextInt(2);

				}
				if (this.Kacheln[x][y] == 3)
				{

					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 2].typ = 1;
					this.spielfeld[3 * (x + 1) - 1][3 * (y + 1) - 2].typ = 1;
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].typ = 1;

					this.spielfeld[3 * (x + 1)][3 * (y + 1) - 2].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1) - 1][3 * (y + 1) - 2].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].textur = r.nextInt(2);

				}
				if (this.Kacheln[x][y] == 1)
				{

					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1)].typ = 1;
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 1].typ = 1;
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].typ = 1;

					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1)].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 1].textur = r.nextInt(2);
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].textur = r.nextInt(2);

				}
				if (this.Kacheln[x][y] == 5)
				{
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1)].typ = 1;

					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1)].textur = r.nextInt(2);
				}
				if (this.Kacheln[x][y] == 6)
				{
					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].typ = 1;

					this.spielfeld[3 * (x + 1) - 2][3 * (y + 1) - 2].textur = r.nextInt(2);
				}
			}
		}

		// Antiblock

		for (x = 1; x < 31; x++)
		{
			this.spielfeld[x][1].typ = 0;
		}
		for (y = 1; y < 31; y++)
		{
			this.spielfeld[1][y].typ = 0;
		}
		for (x = 1; x < 31; x++)
		{
			this.spielfeld[x][30].typ = 0;
		}
		for (y = 1; y < 31; y++)
		{
			this.spielfeld[30][y].typ = 0;
		}
	}

	@Override
	public void input()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void update(final long elapsed)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void output()
	{
		for (int y = 0; y < 32; y++)
		{
			for (int x = 0; x < 32; x++)
			{
				if (this.spielfeld[x][y].typ == 0)
				{
					if (this.spielfeld[x][y].textur == 0)
					{
						this.drawImage(this.floor1Image, 32 * x, 32 * y, null);
					}
					else
					{
						if (this.spielfeld[x][y].textur == 1)
						{
							this.drawImage(this.floor2Image, 32 * x, 32 * y, null);
						}
						else
						{
							this.drawImage(this.floor3Image, 32 * x, 32 * y, null);
						}
					}

				}
				else
				{
					if (this.spielfeld[x][y].textur == 0)
					{
						this.drawImage(this.wall1Image, 32 * x, 32 * y, null);
					}
					else
					{
						this.drawImage(this.wall2Image, 32 * x, 32 * y, null);
					}
				}
			}
		}
	}
}
