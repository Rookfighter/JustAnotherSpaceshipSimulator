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
		if(p_event.getKeyCode() == KeyEvent.VK_W)
			accelerate();
		if(p_event.getKeyCode() == KeyEvent.VK_A)
			turnLeft();
		if(p_event.getKeyCode() == KeyEvent.VK_D)
			turnRight();
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
