package jass.entities.objects;

import jass.entities.weapons.IWeapon;

import java.util.List;

public interface IRocket extends IMovingSpaceObject, IHitable {

	float getDirection();
	
	float getRadius();
	
	float getMaxAccelerateForce();
	float getAccelerateForce();
	void setAccelerateForce(final float p_accelerateForce);
	
	IWeapon getWeapon(final int p_idx);
	List<IWeapon> getWeapons();
}

