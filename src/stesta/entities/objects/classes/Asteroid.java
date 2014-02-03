package stesta.entities.objects.classes;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import stesta.entities.objects.AMovingSpaceObject;
import stesta.entities.objects.IAsteroid;

public class Asteroid extends AMovingSpaceObject implements IAsteroid{

	private static final float DEF_RADIUS = 0.5f;
	private static final float DEF_FRICTION = 0.3f;
	private static final float DEF_DENSITY = 2.5f;
	private static final float DEF_RESTITUTION = 0.3f;
	
	private float radius;
	
	public Asteroid()
	{
		super();
		radius = DEF_RADIUS;
	}
	
	@Override
	public void setRadius(final float p_radius)
	{
		if(hasBody())
			throw new IllegalStateException("Asteroid has already been initialized.");
		if(p_radius <= 0)
			throw new IllegalArgumentException("Asteroid radius cannot be zero or negative.");
		
		radius = p_radius;
	}
	
	@Override
	public float getRadius()
	{
		return radius;
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

	

}
