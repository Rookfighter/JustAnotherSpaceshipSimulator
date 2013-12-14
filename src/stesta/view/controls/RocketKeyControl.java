package stesta.view.controls;

import stesta.controller.rocket.IRocketController;
import lib.control.classes.KeyControl;

public class RocketKeyControl extends KeyControl {

	
	public RocketKeyControl(final IRocketController p_rocketController)
	{
		super();
		RocketKeyContainer container = new RocketKeyContainer();
		onKeyDown(new RocketActionDown(container));
		onKeyUp(new RocketActionUp(container));
		setRocketController(p_rocketController);
	}
	
	private void setRocketController(final IRocketController p_rocketController)
	{
		((ARocketAction)getOnDownAction()).setRocketController(p_rocketController);
		((ARocketAction)getOnUpAction()).setRocketController(p_rocketController);
	}
}
