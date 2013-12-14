package stesta.controller.classes;

import stesta.controller.ISpaceController;
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
	}

	@Override
	public void setSpace(ISpace p_space)
	{
		space = p_space;
	}

}
