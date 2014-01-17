package stesta.entities.factories;

import java.util.Random;

import org.jbox2d.common.Vec2;

import stesta.entities.objects.IAsteroid;
import stesta.entities.objects.IRocket;
import stesta.entities.objects.classes.Asteroid;
import stesta.entities.objects.classes.Rocket;
import stesta.entities.world.ISpace;
import stesta.entities.world.classes.Space;

public class EntityFactory {

	private static final float MIN_ASTEROID_RADIUS = 0.4f;
	private static final float MAX_ASTEROID_RADIUS = 2.0f;
	private static final float MIN_ASTEROID_VELOCITY = 5.0f;
	private static final float MAX_ASTEROID_VELOCITY = 10.0f;
	private static final float MAX_ASTEROID_ANGLE_VELOCITY = (float) Math.toRadians(90);
	
	private ISpace space;
	private Random random;
	
	public EntityFactory()
	{
		space = new Space();
		space.getDimension().set(100,100);
		random = new Random();
	}
	
	public ISpace getSpace()
	{
		return space;
	}
	
	public IRocket createRocket()
	{
		Rocket result = new Rocket();
		result.initialize(space.getPhysicsWorld());
		space.addObject(result);
		return result;
	}
	
	public IAsteroid createAsteroid()
	{
		Asteroid result = new Asteroid();
		result.initialize(space.getPhysicsWorld());
		space.addObject(result);
		return result;
	}
	
	public IAsteroid createRandomAsteroid()
	{
		Asteroid result = new Asteroid();
		
		float radius = MIN_ASTEROID_RADIUS + random.nextFloat() * (MAX_ASTEROID_RADIUS - MIN_ASTEROID_RADIUS);
		float angleVelocity = random.nextFloat() * MAX_ASTEROID_ANGLE_VELOCITY;
		Vec2 velocity = createRandomAsteroidVelocity();
		
		result.setRadius(radius);
		result.initialize(space.getPhysicsWorld());
		result.getBody().setAngularVelocity(angleVelocity);
		result.getBody().setLinearVelocity(velocity);
		
		space.addObject(result);
		return result;
	}
	
	private Vec2 createRandomAsteroidVelocity()
	{
		float velocity = MIN_ASTEROID_VELOCITY + random.nextFloat() * (MAX_ASTEROID_VELOCITY - MIN_ASTEROID_VELOCITY);
		float direction = random.nextFloat() * 2 * (float) Math.PI;
		
		float dx = (float) Math.cos(direction) * velocity;
		float dy = (float) Math.sin(direction) * velocity;
		
		return new Vec2(dx, dy);
	}
	
	public void generateRandomAsteroids(final int count)
	{
		for(int i = 0; i < count; i++)
		{
			IAsteroid asteroid = createRandomAsteroid();
			float x = random.nextFloat() * space.getDimension().Width();
			float y = random.nextFloat() * space.getDimension().Height();
			asteroid.setPosition(new Vec2(x,y));
		}
	}
}
