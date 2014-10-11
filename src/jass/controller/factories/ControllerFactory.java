package jass.controller.factories;

import jass.controller.IController;
import jass.controller.IGameController;
import jass.controller.ISpaceContactListener;
import jass.controller.ISpaceController;
import jass.controller.classes.GameController;
import jass.controller.classes.GlobalAsteroidController;
import jass.controller.classes.SpaceContactListener;
import jass.controller.classes.SpaceController;
import jass.controller.rocket.IRocketController;
import jass.controller.rocket.classes.RocketController;
import jass.entities.factories.EntityFactory;

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
