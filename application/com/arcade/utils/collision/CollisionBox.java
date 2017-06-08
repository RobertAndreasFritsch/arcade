package com.arcade.utils.collision;

import com.arcade.utils.Direction;
import com.arcade.utils.geom.Rectangle2d;
import com.arcade.utils.geom.Vector2d;

public interface CollisionBox
{
	Vector2d getCollisionVector2d();
	
	Rectangle2d getRectangle2d();
	
	void preprozess();

	void prozess(final Collideable collideable);
	
	Collideable getParent();
	
	Direction collissionDirection(final Rectangle2d rectangle2d);
}
