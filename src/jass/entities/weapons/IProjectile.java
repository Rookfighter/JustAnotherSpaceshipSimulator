package jass.entities.weapons;

import jass.entities.objects.IMovingSpaceObject;

public interface IProjectile extends IMovingSpaceObject {
	
	void setDamage(final int p_damage);
	int getDamage();
	
	void setWidth(final float p_width);
	float getWidth();
	
	void setLength(final float p_length);
	float getLength();
	
	float getMaxVelocity();
	void setMaxVelocity(final float p_maxVelocity);
	
}
