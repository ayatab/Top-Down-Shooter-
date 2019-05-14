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
		y += ((obj.getY() - y)- 563/2) * 0.05f; 
		
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
