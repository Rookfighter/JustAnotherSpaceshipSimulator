package jass.entities.objects.classes;

import jass.entities.objects.AHitableMovingSpaceObject;
import jass.entities.objects.IAsteroid;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Asteroid extends AHitableMovingSpaceObject implements IAsteroid{

	public static final float MIN_RADIUS = 0.6f;
	
	private static final float DEF_RADIUS = MIN_RADIUS;
	private static final float DEF_FRICTION = 0.3f;
	private static final float DEF_DENSITY = 2.5f;
	private static final float DEF_RESTITUTION = 0.3f;
	private static final int DEF_LIFE_POINTS = 1200;
	
	private float radius;
	
	public Asteroid()
	{
		super();
		setRadius(DEF_RADIUS);
	}
	
	@Override
	public void setRadius(final float p_radius)
	{
		if(hasBody())
			throw new IllegalStateException("Asteroid has already been initialized.");
		if(p_radius <= 0)
			throw new IllegalArgumentException("Asteroid radius cannot be zero or negative.");
		
		radius = p_radius;
		setMaxLifePoints((int) (radius * DEF_LIFE_POINTS));
		maximizeLifePoints();
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
	protected FixtureDef[] getFixtureDef()
	{
		FixtureDef[] result = new FixtureDef[1];
		result[0] = new FixtureDef();
		CircleShape cs = new CircleShape();
		cs.setRadius(radius);
		
		result[0].shape = cs;
		result[0].density = DEF_DENSITY;
		result[0].restitution = DEF_RESTITUTION;
		result[0].friction = DEF_FRICTION;
		
		return result;
	}

}
