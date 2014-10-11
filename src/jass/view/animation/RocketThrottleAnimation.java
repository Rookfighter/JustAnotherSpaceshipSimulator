package jass.view.animation;

import jass.view.sprites.RocketOn1Sprite;
import jass.view.sprites.RocketOn2Sprite;
import jass.view.sprites.RocketOn3Sprite;
import lib.graphics.animation.classes.Animation;
import lib.utils.DeltaTime;

public class RocketThrottleAnimation extends Animation {

	public RocketThrottleAnimation(final DeltaTime p_delta)
	{
		super();
		setDeltaTime(p_delta);
		getAnimationContainer().setSize(3);
		getAnimationContainer().get(0).set(new RocketOn1Sprite(), 41);
		getAnimationContainer().get(1).set(new RocketOn2Sprite(), 41);
		getAnimationContainer().get(2).set(new RocketOn3Sprite(), 41);
		getDimension().set(32, 32);
		applyChanges();
	}
}
