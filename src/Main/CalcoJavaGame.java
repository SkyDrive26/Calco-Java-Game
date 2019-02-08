package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class CalcoJavaGame extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	
	public CalcoJavaGame() {

		//new Window(1000, 563, "Hello World", this);
		this.setBackground(Color.CYAN);
		//start();
		
		handler = new Handler();
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, this));
		
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
		
		bs.show();
	}
}