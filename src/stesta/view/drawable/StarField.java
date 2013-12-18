package stesta.view.drawable;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import lib.graphics.IDrawable;
import lib.utils.doubl.Vector2D;
import lib.utils.integer.Dimension2DI;
import lib.utils.integer.Position2DI;

public class StarField implements IDrawable{

	private int maxStarsPerRow = 3;
	private int maxStarsPerColumn = 3;
	
	private int drawOrder;
	private Position2DI position;
	private Dimension2DI dimension;
	private List<Position2DI> starList;
	private List<Position2DI> removeList;
	private Random random;
	
	public StarField()
	{
		position = new Position2DI();
		dimension = new Dimension2DI();
		starList = new LinkedList<Position2DI>();
		removeList = new LinkedList<Position2DI>();
		random = new Random();
		setDefaultDrawOrder();
	}
	
	public void assignPosition(final Position2DI p_position)
	{
		Vector2D diff = Vector2D.vectorBetween(position, p_position);
		position.assign(p_position);
		moveBy(diff);
	}
	
	public void moveBy(final Vector2D p_diff)
	{
		moveStarsBy(p_diff);
		createStarsFor(p_diff);
		removeUnseenStars();
	}
	
	private void moveStarsBy(final Vector2D p_diff)
	{
		for(Position2DI starpos : starList)
			starpos.add(p_diff);
	}
	
	private void createStarsFor(final Vector2D p_diff)
	{
		createStarsForX(p_diff.DX);
		createStarsForY(p_diff.DY);
	}
	
	private void createStarsForX(final double p_diffx)
	{
		if(p_diffx > 0)
			createStarsOnLeft((int) p_diffx);
		else if(p_diffx < 0)
			createStarsOnRight((int) p_diffx);
	}
	
	private void createStarsOnLeft(final int p_diffx)
	{
		//from left border to right
		for(int x = 0; x < p_diffx; x += 2)
		{
			int starCount = (int) (random.nextFloat() * maxStarsPerColumn);
			for(int i = 0; i < starCount; i++)
			{
				int y = (int) (random.nextFloat() * dimension.Height());
				starList.add(new Position2DI(x, y));
			}
		}
	}
	
	private void createStarsOnRight(final int p_diffx)
	{
		//from right border to left
		for(int x = dimension.Width(); x > (dimension.Width() + p_diffx); x -= 2)
		{
			int starCount = (int) (random.nextFloat() * maxStarsPerColumn);
			for(int i = 0; i < starCount; i++)
			{
				int y = (int) (random.nextFloat() * dimension.Height());
				starList.add(new Position2DI(x, y));
			}
		}
	}
	
	private void createStarsForY(final double p_diffy)
	{
		//java swing -> pos diff is movement downwards 
		if(p_diffy > 0)
			createStarsOnTop((int) p_diffy);
		else if(p_diffy < 0)
			createStarsOnBot((int) p_diffy);
	}
	
	private void createStarsOnTop(final int p_diffy)
	{
		//from top border downwards
		for(int y = 0; y < p_diffy; y += 2)
		{
			int starCount = (int) (random.nextFloat() * maxStarsPerRow);
			for(int i = 0; i < starCount; i++)
			{
				int x = (int) (random.nextFloat() * dimension.Width());
				starList.add(new Position2DI(x, y));
			}
		}
	}
	
	private void createStarsOnBot(final int p_diffy)
	{
		//from bot border upwards
		for(int y = dimension.Height(); y > (dimension.Height() + p_diffy); y -= 2)
		{
			int starCount = (int) (random.nextFloat() * maxStarsPerColumn);
			for(int i = 0; i < starCount; i++)
			{
				int x = (int) (random.nextFloat() * dimension.Width());
				starList.add(new Position2DI(x, y));
			}
		}
	}
	
	private void removeUnseenStars()
	{
		fillRemoveList();
		starList.removeAll(removeList);
	}
	
	private void fillRemoveList()
	{
		removeList.clear();
		for(Position2DI starpos : starList)
			removeStarIfUnseen(starpos);
	}
	
	
	private void removeStarIfUnseen(final Position2DI p_starpos)
	{
		if(starIsUnseen(p_starpos))
			removeList.add(p_starpos);
	}
	
	private boolean starIsUnseen(final Position2DI p_starpos)
	{
		int diffx = Math.abs((dimension.Width() / 2) - p_starpos.X());
		int diffy = Math.abs((dimension.Height() / 2) - p_starpos.Y());
		
		return diffx > (dimension.Width() / 2) ||
			   diffy > (dimension.Height() / 2);
	}
	
	public void assignDimension(final Dimension2DI p_dimension)
	{
		int diffx = p_dimension.Width() - dimension.Width();
		int diffy = p_dimension.Height() - dimension.Height();
		dimension.assign(p_dimension);
		refillField();
		//if(diffx > 0)
		//	createStarsOnRight(diffx);
		//if(diffy > 0)
		//	createStarsOnBot(diffy);
		
		//removeUnseenStars();
	}
	
	private void refillField()
	{
		starList.clear();
		createStarsOnLeft(dimension.Width());
		createStarsOnTop(dimension.Height());
	}
	
	@Override
	public void draw(final Graphics p_graphic)
	{
		Color old = p_graphic.getColor();
		p_graphic.setColor(Color.WHITE);
		
		for(Position2DI starpos : starList)
			p_graphic.drawRect(starpos.X(), starpos.Y(), 2, 2);
		
		p_graphic.setColor(old);
	}

	@Override
	public int getDrawOrder()
	{
		return drawOrder;
	}

	@Override
	public void setDrawOrder(final int p_drawOrder)
	{
		drawOrder = p_drawOrder;
	}

	@Override
	public void setDefaultDrawOrder()
	{
		drawOrder = Integer.MIN_VALUE;
	}
}
