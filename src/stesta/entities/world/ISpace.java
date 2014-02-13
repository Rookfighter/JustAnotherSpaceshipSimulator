package stesta.entities.world;

import java.util.List;
import java.util.Set;

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
	
	void removeObjects();
	void addObjects();
	
	void splitAsteroid(final IAsteroid p_asteroid);
	Set<IAsteroid> asteroidsToSplit();
	
	Dimension2DI getDimension();
	
	World getPhysicsWorld();
	
	void setTimeStep(final float p_secs);
	float getTimeStep();
}
