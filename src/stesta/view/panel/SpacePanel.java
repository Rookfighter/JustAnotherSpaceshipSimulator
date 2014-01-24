package stesta.view.panel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import stesta.controller.rocket.IRocketController;
import lib.graphics.panel.GamePanel;
import lib.utils.integer.Dimension2DI;

public class SpacePanel extends GamePanel {

	private static final long serialVersionUID = -8839867439410500078L;
	private IRocketController observedController;

	public SpacePanel(final IRocketController p_player)
	{
		super();
		observedController = p_player;
		setDrawListGenerator(new SpaceDrawListGenerator2(observedController));
		this.addComponentListener(new SpacePanelListener());
	}
	
	public IRocketController getObservedController()
	{
		return observedController;
	}

	private class SpacePanelListener implements ComponentListener {

		@Override
		public void componentHidden(ComponentEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent e)
		{
			((SpaceDrawListGenerator2) getDrawListGenerator()).setDimension(new Dimension2DI(getWidth(), getHeight()));
			
		}

		@Override
		public void componentShown(ComponentEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}
