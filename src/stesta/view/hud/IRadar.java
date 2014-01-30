package stesta.view.hud;

import stesta.controller.rocket.IRocketController;
import lib.graphics.IDrawable;
import lib.utils.integer.Position2DI;

public interface IRadar extends IDrawable, IUpdateable{
	
	void setRadius(final int p_radius);
	int getRadius();
	
	void setPosition(final Position2DI p_position);
	Position2DI getPosition();
	
	void setPlayer(final IRocketController p_player);
	
	void setRange(final float p_range);
	float getRange();

}
