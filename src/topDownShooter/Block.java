package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject 
{
	private BufferedImage wall_image;
	private Game game;
	public Block(int x, int y, ID id, UpdateHandler handler, Game game) {
		super(x, y, id, handler);
		this.game = game;
		//ImageLoader loader = new ImageLoader();
		
		wall_image = game.wall_image1;
				//loader.loadImage("/wall2.png");

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
