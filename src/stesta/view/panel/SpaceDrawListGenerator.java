package stesta.view.panel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import stesta.controller.rocket.IRocketController;
import stesta.entities.objects.ISpaceObject;
import lib.graphics.IDrawable;
import lib.graphics.panel.IDrawListGenerator;
import lib.utils.DeltaTime;
import lib.utils.doubl.Dimension2DF;

public class SpaceDrawListGenerator implements IDrawListGenerator {

	private static final double DEF_WIDTH = 800;
	private static final double DEF_HEIGHT = 600;
	
	private DeltaTime delta;
	private IRocketController player;
	private Dimension2DF fovDimension;
	private List<IDrawable> drawList;
	private Map<ISpaceObject, IDrawable> drawMap;
	private DrawableMap drawableGenerator;
	
	public SpaceDrawListGenerator(final IRocketController p_player)
	{
		player = p_player;
		drawMap = new HashMap<ISpaceObject, IDrawable>();
		fovDimension = new Dimension2DF(DEF_WIDTH, DEF_HEIGHT);
	}

	@Override
	public List<IDrawable> generateDrawList()
	{
		drawList = new LinkedList<IDrawable>();
		fillDrawList();
		return drawList;
	}
	
	private void fillDrawList()
	{
		for(ISpaceObject  object : player.getObjectsInRect(fovDimension))
			addDrawableOf(object);
	}
	
	private void addDrawableOf(final ISpaceObject object)
	{
		IDrawable drawable = drawMap.get(object);
		
		if(drawable == null)
		{
			drawable = drawableGenerator.createDrawableFor(object);
			drawMap.put(object, drawable);
		}
		
		drawList.add(drawable);
	}
	
	@Override
	public void setDeltaTime(DeltaTime p_delta)
	{
		delta = p_delta;
	}
	
}
