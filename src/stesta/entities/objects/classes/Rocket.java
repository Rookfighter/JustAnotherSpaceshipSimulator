package stesta.entities.objects.classes;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import stesta.entities.objects.AMovingSpaceObject;
import stesta.entities.objects.IRocket;

public class Rocket extends AMovingSpaceObject implements IRocket{

	private float direction;
	
	public Rocket(World p_world)
	{
		super(p_world);
		setDirection(0.0f);
	}

	@Override
	protected BodyDef getBodyDef()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected FixtureDef getFixtureDef()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getDirection()
	{
		return direction;
	}

	@Override
	public void setDirection(float p_radian)
	{
		direction = p_radian;
	}
	
	

}
