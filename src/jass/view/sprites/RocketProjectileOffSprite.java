package jass.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class RocketProjectileOffSprite extends Sprite {

	public RocketProjectileOffSprite()
	{
		super(ProjectileSpriteSheet.getInstance(), 1);
		getDimension().set(32,32);
	}

}
