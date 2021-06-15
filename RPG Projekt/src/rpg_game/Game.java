package rpg_game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;



public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	
	//Input
	private Keys keys;
	
	//Camera 
	private Camera camera; 
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keys = new Keys();
	}
	
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keys);
		Assets.init();
		
		handler = new Handler(this); 
		camera = new Camera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new Menu(handler);
		State.setState(gameState);
	}
	
	private void tick(){
		keys.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		g.clearRect(0, 0, width, height);
		
		if(State.getState() != null)
			State.getState().render(g);
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			
		}
		
		stop();
		
	}
	
	public Keys getKeys(){
		return keys;
	}
	
	public Camera getCamera() {
		return camera; 
	}
	
	private Handler handler; 
	
	public int getWidth() {
		return width; 
	}
	
	public int getHeight() {
		return height; 
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

