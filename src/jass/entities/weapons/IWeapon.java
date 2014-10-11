package jass.entities.weapons;

public interface IWeapon {

	void setMaxCooldown(final int p_maxCooldown);
	int getMaxCooldown();
	
	void decreaseCooldown();
	void setCooldown(final int p_cooldown);
	int getCooldown();
	
	boolean hasToCooldown();
	
	void fire(final boolean p_fire);
	boolean shouldFire();
}
