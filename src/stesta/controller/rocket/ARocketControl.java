package stesta.controller.rocket;

import stesta.controller.AObjectController;
import stesta.entities.objects.IRocket;

public abstract class ARocketControl extends AObjectController<IRocket> implements IRocketControl  {

	public ARocketControl(final IRocket p_rocket)
	{
		setControlledObject(p_rocket);
	}
	
}
