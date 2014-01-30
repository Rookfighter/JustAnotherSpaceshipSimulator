package stesta.view.hud;

import lib.utils.integer.Dimension2DI;

public abstract class AHudElement implements IHudElement {

	private int drawOrder;
	private Dimension2DI dimension;
	
	public AHudElement()
	{
		setDefaultDrawOrder();
		dimension = new Dimension2DI();
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
	public void setDimension(Dimension2DI p_dimension)
	{
		dimension = p_dimension;
	}

	@Override
	public Dimension2DI getDimension()
	{
		return dimension;
	}

}
