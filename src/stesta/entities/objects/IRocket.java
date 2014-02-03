package stesta.entities.objects;

public interface IRocket extends IMovingSpaceObject, IHitable {

	float getDirection();
	
	float getRadius();
	
	float getMaxAccelerateForce();
	float getAccelerateForce();
	void setAccelerateForce(final float p_accelerateForce);
}

