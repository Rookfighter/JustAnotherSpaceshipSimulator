package jass.entities.weapons;

import jass.entities.objects.AMovingSpaceObject;

public abstract class AProjectile extends AMovingSpaceObject implements IProjectile {

	private int damage;
	
	private float length;
	private float width;
	private float maxVelocity;
	
	public AProjectile()
	{
		super();
		damage = 1;
		width = 1;
		length = 1;
		maxVelocity = 1;
	}
	
	@Override
	public void setDamage(final int p_damage)
	{
		damage = p_damage;
	}

	@Override
	public int getDamage()
	{
		return damage;
	}
	
	@Override 
	public void setWidth(final float p_width)
	{
		width = p_width;
	}
	
	@Override
	public float getWidth()
	{
		return width;
	}
	
	@Override
	public void setLength(final float p_length)
	{
		length = p_length;
	}
	
	@Override
	public float getLength()
	{
		return length;
	}
	
	@Override
	public float getMaxVelocity()
	{
		return maxVelocity;
	}
	
	@Override
	public void setMaxVelocity(final float p_maxVelocity)
	{
		if(p_maxVelocity < 0)
			throw new IllegalArgumentException(String.format("Maximum velocity of projectile cannot be negative: %.2f", p_maxVelocity));
		maxVelocity = p_maxVelocity;
	}
	
}
