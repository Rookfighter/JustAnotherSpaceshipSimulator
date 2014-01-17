package stesta.view.panel;

import java.util.HashMap;
import java.util.Map;

import lib.graphics.sprites.ISprite;
import stesta.entities.objects.IAsteroid;
import stesta.entities.objects.IRocket;
import stesta.entities.objects.ISpaceObject;
import stesta.view.sprites.AsteroidSprite;
import stesta.view.sprites.RocketOffSprite;

public class SpriteMap {

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
			return new RocketOffSprite();
		else if(p_object instanceof IAsteroid)
			return new AsteroidSprite();
		else
			return null; //TODO default sprite
	}
	
}
