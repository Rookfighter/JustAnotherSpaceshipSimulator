package stesta.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public abstract class AMovingSpaceObject extends ASpaceObject implements IMovingSpaceObject {

	public AMovingSpaceObject(World p_world)
	{
		super(p_world);
	}
	
	public Vec2 getVelocity()
	{
		return getBody().getLinearVelocity();
	}

}
