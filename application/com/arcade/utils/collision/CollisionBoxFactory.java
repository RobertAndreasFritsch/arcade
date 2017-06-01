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

	public CollisionBoxFactory(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);
	}

	void bindBox(final Collideable collideable, final Vector2<Double> offset, final Dimension2<Double> dimension2,
	      final CollisionBoxType collisionBoxType)
	{
		this.collisionBoxes.add(new CollisionBoxImpl(collideable, offset, dimension2, collisionBoxType));
	}

	void unbindBox(final CollisionBox collisionBox)
	{
		this.collisionBoxes.remove(collisionBox);
	}

	@Override
	public void update(final long elapsed)
	{
		// TODO find all collisionpair
		// process all collisionpairs that collide
	}
}
