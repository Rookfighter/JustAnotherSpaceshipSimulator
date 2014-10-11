package jass.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class CopyOfRocketProjectileOn2Sprite extends Sprite {

	public CopyOfRocketProjectileOn2Sprite()
	{
		super(ProjectileSpriteSheet.getInstance(), 2);
		getDimension().set(32,32);
	}

}
