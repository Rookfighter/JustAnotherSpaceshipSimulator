package stesta.view.panel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import stesta.controller.rocket.IRocketController;
import stesta.entities.objects.IAsteroid;
import stesta.entities.objects.IMovingSpaceObject;
import stesta.entities.objects.IRocket;
import stesta.entities.objects.ISpaceObject;
import stesta.view.drawable.StarField;
import stesta.view.hud.ISpaceHUD;
import stesta.view.hud.classes.SpaceHUD;
import stesta.view.multimedia.MultimediaRocket;
import lib.graphics.IDrawable;
import lib.graphics.panel.DrawOrderComparator;
import lib.graphics.panel.IDrawListGenerator;
import lib.graphics.sprites.ISprite;
import lib.utils.DeltaTime;
import lib.utils.TimeAccount;
import lib.utils.doubl.Dimension2DF;
import lib.utils.integer.Dimension2DI;
import lib.utils.integer.Position2DI;

public class SpaceDrawListGenerator implements IDrawListGenerator{
	
	private static final double DEF_SIZE_FACTOR = 32.0f;
	private static final int DEF_BUFF_PIXELS = 100;
	
	private IRocketController player;
	
	private ISpaceHUD hud;
	private StarField starField;
	private Dimension2DF bufferedFoV;
	
	private SpriteMap spriteMap;
	
	private double sizeFactor;
	
	private DrawOrderComparator comparator;
	
	private DeltaTime delta;
	private TimeAccount timeAccount;
	
	public SpaceDrawListGenerator(final IRocketController p_player)
	{
		player = p_player;
		hud = new SpaceHUD(p_player);
		starField = new StarField();
		bufferedFoV = new Dimension2DF();
		spriteMap = new SpriteMap();
		sizeFactor = DEF_SIZE_FACTOR;
		comparator = new DrawOrderComparator();
	}
	
	@Override
	public List<IDrawable> generateDrawList()
	{
		List<IDrawable> result = new LinkedList<IDrawable>();
		addObjectSprites(result);
		addStaticObjects(result);
		Collections.sort(result, comparator);
		return result;
	}
	
	private void addObjectSprites(final List<IDrawable> p_list)
	{
		ISprite sprite;
		for(ISpaceObject  object : player.getObjectsInRect(bufferedFoV))
		{
			sprite = spriteMap.get(object);
			setStatus(object, sprite);
			p_list.add(sprite);
		}
	}
	
	private void setStatus(final ISpaceObject p_object, final ISprite p_sprite)
	{
		if(p_object instanceof IRocket)
			setRocketStatus((IRocket) p_object, (MultimediaRocket) p_sprite);
		else
			setUnknownStatus(p_object, p_sprite);
	}
	
	private void setRocketStatus(final IRocket p_rocket, final MultimediaRocket p_multimedia)
	{
		setSpritePosition(p_rocket, p_multimedia);
		estimateMovement(p_rocket, p_multimedia);
		setSpriteRotation(p_rocket, p_multimedia);
		setSpriteDimension(p_rocket, p_multimedia);
		setRocketAnimation(p_rocket, p_multimedia);
		p_multimedia.setDefaultDrawOrder();
	}
	
	private void setRocketAnimation(final IRocket p_rocket, final MultimediaRocket p_multimedia)
	{
		if(p_rocket.getAccelerateForce() > 0)
		{
			if(!p_multimedia.getToDraw().equals(p_multimedia.getAnimation(MultimediaRocket.ANIMATION_ROCKET_MOVEMENT)))
				p_multimedia.setAnimationToDraw(MultimediaRocket.ANIMATION_ROCKET_MOVEMENT);
		}
		else
			p_multimedia.setSpriteToDraw(MultimediaRocket.SPRITE_ROCKET_OFF);
	}
	
