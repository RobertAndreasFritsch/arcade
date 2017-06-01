package com.arcade.utils.collision;

import com.arcade.utils.geom.Dimension2;
import com.arcade.utils.geom.Vector2;

public class CollisionBoxImpl implements CollisionBox
{
	private Vector2<Double> collisionVector2 = new Vector2<Double>(.0, .0);
	private Vector2<Double> collisionVector2Buffer = new Vector2<Double>(.0, .0);
	
	private Collideable collideable;
	private Vector2<Double> offset;
	private Dimension2<Double> dimension2;
	private CollisionBoxType collisionBoxType;

	public CollisionBoxImpl(Collideable collideable, Vector2<Double> offset, Dimension2<Double> dimension2, CollisionBoxType collisionBoxType)
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
	
	void prozess(Collideable collideable)
	{
	}
	
	void update()
	{
		this.collisionVector2 = collisionVector2Buffer;
	}
}
