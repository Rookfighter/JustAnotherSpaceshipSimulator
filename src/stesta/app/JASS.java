package stesta.app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jbox2d.common.Vec2;

import stesta.app.factories.GameFactory;
import lib.app.GameThread;

public class JASS {

	public static void main(String[] args)
	{
		JASS app = new JASS();
		app.initializeGame();
		app.startGame();
		app.waitForThread();
	}
	
	private static final int ASTEROID_COUNT = 400;
	private GameFactory gameFactory;
	
	private JASS()
	{
		gameFactory = new GameFactory();
	}
	
	private void initializeGame()
	{
		gameFactory.getViewFactory().getControllerFactory().getEntityFactory().generateRandomAsteroids(ASTEROID_COUNT);
		gameFactory.getViewFactory().getSpacePanel2().getObservedController().getControlledObject().setPosition(new Vec2(50,50));
	}
	
	private void startGame()
	{
		gameFactory.getViewFactory().getGameFrame().loadSpriteSheets();
		gameFactory.getViewFactory().getGameFrame().setVisible(true);
		gameFactory.getViewFactory().getGameFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFactory.getGameThread().start();
	}
	
	private void waitForThread()
	{
		try
		{
			gameFactory.getGameThread().join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		if(gameFactory.getGameThread().getExitStatus() == GameThread.EXCEPTION_OCCURED)
		{
			Exception e = gameFactory.getGameThread().getException();
			String msg = String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage());
			JOptionPane.showMessageDialog(gameFactory.getViewFactory().getGameFrame(), msg, e.getClass().getSimpleName() , JOptionPane.OK_OPTION);
			e.printStackTrace();
		}
	}

}
