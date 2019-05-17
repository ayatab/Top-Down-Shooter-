package topDownShooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class AmmoBox extends GameObject{


	UpdateHandler handler;
	Game game;
	BufferedImage AmmoBox_image;

	public AmmoBox(int x, int y, ID id, UpdateHandler handler, Game game) {
		super(x, y, id, handler);
		this.handler = handler;
		this.game = game;
		AmmoBox_image = game.getAmmoCrateImage();

	}


	public void tick() {
		if(Game.isRemoveBox())
			for(int i = 0; i < handler.object.size(); i++)
			{
				GameObject obj = handler.object.get(i);
				if(obj.getId() == ID.AmmoBox)
				{
					{
						handler.removeObject(obj);
					}
				}
			}

	}


	public void render(Graphics g) {
		g.drawImage(AmmoBox_image, x, y, null);
		//g.setColor(Color.ORANGE);
		//g.fillRect(x, y, 50, 50);
	}


	public Rectangle getBounds() {

		return new Rectangle(x, y, 50, 50);
	}


}
