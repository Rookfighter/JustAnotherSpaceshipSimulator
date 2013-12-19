package stesta.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;


public abstract class ASpaceObject implements ISpaceObject {

	private Body body;
	private EObjectTypes type;
	
	public ASpaceObject(final EObjectTypes p_type)
	{
		body = null;
		type = p_type;
	}
	
	@Override
	public void initialize(final World p_world)
	{
		if(isInitialized())
			throw new IllegalStateException(String.format("%s-object has already been initialized.", getClass().getName()));
		
		body = p_world.createBody(getBodyDef());
		body.createFixture(getFixtureDef());
	}
	
	protected abstract BodyDef getBodyDef();
	protected abstract FixtureDef getFixtureDef();
	
	@Override
	public boolean isInitialized()
	{
		return body != null;
	}
	
	@Override
	public Body getBody()
	{
		return body;
	}
	
	@Override
	public void setPosition(final Vec2 p_position)
	{
		body.setTransform(p_position, body.getAngle());
	}
	
	@Override
	public Vec2 getPosition()
	{
		return body.getPosition();
	}
	
	@Override
	public EObjectTypes type()
	{
		return type;
	}
	
}
