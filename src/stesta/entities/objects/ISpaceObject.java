package stesta.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public interface ISpaceObject {

	void initialize(final World p_world);
	boolean isInitialized();
	
	Vec2 getPosition();
	void setPosition(final Vec2 p_position);
	
	Body getBody();
	
	EObjectTypes type();
	
}
