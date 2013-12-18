package stesta.entities.factories;

import stesta.entities.objects.classes.Rocket;
import stesta.entities.world.ISpace;
import stesta.entities.world.classes.Space;

public class EntityFactory {

	private ISpace space;
	
	public EntityFactory()
	{
		space = new Space();
	}
	
	public ISpace getSpace()
	{
		return space;
	}
	
	public Rocket createRocket()
	{
		Rocket result = new Rocket(space.getPhysicsWorld());
		space.addObject(result);
		return result;
	}
}
