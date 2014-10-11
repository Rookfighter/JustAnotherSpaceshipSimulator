package jass.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;


public abstract class ASpaceObject implements ISpaceObject {

	private Body body;
	
	public ASpaceObject()
	{
		body = null;
	}
	
	@Override
	public void createBody(final World p_world)
	{
		if(hasBody())
			throw new IllegalStateException(String.format("%s-object has already a body.", getClass().getName()));
		
		body = p_world.createBody(getBodyDef());
		for(FixtureDef fixture : getFixtureDef())
			body.createFixture(fixture);
		body.setUserData(this);
	}
	
	protected abstract BodyDef getBodyDef();
	protected abstract FixtureDef[] getFixtureDef();
	
	@Override
	public boolean hasBody()
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
}
