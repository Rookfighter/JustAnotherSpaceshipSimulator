package jass.view.panel;

import jass.entities.objects.IAsteroid;
import jass.entities.objects.IRocket;
import jass.entities.objects.ISpaceObject;
import jass.entities.weapons.classes.LaserShot;
import jass.view.multimedia.MultimediaRocket;
import jass.view.sprites.AsteroidSprite;
import jass.view.sprites.LaserShotSprite;

import java.util.HashMap;
import java.util.Map;

import lib.graphics.sprites.ISprite;
import lib.utils.DeltaTime;

public class SpriteMap {

	private DeltaTime delta;
	private Map<ISpaceObject, ISprite> spriteMap;
	
	public SpriteMap()
	{
		spriteMap = new HashMap<ISpaceObject, ISprite>();
	}
	
	public ISprite get(final ISpaceObject p_object)
	{
		ISprite result = spriteMap.get(p_object);
		if(result == null)
		{
			result = createSpriteFor(p_object);
			spriteMap.put(p_object, result);
		}
		
		return result;
	}
	
	private ISprite createSpriteFor(final ISpaceObject p_object)
	{
		if(p_object instanceof IRocket)
			return new MultimediaRocket(delta);
		else if(p_object instanceof IAsteroid)
			return new AsteroidSprite();
		else if(p_object instanceof LaserShot)
			return new LaserShotSprite();
		else
			return null; //TODO default sprite
	}
	
	public void setDeltaTime(final DeltaTime p_delta)
	{
		delta = p_delta;
	}
	
	public DeltaTime getDeltaTime()
	{
		return delta;
	}
}
