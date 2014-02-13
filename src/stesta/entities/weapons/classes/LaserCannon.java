package stesta.entities.weapons.classes;

import stesta.entities.weapons.AWeapon;

public class LaserCannon extends AWeapon{

	private static final int DEF_MAX_COOLDOWN = 30;
	
	public LaserCannon()
	{
		super();
		setMaxCooldown(DEF_MAX_COOLDOWN);
	}
	
}
