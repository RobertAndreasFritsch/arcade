package com.arcade.presentation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import com.game.Drawable;
import com.game.Game;
import com.game.KeyRequest;

public enum Presentation implements Drawable
{
	/***/
	TANK("res/games/tankies/img/Menu/Cover.gif", games.tank.Tankies::new),

	/***/
	BOMBER("res/games/bomberman/bomberman_Presentation.png", games.bomberman.Bomberman::new),

	/***/
	KNUTZZ("res/games/knutzzz/knutzzzwallpaper.png", games.knutzzz.Knutzzz::new),

	/***/
	PONG("res/games/pong/pong_Presentation.png", games.pong.Pong::new),

	/***/
	TRON("res/games/tron/tronbild.png", games.tron.Tron::new),

	/***/
	KICKER("res/games/kickthemoff/kickthemoff_Presentation.png", games.kickthemoff.Kickthemoff::new),

	/***/
	ZOMBIES("res/games/zombies/zombieslogomitch.jpg", games.zombies.Zombies::new);

	private Image				image;
	private GameConstructor	gameConstructor;

	private Presentation(final String path, final GameConstructor gameConstructor)
	{
		try
		{
			this.image = Toolkit.getDefaultToolkit().getImage(new File(path).toURI().toURL());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		this.gameConstructor = gameConstructor;
	}

	@Override
	public void draw(final Graphics2D g)
	{
		g.drawImage(this.image, 0, 0, 1048, 1048, null);
	}

	public Game getGame(final JPanel panel, final KeyRequest KEYS)
	{
		return this.gameConstructor.newGame(panel, KEYS);
	}
}
