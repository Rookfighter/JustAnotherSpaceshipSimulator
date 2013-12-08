package stesta.entities.objects;

import org.jbox2d.common.Vec2;


public interface IMovingSpaceObject extends ISpaceObject{

	Vec2 getVelocity();
	
}
