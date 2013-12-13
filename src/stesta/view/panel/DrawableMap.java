package stesta.view.panel;

import java.util.HashMap;
import java.util.Map;

import lib.graphics.IDrawable;
import stesta.entities.objects.ISpaceObject;
import stesta.entities.objects.ObjectTypes;
import stesta.view.IDrawableFactory;

public class DrawableMap {

	private Map<ObjectTypes, IDrawableFactory> typeMap;
	
	public DrawableMap()
	{
		typeMap = new HashMap<ObjectTypes, IDrawableFactory>();
		typeMap.put(ObjectTypes.ROCKET, new RocketSpriteFactory());
	}
	
	public IDrawable createDrawableFor(final ISpaceObject object)
	{
		return typeMap.get(object.type()).createDrawable();
	}
	
}
