package stesta.controller.classes;

import java.util.LinkedList;
import java.util.List;

import stesta.controller.IController;
import stesta.controller.IGameController;
import stesta.controller.ISpaceController;

public class GameController implements IGameController{

	private ISpaceController spaceController;
	private List<IController> controllerList;
	
	public GameController()
	{
		this(null);
	}
	
	public GameController(final ISpaceController p_spaceController)
	{
		setSpaceController(p_spaceController);
		controllerList = new LinkedList<IController>();
	}

	@Override
	public void executeLogics()
	{
		executeControllers();
		spaceController.executeLogics();
	}
	
	private void executeControllers()
	{
		for(IController controller : controllerList)
			controller.executeLogics();
	}

	@Override
	public ISpaceController getSpaceController()
	{
		return spaceController;
	}

	@Override
	public void setSpaceController(ISpaceController p_spaceController)
	{
		spaceController = p_spaceController;
	}

	@Override
	public List<IController> getControllerList()
	{
		return controllerList;
	}

	@Override
	public void addController(IController p_controller)
	{
		if(p_controller instanceof ISpaceController)
			throw new IllegalArgumentException("GameController cannot add ISpaceController object.");
		controllerList.add(p_controller);
	}

	@Override
	public void removeController(IController p_controller)
	{
		controllerList.remove(p_controller);
		
	}

	@Override
	public void clearControllers()
	{
		controllerList.clear();
	}
	
	
	
}
