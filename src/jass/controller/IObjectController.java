package jass.controller;

import jass.entities.objects.ISpaceObject;
import jass.entities.world.ISpace;

import java.util.List;

import lib.utils.doubl.Dimension2DF;

public interface IObjectController<E extends ISpaceObject> extends IController{

	void setSpace(final ISpace p_space);
	ISpace getSpace();
	
	void setControlledObject(final E p_object);
	E getControlledObject();
	
	List<ISpaceObject> getObjectsInRange(final float p_radius);
	List<ISpaceObject> getObjectsInRect(final Dimension2DF p_dimension);
}
