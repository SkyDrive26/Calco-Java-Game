package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import GameObjects.Wall;
import GameObjects.Bush;
import GameObjects.Grass;
import GameObjects.Sand;
import Player.Player;
import Main.Animation;

public class CalcoJavaGame extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	private Thread thread;
	public Menu mainFrame;
	private Handler handler;
	private Camera camera;
	int PlayerHp = 5;
	public static Font myFont;
	
	private BufferedImage layerOne = null;
	private BufferedImage layerTwo = null;
	private BufferedImage layerThree = null;	
	
	private BufferedImage floor_sprite_sheet = null;
	private BufferedImage floor = null;
	private BufferedImage ObjectSpriteSheetImage = null;
	private BufferedImage wall = null;

	private BufferedImage hpAnimation[] = {Sprite.getSprite(1, 7), Sprite.getSprite(2, 7), Sprite.getSprite(3,7)};
	private BufferedImage bush = null;
	private BufferedImage sand = null;
	private BufferedImage grass = null;
	
	private SpriteSheet ObjectSpriteSheet;
	private SpriteSheet wallss;
	private SpriteSheet floorss;
	private Animation hpHearts = new Animation(hpAnimation, 10);
	
	public CalcoJavaGame(Menu mainFrame) {
		//start();
		this.mainFrame = mainFrame;
		handler = new Handler();
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, this));


		this.setBackground(Color.CYAN);
				
		BufferedImageLoader loader = new BufferedImageLoader();
    
		layerOne = loader.LoadImage("/Pngs/Layer_1.png");
		layerTwo = loader.LoadImage("/Pngs/Layer_2.png");
		layerThree = loader.LoadImage("/Pngs/Layer_3.png");
		
		
		floor_sprite_sheet = loader.LoadImage("/Pngs/Sprite_Sheet.png");
		ObjectSpriteSheetImage = loader.LoadImage("/Pngs/Sprite_Sheet_Objects.png");
		
		floorss = new SpriteSheet(floor_sprite_sheet);
		floor = floorss.grabImage(4, 2, 32, 32);

		wallss = new SpriteSheet(ObjectSpriteSheetImage);
		wall = wallss.grabImage(1, 8, 32, 32);
		bush = wallss.grabImage(2, 9, 32, 32);
		grass = wallss.grabImage(3, 9, 32, 32);
		sand = wallss.grabImage(2, 10, 32, 32);
/*
		level = loader.LoadImage("/Pngs/level_2.png");
		ObjectSpriteSheetImage = loader.LoadImage("/Pngs/Sprite_Sheet_Objects.png");

		ObjectSpriteSheet = new SpriteSheet(ObjectSpriteSheetImage);

		floor = ObjectSpriteSheet.grabImage(3, 7, 32, 32);
		wall = ObjectSpriteSheet.grabImage(1, 8, 32, 32);
LATEN STAAN BITTE*/
		//ObjectSpriteSheet = new SpriteSheet(sprite_sheet);
		hpAnimation= new BufferedImage[] {wallss.grabImage(2, 8, 32, 32),wallss.grabImage(3, 8, 32, 32),wallss.grabImage(4, 8, 32, 32), wallss.grabImage(3, 8, 32, 32)};
		hpHearts = new Animation(hpAnimation,10);
		
		
		loadLayerOne(layerOne);
		loadLayerTwo(layerTwo);
		loadLayerThree(layerThree);
	}
	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		
		handler.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;	
		///////////////////////////////            
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		for(int xx = 0; xx < 30*72; xx+=32) {
			for(int yy = 0; yy < 30*72; yy+=32) {
				g.drawImage(grass, xx, yy, null);
			}
		}	
    
		handler.render(g);
		g2d.translate(camera.getX(), camera.getY());
		
	    if(PlayerHp >= 1) {
			g.drawImage(this.hpHearts.getSprite(), 15,15,null);
	    }
	    if(PlayerHp >= 2) {
			g.drawImage(this.hpHearts.getSprite(), 45,15,null);

		}
	    if(PlayerHp >= 3) {
	    	g.drawImage(this.hpHearts.getSprite(), 75,15,null);
			}
	    if(PlayerHp >= 4) {
	    	g.drawImage(this.hpHearts.getSprite(),105,15,null);
			}
	    if(PlayerHp >= 5) {
	    	g.drawImage(this.hpHearts.getSprite(), 135,15,null);
			}
	    g.setFont (myFont);
	   
		
		///////////////////////////////
		g.dispose();
		
		
		
		bs.show();
	}
	//loading the level
	private void loadLayerOne(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		myFont = new Font ("Serif", Font.BOLD, 20);
			
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0;yy<h; yy++) {
				int pixel = image.getRGB (xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) &0xff;
				
				if(red == 0 && green == 100 && blue == 0)
					handler.addObject(new Grass(xx*32, yy*32, ID.Grass, this.grass));
				
				else if(red == 255 && green == 255 && blue == 0)
					handler.addObject(new Sand(xx*32, yy*32, ID.Sand, this.sand));
									
				}
			}
		}
	
	private void loadLayerTwo(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
			
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0;yy<h; yy++) {
				int pixel = image.getRGB (xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) &0xff;
									
				if(red == 0 && green == 255 && blue == 0)
					handler.addObject(new Wall(xx*32, yy*32, ID.Wall, this.wall));
				
				else if(red == 0 && green == 0 && blue == 255)
					handler.addObject(new Bush(xx*32, yy*32, ID.Bush, this.bush));
					
					
				}
			}
		camera.setCameraBounds(w, h);
		}
	
	private void loadLayerThree(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
			
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0;yy<h; yy++) {
				int pixel = image.getRGB (xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) &0xff;
			
				if(red == 255 && green == 0 && blue == 0)
					handler.addObject(new Player(xx*32, yy*32, ID.Player, handler, this, camera));
				
				}
			}
		}
	

	public void setIsRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
}