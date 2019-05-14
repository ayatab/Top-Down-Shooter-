package topDownShooter;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject 
{
	
	protected int x, y;
	//speed at which our object is going, positive is up or right
	protected float velX = 0, velY = 0;
	protected ID id;
	private UpdateHandler handler;
	
	public GameObject(int x, int y, ID id, UpdateHandler handler)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		
	}
	


	public abstract void tick();
	//needs to update
	public abstract void render(Graphics g);
	//needs to render an object
	public abstract Rectangle getBounds();
	//collision detection

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	
}
