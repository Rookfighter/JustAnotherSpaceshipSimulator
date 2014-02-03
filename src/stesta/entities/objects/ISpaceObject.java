package stesta.entities.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public interface ISpaceObject {

	void createBody(final World p_world);
	boolean hasBody();
	
	Vec2 getPosition();
	void setPosition(final Vec2 p_position);
	
	Body getBody();
	
}
