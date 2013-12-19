package stesta.view.panel;

import lib.graphics.IDrawable;
import stesta.view.IDrawableFactory;
import stesta.view.sprites.AsteroidSprite;

public class AsteroidSpriteFactory implements IDrawableFactory{

	@Override
	public IDrawable createDrawable()
	{
		return new AsteroidSprite();
	}

}
