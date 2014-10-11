package jass.view.controls;

import jass.controller.rocket.IRocketController;
import lib.control.IKeyAction;

public abstract class ARocketAction implements IKeyAction{

	private IRocketController rocketController;
	private RocketKeyContainer rocketContainer;
	
	public ARocketAction(final RocketKeyContainer p_keyContainer)
	{
		setRocketKeyContainer(p_keyContainer);
	}
	
	public void setRocketKeyContainer(final RocketKeyContainer p_keyContainer)
	{
		rocketContainer = p_keyContainer;
	}
	
	public void setRocketController(final IRocketController p_rocketController)
	{
		rocketController = p_rocketController;
	}
	
	public RocketKeyContainer getRocketKeyContainer()
	{
		return rocketContainer;
	}
	
	public IRocketController getRocketController()
	{
		return rocketController;
	}
	
}
