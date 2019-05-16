package topDownShooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	public boolean isRunning = false;
	private Thread thread;
	private UpdateHandler handler;
	public GameCamera camera;

	private BufferedImage level_background = null;
	private BufferedImage floor_image = null;
	public BufferedImage wall_image1 = null;
	public BufferedImage[] dittoAnim = new BufferedImage[4];
	public BufferedImage[] blobAnim = new BufferedImage[4];

	public int ammo = 100;
	public int ShooterManHP = 100;

	public Game()
	{


		handler = new UpdateHandler();
		camera = new GameCamera(0, 0);

		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, this));


		ImageLoader loader = new ImageLoader();
		level_background = loader.loadImage("/gameLevel2.png");
		floor_image = loader.loadImage("/floor2.jpg");
		wall_image1 = loader.loadImage("/wall.jpg");
		
		//shooter ditto
		
		dittoAnim[0] = loader.loadImage("/ditto1.png");
		dittoAnim[1] = loader.loadImage("/ditto2.png");
		dittoAnim[2] = loader.loadImage("/ditto3.png");
		dittoAnim[3] = loader.loadImage("/ditto4.png");
		
		blobAnim[0] = loader.loadImage("/blob1.png");
		blobAnim[1] = loader.loadImage("/blob2.png");
		blobAnim[2] = loader.loadImage("/blob3.png");
		blobAnim[3] = loader.loadImage("/blob4.png");
		
		
		

		//handler.addObject(new Box(100, 100, ID.Block));
		//handler.addObject(new ShooterMan(100, 100, ID.Player, handler));
		loadLevel(level_background);

		new GameWindow(1000, 1000, "Top Down Shooter", this);
		start();


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
		for(int i = 0; i < handler.object.size(); i++)
		{
			if(handler.object.get(i).getId() == ID.Player)
			{
				camera.tick(handler.object.get(i));
			}
		}
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
		Graphics2D g2d = (Graphics2D) g;
		/////////////////graphics for the game////////////////////


		g.setColor(Color.gray);
		g.fillRect(0, 0, 1000, 1000);

		g2d.translate(-camera.getX(), -camera.getY());

		for(int i = 0; i < 30*72; i +=32)
		{
			for(int j = 0; j < 30*72; j+= 32)
			{
				g.drawImage(floor_image, i, j, null);
			}
		}

		handler.render(g);

		g2d.translate(camera.getX(), camera.getY());
		//shows the objects above background because it renders after our background

		/////////////////////////////////////
		g.dispose();
		bs.show();
	}
	private void loadLevel(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();

		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255)
				{
					handler.addObject(new Block(i*32, j*32, ID.Block, handler, this));
				}
				if(blue == 255 && green == 0)
				{
					handler.addObject(new ShooterMan(i*32, j*32, ID.Player, handler, this));
				}
				if (green == 255 && blue == 0)
				{
					handler.addObject(new Enemy(i*32, j*32, ID.Enemy, handler, this));
				}
				if (green == 255 && blue == 255)
				{
					handler.addObject(new AmmoBox(i*32, j*32, ID.AmmoBox, handler));
				}

			}
		}
	}
	public static void main(String args[])
	{
		new Game();
	}
}
