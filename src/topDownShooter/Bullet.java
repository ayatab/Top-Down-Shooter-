package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.reflect.Field;

public class Bullet extends GameObject {

	private UpdateHandler handler;
	public boolean didHit;

	public Bullet(int x, int y, ID id, UpdateHandler handler, int mouseX, int mouseY) {
		super(x, y, id, handler);
		this.handler = handler;
		// TODO Auto-generated constructor stub

		calculateVelocity(x, y, mouseX, mouseY);
	}

	@Override
	public void tick() {

		x += velX;
		y += velY;

		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject obj = handler.object.get(i);

			if(obj.getId() == ID.Block)
			{
				if(getBounds().intersects(obj.getBounds()))
				{
					handler.removeObject(this);
				}
			}
//			if(obj.getId() == ID.Enemy)
//			{
//				
//				if(getBounds().intersects(obj.getBounds()))
//				{
//					didHit = true;
//					handler.removeObject(this);
//				}
//			}
			// add enemy hit
		}
	}

	public void calculateVelocity(int fromX, int fromY, int toX, int toY)
	{
		double distance = Math.sqrt(Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
		double speed = 10; //set the speed in [2,n)  n should be < 20 for normal speed
		//find Y
		velY = (float)((toY - fromY) * speed / distance);
		//find X
		velX = (float)((toX - fromX) * speed / distance);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.yellow);
		g.fillOval(x, y, 10, 10);
	}
	
	

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 10, 10);
	}
}
