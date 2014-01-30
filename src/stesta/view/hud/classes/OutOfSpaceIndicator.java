package stesta.view.hud.classes;

import java.awt.Graphics;

import org.jbox2d.common.Vec2;

import stesta.controller.rocket.IRocketController;
import stesta.entities.objects.IRocket;
import stesta.entities.world.ISpace;
import stesta.view.hud.IArrow;
import stesta.view.hud.IOutOfSpaceIndicator;
import lib.utils.integer.Dimension2DI;

public class OutOfSpaceIndicator implements IOutOfSpaceIndicator {

	private int drawOrder;
	
	private IRocketController player;
	//TODO private String indicatorText;
	
	private boolean outOfSpace;
	private Vec2 toMid;
	
	private Dimension2DI dimension;
	
	private IArrow arrow;
	
	public OutOfSpaceIndicator(final IRocketController p_player)
	{
		player = p_player;
		toMid = new Vec2();
		dimension = new Dimension2DI();
		arrow = new IndicatorArrow();
	}
	
	@Override
	public void update()
	{
		IRocket object = player.getControlledObject();
		ISpace space = player.getSpace();
		outOfSpace = object.getPosition().x > space.getDimension().Width() || object.getPosition().x < 0
		             || object.getPosition().y > space.getDimension().Height() || object.getPosition().y < 0;
		if(outOfSpace)
		{
			float dx = (space.getDimension().Width() / 2) - object.getPosition().x;
			float dy = (space.getDimension().Height() / 2) - object.getPosition().y;
			toMid.set(dx,dy);
		}
	}
	
	@Override
	public void draw(Graphics p_graphic)
	{
		if(outOfSpace)
			drawIndicator(p_graphic);
	}
	
	private void drawIndicator(final Graphics p_graphic)
	{
		arrow.assignDirection(toMid);
		arrow.draw(p_graphic);
	}

	@Override
	public int getDrawOrder()
	{
		return drawOrder;
	}

	@Override
	public void setDrawOrder(int p_drawOrder)
	{
		drawOrder = p_drawOrder;
	}

	@Override
	public void setDefaultDrawOrder()
	{
		drawOrder = Integer.MAX_VALUE;
	}

	@Override
	public Dimension2DI getDimension()
	{
		return dimension;
	}

	@Override
	public void assignDimension(Dimension2DI p_dimension)
	{
		dimension.assign(p_dimension);
		arrow.setLength(dimension.Height() / 5);
		arrow.setPosition(dimension.Width() / 2, dimension.Height() / 2);
	}

}
