package stesta.view.panel;

import lib.graphics.IDrawable;
import stesta.view.IDrawableFactory;
import stesta.view.sprites.RocketOffSprite;

public class RocketSpriteFactory implements IDrawableFactory{

	@Override
	public IDrawable createDrawable()
	{
		return new RocketOffSprite();
	}

}
