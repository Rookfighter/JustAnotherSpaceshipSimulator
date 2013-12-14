package stesta.view.sprites;

import lib.graphics.sprites.classes.Sprite;

public class StarSprite extends Sprite{
	
	public StarSprite()
	{
		super(StarSpriteSheet.getInstance(), 0);
		getDimension().set(8,8);
		setDrawOrder(Integer.MAX_VALUE);
	}

}
