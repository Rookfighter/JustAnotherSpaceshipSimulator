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
		//System.out.printf("POS: %.2f/%.2f\n", getControlledObject().getPosition().x, getControlledObject().getPosition().y);
	}
	
	private void executeControls()
	{
		synchronized(controlQueue)
		{
			while(!controlQueue.isEmpty())
				controlQueue.poll().executeLogics();
		}
	}
	
	@Override
	public void turnRight()
	{
		IRocketControl control = new RocketTurnRight(getControlledObject());
		control.setSpace(getSpace());
		addControl(control);
	}

	@Override
	public void turnLeft()
	{
		IRocketControl control = new RocketTurnLeft(getControlledObject());
		control.setSpace(getSpace());
		addControl(control);
	}

	@Override
	public void accelerate()
	{
		IRocketControl control = new RocketAccelerate(getControlledObject());
		control.setSpace(getSpace());
		addControl(control);
	}
	
	@Override
	public void stopTurning()
	{
		IRocketControl control = new RocketStopTurning(getControlledObject());
		control.setSpace(getSpace());
		addControl(control);
	}
	
	private void addControl(final IRocketControl p_control)
	{
		synchronized(controlQueue)
		{
			controlQueue.add(p_control);
		}
	}
	
	

	

}
