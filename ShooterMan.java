package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ShooterMan extends GameObject{

	UpdateHandler handler; 
	
	public ShooterMan(int x, int y, ID id, UpdateHandler handler) {
		super(x, y, id);
		this.handler = handler;

	}


	public void tick() {
		x += velX;
		y += velY;
		
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


	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 50, 100);
		
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, 50, 100);
	}

}
