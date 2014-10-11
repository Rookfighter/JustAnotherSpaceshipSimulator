package jass.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class RocketOffSprite extends Sprite{

	public RocketOffSprite()
	{
		super(RocketSpriteSheet.getInstance(), 0);
		getDimension().set(32,32);
	}
	
}
