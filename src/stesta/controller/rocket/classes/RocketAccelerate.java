package stesta.controller.rocket.classes;

import org.jbox2d.common.Vec2;

import stesta.controller.rocket.ARocketControl;
import stesta.entities.objects.IRocket;

public class RocketAccelerate extends ARocketControl {

	private static final float ACC_FORCE = 1.0f;
	
	public RocketAccelerate(IRocket p_rocket)
	{
		super(p_rocket);
	}

	@Override
	public void executeLogics()
	{
		float dx = (float) Math.cos(getRocket().getDirection()) * ACC_FORCE;
		float dy = (float) Math.sin(getRocket().getDirection()) * ACC_FORCE;
		Vec2 force = new Vec2(dx,dy);
		getRocket().getBody().applyForceToCenter(force);
	}

}
