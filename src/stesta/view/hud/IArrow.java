package stesta.view.hud;

import java.awt.Color;
import java.awt.Graphics;

import org.jbox2d.common.Vec2;

import lib.utils.integer.Position2DI;

public interface IArrow {

	void setLength(final float p_length);
	float getLength();
	
	void setPosition(final int p_x, final int p_y);
	Position2DI getPosition();
	
	void setWidth(final float p_width);
	float getWidth();
	
	void setForeground(final Color p_color);
	Color getForeground();
	
	void assignDirection(final Vec2 p_vector);
	
	void draw(final Graphics p_graphic);
	
}
