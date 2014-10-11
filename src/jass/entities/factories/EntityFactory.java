package jass.entities.factories;

import jass.entities.objects.IAsteroid;
import jass.entities.objects.IRocket;
import jass.entities.objects.classes.Asteroid;
import jass.entities.objects.classes.Rocket;
import jass.entities.world.ISpace;
import jass.entities.world.classes.Space;

import java.util.Random;

import org.jbox2d.common.Vec2;

public class EntityFactory {

	private static final float MIN_ASTEROID_RADIUS = Asteroid.MIN_RADIUS;
	private static final float MAX_ASTEROID_RADIUS = 2.0f;
	private static final float MIN_ASTEROID_VELOCITY = 5.0f;
	private static final float MAX_ASTEROID_VELOCITY = 10.0f;
	private static final float MAX_ASTEROID_ANGLE_VELOCITY = (float) Math.toRadians(90);
	
	private ISpace space;
	private Random random;
	
	public EntityFactory()
	{
		space = new Space();
		space.getDimension().set(400,400);
		random = new Random();
	}
	
	public ISpace getSpace()
	{
		return space;
	}
	
	public IRocket createRocket()
	{
		Rocket result = new Rocket();
		result.createBody(space.getPhysicsWorld());
		space.addObject(result);
		return result;
	}
	
	public IAsteroid createAsteroid()
	{
		Asteroid result = new Asteroid();
		result.createBody(space.getPhysicsWorld());
		space.addObject(result);
		return result;
	}
	
	public IAsteroid createAsteroid(final float p_radius)
	{
		Asteroid result = new Asteroid();
		result.setRadius(p_radius);
		result.createBody(space.getPhysicsWorld());
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
		result.createBody(space.getPhysicsWorld());
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
