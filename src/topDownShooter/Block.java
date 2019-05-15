package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject 
{
	private BufferedImage wall_image;
	public Block(int x, int y, ID id, UpdateHandler handler) {
		super(x, y, id, handler);
		ImageLoader loader = new ImageLoader();
		
		wall_image = loader.loadImage("/wall2.png");

	}


	public void tick() {

		
	}


	public void render(Graphics g) {
		g.drawImage(wall_image, x, y, null);
		//g.setColor(Color.black);
		//g.fillRect(x, y, 32, 32);
		
	}


	public Rectangle getBounds() {

		return new Rectangle(x, y, 32, 32);
	}

}
