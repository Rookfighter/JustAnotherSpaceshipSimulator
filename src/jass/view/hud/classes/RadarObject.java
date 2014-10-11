package jass.view.hud.classes;

import org.jbox2d.common.Vec2;

public class RadarObject {

	public Vec2 Positon;
	public Vec2 Distance;
	public RadarType Type;
	
	public RadarObject()
	{
		Positon = new Vec2();
		Distance = new Vec2();
		Type = RadarType.NEUTRAL;
	}
	
	public RadarObject(final Vec2 p_positon, final Vec2 p_distance, final RadarType p_type)
	{
		Positon = p_positon;
		Distance = p_distance;
		Type = p_type;
	}
}
