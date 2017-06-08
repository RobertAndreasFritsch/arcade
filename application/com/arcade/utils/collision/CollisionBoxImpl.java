package com.arcade.utils.collision;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import com.arcade.utils.Direction;
import com.arcade.utils.geom.Dimension2d;
import com.arcade.utils.geom.Rectangle2d;
import com.arcade.utils.geom.Vector2d;

public class CollisionBoxImpl extends Rectangle2d implements CollisionBox
{
	private Collideable	parent;
	private Vector2d		cVevtor2d;
	private Vector2d		offset;
	private Dimension2d	dimension2d;

	public CollisionBoxImpl(Collideable parent, Dimension2d dimension2d, Vector2d offset)
	{
		super(parent.getRectangle2d().asPoint2d().add(offset), dimension2d);

		this.parent = parent;
		this.dimension2d = dimension2d;
		this.offset = offset;
	}

	@Override
	public Vector2d getCollisionVector2d()
	{
		return this.cVevtor2d;
	}

	public void preprozess()
	{
		set(parent.getRectangle2d().asPoint2d().add(offset), dimension2d);
		cVevtor2d.set(.0, .0);
	}

	@Override
	public void prozess(Collideable collideable)
	{
		this.cVevtor2d.set(this.cVevtor2d.add(collideable.getVector2d()));
	}

	@Override
	public Collideable getParent()
	{
		return this.parent;
	}

	@Override
	public Rectangle2d getRectangle2d()
	{
		return this;
	}

	@Override
	public Direction collissionDirection(Rectangle2d rectangle2d)
	{
		
		return null;
	}
}
