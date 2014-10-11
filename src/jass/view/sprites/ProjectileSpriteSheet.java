package jass.view.sprites;

import lib.graphics.sprites.classes.SpriteSheet;
import lib.utils.integer.Dimension2DI;

public class ProjectileSpriteSheet extends SpriteSheet{
	
	private static final ProjectileSpriteSheet instance = new ProjectileSpriteSheet();
	
	public ProjectileSpriteSheet()
	{
		super(new Dimension2DI(32, 32), "images/projectiles.png");
	}
	
	public static ProjectileSpriteSheet getInstance()
	{
		return instance;
	}

}
