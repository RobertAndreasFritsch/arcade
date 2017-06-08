package com.arcade.utils.geom;

public class Rectangle2<N extends Number>
{
	public N x, y, w, h;

	public Rectangle2(N x, N y, N w, N h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Rectangle2(Point2<N> p, Dimension2<N> d)
	{
		this.x = p.x;
		this.y = p.y;
		this.w = d.w;
		this.h = d.h;
	}
	
	public void set(N x, N y, N w, N h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void set(Point2<N> p, Dimension2<N> d)
	{
		this.x = p.x;
		this.y = p.y;
		this.w = d.w;
		this.h = d.h;
	}

	public void set(Rectangle2<N> r)
	{
		this.x = r.x;
		this.y = r.y;
		this.w = r.w;
		this.h = r.h;
	}
}
