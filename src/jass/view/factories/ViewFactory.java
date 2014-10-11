package jass.view.factories;

import jass.controller.factories.ControllerFactory;
import jass.controller.rocket.IRocketController;
import jass.view.controls.RocketKeyControl;
import jass.view.frame.SpaceFrame;
import jass.view.panel.SpacePanel;
import jass.view.sprites.SpaceSheetLoader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import lib.graphics.components.StatusPanel;
import lib.graphics.frame.GameFrame;
import lib.graphics.panel.GamePanelManager;

public class ViewFactory {
	
	private static final boolean SPLIT_SCREEN = false;

	private ControllerFactory controllerFactory;
	
	private JPanel gridPanel;
	private SpacePanel spacePanel;
	private SpacePanel spacePanel2;
	private GamePanelManager gamePanelManager;
	private StatusPanel statusPanel;
	private SpaceFrame spaceFrame;
	private RocketKeyControl keyControl;
	
	public ViewFactory()
	{
		createComponents();
		initSpacePanel();
		initGridPanel();
		initSpacePanelManager();
		initSpaceFrame();
	}

	private void createComponents()
	{
		controllerFactory = new ControllerFactory();
		gridPanel = new JPanel();
		
		IRocketController controller = controllerFactory.createRocketController();
		spacePanel = new SpacePanel(controller);
		keyControl = new RocketKeyControl(controller);
		
		controller = controllerFactory.createRocketController();
		spacePanel2 = new SpacePanel(controller);
		
		gamePanelManager = new GamePanelManager();
		spaceFrame = new SpaceFrame(); 
		
		statusPanel = new StatusPanel();
		
	}
	
	private void initSpacePanel()
	{
		spacePanel.setBackground(Color.BLACK);
		spacePanel2.setBackground(Color.BLACK);
	}
	
	private void initGridPanel()
	{
		if(SPLIT_SCREEN)
			gridPanel.setLayout(new GridLayout(1,2));
		else
			gridPanel.setLayout(new GridLayout(1,1));
		gridPanel.setPreferredSize(new Dimension(800,600));
		gridPanel.add(spacePanel);
		if(SPLIT_SCREEN)
			gridPanel.add(spacePanel2);
	}
	
	private void initSpacePanelManager()
	{
		gamePanelManager.add(spacePanel);
		if(SPLIT_SCREEN)
			gamePanelManager.add(spacePanel2);
	}

	private void initSpaceFrame()
	{
		spaceFrame.setTitle("Just Another Spaceship Simulator");
		spaceFrame.add(gridPanel, BorderLayout.CENTER);
		spaceFrame.add(statusPanel, BorderLayout.EAST);
		spaceFrame.setKeyControl(keyControl);
		spaceFrame.setSpriteSheetLoader(new SpaceSheetLoader());
		spaceFrame.pack();
	}
	
	public GameFrame getGameFrame()
	{
		return spaceFrame;
	}
	
	public JPanel getGridPanel()
	{
		return gridPanel;
	}
	
	public SpacePanel getSpacePanel()
	{
		return spacePanel;
	}
	
	public SpacePanel getSpacePanel2()
	{
		return spacePanel2;
	}
	
	public GamePanelManager getGamePanelManager()
	{
		return gamePanelManager;
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
