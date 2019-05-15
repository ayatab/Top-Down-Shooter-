package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ShooterMan extends GameObject{

	UpdateHandler handler; 
	Game game;
	private int hp = 100;
	
	public ShooterMan(int x, int y, ID id, UpdateHandler handler, Game game) {
		super(x, y, id, handler);
		this.handler = handler;
		this.game = game;

		
		
	}


	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		//movement of character
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = 5;
		else if(!handler.isUp()) velY = 0;

		if(handler.isRight()) velX = 5;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX = -5;
		else if(!handler.isRight()) velX = 0;
		
	}
	private void collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject obj = handler.object.get(i);
			
			if(obj.getId() == ID.Block)
			{
				if(getBounds().intersects(obj.getBounds()))
				{
					x += velX * -1;
					y += velY * -1;
				}
			}
			
			if(obj.getId() == ID.Enemy)
			{
				if(getBounds().intersects(obj.getBounds()))
				hp -= 5;
			}
			if(obj.getId() == ID.AmmoBox)
			{
				if(getBounds().intersects(obj.getBounds()))
				{
					game.ammo += 10;
					handler.removeObject(obj);
				}
			}
			
			
		}
		if(hp <= 0)
		{
			
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 32, 64);
		
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, 32, 64);
	}

}
