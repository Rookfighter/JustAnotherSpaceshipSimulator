package jass.view.animation;

import jass.view.sprites.RocketProjectileOn1Sprite;
import jass.view.sprites.RocketProjectileOn2Sprite;
import jass.view.sprites.RocketProjectileOn3Sprite;
import lib.graphics.animation.classes.Animation;
import lib.utils.DeltaTime;

public class RocketProjectileThrottleAnimation extends Animation{

	public RocketProjectileThrottleAnimation(final DeltaTime p_delta)
	{
		super();
		setDeltaTime(p_delta);
		getAnimationContainer().setSize(3);
		getAnimationContainer().get(0).set(new RocketProjectileOn1Sprite(), 41);
		getAnimationContainer().get(1).set(new RocketProjectileOn2Sprite(), 41);
		getAnimationContainer().get(2).set(new RocketProjectileOn3Sprite(), 41);
		getDimension().set(32, 32);
		applyChanges();
	}
}
