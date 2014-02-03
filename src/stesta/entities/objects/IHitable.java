package stesta.entities.objects;

public interface IHitable {

	int getLifePoints();
	void setLifePoints(final int p_lifePoints);
	
	void setMaxLifePoints(final int p_lifePoints);
	int getMaxLifePoints();
	
	void decLifePoints(final int p_value);
	void incLifePoints(final int p_value);
	
	boolean isDead();
	
}
