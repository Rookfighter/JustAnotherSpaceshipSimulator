package stesta.view.factories;

import java.awt.BorderLayout;
import java.awt.Color;

import lib.graphics.components.StatusPanel;
import lib.utils.integer.Dimension2DI;
import stesta.controller.factories.ControllerFactory;
import stesta.view.frame.SpaceFrame;
import stesta.view.panel.SpacePanel;

public class ViewFactory {

	private ControllerFactory controllerFactory;
	private SpacePanel spacePanel;
	private StatusPanel statusPanel;
	private SpaceFrame spaceFrame;
	
	public ViewFactory()
	{
		createComponents();
		initSpacePanel();
		initSpaceFrame();
	}

	private void createComponents()
	{
		controllerFactory = new ControllerFactory();
		spacePanel = new SpacePanel(controllerFactory.createRocketController());
		spaceFrame = new SpaceFrame(); 
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
		spaceFrame.pack();
	}

	
}
