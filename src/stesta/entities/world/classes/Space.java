package stesta.entities.world.classes;

import java.util.LinkedList;
import java.util.List;

import lib.utils.integer.Dimension2DI;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import stesta.entities.objects.IAsteroid;
import stesta.entities.objects.IRocket;
import stesta.entities.objects.ISpaceObject;
import stesta.entities.world.ISpace;

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
	public void addObject(ISpaceObject p_object)
	{
		if(p_object instanceof IAsteroid)
			asteroidList.add((IAsteroid) p_object);
		else if(p_object instanceof IRocket)
			rocketList.add((IRocket) p_object);
		else
			miscObjectList.add(p_object);
	}

	@Override
	public void removeObject(ISpaceObject p_object)
	{
		physicsWorld.destroyBody(p_object.getBody());
		if(p_object instanceof IAsteroid)
			asteroidList.remove((IAsteroid) p_object);
		else if(p_object instanceof IRocket)
			rocketList.remove((IRocket) p_object);
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
	public void setTimeStep(float p_secs)
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

}
