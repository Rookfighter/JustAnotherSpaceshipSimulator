package stesta.view.hud.classes;

import java.awt.Graphics;

import org.jbox2d.common.Vec2;

import stesta.controller.rocket.IRocketController;
import stesta.entities.objects.IRocket;
import stesta.entities.world.ISpace;
import stesta.view.hud.AHudElement;
import stesta.view.hud.IArrow;
import stesta.view.hud.IOutOfSpaceIndicator;
import lib.utils.integer.Dimension2DI;

public class OutOfSpaceIndicator extends AHudElement implements IOutOfSpaceIndicator {

	private final static int DEF_ARROW_DISTANCE = 32; 
	
	private int drawOrder;
	
	private IRocketController player;
	//TODO private String indicatorText;
	
	private boolean outOfSpace;
	private Vec2 toMid;
	
	private IArrow arrow;
	
	public OutOfSpaceIndicator(final IRocketController p_player)
	{
		super();
		player = p_player;
		toMid = new Vec2();
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
		calcArrowPosition();
		arrow.draw(p_graphic);
	}
	
	private void calcArrowPosition()
	{
		int x = getDimension().Width() / 2;
		int y = getDimension().Height() / 2;
		
		float lengthFac = (DEF_ARROW_DISTANCE + arrow.getLength() / 2) / toMid.length();
		x += toMid.x * lengthFac;
		y += toMid.y * lengthFac;
		
		arrow.setPosition(x,y);
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
	public void setDimension(final Dimension2DI p_dimension)
	{
		super.setDimension(p_dimension);
		arrow.setLength(getDimension().Height() / 5);
	}

}
