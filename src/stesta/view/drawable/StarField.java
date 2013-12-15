package stesta.view.drawable;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import lib.graphics.IDrawable;
import lib.utils.integer.Dimension2DI;
import lib.utils.integer.Position2DI;

public class StarField implements IDrawable{

	private static final int SIZE_FACTOR = 2;
	
	private int drawOrder;
	private Position2DI position;
	private Dimension2DI dimension;
	private List<Position2DI> starList;
	private Random random;
	
	public StarField()
	{
		this(new Position2DI(),new Dimension2DI());
	}
	
	public StarField(Position2DI p_position, Dimension2DI p_dimension)
	{
		position = p_position;
		dimension = p_dimension;
		starList = new LinkedList<Position2DI>();
		random = new Random();
	}
	
	public void setPosition(final Position2DI p_position)
	{
		changeListFor(p_position);
	}
	
	private void changeListFor(final Position2DI p_position)
	{
		int minX = Math.min(position.X(), p_position.X());
		int maxX = Math.max(position.X(), p_position.X());
		
		for(int i = minX; i < maxX; ++i)
			createStarsFor(i);
	}
	
	private void createStarsFor(final int p_x)
	{
		
	}
	
	public void setDimension(final Dimension2DI p_dimension)
	{
		dimension.assign(p_dimension);
	}
	
	@Override
	public void draw(Graphics p_graphic)
	{
		// TODO Auto-generated method stub
		
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
		drawOrder = Integer.MIN_VALUE;
	}
	

}
