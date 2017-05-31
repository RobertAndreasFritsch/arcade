package games.tron;

import java.awt.event.KeyEvent;

import com.game.Game;
import com.game.ProceedsInput;
import com.game.Updateable;
import com.game.ctrl.KeyRequest;

public class Controll implements Updateable, ProceedsInput
{

	private final KeyRequest	KEYS;
	private final Game			game;

	public Controll(final Game game, final KeyRequest KEYS)
	{
		this.game = game;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput()
	{
		if (this.KEYS.isPressed(KeyEvent.VK_ESCAPE))
		{
			this.game.setRunning(false);
		}
	}

	@Override
	public void update(final long elapsed)
	{
	}
}
