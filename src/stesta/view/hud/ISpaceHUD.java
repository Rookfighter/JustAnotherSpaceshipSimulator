package stesta.view.hud;

import lib.graphics.IDrawable;
import lib.utils.integer.Dimension2DI;

public interface ISpaceHUD extends IDrawable{

	Dimension2DI getDimension();
	void setDimension(final Dimension2DI p_dimension);
	
	void update();
}
