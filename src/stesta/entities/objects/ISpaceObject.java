package stesta.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public interface ISpaceObject {

	Vec2 getPosition();
	
	Body getBody();
	
	EObjectTypes type();
	
}
