package com.arcade.utils.geom;

public class Point2<N extends Number>
{
	public Point2(N x, N y)
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

	public void set(Point2<N> p)
	{
		this.x = p.x;
		this.y = p.y;
	}
}
