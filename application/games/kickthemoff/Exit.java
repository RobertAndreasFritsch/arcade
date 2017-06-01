package games.kickthemoff;

import java.awt.event.KeyEvent;

import com.game.deprecated.ProceedsInput;

public class Exit implements ProceedsInput
{
	private final Kickthemoff game;

	public Exit(final Kickthemoff game)
	{
		this.game = game;
	}

	@Override
	public void processInput()
	{
		if (this.game.getKEYS().isPressed(KeyEvent.VK_M))
		{
			this.game.setRunning(false);
		}
	}

}
