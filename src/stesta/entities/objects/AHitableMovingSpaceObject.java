package stesta.entities.objects;


public abstract class AHitableMovingSpaceObject extends AMovingSpaceObject implements IHitableMovingSpaceObject{

	private int maxLifePoints;
	private int lifePoints;
	
	public AHitableMovingSpaceObject()
	{
		super();
		lifePoints = 1;
		maxLifePoints = 1;
	}
	
	@Override
	public int getLifePoints()
	{
		return lifePoints;
	}

	@Override
	public void setLifePoints(final int p_lifePoints)
	{
		lifePoints = p_lifePoints;
	}
	
	@Override
	public void maximizeLifePoints()
	{
		setLifePoints(getMaxLifePoints());
	}

	@Override
	public void setMaxLifePoints(final int p_lifePoints)
	{
		if(p_lifePoints < 0)
			throw new IllegalArgumentException(String.format("Hitable spaceobject max lifepoints cannot be negative: %d.", p_lifePoints));
		maxLifePoints = p_lifePoints;
	}

	@Override
	public int getMaxLifePoints()
	{
		return maxLifePoints;
	}

	@Override
	public void decLifePoints(final int p_value)
	{
		lifePoints -= p_value;
	}

	@Override
	public void incLifePoints(final int p_value)
	{
		lifePoints += p_value;
		if(lifePoints > maxLifePoints)
			lifePoints = maxLifePoints;
	}

	@Override
	public boolean isDead()
	{
		return lifePoints < 0;
	}

}
