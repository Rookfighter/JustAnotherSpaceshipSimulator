package jass.view.hud.classes;

import jass.controller.rocket.IRocketController;
import jass.entities.objects.IRocket;
import jass.entities.objects.ISpaceObject;
import jass.view.hud.AHudElement;
import jass.view.hud.IRadar;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import lib.utils.integer.Dimension2DI;
import lib.utils.integer.Position2DI;

public class SpaceRadar extends AHudElement implements IRadar {

	private static final int DEF_COOLDOWN_LIMIT = 240;
	private static final int MAX_TRANSPARENT = 255;
	private static final int MIN_TRANSPARENT = 75;
	private static final Color RADAR_FOREGROUND = new Color(154,205,50);
	private static final Color RADAR_BACKGROUND = new Color(244,238,224);
	
	private IRocketController player;
	
	private int radius;
	private Position2DI position;
	
	private float range;
	
	private List<RadarObject> distanceList;
	
	private int cooldown;
	private int coolDownLimit;
	
	private float sizeFactor;
	
	
	public SpaceRadar(final IRocketController p_player)
	{
		super();
		player = p_player;
		coolDownLimit = DEF_COOLDOWN_LIMIT;
		cooldown = 0;
		distanceList = new LinkedList<RadarObject>();
		position = new Position2DI();
		radius = 1;
		range = 1;
		updateSizeFactor();
	}

	@Override
	public void draw(Graphics p_graphic)
	{
		drawCircle(p_graphic);
		drawObjects(p_graphic);
	}
	
	private void drawCircle(final Graphics p_graphic)
	{
		Color color = p_graphic.getColor();
		
		p_graphic.setColor(RADAR_BACKGROUND);
		p_graphic.fillOval(position.X() - radius, position.Y() - radius, 2 * radius, 2 * radius);
		p_graphic.setColor(RADAR_FOREGROUND);
		p_graphic.fillOval(position.X() + 2 - radius, position.Y() + 2 - radius, 2* (radius - 2), 2* (radius - 2));
		
		p_graphic.setColor(color);
	}
	
	private void drawObjects(final Graphics p_graphic)
	{
		Color color = p_graphic.getColor();
		
		for(RadarObject object : distanceList)
		{
			drawDotFor(p_graphic, object);
		}
		p_graphic.setColor(color);
	}
	
	private void drawDotFor(final Graphics p_graphic,final RadarObject p_object)
	{
		float transparentFactor = 1.0f - (float) cooldown / (float) coolDownLimit;
		int transparent = MIN_TRANSPARENT + (int) ((MAX_TRANSPARENT - MIN_TRANSPARENT) * transparentFactor);
		
		if(p_object.Type == RadarType.NEUTRAL)
			p_graphic.setColor(new Color(255, 255, 0, transparent));
		else if(p_object.Type == RadarType.ENEMY)
			p_graphic.setColor(new Color(255, 0, 0, transparent));
		else 
			p_graphic.setColor(new Color(0, 0, 255, transparent));
		
		int dotRadius = 3;
		int x = position.X() + (int) (p_object.Distance.x * sizeFactor) - dotRadius;
		int y = position.Y() + (int) (p_object.Distance.y * sizeFactor) - dotRadius;
		p_graphic.fillOval(x, y, 2 * dotRadius, 2 * dotRadius);
	}
	
	@Override
	public void update()
	{
		cooldown %= coolDownLimit;
		if(cooldown == 0)
			generateRadarPositions();
		cooldown++;
		
	}
	
	private void generateRadarPositions()
	{
		List<ISpaceObject> objects = player.getObjectsInRange(range);
		RadarObject radarObject;
		distanceList.clear();
		for(ISpaceObject object : objects)
		{
			radarObject = new RadarObject();
			
			float dx = object.getPosition().x - player.getControlledObject().getPosition().x;
			float dy = object.getPosition().y - player.getControlledObject().getPosition().y;
			
			radarObject.Positon.set(object.getPosition());
			radarObject.Distance.set(dx,dy);
			
			if(object.equals(player.getControlledObject()))
				radarObject.Type = RadarType.ALLY;
			else if(object instanceof IRocket)
				radarObject.Type = RadarType.ENEMY;
			else
				radarObject.Type = RadarType.NEUTRAL;
			
			distanceList.add(radarObject);
		}
	}

	@Override
	public void setRadius(int p_radius)
	{
		radius = p_radius;
		updateSizeFactor();
	}

	@Override
	public int getRadius()
	{
		return radius;
	}

	@Override
	public void setPosition(Position2DI p_position)
	{
		position = p_position;
	}

	@Override
	public Position2DI getPosition()
	{
		return position;
	}
	
	@Override
	public void setRange(float p_range)
	{
		range = p_range;
		updateSizeFactor();
	}

	@Override
	public float getRange()
	{
		return range;
	}

	private void updateSizeFactor()
	{
		sizeFactor = ((float) radius) / range;
	}
	
	@Override
	public void setHudDimension(final Dimension2DI p_dimension)
	{
		super.setHudDimension(p_dimension);
		updateRadiusAndPosition();
	}
	
	private void updateRadiusAndPosition()
	{
		setRadius(getHudDimension().Width() / (4 * 2));
		getPosition().set(radius, getHudDimension().Height() - radius);
	}
	
}
