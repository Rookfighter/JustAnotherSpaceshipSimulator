package jass.controller.rocket.classes;


import jass.controller.AObjectController;
import jass.controller.rocket.IRocketController;
import jass.entities.objects.IRocket;
import jass.entities.objects.classes.Rocket;
import jass.entities.weapons.IWeapon;
import jass.entities.weapons.classes.LaserCannon;
import jass.entities.weapons.classes.LaserShot;

import org.jbox2d.common.Vec2;

public class RocketController extends AObjectController<IRocket> implements IRocketController {

	private static final float DEF_LEFT_ANG_VEL = (float) Math.toRadians(7);
	private static final float DEF_RIGHT_ANG_VEL = (float) Math.toRadians(7);
	
	private float angularVelocity;
	
	public RocketController()
	{
		angularVelocity = 0;
	}
		
	@Override
	public void executeLogics()
	{
		processWeapons();
		accelerateRocket();
		turnRocket();
	}

	private void accelerateRocket()
	{
		float dx = (float) Math.cos(getControlledObject().getDirection()) * getControlledObject().getAccelerateForce();
		float dy = (float) Math.sin(getControlledObject().getDirection()) * getControlledObject().getAccelerateForce();
		Vec2 force = new Vec2(dx,dy);
		getControlledObject().getBody().applyForceToCenter(force);
	}
	
	private void turnRocket()
	{
		float angVel = angularVelocity / getSpace().getTimeStep();
		getControlledObject().getBody().setAngularVelocity(angVel);
	}
	
	private void processWeapons()
	{
		for(IWeapon weapon : getControlledObject().getWeapons())
			executeWeaponLogic(weapon);
	}
	
	private void executeWeaponLogic(final IWeapon p_weapon)
	{
		if(p_weapon.hasToCooldown())
			p_weapon.decreaseCooldown();
		if(p_weapon.shouldFire())
		{
			if(!p_weapon.hasToCooldown())
				fireWeapon(p_weapon);
			else
				p_weapon.fire(false);
		}
	}
	
	private void fireWeapon(final IWeapon p_weapon)
	{
		if(p_weapon instanceof LaserCannon)
			spawnLaserShot();
		
		p_weapon.fire(false);
		p_weapon.setCooldown(p_weapon.getMaxCooldown());
	}
	
	private void spawnLaserShot()
	{
		float direction = getControlledObject().getDirection();
		float dx = (float) Math.cos(direction);
		float dy = (float) Math.sin(direction);
		
		LaserShot shot = new LaserShot();
		shot.createBody(getSpace().getPhysicsWorld());
		
		Vec2 position = new Vec2(getControlledObject().getPosition());
		float distance = getControlledObject().getRadius() + shot.getLength() / 2;
		position.x += (dx * distance);
		position.y += (dy * distance);
		shot.getBody().setTransform(position, direction);
		
		shot.getVelocity().set(dx * shot.getMaxVelocity(), dy * shot.getMaxVelocity());
		getSpace().addObject(shot);
	}

	@Override
	public void turnRight()
	{
		angularVelocity = DEF_RIGHT_ANG_VEL;
	}

	@Override
	public void turnLeft()
	{
		angularVelocity = -DEF_LEFT_ANG_VEL;
	}

	@Override
	public void accelerate()
	{
		getControlledObject().setAccelerateForce(getControlledObject().getMaxAccelerateForce());
	}
	
	@Override
	public void stopTurnRight()
	{
		angularVelocity = 0;
	}

	@Override
	public void stopTurnLeft()
	{
		angularVelocity = 0;
	}

	@Override
	public void stopAccelerate()
	{
		getControlledObject().setAccelerateForce(0);
	}
	
	@Override
	public void fireLaserCannon()
	{
		getControlledObject().getWeapon(Rocket.LASERCANNON).fire(true);
	}
}
