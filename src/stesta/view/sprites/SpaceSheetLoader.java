package stesta.view.sprites;

import lib.graphics.sprites.classes.SpriteSheetLoader;

public class SpaceSheetLoader extends SpriteSheetLoader{
	
	public SpaceSheetLoader()
	{
		super(1);
		addSpriteSheet(RocketSpriteSheet.getInstance());
	}

}
