package stesta.controller.classes;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import stesta.controller.ISpaceContactListener;
import stesta.entities.objects.IAsteroid;
import stesta.entities.objects.IRocket;
import stesta.entities.world.ISpace;

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
	public void beginContact(Contact p_contact)
	{
		
	}

	@Override
	public void endContact(Contact p_contact)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact p_contact, ContactImpulse p_impulse)
	{
		checkAsteroidRocketCollision(p_contact, p_impulse);
		checkRocketCollision(p_contact, p_impulse);
	}
	
	private void checkAsteroidRocketCollision(Contact p_contact, ContactImpulse p_impulse)
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
	
	private void checkRocketCollision(Contact p_contact, ContactImpulse p_impulse)
	{
		if(p_contact.getFixtureA().getBody().getUserData() instanceof IRocket &&
		   p_contact.getFixtureB().getBody().getUserData() instanceof IRocket)
		{
			processRocketCollision(
					(IRocket)p_contact.getFixtureA().getBody().getUserData(),
					p_impulse);
		}
	}
	
	private void processRocketCollision(IRocket p_rocket,ContactImpulse p_impulse)
	{
		if(p_rocket.getLifePoints() > 0)
		{
			float damage = getRocketDamageExponential(p_impulse);
			
			System.out.printf("Damage: %.2f \n", damage);
			p_rocket.decLifePoints((int) damage);
			if(p_rocket.isDead())
				space.removeObject(p_rocket);
		}
	}
	
	private float getRocketDamageExponential(ContactImpulse p_impulse)
	{
		float impulseSum = 0.0f;
		for(float impulse : p_impulse.normalImpulses)
			impulseSum += impulse;
		
		//exponential function
		return (float) Math.pow(DAMAGE_BASE, impulseSum / DAMAGE_DENOM);
	}
	
	@Override
	public void preSolve(Contact p_contact, Manifold p_manifold)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpace(ISpace p_space)
	{
		space = p_space;
	}

	@Override
	public ISpace getSpace()
	{
		return space;
	}

}
