package stesta.view.animation;

import lib.graphics.animation.classes.Animation;
import lib.utils.DeltaTime;
import stesta.view.sprites.RocketOn1Sprite;
import stesta.view.sprites.RocketOn2Sprite;
import stesta.view.sprites.RocketOn3Sprite;

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
