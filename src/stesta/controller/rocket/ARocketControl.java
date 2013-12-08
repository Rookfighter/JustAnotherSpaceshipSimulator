package stesta.controller.rocket;

import stesta.entities.objects.IRocket;

public abstract class ARocketControl implements IRocketControl {

	private IRocket rocket;
	
	public ARocketControl(final IRocket p_rocket)
	{
		setRocket(p_rocket);
	}
	
	@Override
	public void setRocket(IRocket p_rocket)
	{
		rocket = p_rocket;

	}
	
	@Override
	public IRocket getRocket()
	{
		return rocket;
	}

}
