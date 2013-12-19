package stesta.view.panel;

import java.util.HashMap;
import java.util.Map;

import lib.graphics.IDrawable;
import stesta.entities.objects.ISpaceObject;
import stesta.entities.objects.EObjectTypes;
import stesta.view.IDrawableFactory;

public class DrawableMap {

	private Map<EObjectTypes, IDrawableFactory> typeMap;
	
	public DrawableMap()
	{
		typeMap = new HashMap<EObjectTypes, IDrawableFactory>();
		typeMap.put(EObjectTypes.ROCKET, new RocketSpriteFactory());
		typeMap.put(EObjectTypes.ASTEROID, new AsteroidSpriteFactory());
	}
	
	public IDrawable createDrawableFor(final ISpaceObject object)
	{
		return typeMap.get(object.type()).createDrawable();
	}
	
}
