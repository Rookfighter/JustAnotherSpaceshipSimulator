package stesta.entities.objects.classes;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import stesta.entities.objects.AMovingSpaceObject;
import stesta.entities.objects.IRocket;

public class Rocket extends AMovingSpaceObject implements IRocket{

	public static final float DEF_RADIUS = 0.5f;
	private static final float DEF_FRICTION = 0.3f;
	private static final float DEF_DENSITY = 0.6f;
	private static final float DEF_RESTITUTION = 0.5f;
	private static final float MAX_ACCELERATE_FORCE = 10.0f;
	private static final int DEF_MAX_LIFEPOINTS = 1000;
	
	private float radius;
	private float accelerateForce;
	
	private int lifePoints;
	private int maxLifePoints;
	
	public Rocket()
	{
		super();
		radius = DEF_RADIUS;
		accelerateForce = 0;
		maxLifePoints = DEF_MAX_LIFEPOINTS;
		lifePoints = maxLifePoints;
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

	@Override
	public int getLifePoints()
	{
		return lifePoints;
	}

	@Override
	public void setLifePoints(final int p_lifePoints)
	{
		lifePoints = p_lifePoints;
	}

	@Override
	public void setMaxLifePoints(final int p_lifePoints)
	{
		if(p_lifePoints < 0)
			throw new IllegalArgumentException(String.format("Rocket max lifepoints cannot be negative: %d.", p_lifePoints));
		maxLifePoints = p_lifePoints;
		incLifePoints(0);
	}

	@Override
	public int getMaxLifePoints()
	{
		return maxLifePoints;
	}

	@Override
	public void decLifePoints(int p_value)
	{
		lifePoints -= p_value;
	}

	@Override
	public void incLifePoints(int p_value)
	{
		lifePoints += p_value;
		if(lifePoints > maxLifePoints)
			lifePoints = maxLifePoints;
	}

	@Override
	public boolean isDead()
	{
		return lifePoints <= 0;
	}

}
