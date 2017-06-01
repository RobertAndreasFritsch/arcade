package games.bomberman;

import java.awt.event.KeyEvent;

import com.game.ctrl.KeyCtrl;
import com.game.deprecated.ProceedsInput;
import com.game.deprecated.Updateable;

public class GameOverWaiter implements Updateable, ProceedsInput
{
	private final Bomberman	game;
	private final KeyCtrl	KEYS;

	public GameOverWaiter(final Bomberman game, final KeyCtrl KEYS)
	{
		this.game = game;
		this.KEYS = KEYS;
	}

	@Override
	public void processInput()
	{
		if (this.KEYS.isPressed(KeyEvent.VK_M))
		{
			this.game.setRunning(false);
		}
	}

	@Override
	public void update(final long elapsed)
	{
		int livingPlayers = 0;
		if (this.game.Player1 == null ? false : !this.game.Player1.isDead())
		{
			livingPlayers++;
		}
		if (this.game.Player2 == null ? false : !this.game.Player2.isDead())
		{
			livingPlayers++;
		}
		if (this.game.Player3 == null ? false : !this.game.Player3.isDead())
		{
			livingPlayers++;
		}
		if (this.game.Player4 == null ? false : !this.game.Player4.isDead())
		{
			livingPlayers++;
		}

		if (livingPlayers <= 1)
		{
			this.game.setRunning(false);
		}
	}

}
