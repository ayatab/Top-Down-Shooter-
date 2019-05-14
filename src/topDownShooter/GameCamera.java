package topDownShooter;

public class GameCamera 
{
	private float x, y;
	
	public GameCamera(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject obj)
	{
		x += ((obj.getX() - x)- 1000/2) * 0.05f; 
		y += ((obj.getY() - y)- 1000/2) * 0.05f; 
		
		if(x <= 0)
		{
			x=0;
		}
		if(x >= 1050)
		{
			x= 1050;
		}
		if(y <= 0)
		{
			y = 0;
		}
		if(y >= 1100)
		{
			y = 1100;
		}
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
