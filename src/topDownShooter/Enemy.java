package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject
{
	private UpdateHandler handler;

	private BufferedImage[] blob;
	private Animation animation;
	Game game;

	Random r = new Random();
	int chooseNum = 0;
	int hp = 100;


	public Enemy(int x, int y, ID id, UpdateHandler handler, Game game) {
		super(x, y, id, handler);
		this.handler = handler;
		this.game = game;


		blob = game.blobAnim;
		animation = new Animation(blob, 100);
	}


	public void tick() {

		//x += velX;
		//x += velY;

		animation.tick();

		chooseNum = r.nextInt(10);

		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject obj = handler.object.get(i);



			if(obj.getId() == ID.Bullet)
			{
				if(getBounds().intersects(obj.getBounds()))
				{

					hp -= 20;
					handler.removeObject(obj);
					//					System.out.println("hp is at " + hp);

				}
			}

			if(obj.getId() == ID.Player)
			{
				if((Math.abs(obj.getX()-x) <= 700) && (Math.abs(obj.getY()-y) <= 700))
				{
					int xAngle = (int) ((obj.getX() - x) / ((Math.abs(obj.getX() - x) + 1) * .5));
					int yAngle = (int) ((obj.getY() - y) / ((Math.abs(obj.getY() - y) + 1) * .5));

					//double angle = Math.atan2(yAngle, xAngle);

					x += xAngle;
					//(1 * Math.cos(angle));
					y += yAngle;
					//(1 * Math.sin(angle));

				}	
			}
			if(obj.getId() == ID.Block)
			{
				if(getWallBounds().intersects(obj.getBounds()))
				{
					x += (obj.getX() - x) * -.1;
					y += (obj.getY() - y) * -.1;

				}
				
			}
			if(obj.getId() == ID.Enemy)
			{
				if(getWallBounds().intersects(obj.getBounds()))
				{
					x += (obj.getX() - x) * -.1;
					y += (obj.getY() - y) * -.1;

				}
				
			}
		}



		if(hp <= 0)
		{
			handler.removeObject(this);
		}



	}


	public void render(Graphics g) {
		//		g.setColor(Color.blue);
		//		g.fillRect(x, y, 50, 50);
		animation.render(g, x, y, 50, 50);


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
