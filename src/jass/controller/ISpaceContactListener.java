package jass.controller;

import jass.entities.world.ISpace;

import org.jbox2d.callbacks.ContactListener;

public interface ISpaceContactListener extends ContactListener {

	void setSpace(final ISpace p_space);
	ISpace getSpace();
	
}
