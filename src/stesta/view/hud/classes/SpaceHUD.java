package stesta.view.hud.classes;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import lib.utils.integer.Dimension2DI;
import stesta.controller.rocket.IRocketController;
import stesta.view.hud.AHudElement;
import stesta.view.hud.IHudElement;
import stesta.view.hud.IRadar;
import stesta.view.hud.ISpaceHUD;

public class SpaceHUD extends AHudElement implements ISpaceHUD {

	private static final float DEF_RADAR_RANGE = 35.0f;
	
	private List<IHudElement> elements;
	
	
	public SpaceHUD(final IRocketController p_player)
	{
		super();
		elements = new LinkedList<IHudElement>();
		addElement(new OutOfSpaceIndicator(p_player));
		IRadar radar = new SpaceRadar(p_player);
		radar.setRange(DEF_RADAR_RANGE);
		addElement(radar);
		addElement(new SpaceLifebar(p_player));
	}
	
	@Override
	public void update()
	{
		for(IHudElement element : elements)
			element.update();
	}

	@Override
	public void draw(Graphics p_graphic)
	{
		for(IHudElement element : elements)
			element.draw(p_graphic);
	}
	
	@Override
	public void setHudDimension(final Dimension2DI p_dimension)
	{
		super.setHudDimension(p_dimension);
		for(IHudElement element : elements)
			element.setHudDimension(getHudDimension());
	}

	@Override
	public void addElement(IHudElement p_element)
	{
		elements.add(p_element);
		p_element.setHudDimension(getHudDimension());
	}

	@Override
	public void removeElement(IHudElement p_element)
	{
		elements.remove(p_element);
	}

	@Override
	public void clearElements()
	{
		elements.clear();
	}

	@Override
	public List<IHudElement> getElements()
	{
		return elements;
	}
}
