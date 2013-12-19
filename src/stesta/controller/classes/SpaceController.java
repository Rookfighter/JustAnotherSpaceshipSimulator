package stesta.controller.classes;

import org.jbox2d.common.Vec2;

import stesta.controller.ISpaceController;
import stesta.entities.objects.ISpaceObject;
import stesta.entities.world.ISpace;

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
		correctPositions();
	}
	
	private void correctPositions()
	{
		for(ISpaceObject object : space.getObjects())
			correctPositionOf(object);
	}
	
	private void correctPositionOf(final ISpaceObject p_object)
	{
		if(p_object.getPosition().x < 0)
			p_object.setPosition(new Vec2(space.getDimension().Width(), p_object.getPosition().y));
		else if (p_object.getPosition().x > space.getDimension().Width())
			p_object.setPosition(new Vec2(0 , p_object.getPosition().y));
		
		if(p_object.getPosition().y < 0)
			p_object.setPosition(new Vec2( p_object.getPosition().x, space.getDimension().Height()));
		else if (p_object.getPosition().y > space.getDimension().Height())
			p_object.setPosition(new Vec2( p_object.getPosition().y, 0));
	}

	@Override
	public void setSpace(ISpace p_space)
	{
		space = p_space;
	}

}
