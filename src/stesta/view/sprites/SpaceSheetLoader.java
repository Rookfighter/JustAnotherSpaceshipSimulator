package stesta.view.sprites;

import lib.graphics.sprites.classes.SpriteSheetLoader;

public class SpaceSheetLoader extends SpriteSheetLoader{
	
	public SpaceSheetLoader()
	{
		super(2);
		addSpriteSheet(RocketSpriteSheet.getInstance());
		addSpriteSheet(AsteroidSpriteSheet.getInstance());
	}

}
