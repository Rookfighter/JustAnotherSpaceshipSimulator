package stesta.controller.rocket.classes;

import stesta.controller.rocket.ARocketControl;
import stesta.entities.objects.IRocket;

public class RocketTurnLeft extends ARocketControl{
	
	private static final float TURN_RAD = (float) Math.toRadians(7);

	public RocketTurnLeft(IRocket p_rocket)
	{
		super(p_rocket);
	}
	
	@Override
	public void executeLogics()
	{
		float angularVelocity = TURN_RAD / getSpace().getTimeStep();
		
		getControlledObject().getBody().setAngularVelocity(-angularVelocity);
	}

}
