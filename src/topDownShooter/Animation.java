package topDownShooter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] images;
	private int interval;
	private int index;
	private long timer;
	private long timeNow;
	private long timeBefore;
	
	public Animation(BufferedImage[] images, int interval)
	{
		this.images = images;
		this.interval = interval;
		index = 0;
		timer = 0;
		timeNow = 0;
		timeBefore = System.currentTimeMillis();
		
	}
	public void tick()
	{
		timeNow = System.currentTimeMillis();
		timer += timeNow - timeBefore;
		timeBefore = timeNow;
	
		if(timer >= interval)
		{
			index++;
			timer = 0;
		}
		
		if(index >= images.length)
		{
			index = 0;
		}
	}
	
	public void render(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage(images[index], x, y, null);
	}
	
	
}
