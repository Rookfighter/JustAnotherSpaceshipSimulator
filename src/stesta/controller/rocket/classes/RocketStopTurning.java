package stesta.controller.rocket.classes;

import stesta.controller.rocket.ARocketControl;
import stesta.entities.objects.IRocket;

public class RocketStopTurning extends ARocketControl{

	public RocketStopTurning(IRocket p_rocket)
	{
		super(p_rocket);
	}

	@Override
	public void executeLogics()
	{
		getControlledObject().getBody().setAngularVelocity(0);
	}
	
	

}
