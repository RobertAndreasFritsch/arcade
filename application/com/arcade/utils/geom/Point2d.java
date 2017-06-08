package com.arcade.utils.geom;

public class Point2d extends Point2<Double>
{
	public Point2d(double x, double y)
	{
		super(x, y);
	}
	
	public Point2d add(Vector2d point2d) {
		return new Point2d(x + point2d.x, this.y + point2d.y);
	}

	public Point2d mul(Vector2d point2d) {
		return new Point2d(x * point2d.x, this.y * point2d.y);
	}
}
