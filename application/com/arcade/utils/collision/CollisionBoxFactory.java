package com.arcade.utils.collision;

import java.util.ArrayList;
import java.util.List;

import com.arcade.utils.geom.Dimension2;
import com.arcade.utils.geom.Vector2;
import com.game.SimpleGameObject;
import com.game.ctrl.CtrlFactory;

public class CollisionBoxFactory extends SimpleGameObject
{
	private final List<CollisionBox> collisionBoxes = new ArrayList<>();
	
	public CollisionBoxFactory(CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);
	}
	
	void bindBox(Collideable collideable, Vector2<Double> offset, Dimension2<Double> dimension2, CollisionBoxType collisionBoxType) {
		collisionBoxes.add(new CollisionBoxImpl(collideable, offset, dimension2, collisionBoxType));
	}
	
	void unbindBox(CollisionBox collisionBox) {
		collisionBoxes.remove(collisionBox);
	}

	@Override
	public void update(long elapsed)
	{
		
	}
}
