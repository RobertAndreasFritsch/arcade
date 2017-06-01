package com.arcade.utils.geom;

public class Dimension2<N extends Number>
{
	public Dimension2(N w, N h)
	{
		this.w = w;
		this.h = h;
	}
	
	public N w, h;
}
