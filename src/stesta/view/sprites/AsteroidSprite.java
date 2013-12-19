package stesta.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class AsteroidSprite extends Sprite{

	public AsteroidSprite()
	{
		super(AsteroidSpriteSheet.getInstance(), 0);
		getDimension().set(32,32);
	}
}
