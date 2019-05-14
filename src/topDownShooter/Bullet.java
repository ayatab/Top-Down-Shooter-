package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

	private UpdateHandler handler;
	
	public Bullet(int x, int y, ID id, UpdateHandler handler, int mouseX, int mouseY) {
		super(x, y, id, handler);
		this.handler = handler;
		// TODO Auto-generated constructor stub
		
		velX = (mouseX - x) / 10;
		velY = (mouseY - y) / 10;
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
		}
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
