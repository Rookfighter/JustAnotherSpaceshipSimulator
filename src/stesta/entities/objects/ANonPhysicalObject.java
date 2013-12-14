package stesta.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public abstract class ANonPhysicalObject implements ISpaceObject{

	private Vec2 position;
	
	public ANonPhysicalObject()
	{
		position = new Vec2(0,0);
	}
	
	@Override
	public Vec2 getPosition()
	{
		return position;
	}

	@Override
	public Body getBody()
	{
		return null;
	}

}
