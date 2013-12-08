package stesta.controller;

import java.util.List;

import lib.utils.doubl.Dimension2DF;
import stesta.entities.objects.ISpaceObject;
import stesta.entities.world.ISpace;

public abstract class AObjectController<E> implements IObjectController<E> {

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
	public List<ISpaceObject> getObjectsInRange(double p_radius)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ISpaceObject> getObjectsInRect(Dimension2DF p_dimension)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
