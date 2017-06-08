package com.arcade.utils.collision;

import com.arcade.utils.geom.Rectangle2d;
import com.arcade.utils.geom.Vector2d;

public interface Collideable
{
	Rectangle2d getRectangle2d();
	Vector2d getVector2d();
}
