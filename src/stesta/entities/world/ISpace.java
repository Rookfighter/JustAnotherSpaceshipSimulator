package stesta.entities.world;

import java.util.List;

import lib.utils.integer.Dimension2DI;

import org.jbox2d.dynamics.World;

import stesta.entities.objects.ISpaceObject;

public interface ISpace {

	
	List<ISpaceObject> getObjects();
	
	void addObject(final ISpaceObject p_object);
	void removeObject(final ISpaceObject p_object);
	void clear();
	
	Dimension2DI getDimension();
	
	World getPhysicsWorld();
}
