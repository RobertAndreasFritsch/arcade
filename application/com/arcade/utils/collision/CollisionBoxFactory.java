package com.arcade.utils.collision;

import java.util.ArrayList;
import java.util.List;

import com.arcade.utils.geom.Dimension2d;
import com.arcade.utils.geom.Vector2d;
import com.game.SimpleGameObject;
import com.game.ctrl.CtrlFactory;

public class CollisionBoxFactory extends SimpleGameObject
{
	private final List<CollisionBox> collisionBoxes = new ArrayList<>();

	CollisionBoxFactory(final CtrlFactory ctrlFactory)
	{
		super(ctrlFactory);
	}

	public static final CollisionBoxFactory newCollisionFactory(final CtrlFactory ctrlFactory)
	{
		return new CollisionBoxFactory(ctrlFactory);
	}

	CollisionBox bindBox(final Collideable collideable, final Dimension2d dimension2d, final Vector2d offset)
	{
		CollisionBox collisionBox = new CollisionBoxImpl(collideable, dimension2d, offset);
		this.collisionBoxes.add(collisionBox);
		return collisionBox;
	}

	void unbindBox(final CollisionBox collisionBox)
	{
		this.collisionBoxes.remove(collisionBox);
	}

	void unbindAllBoxes(final Collideable collideable)
	{
		for (CollisionBox collisionBox : collisionBoxes)
		{
			if (collisionBox.getParent() == collideable) unbindBox(collisionBox);
		}
	}

	@Override
	public void update(final long elapsed)
	{
		CollisionBox[] box = new CollisionBox[collisionBoxes.size()];
		int i = 0;
		for (CollisionBox collisionBox : collisionBoxes)
		{
			box[i++] = collisionBox;
		}

		for (i = 0; i < box.length; i++)
		{
			for (int j = i + 1; j < box.length; j++)
			{
				if (box[i].getRectangle2d().intersects(box[j].getRectangle2d()))
				{
					box[i].prozess(box[j].getParent());
					box[j].prozess(box[i].getParent());
				}
			}
		}
	}
}
