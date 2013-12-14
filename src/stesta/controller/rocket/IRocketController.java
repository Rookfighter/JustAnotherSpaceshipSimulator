package stesta.controller.rocket;

import stesta.controller.IObjectController;
import stesta.entities.objects.IRocket;

public interface IRocketController extends IObjectController<IRocket> {
	
	void turnRight();
	void turnLeft();
	void stopTurning();
	
	void accelerate();
}
