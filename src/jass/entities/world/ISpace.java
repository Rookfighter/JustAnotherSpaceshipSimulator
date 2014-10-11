package jass.entities.world;

import jass.entities.objects.IAsteroid;
import jass.entities.objects.IRocket;
import jass.entities.objects.ISpaceObject;

import java.util.List;
import java.util.Set;

import lib.utils.integer.Dimension2DI;

import org.jbox2d.dynamics.World;

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
