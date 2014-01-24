package stesta.controller.rocket.classes;


import org.jbox2d.common.Vec2;

import stesta.controller.AObjectController;
import stesta.controller.rocket.IRocketController;
import stesta.entities.objects.IRocket;

public class RocketController extends AObjectController<IRocket> implements IRocketController {

	private static final float DEF_LEFT_ANG_VEL = (float) Math.toRadians(7);
	private static final float DEF_RIGHT_ANG_VEL = (float) Math.toRadians(7);
	
	private float angularVelocity;
	
	public RocketController()
	{
		angularVelocity = 0;
	}
		
	@Override
	public void executeLogics()
	{
		accelerateRocket();
		turnRocket();
	}

	private void accelerateRocket()
	{
		float dx = (float) Math.cos(getControlledObject().getDirection()) * getControlledObject().getAccelerateForce();
		float dy = (float) Math.sin(getControlledObject().getDirection()) * getControlledObject().getAccelerateForce();
		Vec2 force = new Vec2(dx,dy);
		getControlledObject().getBody().applyForceToCenter(force);
	}
	
	private void turnRocket()
	{
		float angVel = angularVelocity / getSpace().getTimeStep();
		getControlledObject().getBody().setAngularVelocity(angVel);
	}

	@Override
	public void turnRight()
	{
		angularVelocity = DEF_RIGHT_ANG_VEL;
	}

	@Override
	public void turnLeft()
	{
		angularVelocity = -DEF_LEFT_ANG_VEL;
	}

	@Override
	public void accelerate()
	{
		getControlledObject().setAccelerateForce(getControlledObject().getMaxAccelerateForce());
	}
	
	@Override
	public void stopTurnRight()
	{
		angularVelocity = 0;
	}

	@Override
	public void stopTurnLeft()
	{
		angularVelocity = 0;
	}

	@Override
	public void stopAccelerate()
	{
		getControlledObject().setAccelerateForce(0);
	}
}
