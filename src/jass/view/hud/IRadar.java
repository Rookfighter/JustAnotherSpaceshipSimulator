package jass.view.hud;

import lib.utils.integer.Position2DI;

public interface IRadar extends IHudElement{
	
	void setRadius(final int p_radius);
	int getRadius();
	
	void setPosition(final Position2DI p_position);
	Position2DI getPosition();
	
	void setRange(final float p_range);
	float getRange();

}
