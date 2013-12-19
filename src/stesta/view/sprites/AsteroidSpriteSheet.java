package stesta.view.sprites;

import lib.graphics.sprites.classes.SpriteSheet;
import lib.utils.integer.Dimension2DI;

public class AsteroidSpriteSheet extends SpriteSheet{

	private static final AsteroidSpriteSheet instance = new AsteroidSpriteSheet();
	
	private AsteroidSpriteSheet()
	{
		super(new Dimension2DI(32, 32), "images/asteroid.png");
	}
	
	public static AsteroidSpriteSheet getInstance()
	{
		return instance;
	}
}
