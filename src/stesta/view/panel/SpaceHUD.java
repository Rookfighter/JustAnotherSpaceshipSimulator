package stesta.view.panel;

import java.awt.Graphics;

import lib.utils.integer.Dimension2DI;
import stesta.view.ISpaceHUD;

public class SpaceHUD implements ISpaceHUD {

	private Dimension2DI dimension;
	private int drawOrder;
	
	public SpaceHUD()
	{
		dimension = new Dimension2DI();
		setDefaultDrawOrder();
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
		//in foreground
		drawOrder = Integer.MAX_VALUE;
	}

	@Override
	public Dimension2DI getDimension()
	{
		return dimension;
	}
	
	
}
