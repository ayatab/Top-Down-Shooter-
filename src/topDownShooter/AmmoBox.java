package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AmmoBox extends GameObject{

	public AmmoBox(int x, int y, ID id, UpdateHandler handler) {
		super(x, y, id, handler);

	}


	public void tick() {
		
		
	}


	public void render(Graphics g) {

		g.setColor(Color.ORANGE);
		g.fillRect(x, y, 50, 50);
	}


	public Rectangle getBounds() {

		return new Rectangle(x, y, 50, 50);
	}
	
}
