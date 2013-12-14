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
			case KeyEvent.VK_D:	
				stopTurning();
				break;
		}
	}

	private void stopTurning()
	{
		getRocketController().stopTurning();
	}


}
