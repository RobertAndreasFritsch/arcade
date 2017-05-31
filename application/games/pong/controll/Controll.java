package games.pong.controll;

import java.awt.event.KeyEvent;

import com.game.Game;
import com.game.KeyRequest;
import com.game.ProceedsInput;

public class Controll implements ProceedsInput
{

	private final Game			parent;
	private final KeyRequest	KEYS;

	public Controll(final Game parent, final KeyRequest KEYS)
	{
		this.parent = parent;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput()
	{
		if (this.KEYS.isPressed(KeyEvent.VK_ESCAPE))
		{
			this.parent.setRunning(false);
		}
	}

}
