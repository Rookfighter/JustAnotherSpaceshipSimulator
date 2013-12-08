package stesta.controller;

import java.util.List;

public interface IGameController extends IController {

	ISpaceController getSpaceController();
	void setSpaceController(final ISpaceController p_spaceController);
	
	List<IController> getControllerList();
	
	void addController(final IController p_controller);
	void removeController(final IController p_controller);
	void clearControllers();
	
}
