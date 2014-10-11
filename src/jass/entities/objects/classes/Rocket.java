package jass.entities.objects.classes;

import jass.entities.objects.AHitableMovingSpaceObject;
import jass.entities.objects.IRocket;
import jass.entities.weapons.IWeapon;
import jass.entities.weapons.classes.LaserCannon;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Rocket extends AHitableMovingSpaceObject implements IRocket{

	public static final float DEF_RADIUS = 0.5f;
	public static final int LASERCANNON = 0;
	
	private static final float DEF_FRICTION = 0.3f;
	private static final float DEF_DENSITY = 0.6f;
	private static final float DEF_RESTITUTION = 0.5f;
	private static final float MAX_ACCELERATE_FORCE = 10.0f;
	private static final int DEF_MAX_LIFEPOINTS = 1000;
	private static final int DEF_WEAPON_COUNT = 2;
	
	private float radius;
	private float accelerateForce;
	
	private List<IWeapon> weapons;
	
	public Rocket()
	{
		super();
		radius = DEF_RADIUS;
		accelerateForce = 0;
		setMaxLifePoints(DEF_MAX_LIFEPOINTS);
		setLifePoints(getMaxLifePoints());
		weapons = new ArrayList<IWeapon>(DEF_WEAPON_COUNT);
		initWeapons();
	}
	
	private void initWeapons()
	{
		weapons.add(new LaserCannon());
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
		
		FixtureDef result[] = new FixtureDef[1];
		result[0] = new FixtureDef();
		CircleShape cs = new CircleShape();
		cs.setRadius(radius);
		
		result[0].shape = cs;
		result[0].density = DEF_DENSITY;
		result[0].restitution = DEF_RESTITUTION;
		result[0].friction = DEF_FRICTION;
		
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
	public IWeapon getWeapon(final int p_idx)
	{
		return weapons.get(p_idx);
	}

	@Override
	public List<IWeapon> getWeapons()
	{
		return weapons;
	}

}
