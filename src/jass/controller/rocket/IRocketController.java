package jass.controller.rocket;

import jass.controller.IObjectController;
import jass.entities.objects.IRocket;

public interface IRocketController extends IObjectController<IRocket> {
	
	void turnRight();
	void stopTurnRight();
	void turnLeft();
	void stopTurnLeft();
	
	void accelerate();
	void stopAccelerate();
	
	void fireLaserCannon();
}
