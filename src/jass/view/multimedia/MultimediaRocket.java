package jass.view.multimedia;

import jass.view.animation.RocketThrottleAnimation;
import jass.view.sprites.RocketOffSprite;
import lib.multimedia.classes.MultimediaObject;
import lib.utils.DeltaTime;

public class MultimediaRocket extends MultimediaObject{

	public static final int SPRITE_ROCKET_OFF = 0;
	public static final int ANIMATION_ROCKET_MOVEMENT = 0;
	
	public MultimediaRocket(final DeltaTime p_delta)
	{
		super();
		setDeltaTime(p_delta);
		getDimension().set(32,32);
		addSprites();
		addAnimations();
		setSpriteToDraw(SPRITE_ROCKET_OFF);
	}
	
	private void addSprites()
	{
		addSprite(new RocketOffSprite());
	}
	
	private void addAnimations()
	{
		addAnimation(new RocketThrottleAnimation(null));
	}
	
}
