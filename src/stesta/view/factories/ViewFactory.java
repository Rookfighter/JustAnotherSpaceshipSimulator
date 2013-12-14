package stesta.view.factories;

import java.awt.BorderLayout;
import java.awt.Color;

import lib.graphics.components.StatusPanel;
import lib.graphics.frame.GameFrame;
import lib.graphics.panel.GamePanel;
import lib.utils.integer.Dimension2DI;
import stesta.controller.factories.ControllerFactory;
import stesta.controller.rocket.IRocketController;
import stesta.view.controls.RocketKeyControl;
import stesta.view.frame.SpaceFrame;
import stesta.view.panel.SpacePanel;
import stesta.view.sprites.SpaceSheetLoader;

public class ViewFactory {

	private ControllerFactory controllerFactory;
	private SpacePanel spacePanel;
	private StatusPanel statusPanel;
	private SpaceFrame spaceFrame;
	private RocketKeyControl keyControl;
	
	public ViewFactory()
	{
		createComponents();
		initSpacePanel();
		initSpaceFrame();
	}

	private void createComponents()
	{
		controllerFactory = new ControllerFactory();
		IRocketController controller = controllerFactory.createRocketController();
		spacePanel = new SpacePanel(controller);
		spaceFrame = new SpaceFrame(); 
		keyControl = new RocketKeyControl(controller);
		statusPanel = new StatusPanel();
	}
	
	private void initSpacePanel()
	{
		spacePanel.setBackground(Color.BLACK);
		spacePanel.setPreferredSize(new Dimension2DI(800,600));
	}

	private void initSpaceFrame()
	{
		spaceFrame.add(spacePanel, BorderLayout.CENTER);
		spaceFrame.add(statusPanel, BorderLayout.EAST);
		spaceFrame.setKeyControl(keyControl);
		spaceFrame.setSpriteSheetLoader(new SpaceSheetLoader());
		spaceFrame.pack();
	}
	
	public GameFrame getGameFrame()
	{
		return spaceFrame;
	}
	
	public GamePanel getGamePanel()
	{
		return spacePanel;
	}
	
	public StatusPanel getStatusPanel()
	{
		return statusPanel;
	}
	
	public ControllerFactory getControllerFactory()
	{
		return controllerFactory;
	}

	
}
