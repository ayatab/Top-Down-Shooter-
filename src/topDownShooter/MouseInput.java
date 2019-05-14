package topDownShooter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private UpdateHandler handler;
	private GameCamera camera;
	
	public MouseInput(UpdateHandler handler, GameCamera camera)
	{
		this.handler = handler;
		this.camera = camera;
		
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mouseX = (int) (e.getX() + camera.getX());
		int mouseY = (int) (e.getY() + camera.getY());
		//adding camera position because the position of the camera of the map is where the actual position is, and the position of hte mouse depends on the camera position
	
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject obj = handler.object.get(i);
			
			if(obj.getId() == ID.Player)
			{
				handler.addObject(new Bullet(obj.getX()+16, obj.getY() + 24, ID.Bullet, handler, mouseX, mouseY));
			}
		}
		
	}
}
