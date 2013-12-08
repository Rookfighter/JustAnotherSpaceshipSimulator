package stesta.controller;

import java.util.List;

import lib.utils.doubl.Dimension2DF;
import stesta.entities.objects.ISpaceObject;
import stesta.entities.world.ISpace;

public interface IObjectController<E> extends IController{

	void setSpace(final ISpace p_space);
	ISpace getSpace();
	
	void setControlledObject(final E p_object);
	E getControlledObject();
	
	List<ISpaceObject> getObjectsInRange(final double p_radius);
	List<ISpaceObject> getObjectsInRect(final Dimension2DF p_dimension);
}
