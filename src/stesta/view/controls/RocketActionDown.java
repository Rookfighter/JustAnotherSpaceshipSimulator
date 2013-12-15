package stesta.view.controls;

import java.awt.event.KeyEvent;

public class RocketActionDown extends ARocketAction{

	public RocketActionDown(RocketKeyContainer p_keyContainer)
	{
		super(p_keyContainer);
	}

	@Override
	public void process(KeyEvent p_event)
	{
		switch(p_event.getKeyCode())
		{
			case KeyEvent.VK_A:
				turnLeft();
				break;
			case KeyEvent.VK_D:	
				turnRight();
				break;
			case KeyEvent.VK_W:
				accelerate();
		}
	}
	
	private void accelerate()
	{
		getRocketController().accelerate();
	}
	
	private void turnLeft()
	{
		getRocketController().turnLeft();
	}
	
	private void turnRight()
	{
		getRocketController().turnRight();
	}

}
