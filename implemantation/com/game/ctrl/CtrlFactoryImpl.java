package com.game.ctrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class CtrlFactoryImpl implements CtrlFactory
{
	private final JFrame		jFrame				= new JFrame();
	public List<SoundCtrl>	soundCtrls			= new ArrayList<>();

	KeyCtrl						keyCtrl;
	GraphicsCtrl				graphicsCtrlImpl	= new GraphicsCtrlImpl(this.jFrame);

	CtrlFactoryImpl(final KeyCtrlType keyCtrlType)
	{
		switch (keyCtrlType)
		{
		case Microcontroller:
			this.keyCtrl = new MicrocontrollerKeyCtrlImpl(this.jFrame);
			break;

		default: // <- Keyboard
			this.keyCtrl = new KeboardKeyCtrlImpl(this.jFrame);
			break;
		}
	}

	@Override
	public KeyCtrl keyRequestInstance()
	{
		return this.keyCtrl;
	}

	@Override
	public GraphicsCtrl graphicsControllerInstance()
	{
		return this.graphicsCtrlImpl;
	}

	@Override
	public SoundCtrl newSound(final File file, final boolean loop, final SoundType soundType)
	{
		SoundCtrl soundCtrl;

		try
		{
			switch (soundType)
			{
			case MP3:
				soundCtrl = new MP3SoundCtrlImpl(file, loop);
				break;
			case WAV:
				soundCtrl = new WAVSoundCtrl(file, loop);
				break;
			default:
				throw new Exception("unknown Type : " + soundType);
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return null;
		}

		this.soundCtrls.add(soundCtrl);
		return soundCtrl;
	}

	@Override
	public void closeSounds()
	{
		for (final SoundCtrl soundCtrl : this.soundCtrls)
		{
			soundCtrl.stop();
		}
	}
}
