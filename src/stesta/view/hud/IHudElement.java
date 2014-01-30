package stesta.view.hud;

import lib.graphics.IDrawable;
import lib.utils.integer.Dimension2DI;

public interface IHudElement extends IDrawable, IUpdateable {
	
	void setDimension(final Dimension2DI p_dimension);
	Dimension2DI getDimension();

}
