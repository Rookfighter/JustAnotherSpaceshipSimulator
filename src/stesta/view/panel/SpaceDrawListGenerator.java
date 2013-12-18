package stesta.view.panel;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import stesta.controller.rocket.IRocketController;
import stesta.entities.objects.IRocket;
import stesta.entities.objects.ISpaceObject;
import stesta.view.drawable.StarField;
import lib.graphics.IDrawable;
import lib.graphics.panel.DrawOrderComparator;
import lib.graphics.panel.IDrawListGenerator;
import lib.graphics.sprites.ISprite;
import lib.utils.DeltaTime;
import lib.utils.doubl.Dimension2DF;
import lib.utils.integer.Dimension2DI;
import lib.utils.integer.Position2DI;

public class SpaceDrawListGenerator implements IDrawListGenerator {

	private static final int DEF_WIDTH = 800;
	private static final int DEF_HEIGHT = 600;
	
	private DeltaTime delta;
	private IRocketController player;
	private Dimension2DF fovDimension;
	private List<IDrawable> drawList;
	private Map<ISpaceObject, IDrawable> drawMap;
	private DrawableMap drawableGenerator;
	private DrawOrderComparator comparator;
	private float sizeFactor;
	private StarField starField;
	
	public SpaceDrawListGenerator(final IRocketController p_player)
	{
		player = p_player;
		drawMap = new HashMap<ISpaceObject, IDrawable>();
		fovDimension = new Dimension2DF();
		comparator = new DrawOrderComparator();
		drawableGenerator = new DrawableMap();
		starField = new StarField();
		
		calculateSizeFactor();
		setDimension(new Dimension2DI(DEF_WIDTH, DEF_HEIGHT));
	}
	
	private void setDimension(final Dimension2DI p_dimension)
	{
		double fovWidth = (double) (p_dimension.Width()) / sizeFactor;
		double fovHeight = (double) (p_dimension.Height()) / sizeFactor;
		fovDimension.set(fovWidth, fovHeight);
		starField.assignDimension(p_dimension);
	}
	
	private void calculateSizeFactor()
	{
		ISprite sprite = (ISprite) getDrawableFor(player.getControlledObject());
		sizeFactor = ((sprite.getDimension().Width() / 2) / player.getControlledObject().getRadius());
	}

	@Override
	public List<IDrawable> generateDrawList()
	{
		drawList = new LinkedList<IDrawable>();
		fillDrawList();
		addPermanentDrawables();
		Collections.sort(drawList, comparator);
		return drawList;
	}
	
	private void fillDrawList()
	{
		for(ISpaceObject  object : player.getObjectsInRect(fovDimension))
			addDrawableOf(object);
	}
	
	private void addDrawableOf(final ISpaceObject object)
	{
		IDrawable drawable = getDrawableFor(object);
		setDrawablePosition(object, drawable);
		setDrawableDirection(object, drawable);
		drawable.setDefaultDrawOrder();
		
		drawList.add(drawable);
	}
	
	private IDrawable getDrawableFor(final ISpaceObject p_object)
	{
		IDrawable drawable = drawMap.get(p_object);
		
		if(drawable == null)
		{
			drawable = drawableGenerator.createDrawableFor(p_object);
			drawMap.put(p_object, drawable);
		}
		
		return drawable;
	}

	private void setDrawablePosition(ISpaceObject p_object, IDrawable p_drawable)
	{
		if(!(p_drawable instanceof ISprite))
			return;
		
		ISprite sprite = (ISprite) p_drawable;
		float diffx = player.getControlledObject().getPosition().x - p_object.getPosition().x;
		float diffy = player.getControlledObject().getPosition().y - p_object.getPosition().y;
		float x = diffx + (float) (fovDimension.Width() / 2);
		float y = diffy + (float) (fovDimension.Height() / 2);
		
		sprite.getPosition().set((int) (sizeFactor * x), (int) (sizeFactor * y));
	}
	
	private void setDrawableDirection(ISpaceObject p_object, IDrawable p_drawable)
	{
		if(!(p_drawable instanceof ISprite))
			return;
		
		if(!(p_object instanceof IRocket))
			return;
		
		ISprite sprite = (ISprite) p_drawable;
		IRocket rocket = (IRocket) p_object;
		sprite.setRotation(rocket.getDirection() - Math.toRadians(90));
		
	}
	
	private void addPermanentDrawables()
	{
		addStarField();
	}
	
	private void addStarField()
	{
		int x = - (int) (player.getControlledObject().getPosition().x * sizeFactor);
		int y = - (int) (player.getControlledObject().getPosition().y * sizeFactor);
		starField.assignPosition(new Position2DI(x,y));
		drawList.add(starField);
	}
	
	@Override
	public void setDeltaTime(DeltaTime p_delta)
	{
		delta = p_delta;
	}
	
}
