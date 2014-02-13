package stesta.entities.weapons.classes;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import stesta.entities.weapons.AProjectile;

public class LaserShot extends AProjectile {

	private static final int DEF_DAMAGE = 225;
	private static final float DEF_LENGTH = 0.4f;
	private static final float DEF_WIDTH = DEF_LENGTH / 4.0f;
	private static final float DEF_DENSITY = 0.1f;
	private static final float DEF_RESTITUTION = 0.1f;
	private static final float DEF_FRICTION = 0.1f;
	private static final float DEF_MAX_VELOCITY = 40.0f;
	
	public LaserShot()
	{
		super();
		setDamage(DEF_DAMAGE);
		setLength(DEF_LENGTH);
		setWidth(DEF_WIDTH);
		setMaxVelocity(DEF_MAX_VELOCITY);
	}
	
	@Override
	protected BodyDef getBodyDef()
	{
		BodyDef result = new BodyDef();
		result.position.set(0.0f, 0.0f);
		result.type = BodyType.DYNAMIC;
		result.allowSleep = true;
		result.bullet = true;
		
		return result;
	}

	@Override
	protected FixtureDef[] getFixtureDef()
	{
		FixtureDef[] result = new FixtureDef[3];
		result[0] = getCenterCircle(); 
		result[1] = getUpperCircle();
		result[2] = getLowerCircle();
		
		return result;
	}
	
	private FixtureDef getCenterCircle()
	{
		FixtureDef result = new FixtureDef();
		CircleShape cs = new CircleShape();
		cs.setRadius(getCenterRadius());
		
		result.shape = cs;
		result.density = DEF_DENSITY;
		result.restitution = DEF_RESTITUTION;
		result.friction = DEF_FRICTION;
		
		return result;
	}
	
	private float getCenterRadius()
	{
		return getWidth() / 2.0f;
	}
	
	private FixtureDef getUpperCircle()
	{
		FixtureDef result = new FixtureDef();
		CircleShape cs = new CircleShape();
		cs.setRadius(getSmallCircleRadius());
		cs.m_p.set(0, getSmallCircleOffset());
		
		result.shape = cs;
		result.density = DEF_DENSITY;
		result.restitution = DEF_RESTITUTION;
		result.friction = DEF_FRICTION;
		
		return result;
	}
	
	private FixtureDef getLowerCircle()
	{
		FixtureDef result = new FixtureDef();
		CircleShape cs = new CircleShape();
		cs.setRadius(getSmallCircleRadius());
		cs.m_p.set(0, -getSmallCircleOffset());
		
		result.shape = cs;
		result.density = DEF_DENSITY;
		result.restitution = DEF_RESTITUTION;
		result.friction = DEF_FRICTION;
		
		return result;
	}
	
	private float getSmallCircleRadius()
	{
		//length - 2 * centerRadius; 2 * centerRad == width
		//-> /2 because 2 circles at edge
		// /2 because radius is only half
		return (getLength() - getWidth()) / 4;
	}
	
	private float getSmallCircleOffset()
	{
		return getSmallCircleRadius() + getCenterRadius();
	}

}
