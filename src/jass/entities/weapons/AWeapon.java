package jass.entities.weapons;

public abstract class AWeapon implements IWeapon{

	private int maxCooldown;
	private int cooldown;
	private boolean fire;
	
	public AWeapon()
	{
		cooldown = 0;
		maxCooldown = 0;
		fire = false;
	}
	
	@Override
	public void setMaxCooldown(final int p_maxCooldown)
	{
		if(p_maxCooldown < 0)
			throw new IllegalArgumentException(String.format("Maximum weapon cooldown cannot be negative: %d.", p_maxCooldown));
		maxCooldown = p_maxCooldown;
	}

	@Override
	public int getMaxCooldown()
	{
		return maxCooldown;
	}
	
	@Override
	public void decreaseCooldown()
	{
		cooldown--;
	}

	@Override
	public void setCooldown(final int p_cooldown)
	{
		if(p_cooldown < 0)
			throw new IllegalArgumentException(String.format("Weapon cooldown cannot be negative: %d.", p_cooldown));
		cooldown = p_cooldown;
	}

	@Override
	public int getCooldown()
	{
		return cooldown;
	}

	@Override
	public boolean hasToCooldown()
	{
		return cooldown > 0;
	}
	
	@Override
	public void fire(final boolean p_fire)
	{
		fire = p_fire;
	}
	
	@Override
	public boolean shouldFire()
	{
		return fire;
	}
	
	
}
