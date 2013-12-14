package stesta.view.panel;

import lib.graphics.IDrawable;
import stesta.view.IDrawableFactory;
import stesta.view.sprites.StarSprite;

public class StarSpriteFactory implements IDrawableFactory{

	@Override
	public IDrawable createDrawable()
	{
		return new StarSprite();
	}

}
