package stesta.view.animation;

import lib.graphics.animation.classes.Animation;
import lib.utils.DeltaTime;
import stesta.view.sprites.RocketProjectileOn1Sprite;
import stesta.view.sprites.RocketProjectileOn2Sprite;
import stesta.view.sprites.RocketProjectileOn3Sprite;

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
