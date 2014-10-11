package jass.view.hud;

import lib.utils.integer.Dimension2DI;
import lib.utils.integer.Position2DI;

public interface ILifebar extends IHudElement {
	
	void setMax(final int p_value);
	void setValue(final int p_value);
	
	void setPosition(final Position2DI p_position);
	Position2DI getPosition();
	
	void setDimension(final Dimension2DI p_dimension);
	Dimension2DI getDimension();

}
