package stesta.entities.objects.classes;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import stesta.entities.objects.AMovingSpaceObject;
import stesta.entities.objects.IRocket;
import stesta.entities.objects.EObjectTypes;

public class Rocket extends AMovingSpaceObject implements IRocket{

	public static final float DEF_RADIUS = 0.5f;
	private static final float DEF_FRICTION = 0.3f;
	private static final float DEF_DENSITY = 0.6f;
	private static final float DEF_RESTITUTION = 0.5f;
	private static final float MAX_ACCELERATE_FORCE = 10.0f;
	
	private float radius;
	private float accelerateForce;
	
	public Rocket()
	{
		super(EObjectTypes.ROCKET);
		radius = DEF_RADIUS;
		accelerateForce = 0;
	}

	@Override
	protected BodyDef getBodyDef()
	{
		BodyDef result = new BodyDef();
		result.position.set(0.0f, 0.0f);
		result.type = BodyType.DYNAMIC;
		result.allowSleep = true;
		
		return result;
	}

	@Override
	protected FixtureDef getFixtureDef()
	{
		
		FixtureDef result = new FixtureDef();
		CircleShape cs = new CircleShape();
		cs.setRadius(radius);
		
		result.shape = cs;
		result.density = DEF_DENSITY;
		result.restitution = DEF_RESTITUTION;
		result.friction = DEF_FRICTION;
		
		return result;
	}

	@Override
	public float getDirection()
	{
		return getBody().getAngle();
	}
	
	@Override
	public float getRadius()
	{
		return radius;
	}

	@Override
	public float getMaxAccelerateForce()
	{
		return MAX_ACCELERATE_FORCE;
	}

	@Override
	public float getAccelerateForce()
	{
		return accelerateForce;
	}

	@Override
	public void setAccelerateForce(final float p_accelerateForce)
	{
		accelerateForce = p_accelerateForce;
	}

}
