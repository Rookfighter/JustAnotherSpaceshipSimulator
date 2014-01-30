package stesta.view.hud.classes;

import java.awt.Graphics;

import lib.utils.integer.Dimension2DI;
import stesta.controller.rocket.IRocketController;
import stesta.view.hud.IOutOfSpaceIndicator;
import stesta.view.hud.IRadar;
import stesta.view.hud.ISpaceHUD;

public class SpaceHUD implements ISpaceHUD {

	private static final float DEF_RADAR_RANGE = 35.0f;
	
	private Dimension2DI dimension;
	private int drawOrder;
	private IRadar radar;
	private IOutOfSpaceIndicator indicator;
	private IRocketController player;
	
	public SpaceHUD(final IRocketController p_player)
	{
		indicator = new OutOfSpaceIndicator(p_player);
		radar = new SpaceRadar();
		radar.setRange(DEF_RADAR_RANGE);
		setDimension(new Dimension2DI());
		setDefaultDrawOrder();
		setPlayer(p_player);
	}
	
	@Override
	public void update()
	{
		radar.update();
		indicator.update();
	}

	@Override
	public void draw(Graphics p_graphic)
	{
		radar.draw(p_graphic);
		indicator.draw(p_graphic);
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

	@Override
	public void setDimension(final Dimension2DI p_dimension)
	{
		dimension = p_dimension;
		updateRadar();
		indicator.assignDimension(dimension);
	}
	
	private void updateRadar()
	{
		int radius = dimension.Width() / (4 * 2);
		if(radius >= 1)
			radar.setRadius(radius);
		else
			radar.setRadius(1);
		
		radar.getPosition().set(radius, dimension.Height() - radius);
	}

	private void setPlayer(final IRocketController p_player)
	{
		player = p_player;
		radar.setPlayer(player);
	}

}
