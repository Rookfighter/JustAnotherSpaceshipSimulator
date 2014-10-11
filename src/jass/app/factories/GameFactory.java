package jass.app.factories;

import jass.app.SpaceGameThread;
import jass.view.factories.ViewFactory;
import lib.app.GameThread;

public class GameFactory {

	private ViewFactory viewFactory;
	private SpaceGameThread gameThread;
	
	public GameFactory()
	{
		createComponents();
		initializeGameThread();
	}
	
	private void createComponents()
	{
		viewFactory = new ViewFactory();
		gameThread = new SpaceGameThread();
	}
	
	private void initializeGameThread()
	{
		gameThread.setGameController(viewFactory.getControllerFactory().getGameController());
		gameThread.addRedrawable(viewFactory.getGamePanelManager());
		gameThread.addRedrawable(viewFactory.getStatusPanel());
		gameThread.setPriority(Thread.MAX_PRIORITY);
	}
	
	public ViewFactory getViewFactory()
	{
		return viewFactory;
	}
	
	public GameThread getGameThread()
	{
		return gameThread;
	}
	
}
