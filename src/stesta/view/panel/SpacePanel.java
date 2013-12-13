package stesta.view.panel;

import stesta.controller.rocket.IRocketController;
import lib.graphics.panel.GamePanel;

public class SpacePanel extends GamePanel {

	private static final long serialVersionUID = -8839867439410500078L;

	public SpacePanel(final IRocketController p_player)
	{
		super();
		setDrawListGenerator(new SpaceDrawListGenerator(p_player));
	}
	
}
