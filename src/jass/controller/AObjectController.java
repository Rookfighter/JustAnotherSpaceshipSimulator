package jass.controller;

import jass.entities.objects.ISpaceObject;
import jass.entities.world.ISpace;

import java.util.LinkedList;
import java.util.List;

import lib.utils.doubl.Dimension2DF;

public abstract class AObjectController<E extends ISpaceObject> implements IObjectController<E> {

	private ISpace space;
	private E controlledObject;
	
	@Override
	public void setSpace(ISpace p_space)
	{
		space = p_space;
	}
	
	@Override
	public ISpace getSpace()
	{
		return space;
	}

	@Override
	public void setControlledObject(E p_object)
	{
		controlledObject = p_object;
	}
	
	@Override
	public E getControlledObject()
	{
		return controlledObject;
	}

	@Override
	public List<ISpaceObject> getObjectsInRange(final float p_radius)
	{
		List<ISpaceObject> result = new LinkedList<ISpaceObject>();
		
		for(ISpaceObject object : getSpace().getObjects())
			if(objectIsInRadius(object, p_radius))
				result.add(object);
		
		return result;
	}
	
	private boolean objectIsInRadius(final ISpaceObject p_object, final float radius)
	{
		float diffx = getControlledObject().getPosition().x - p_object.getPosition().x;
		float diffy = getControlledObject().getPosition().y - p_object.getPosition().y;
		float diffSq = diffx * diffx + diffy * diffy;
		
		return diffSq <= (radius * radius);
	}

	@Override
	public List<ISpaceObject> getObjectsInRect(Dimension2DF p_dimension)
	{
		List<ISpaceObject> result = new LinkedList<ISpaceObject>();
		for(ISpaceObject object : getSpace().getObjects())
			if(objectIsInRect(object, p_dimension))
				result.add(object);
		
		return result;
	}
	
	private boolean objectIsInRect(final ISpaceObject p_object, final Dimension2DF p_dimension)
	{
		float diffx = Math.abs(getControlledObject().getPosition().x - p_object.getPosition().x);
		float diffy = Math.abs(getControlledObject().getPosition().y - p_object.getPosition().y);
		boolean isInWidth = diffx <= (p_dimension.Width() / 2);
		boolean isInHeight = diffy <= (p_dimension.Height() / 2);
		
		return isInWidth && isInHeight;
	}

}