	private void setUnknownStatus(final ISpaceObject p_object, final ISprite p_sprite)
	{
		setSpritePosition(p_object, p_sprite);
		if(p_object instanceof IMovingSpaceObject)
			estimateMovement((IMovingSpaceObject) p_object, p_sprite);
			
		setSpriteRotation(p_object, p_sprite);
		setSpriteDimension(p_object, p_sprite);
		p_sprite.setDefaultDrawOrder();
	}
	
	private void setSpritePosition(final ISpaceObject p_object, final ISprite p_sprite)
	{
		float diffx =  p_object.getPosition().x - player.getControlledObject().getPosition().x;
		float diffy =  p_object.getPosition().y - player.getControlledObject().getPosition().y;
		int x = (int) (sizeFactor * diffx +  hud.getHudDimension().Width() / 2 - p_sprite.getDimension().Width() / 2);
		int y = (int) (sizeFactor * diffy + hud.getHudDimension().Height() / 2 - p_sprite.getDimension().Height() / 2);
		
		p_sprite.getPosition().set(x, y);
	}
	
	private void estimateMovement(final IMovingSpaceObject p_object, final ISprite p_sprite)
	{
		//TODO code does not work properly
		//Vec2 velocity = p_object.getVelocity();
		//float factor = (float) (timeAccount.valueMilli()) / (float) (timeAccount.getStepMilli());
		//int x = p_sprite.getPosition().X() + (int) ((velocity.x * factor * sizeFactor) / timeAccount.getStepMilli());
		//int y = p_sprite.getPosition().Y() + (int) ((velocity.y * factor * sizeFactor)  / timeAccount.getStepMilli());
		
		//p_sprite.getPosition().set(x, y);
	}
	
	private void setSpriteRotation(final ISpaceObject p_object, final ISprite p_sprite)
	{
		p_sprite.setRotation(p_object.getBody().getAngle() + Math.toRadians(90));
	}
	
	private void setSpriteDimension(final ISpaceObject p_object, final ISprite p_sprite)
	{
		if(p_object instanceof IAsteroid)
			setAsteroidDimension((IAsteroid) p_object, p_sprite);
		else if(p_object instanceof IRocket)
			setRocketDimension((IRocket) p_object, p_sprite);
	}
	
	private void setAsteroidDimension(final IAsteroid p_asteroid, final ISprite p_sprite)
	{
		p_sprite.getDimension().set((int) (p_asteroid.getRadius() * sizeFactor * 2), (int) (p_asteroid.getRadius() * sizeFactor * 2));
	}
	
	private void setRocketDimension(final IRocket p_rocket, final ISprite p_sprite)
	{
		p_sprite.getDimension().set((int) (p_rocket.getRadius() * sizeFactor * 2), (int) (p_rocket.getRadius() * sizeFactor * 2));
	}
	
	private void addStaticObjects(final List<IDrawable> p_list)
	{
		calcStarfieldPosition();
		hud.update();
		
		p_list.add(starField);
		p_list.add(hud);
	}
	
	private void calcStarfieldPosition()
	{
		int x = (int) (player.getControlledObject().getPosition().x * sizeFactor);
		int y = (int) (player.getControlledObject().getPosition().y * sizeFactor);
		starField.assignPosition(new Position2DI(x,y));
	}
	
	public void setDimension(final Dimension2DI p_dimension)
	{
		hud.setHudDimension(new Dimension2DI(p_dimension.Width(), p_dimension.Height()));
		starField.assignDimension(hud.getHudDimension());
		
		double bufferedWidth = (double) (p_dimension.Width() + DEF_BUFF_PIXELS)  / (double) (sizeFactor);
		double bufferedHeight = (double) (p_dimension.Height() + DEF_BUFF_PIXELS) / (double) (sizeFactor);
		bufferedFoV.set(bufferedWidth, bufferedHeight);
	}

	@Override
	public void setDeltaTime(final DeltaTime p_delta)
	{
		delta = p_delta;
		spriteMap.setDeltaTime(delta);
	}

	@Override
	public void setTimeAccount(final TimeAccount p_account)
	{
		timeAccount = p_account;
	}

}
