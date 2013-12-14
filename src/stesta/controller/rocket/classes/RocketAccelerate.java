package stesta.controller.rocket.classes;

import org.jbox2d.common.Vec2;

import stesta.controller.rocket.ARocketControl;
import stesta.entities.objects.IRocket;

public class RocketAccelerate extends ARocketControl {

	private static final float ACC_FORCE = 10.0f;
	
	public RocketAccelerate(IRocket p_rocket)
	{
		super(p_rocket);
	}

	@Override
	public void executeLogics()
	{
		float dx = (float) Math.cos(getControlledObject().getDirection()) * ACC_FORCE;
		float dy = (float) Math.sin(getControlledObject().getDirection()) * ACC_FORCE;
		Vec2 force = new Vec2(dx,dy);
		getControlledObject().getBody().applyForceToCenter(force);
	}

}
