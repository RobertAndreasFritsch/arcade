package games.pong.controll;

import java.awt.event.KeyEvent;

import com.game.Game;
import com.game.ctrl.KeyCtrl;
import com.game.deprecated.ProceedsInput;

public class Controll implements ProceedsInput
{

	private final Game		parent;
	private final KeyCtrl	KEYS;

	public Controll(final Game parent, final KeyCtrl KEYS)
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
