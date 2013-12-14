package stesta.view.sprites;

import lib.graphics.sprites.classes.SpriteSheet;
import lib.utils.integer.Dimension2DI;

public class StarSpriteSheet extends SpriteSheet{
	
	private static final StarSpriteSheet instance = new StarSpriteSheet();
	
	private StarSpriteSheet()
	{
		super(new Dimension2DI(32,32), "images/star.png");
	}
	
	public static StarSpriteSheet getInstance()
	{
		return instance;
	}

}
