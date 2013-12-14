package stesta.entities.objects.classes;

import stesta.entities.objects.ANonPhysicalObject;
import stesta.entities.objects.EObjectTypes;

public class Star extends ANonPhysicalObject {
	
	private EObjectTypes type;
	
	public Star()
	{
		super();
		type = EObjectTypes.STAR;
	}
	
	@Override
	public EObjectTypes type()
	{
		return type;
	}

}
