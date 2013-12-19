package stesta.entities.objects;

import org.jbox2d.common.Vec2;

public abstract class AMovingSpaceObject extends ASpaceObject implements IMovingSpaceObject {

	public AMovingSpaceObject(final EObjectTypes p_type)
	{
		super(p_type);
	}
	
	public Vec2 getVelocity()
	{
		return getBody().getLinearVelocity();
	}

}
