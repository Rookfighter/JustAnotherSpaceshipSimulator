package stesta.view.controls;

import java.awt.event.KeyEvent;

public class RocketActionUp extends ARocketAction{

	public RocketActionUp(RocketKeyContainer p_keyContainer)
	{
		super(p_keyContainer);
	}

	@Override
	public void process(KeyEvent p_event)
	{
		switch(p_event.getKeyCode())
		{
			case KeyEvent.VK_A:
				stopTurningLeft();
				break;
			case KeyEvent.VK_D:	
				stopTurningRight();
				break;
			case KeyEvent.VK_W:
				stopAccelerating();
		}
	}

	private void stopAccelerating()
	{
		getRocketController().stopAccelerate();
	}

	private void stopTurningRight()
	{
		getRocketController().stopTurnRight();
		
	}

	private void stopTurningLeft()
	{
		getRocketController().stopTurnLeft();
	}



}
