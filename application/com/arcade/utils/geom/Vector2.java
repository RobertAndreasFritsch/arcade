package com.arcade.utils.geom;

public class Vector2<N extends Number>
{
	public Vector2(N x, N y)
	{
		this.x = x;
		this.y = y;
	}

	public N x, y;
	
	public void set(N x, N y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2<N> v)
	{
		this.x = v.x;
		this.y = v.y;
	}
}
