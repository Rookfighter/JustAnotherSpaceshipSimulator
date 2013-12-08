package stesta.entities.world.classes;

import java.util.LinkedList;
import java.util.List;

import lib.utils.integer.Dimension2DI;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import stesta.entities.objects.ISpaceObject;
import stesta.entities.world.ISpace;

public class Space implements ISpace {
	
	private static final int DEF_WIDTH = 1000;
	private static final int DEF_HEIGHT = 1000;
	
	private Dimension2DI dimension;
	private World physicsWorld;
	private List<ISpaceObject> spaceObjectList;

	public Space()
	{
		this(new Dimension2DI(DEF_WIDTH,DEF_HEIGHT));
	}
	
	public Space(final Dimension2DI p_dimension)
	{
		dimension = p_dimension;
		spaceObjectList = new LinkedList<ISpaceObject>();
		intiPhysicsWorld();
	}
	
	private void intiPhysicsWorld()
	{
		Vec2 gravity = new Vec2(0.0f, 0.0f);
		physicsWorld = new World(gravity);
	}
	
	@Override
	public List<ISpaceObject> getObjects()
	{
		return spaceObjectList;
	}

	@Override
	public void addObject(ISpaceObject p_object)
	{
		spaceObjectList.add(p_object);
	}

	@Override
	public void removeObject(ISpaceObject p_object)
	{
		physicsWorld.destroyBody(p_object.getBody());
		spaceObjectList.remove(p_object);
	}
	
	@Override
	public void clear()
	{
		for(ISpaceObject object : spaceObjectList)
			physicsWorld.destroyBody(object.getBody());
		spaceObjectList.clear();
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

}
