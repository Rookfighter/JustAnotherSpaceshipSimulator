package jass.controller.classes;

import jass.controller.ISpaceController;
import jass.entities.objects.IAsteroid;
import jass.entities.world.ISpace;

public class SpaceController implements ISpaceController {

	private static final int DEF_VEL_ITERATIONS = 6;
	private static final int DEF_POS_ITERATIONS = 2;
	
	private ISpace space;
	
	public SpaceController()
	{
		this(null);
	}
	
	public SpaceController(final ISpace p_space)
	{
		space = p_space;
	}
	
	@Override
	public void executeLogics()
	{
		space.getPhysicsWorld().step(space.getTimeStep(), DEF_VEL_ITERATIONS, DEF_POS_ITERATIONS);
		space.addObjects();
		space.removeObjects();
		correctPositions();
	}
	
	private void correctPositions()
	{
		for(IAsteroid asteroid : space.getAsteroids())
			correctPositionOf(asteroid);
	}
	
	private void correctPositionOf(final IAsteroid p_asteroid)
	{
		if(p_asteroid.getPosition().x < 0 && p_asteroid.getVelocity().x < 0)
			p_asteroid.getVelocity().x = -p_asteroid.getVelocity().x;
		else if (p_asteroid.getPosition().x > space.getDimension().Width() && p_asteroid.getVelocity().x > 0)
			p_asteroid.getVelocity().x = -p_asteroid.getVelocity().x;
		
		if(p_asteroid.getPosition().y < 0 && p_asteroid.getVelocity().y < 0)
			p_asteroid.getVelocity().y = -p_asteroid.getVelocity().y;
		else if (p_asteroid.getPosition().y > space.getDimension().Height() && p_asteroid.getVelocity().y > 0)
			p_asteroid.getVelocity().y = -p_asteroid.getVelocity().y;
	}

	@Override
	public void setSpace(ISpace p_space)
	{
		space = p_space;
	}

}
