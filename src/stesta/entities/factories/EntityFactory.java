package stesta.entities.factories;

import java.util.Random;

import stesta.entities.objects.classes.Rocket;
import stesta.entities.objects.classes.Star;
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
	
	public Star createStar()
	{
		Star result = new Star();
		space.addObject(result);
		return result;
	}
	
	public void generateStars(final int count)
	{
		
		Random rand = new Random();
		Star star;
		for(int i = 0; i < count; ++i)
		{
			star = createStar();
			
			float x = rand.nextFloat() * space.getDimension().Width();
			float y = rand.nextFloat() * space.getDimension().Height();
			star.getPosition().set(x,y);
		}
	}
	
	
	
}
