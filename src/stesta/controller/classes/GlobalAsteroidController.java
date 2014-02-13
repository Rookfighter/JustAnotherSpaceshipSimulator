package stesta.controller.classes;

import org.jbox2d.common.Vec2;

import stesta.controller.IController;
import stesta.entities.factories.EntityFactory;
import stesta.entities.objects.IAsteroid;
import stesta.entities.objects.classes.Asteroid;
import stesta.entities.world.ISpace;

public class GlobalAsteroidController implements IController{

	private static final float SPLIT_SPEED_FACTOR = 2.0f;
	
	private ISpace space;
	
	private EntityFactory entityFactory;
	
	public GlobalAsteroidController(final EntityFactory p_entityFactory)
	{
		space = p_entityFactory.getSpace();
		entityFactory = p_entityFactory;
	}
	
	@Override
	public void executeLogics()
	{
		splitAsteroids();
		
	}
	
	private void splitAsteroids()
	{
		for(IAsteroid asteroid : space.asteroidsToSplit())
			split(asteroid);
		space.asteroidsToSplit().clear();
	}
	
	private void split(final IAsteroid p_asteroid)
	{
		float newRadius = p_asteroid.getRadius() / 2;
		if(newRadius >= Asteroid.MIN_RADIUS)
		{
			Vec2 vector = new Vec2(-p_asteroid.getVelocity().x , p_asteroid.getVelocity().y);
			float length = p_asteroid.getVelocity().length();
			vector.x /= length;
			vector.y /= length;
			float dx = vector.x * newRadius;
			float dy = vector.y * newRadius;
			
			IAsteroid asteroid1 = entityFactory.createAsteroid(newRadius);
			IAsteroid asteroid2 = entityFactory.createAsteroid(newRadius);
			
			Vec2 asteroidPos = new Vec2(p_asteroid.getPosition());
			asteroidPos.x += dx;
			asteroidPos.y += dy;
			asteroid1.setPosition(asteroidPos);
			asteroid1.getVelocity().set(vector.x * length * SPLIT_SPEED_FACTOR, vector.y * length * SPLIT_SPEED_FACTOR);
			
			asteroidPos.set(p_asteroid.getPosition());
			asteroidPos.x -= dx;
			asteroidPos.y -= dy;
			asteroid2.setPosition(asteroidPos);
			asteroid2.getVelocity().set(-vector.x * length * SPLIT_SPEED_FACTOR, -vector.y * length * SPLIT_SPEED_FACTOR);
		}
		
		space.removeObject(p_asteroid);
	}
}
