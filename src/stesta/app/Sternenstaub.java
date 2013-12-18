package stesta.app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jbox2d.common.Vec2;

import stesta.app.factories.GameFactory;
import lib.app.GameThread;

public class Sternenstaub {

	public static void main(String[] args)
	{
		Sternenstaub app = new Sternenstaub();
		app.initializeGame();
		app.startGame();
		app.waitForThread();
	}
	
	
	private GameFactory gameFactory;
	
	private Sternenstaub()
	{
		gameFactory = new GameFactory();
	}
	
	private void initializeGame()
	{
		gameFactory.getViewFactory().getControllerFactory().getEntityFactory().createRocket().getBody().setTransform(new Vec2(10.0f,10.0f), 0);
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
