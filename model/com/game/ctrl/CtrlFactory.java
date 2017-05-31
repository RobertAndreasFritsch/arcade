package com.game.ctrl;

public interface CtrlFactory
{
	static CtrlFactory newCtrlFactory (KeyRequestType keyRequestType) {
		return new CtrlFactoryImpl(keyRequestType);
	}
	
	KeyRequest keyRequestInstance();
}