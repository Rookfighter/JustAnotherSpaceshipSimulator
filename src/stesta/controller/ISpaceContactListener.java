package stesta.controller;

import org.jbox2d.callbacks.ContactListener;

import stesta.entities.world.ISpace;

public interface ISpaceContactListener extends ContactListener {

	void setSpace(final ISpace p_space);
	ISpace getSpace();
	
}
