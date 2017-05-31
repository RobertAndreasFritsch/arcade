package com.arcade.utils;

public class FinitRingList<T>
{
	private int i = 0;
	private final T[] ts;
	
	public FinitRingList (T[] ts){
		this.ts = ts;
	}

	public Object next()
	{
		return ts[i++ % ts.length];
	}
}
