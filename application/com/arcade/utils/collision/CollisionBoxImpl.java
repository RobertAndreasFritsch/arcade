package com.arcade.utils.collision;

import com.arcade.utils.geom.Dimension2;
import com.arcade.utils.geom.Vector2;

public class CollisionBoxImpl implements CollisionBox
{
	private Vector2<Double>				collisionVector2			= new Vector2<>(.0, .0);
	private final Vector2<Double>		collisionVector2Buffer	= new Vector2<>(.0, .0);

	private final Collideable			collideable;
	private final Vector2<Double>		offset;
	private final Dimension2<Double>	dimension2;
	private final CollisionBoxType	collisionBoxType;

	public CollisionBoxImpl(final Collideable collideable, final Vector2<Double> offset,
	      final Dimension2<Double> dimension2, final CollisionBoxType collisionBoxType)
	{
		this.collideable = collideable;
		this.offset = offset;
		this.dimension2 = dimension2;
		this.collisionBoxType = collisionBoxType;
	}

	@Override
	public Vector2<Double> getCollisionVector2()
	{
		return this.collisionVector2;
	}

	void prozess(final Collideable collideable)
	{
	}

	void update()
	{
		this.collisionVector2 = this.collisionVector2Buffer;
	}
}
