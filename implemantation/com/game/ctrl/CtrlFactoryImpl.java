package com.game.ctrl;

import com.game.ctrl.KeyRequest;

public class CtrlFactoryImpl implements CtrlFactory
{
	KeyRequest keyRequest;
	
	CtrlFactoryImpl(KeyRequestType keyRequestType) {
		switch (keyRequestType)
		{
		case Microcontroller:
			keyRequest = new MicrocontrollerKeyRequest();
			break;

		default: // <- Keyboard
			keyRequest = new KeboardKeyRequest();
			break;
		}
	}
	
	@Override
	public KeyRequest keyRequestInstance() {
		return keyRequest;
	}
}
