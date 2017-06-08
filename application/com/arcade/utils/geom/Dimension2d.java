package com.arcade.utils.geom;

public class Dimension2d extends Dimension2<Double>
{
	public Dimension2d(double w, double h)
	{
		super(w, h);
	}

	public int compare(Dimension2d dimension2d) {
		
		double vol1 = w * h;
		double vol2 = dimension2d.w * dimension2d.h;
		
		return vol1 > vol2? 1 : vol1 < vol2? -1 : 0;
	}
	
	public Dimension2d add(Dimension2d dimension2d) {
		return new Dimension2d(w + dimension2d.w, this.h + dimension2d.h);
	}

	public Dimension2d mul(Dimension2d dimension2d) {
		return new Dimension2d(w * dimension2d.w, this.h * dimension2d.h);
	}
}
