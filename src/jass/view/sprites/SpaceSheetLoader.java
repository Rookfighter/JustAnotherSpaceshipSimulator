package jass.view.sprites;

import lib.graphics.sprites.classes.SpriteSheetLoader;

public class SpaceSheetLoader extends SpriteSheetLoader{
	
	public SpaceSheetLoader()
	{
		super(3);
		addSpriteSheet(RocketSpriteSheet.getInstance());
		addSpriteSheet(AsteroidSpriteSheet.getInstance());
		addSpriteSheet(ProjectileSpriteSheet.getInstance());
	}
}
