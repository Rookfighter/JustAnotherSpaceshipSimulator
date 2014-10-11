package jass.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class LaserShotSprite extends Sprite{

	public LaserShotSprite()
	{
		super(ProjectileSpriteSheet.getInstance(), 0);
		getDimension().set(32,32);
	}

}
