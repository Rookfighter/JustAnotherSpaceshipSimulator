package stesta.controller.rocket.classes;

import java.util.LinkedList;
import java.util.Queue;

import stesta.controller.AObjectController;
import stesta.controller.rocket.IRocketControl;
import stesta.controller.rocket.IRocketController;
import stesta.entities.objects.IRocket;

public class RocketController extends AObjectController<IRocket> implements IRocketController {

	private Queue<IRocketControl> controlQueue;
	
	public RocketController()
	{
		controlQueue = new LinkedList<IRocketControl>();
	}
		
	@Override
	public void executeLogics()
	{
		executeControls();
		turnRocket();
	}
	
	private void executeControls()
	{
		synchronized(controlQueue)
		{
			while(!controlQueue.isEmpty())
				controlQueue.poll().executeLogics();
		}
	}
	
	private void turnRocket()
	{
		float directionDiff = getDirectionDiff();
		
		float angularVelocity = directionDiff / getSpace().getTimeStep();
		getControlledObject().getBody().setAngularVelocity(angularVelocity);
	}
	
	private float getDirectionDiff()
	{
		return getControlledObject().getBody().getAngle() - getControlledObject().getDirection();
	}
	
	@Override
	public void turnRight()
	{
		addControl(new RocketTurnRight(getControlledObject()));
	}

	@Override
	public void turnLeft()
	{
		addControl(new RocketTurnLeft(getControlledObject()));
	}

	@Override
	public void accelerate()
	{
		addControl(new RocketAccelerate(getControlledObject()));
	}
	
	private void addControl(final IRocketControl p_control)
	{
		synchronized(controlQueue)
		{
			controlQueue.add(p_control);
		}
	}
	
	

	

}
