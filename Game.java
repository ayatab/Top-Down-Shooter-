package topDownShooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	public boolean isRunning = false;
	private Thread thread;
	private UpdateHandler handler;

	public Game()
	{
		new GameWindow(1000, 1000, "Top Down Shooter", this);
		start();
		handler = new UpdateHandler();
		
		//handler.addObject(new Box(100, 100, ID.Block));
		handler.addObject(new ShooterMan(100, 100, ID.Player, handler));
		
		this.addKeyListener(new KeyInput(handler));
	}
	//ask
	public void start()
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	public void stop()
	{
		isRunning = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	//minecraft dev code
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	//updates everything in the game
	public void tick()
	{
		handler.tick();
	}

	//renders everything in the game, around 2000 times a second
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		/////////////////graphics for the game////////////////////

		g.setColor(Color.gray);
		g.fillRect(0, 0, 1000, 1000);

		handler.render(g);
		//shows the objects above background because it renders after our background

		/////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public static void main(String args[])
	{
		new Game();
	}
}
