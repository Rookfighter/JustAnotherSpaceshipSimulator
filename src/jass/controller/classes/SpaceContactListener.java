package jass.controller.classes;

import jass.controller.ISpaceContactListener;
import jass.entities.objects.IAsteroid;
import jass.entities.objects.IHitable;
import jass.entities.objects.IRocket;
import jass.entities.objects.ISpaceObject;
import jass.entities.weapons.IProjectile;
import jass.entities.world.ISpace;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

public class SpaceContactListener implements ISpaceContactListener{

	private static final float DAMAGE_DENOM = 3.0f;
	private static final float DAMAGE_BASE = 3.0f;
	
	private ISpace space;
	
	public SpaceContactListener()
	{
		this(null);
	}
	
	public SpaceContactListener(final ISpace p_space)
	{
		setSpace(p_space);
	}
	
	@Override
	public void beginContact(final Contact p_contact)
	{
		checkProjectileCollision(p_contact);
	}
	
	private void checkProjectileCollision(final Contact p_contact)
	{
		if(p_contact.getFixtureA().getBody().getUserData() instanceof IProjectile &&
		   p_contact.getFixtureB().getBody().getUserData() instanceof ISpaceObject)
		{
			p_contact.setEnabled(false);
			processProjectileCollision( (IProjectile) p_contact.getFixtureA().getBody().getUserData(), (ISpaceObject) p_contact.getFixtureB().getBody().getUserData());
		}
		else if(p_contact.getFixtureB().getBody().getUserData() instanceof IProjectile &&
				p_contact.getFixtureA().getBody().getUserData() instanceof ISpaceObject)
		{
			p_contact.setEnabled(false);
			processProjectileCollision( (IProjectile) p_contact.getFixtureB().getBody().getUserData(), (ISpaceObject) p_contact.getFixtureA().getBody().getUserData());
		}
	}
	
	private void processProjectileCollision(final IProjectile p_projectile, final ISpaceObject p_object)
	{
		if(p_object instanceof IHitable)
		{
			IHitable hitableObject = (IHitable) p_object;
			hitableObject.decLifePoints(p_projectile.getDamage());
			checkHitableDeath(hitableObject);
		}
		
		getSpace().removeObject(p_projectile);
	}
	
	private void checkHitableDeath(final IHitable p_hitable)
	{
		if(p_hitable.isDead())
		{
			if(p_hitable instanceof IAsteroid)
				space.splitAsteroid((IAsteroid) p_hitable);
			else if(p_hitable instanceof ISpaceObject)
				space.removeObject((ISpaceObject) p_hitable);
		}
	}

	@Override
	public void endContact(final Contact p_contact)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(final Contact p_contact, final ContactImpulse p_impulse)
	{
		checkAsteroidRocketCollision(p_contact, p_impulse);
		checkRocketCollision(p_contact, p_impulse);
	}
	
	private void checkAsteroidRocketCollision(final Contact p_contact, final ContactImpulse p_impulse)
	{
		//cast the right body data
		if(p_contact.getFixtureA().getBody().getUserData() instanceof IRocket &&
		   p_contact.getFixtureB().getBody().getUserData() instanceof IAsteroid)
		{
			processRocketCollision(
					(IRocket) p_contact.getFixtureA().getBody().getUserData(),
					p_impulse);
		}
		/**else if(p_contact.getFixtureA().getBody().getUserData() instanceof IAsteroid &&
				p_contact.getFixtureB().getBody().getUserData() instanceof IRocket)
		{
			processRocketCollision(
					(IRocket) p_contact.getFixtureB().getBody().getUserData(),
					p_impulse);
		}**/
	}
	
	private void checkRocketCollision(final Contact p_contact, final ContactImpulse p_impulse)
	{
		if(p_contact.getFixtureA().getBody().getUserData() instanceof IRocket &&
		   p_contact.getFixtureB().getBody().getUserData() instanceof IRocket)
		{
			processRocketCollision(
					(IRocket)p_contact.getFixtureA().getBody().getUserData(),
					p_impulse);
		}
	}
	
	private void processRocketCollision(final IRocket p_rocket,final ContactImpulse p_impulse)
	{
		if(p_rocket.getLifePoints() > 0)
		{
			float damage = getRocketDamageExponential(p_impulse);
			
			p_rocket.decLifePoints((int) damage);
			checkHitableDeath(p_rocket);
		}
	}
	
	private float getRocketDamageExponential(final ContactImpulse p_impulse)
	{
		float impulseSum = 0.0f;
		for(float impulse : p_impulse.normalImpulses)
			impulseSum += impulse;
		
		//exponential function
		return (float) Math.pow(DAMAGE_BASE, impulseSum / DAMAGE_DENOM);
	}
	
	@Override
	public void preSolve(final Contact p_contact, final Manifold p_manifold)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpace(final ISpace p_space)
	{
		space = p_space;
	}

	@Override
	public ISpace getSpace()
	{
		return space;
	}

}
