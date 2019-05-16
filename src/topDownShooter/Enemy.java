package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject
{
	private UpdateHandler handler;

	Random r = new Random();
	int chooseNum = 0;
	int hp = 100;


	public Enemy(int x, int y, ID id, UpdateHandler handler) {
		super(x, y, id, handler);
		this.handler = handler;
	}


	public void tick() {
		x += velX;
		x += velY;

		chooseNum = r.nextInt(10);

		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject obj = handler.object.get(i);

			if(obj.getId() == ID.Block)
			{
				if(getWallBounds().intersects(obj.getBounds()))
				{
					x += (velX*5) * -1;
					y += (velY *5) * -1;
					velX *= -1;
					velY *= -1;
				}
				else if(chooseNum == 0)
				{
					velX = (r.nextInt(4 - - 4) + -4);
					velX = (r.nextInt(4 - - 4) + -4);

				}
			}

			if(obj.getId() == ID.Bullet)
			{
				if(getBounds().intersects(obj.getBounds()))
				{
					
					hp -= 20;
					handler.removeObject(obj);
//					System.out.println("hp is at " + hp);
					
				}
			}


		}
		if(hp <= 0)
		{
			handler.removeObject(this);
		}


	}


	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 50, 50);
		

	}
	public int getHp() {
		return hp;
	}


	public void setHp(int enemy_hp) {
		hp = enemy_hp;
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 50, 50);
	}

	public Rectangle getWallBounds()
	{
		return new Rectangle(x, y, 60, 60);
	}
	

}
