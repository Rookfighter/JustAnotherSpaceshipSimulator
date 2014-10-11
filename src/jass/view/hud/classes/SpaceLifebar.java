package jass.view.hud.classes;

import jass.controller.rocket.IRocketController;
import jass.view.hud.AHudElement;
import jass.view.hud.ILifebar;

import java.awt.Color;
import java.awt.Graphics;

import lib.utils.integer.Dimension2DI;
import lib.utils.integer.Position2DI;

public class SpaceLifebar extends AHudElement implements ILifebar {

	private static final Color DEF_BACKGROUND_COLOR = Color.WHITE;
	private static final Color DEF_BACKGROUND2_COLOR = Color.BLACK;
	private static final Color DEF_FOREGROUND_COLOR = Color.RED;
	
	private static final float DEF_POSITION_X_FACTOR = 5.0f / 16.0f;
	private static final int DEF_POSITION_Y_OFFSET = 30;
	private static final float DEF_WIDTH_FACTOR = 10.0f / 16.0f;
	private static final int DEF_HEIGHT = 20;
	
	private static final int MIN_WIDTH = 30;
	
	private IRocketController rocketController;
	
	private Dimension2DI dimension;
	private Position2DI position;
	
	private int value;
	private int maxValue;
	private int borderWidth;
	
	public SpaceLifebar(final IRocketController p_rocketController)
	{
		rocketController = p_rocketController;
		dimension = new Dimension2DI(200,10);
		position = new Position2DI();
		borderWidth = 3;
	}
	
	@Override
	public void draw(final Graphics p_graphic)
	{
		Color color = p_graphic.getColor();
		
		p_graphic.setColor(DEF_BACKGROUND_COLOR);
		p_graphic.fillRect(position.X(), position.Y(),
				           dimension.Width(), dimension.Height());
		
		p_graphic.setColor(DEF_BACKGROUND2_COLOR);
		int width = dimension.Width() - 2 * borderWidth;
		int height = dimension.Height() - 2 * borderWidth;
		int x = position.X() + borderWidth;
		int y = position.Y() + borderWidth;
		p_graphic.fillRect(x, y, width, height);
		
		p_graphic.setColor(DEF_FOREGROUND_COLOR);
		float lengthFactor = (float) value / (float) maxValue;
		width *= lengthFactor;
		p_graphic.fillRect(x, y, width, height);
		
		p_graphic.setColor(color);
	}

	@Override
	public void update()
	{
		setMax(rocketController.getControlledObject().getMaxLifePoints());
		setValue(rocketController.getControlledObject().getLifePoints());
	}

	@Override
	public void setMax(final int p_value)
	{
		maxValue = p_value;
	}

	@Override
	public void setValue(final int p_value)
	{
		if(p_value >= 0)
			value = p_value;
		else
			value = 0;
	}

	@Override
	public void setDimension(final Dimension2DI p_dimension)
	{
		dimension = p_dimension;
	}

	@Override
	public Dimension2DI getDimension()
	{
		return dimension;
	}

	@Override
	public void setPosition(final Position2DI p_position)
	{
		position = p_position;
		
	}

	@Override
	public Position2DI getPosition()
	{
		return position;
	}
	
	@Override
	public void setHudDimension(final Dimension2DI p_dimension)
	{
		super.setHudDimension(p_dimension);
		updatePosition();
		updateDimension();
	}
	
	private void updatePosition()
	{
		int x = (int) (getHudDimension().Width() * DEF_POSITION_X_FACTOR);
		int y = getHudDimension().Height() - DEF_POSITION_Y_OFFSET;
		position.set(x,y);
	}
	
	private void updateDimension()
	{
		int width = (int) (getHudDimension().Width() * DEF_WIDTH_FACTOR);
		width = Math.max(width, MIN_WIDTH);
		dimension.set(width, DEF_HEIGHT);
	}

}
