package stesta.controller.rocket.classes;

import stesta.controller.rocket.ARocketControl;
import stesta.entities.objects.IRocket;

public class RocketTurnLeft extends ARocketControl{
	
	private static final float TURN_RAD = (float) Math.toRadians(30);

	public RocketTurnLeft(IRocket p_rocket)
	{
		super(p_rocket);
	}
	
	@Override
	public void executeLogics()
	{
		getRocket().setDirection(getRocket().getDirection() - TURN_RAD);
	}

}