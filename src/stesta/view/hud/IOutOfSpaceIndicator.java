package stesta.view.hud;

import lib.graphics.IDrawable;
import lib.utils.integer.Dimension2DI;

public interface IOutOfSpaceIndicator extends IDrawable, IUpdateable{

	void assignDimension(final Dimension2DI p_dimension);
	Dimension2DI getDimension();
}
