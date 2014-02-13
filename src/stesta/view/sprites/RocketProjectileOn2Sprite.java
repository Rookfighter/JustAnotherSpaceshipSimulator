package stesta.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class RocketProjectileOn2Sprite extends Sprite {

	public RocketProjectileOn2Sprite()
	{
		super(ProjectileSpriteSheet.getInstance(), 3);
		getDimension().set(32,32);
	}

}
