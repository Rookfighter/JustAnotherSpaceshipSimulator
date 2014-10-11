package jass.entities.world.classes;

import jass.entities.objects.IAsteroid;
import jass.entities.objects.IRocket;
import jass.entities.objects.ISpaceObject;
import jass.entities.world.ISpace;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lib.utils.integer.Dimension2DI;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Space implements ISpace {
	
	private static final int DEF_WIDTH = 1000;
	private static final int DEF_HEIGHT = 1000;
	private static final float DEF_TIME_STEP = 1f / 60f;
	
	private Dimension2DI dimension;
	private World physicsWorld;
	
	private List<IAsteroid> asteroidList;
	private List<IRocket> rocketList;
	private List<ISpaceObject> miscObjectList;
	private List<ISpaceObject> objectList;
	
	private List<ISpaceObject> toAdd;
	private List<ISpaceObject> toRemove;
	
	private Set<IAsteroid> toSplit;
	
	private float timeStep;

	public Space()
	{
		this(new Dimension2DI(DEF_WIDTH,DEF_HEIGHT));
	}
	
	public Space(final Dimension2DI p_dimension)
	{
		dimension = p_dimension;
		timeStep = DEF_TIME_STEP;
		asteroidList = new LinkedList<IAsteroid>();
		rocketList = new LinkedList<IRocket>();
		miscObjectList = new LinkedList<ISpaceObject>();
		objectList = new LinkedList<ISpaceObject>();
		toAdd = new LinkedList<ISpaceObject>();
		toRemove = new LinkedList<ISpaceObject>();
		toSplit = new HashSet<IAsteroid>();
		initPhysicsWorld();
	}
	
	private void initPhysicsWorld()
	{
		Vec2 gravity = new Vec2(0.0f, 0.0f);
		physicsWorld = new World(gravity);
	}
	
	@Override
	public List<ISpaceObject> getObjects()
	{
		objectList.clear();
		objectList.addAll(asteroidList);
		objectList.addAll(rocketList);
		objectList.addAll(miscObjectList);
		return objectList;
	}
	
	@Override
	public List<IAsteroid> getAsteroids()
	{
		return asteroidList;
	}
	
	@Override
	public List<IRocket> getRockets()
	{
		return rocketList;
	}

	@Override
	public void addObject(final ISpaceObject p_object)
	{
		toAdd.add(p_object);
	}

	@Override
	public void removeObject(final ISpaceObject p_object)
	{
		toRemove.add(p_object);
	}
	
	@Override
	public void addObjects()
	{
		for(ISpaceObject object : toAdd)
			add(object);
		toAdd.clear();
	}
	
	private void add(final ISpaceObject p_object)
	{
		if(p_object instanceof IAsteroid)
			asteroidList.add((IAsteroid) p_object);
		else if(p_object instanceof IRocket)
			rocketList.add((IRocket) p_object);
		else
			miscObjectList.add(p_object);
	}
	
	@Override
	public void removeObjects()
	{
		for(ISpaceObject object : toRemove)
			remove(object);
		toRemove.clear();
	}
	
	private void remove(final ISpaceObject p_object)
	{
		physicsWorld.destroyBody(p_object.getBody());
		if(p_object instanceof IAsteroid)
			asteroidList.remove(p_object);
		else if(p_object instanceof IRocket)
			rocketList.remove(p_object);
		else
			miscObjectList.remove(p_object);
	}

	@Override
	public void clear()
	{
		for(ISpaceObject object : getObjects())
			physicsWorld.destroyBody(object.getBody());
		
		asteroidList.clear();
		rocketList.clear();
		miscObjectList.clear();
	}

	@Override
	public Dimension2DI getDimension()
	{
		return dimension;
	}

	@Override
	public World getPhysicsWorld()
	{
		return physicsWorld;
	}

	@Override
	public void setTimeStep(final float p_secs)
	{
		if(p_secs <= 0)
			throw new IllegalArgumentException("TimeStep of Space cannot be zero or negative.");
		timeStep = p_secs;
	}

	@Override
	public float getTimeStep()
	{
		return timeStep;
	}

	@Override
	public void splitAsteroid(final IAsteroid p_asteroid)
	{
		toSplit.add(p_asteroid);
	}

	@Override
	public Set<IAsteroid> asteroidsToSplit()
	{
		return toSplit;
	}

}
