package stesta.controller.factories;

import stesta.controller.IController;
import stesta.controller.IGameController;
import stesta.controller.ISpaceContactListener;
import stesta.controller.ISpaceController;
import stesta.controller.classes.GameController;
import stesta.controller.classes.GlobalAsteroidController;
import stesta.controller.classes.SpaceContactListener;
import stesta.controller.classes.SpaceController;
import stesta.controller.rocket.IRocketController;
import stesta.controller.rocket.classes.RocketController;
import stesta.entities.factories.EntityFactory;

public class ControllerFactory {

	private EntityFactory entityFactory;
	private ISpaceController spaceController;
	private IGameController gameController;
	private ISpaceContactListener contactListener;
	private IController asteroidController;
	
	public ControllerFactory()
	{
		entityFactory = new EntityFactory();
		createSpaceController();
		createGameController();
		createContactListener();
		createAsteroidController();
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
	
	private void createContactListener()
	{
		contactListener = new SpaceContactListener();
		contactListener.setSpace(entityFactory.getSpace());
		entityFactory.getSpace().getPhysicsWorld().setContactListener(contactListener);
	}
	
	private void createAsteroidController()
	{
		asteroidController = new GlobalAsteroidController(entityFactory);
		gameController.addController(asteroidController);
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
	
	public IController getAsteroidController()
	{
		return asteroidController;
	}
}
