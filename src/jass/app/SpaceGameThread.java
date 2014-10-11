package jass.app;

import jass.controller.IGameController;
import lib.app.GameThread;

public class SpaceGameThread extends GameThread{

	private IGameController gameController;
	
	@Override
	protected void executeLogics()
	{
		gameController.executeLogics();
	}
	
	public void setGameController(final IGameController p_gameController)
	{
		gameController = p_gameController;
	}

}
