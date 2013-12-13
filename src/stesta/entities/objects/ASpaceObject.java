package stesta.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;


public abstract class ASpaceObject implements ISpaceObject {

	private Body body;
	private ObjectTypes type;
	
	public ASpaceObject(final ObjectTypes p_type, final World p_world)
	{
		body = p_world.createBody(getBodyDef());
		body.createFixture(getFixtureDef());
		type = p_type;
	}
	
	protected abstract BodyDef getBodyDef();
	protected abstract FixtureDef getFixtureDef();
	
	@Override
	public Body getBody()
	{
		return body;
	}
	
	@Override
	public Vec2 getPosition()
	{
		return body.getPosition();
	}
	
	@Override
	public ObjectTypes type()
	{
		return type;
	}
	
}
