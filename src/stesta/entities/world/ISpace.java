package stesta.entities.world;

import java.util.List;

import lib.utils.integer.Dimension2DI;

import org.jbox2d.dynamics.World;

import stesta.entities.objects.IAsteroid;
import stesta.entities.objects.IRocket;
import stesta.entities.objects.ISpaceObject;

public interface ISpace {

	
	List<ISpaceObject> getObjects();
	List<IAsteroid> getAsteroids();
	List<IRocket> getRockets();
	
	void addObject(final ISpaceObject p_object);
	void removeObject(final ISpaceObject p_object);
	void clear();
	
	Dimension2DI getDimension();
	
	World getPhysicsWorld();
	
	void setTimeStep(final float p_secs);
	float getTimeStep();
}
