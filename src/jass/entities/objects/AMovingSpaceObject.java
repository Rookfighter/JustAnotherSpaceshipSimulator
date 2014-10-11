package jass.entities.objects;

import org.jbox2d.common.Vec2;

public abstract class AMovingSpaceObject extends ASpaceObject implements IMovingSpaceObject {

	public AMovingSpaceObject()
	{
		super();
	}
	
	public Vec2 getVelocity()
	{
		return getBody().getLinearVelocity();
	}

}
