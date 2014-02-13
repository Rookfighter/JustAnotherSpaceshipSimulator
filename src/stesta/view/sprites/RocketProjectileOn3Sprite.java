package stesta.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class RocketProjectileOn3Sprite extends Sprite {

	public RocketProjectileOn3Sprite()
	{
		super(ProjectileSpriteSheet.getInstance(), 3);
		getDimension().set(32, 32);
	}

}
