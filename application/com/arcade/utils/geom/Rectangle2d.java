package com.arcade.utils.geom;

public class Rectangle2d extends Rectangle2<Double>
{
	public Rectangle2d(double x, double y, double w, double h)
	{
		super(x, y, w, h);
	}

	public Rectangle2d(Point2d point2d, Dimension2d dimension2d)
	{
		super(point2d, dimension2d);
	}

	public boolean intersects(Point2d point2d)
	{
		return point2d.x < this.x || point2d.x > this.x + this.w;
	}

	public boolean intersects(Rectangle2d rectangle2d)
	{
		if (rectangle2d.x + rectangle2d.w < this.x | rectangle2d.x > this.x + this.w) return true;
		if (rectangle2d.y + rectangle2d.h < this.y | rectangle2d.y > this.y + this.h) return true;

		return false;
	}

	public Point2d asPoint2d()
	{
		return new Point2d(this.x, this.y);
	}

	public Dimension2d asDimension2d()
	{
		return new Dimension2d(this.w, this.h);
	}

	public Dimension2d add(Dimension2d dimension2d)
	{
		return new Dimension2d(w + dimension2d.w, this.h + dimension2d.h);
	}

	public Dimension2d mul(Dimension2d dimension2d)
	{
		return new Dimension2d(w * dimension2d.w, this.h * dimension2d.h);
	}

	public Rectangle2d add(Vector2d vector2d)
	{
		return new Rectangle2d(x + vector2d.x, this.y + vector2d.y, this.w, this.h);
	}

	public Rectangle2d mul(Vector2d vector2d)
	{
		return new Rectangle2d(x * vector2d.x, this.y * vector2d.y, this.w, this.h);
	}
}
