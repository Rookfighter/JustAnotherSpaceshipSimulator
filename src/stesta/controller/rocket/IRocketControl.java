package stesta.controller.rocket;

import stesta.controller.IController;
import stesta.entities.objects.IRocket;

public interface IRocketControl extends IController{
	
	void setRocket(final IRocket p_rocket);
	IRocket getRocket();
}
