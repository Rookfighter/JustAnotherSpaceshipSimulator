package stesta.controller.factories;

import stesta.controller.IGameController;
import stesta.controller.ISpaceController;
import stesta.controller.classes.GameController;
import stesta.controller.classes.SpaceController;
import stesta.controller.rocket.IRocketController;
import stesta.controller.rocket.classes.RocketController;
import stesta.entities.factories.EntityFactory;

public class ControllerFactory {

	private EntityFactory entityFactory;
	private ISpaceController spaceController;
	private IGameController gameController;
	
	public ControllerFactory()
	{
		entityFactory = new EntityFactory();
		createSpaceController();
		createGameController();
	}
	
	private void createSpaceController()
	{
		spaceController = new SpaceController();
		spaceController.setSpace(entityFactory.getSpace());
	}
	
	private void createGameController()
	{
		gameController = new GameController();
		gameController.setSpaceController(spaceController);
	}
	
	public EntityFactory getEntityFactory()
	{
		return entityFactory;
	}
	
	public IRocketController createRocketController()
	{
		IRocketController result = new RocketController();
		result.setControlledObject(entityFactory.createRocket());
		result.setSpace(entityFactory.getSpace());
		
		gameController.addController(result);
		
		return result;
	}
	
	public ISpaceController getSpaceController()
	{
		return spaceController;
	}
	
	public IGameController getGameController()
	{
		return gameController;
	}
}
