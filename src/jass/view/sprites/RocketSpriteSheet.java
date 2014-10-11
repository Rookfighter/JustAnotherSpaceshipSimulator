package jass.view.sprites;

import lib.graphics.sprites.classes.SpriteSheet;
import lib.utils.integer.Dimension2DI;

public class RocketSpriteSheet extends SpriteSheet{

	private static final RocketSpriteSheet instance = new RocketSpriteSheet();
	
	private RocketSpriteSheet()
	{
		super(new Dimension2DI(32, 32), "images/rocket.png");
	}
	
	public static RocketSpriteSheet getInstance()
	{
		return instance;
	}
}
