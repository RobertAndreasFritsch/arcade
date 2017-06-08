package com.arcade.utils.geom;

public class Vector2d extends Vector2<Double>
{
	public Vector2d(double x, double y)
	{
		super(x, y);
	}
	
	public Vector2d add(Vector2d vector2d) {
		return new Vector2d(x + vector2d.x, this.y + vector2d.y);
	}

	public Vector2d mul(Vector2d vector2d) {
		return new Vector2d(x * vector2d.x, this.y * vector2d.y);
	}
}
