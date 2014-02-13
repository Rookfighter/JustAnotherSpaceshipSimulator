package stesta.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class RocketProjectileOn1Sprite extends Sprite {

	public RocketProjectileOn1Sprite()
	{
		super(ProjectileSpriteSheet.getInstance(), 2);
		getDimension().set(32,32);
	}

}
