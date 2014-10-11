package jass.view.hud.classes;

import jass.view.hud.IArrow;

import java.awt.Color;
import java.awt.Graphics;

import org.jbox2d.common.Vec2;

import lib.utils.integer.Position2DI;

public class IndicatorArrow implements IArrow{
	
	private static final Color DEF_ARROWCOLOR = Color.RED;
	private static final float DEF_WIDTH = 10;
	private static final float DEF_RECTLENGTH_FACTOR = 5.0f / 12.0f;
	private static final float DEF_TRILENGTH_FACTOR = 4.0f / 12.0f;
	private static final float DEF_TRIWIDTH_FACTOR = 2.0f;
	
	private Position2DI position;
	
	private float width;
	private float length;
	private Vec2 arrowVector;
	
	private Color arrowColor;
	
	public IndicatorArrow()
	{
		position = new Position2DI();
		width = DEF_WIDTH;
		arrowVector = new Vec2();
		arrowColor = DEF_ARROWCOLOR;
	}
	
	@Override
	public void draw(Graphics p_graphic)
	{
		Vec2 perpendVec = new Vec2(arrowVector.y, -arrowVector.x);
		setVectorLength(perpendVec, width / 2);
		
		Color color = p_graphic.getColor();
		p_graphic.setColor(arrowColor);
		
		drawRect(p_graphic, perpendVec);
		drawTriangle(p_graphic, perpendVec);
		
		p_graphic.setColor(color);
	}
	
	private void drawRect(Graphics p_graphic, Vec2 p_perpendVec)
	{
		int[] xPos = new int[4];
		int[] yPos = new int[4];
		
		xPos[0] = (int) (position.X() - arrowVector.x / 2 + p_perpendVec.x);
		yPos[0] = (int) (position.Y() - arrowVector.y / 2 + p_perpendVec.y);
		
		xPos[1] = (int) (position.X() - arrowVector.x / 2 - p_perpendVec.x);
		yPos[1] = (int) (position.Y() - arrowVector.y / 2 - p_perpendVec.y);
		
		Vec2 shorterArrowVec = new Vec2(arrowVector);
		shorterArrowVec.x *= DEF_RECTLENGTH_FACTOR;
		shorterArrowVec.y *= DEF_RECTLENGTH_FACTOR;
		
		xPos[2] = (int) (position.X() + shorterArrowVec.x - p_perpendVec.x);
		yPos[2] = (int) (position.Y() + shorterArrowVec.y - p_perpendVec.y);
		
		xPos[3] = (int) (position.X() + shorterArrowVec.x + p_perpendVec.x);
		yPos[3] = (int) (position.Y() + shorterArrowVec.y + p_perpendVec.y);
		
		p_graphic.fillPolygon(xPos, yPos, 4);
	}
	
	private void drawTriangle(Graphics p_graphic, Vec2 p_perpendVec)
	{
		int[] xPos = new int[3];
		int[] yPos = new int[3];
		
		Vec2 longerPerpendVec = new Vec2(p_perpendVec);
		longerPerpendVec.x *= DEF_TRIWIDTH_FACTOR;
		longerPerpendVec.y *= DEF_TRIWIDTH_FACTOR;
		
		Vec2 shorterArrowVec = new Vec2(arrowVector);
		shorterArrowVec.x *= DEF_TRILENGTH_FACTOR;
		shorterArrowVec.y *= DEF_TRILENGTH_FACTOR;
		
		xPos[0] = (int) (position.X() + shorterArrowVec.x + longerPerpendVec.x);
		yPos[0] = (int) (position.Y() + shorterArrowVec.y + longerPerpendVec.y);
		
		xPos[1] = (int) (position.X() + shorterArrowVec.x - longerPerpendVec.x);
		yPos[1] = (int) (position.Y() + shorterArrowVec.y - longerPerpendVec.y);
		
		xPos[2] = (int) (position.X() + arrowVector.x / 2);
		yPos[2] = (int) (position.Y() + arrowVector.y / 2);
		
		p_graphic.fillPolygon(xPos, yPos, 3);
	}
	
	private void setVectorLength(final Vec2 p_vector, float p_length)
	{
		float factor = p_length / p_vector.length();
		p_vector.x *= factor;
		p_vector.y *= factor;
	}

	@Override
	public void setLength(float p_length)
	{
		float factor = p_length / length;
		arrowVector.x *= factor;
		arrowVector.y *= factor;
		length = p_length;
	}
	
	@Override
	public void assignDirection(Vec2 p_vector)
	{
		float oldLength = length;
		arrowVector.set(p_vector);
		length = arrowVector.length();
		setLength(oldLength);
	}

	@Override
	public float getLength()
	{
		return arrowVector.length();
	}

	@Override
	public void setPosition(int p_x, int p_y)
	{
		position.set(p_x, p_y);
	}

	@Override
	public Position2DI getPosition()
	{
		return position;
	}

	@Override
	public void setWidth(float p_width)
	{
		width = p_width;
	}

	@Override
	public float getWidth()
	{
		return width;
	}

	@Override
	public void setForeground(Color p_color)
	{
		arrowColor = p_color;
	}

	@Override
	public Color getForeground()
	{
		return arrowColor;
	}

}
