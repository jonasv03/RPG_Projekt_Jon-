package rpg_game;

import java.awt.Graphics;

public class Map {

	private Handler handler; 
	private int width, height; 
	private int spawnX, spawnY; 
	private int[][] tiles; 
	
	private EntityManager entityManager; 
	
	public Map(Handler handler, String path) {
		this.handler = handler; 
		entityManager = new EntityManager(handler, new Player(handler, 100, 100)); 
		entityManager.addEntity(new Tree(handler, 500, 350, width, height));
		entityManager.addEntity(new Tree(handler, 300, 800, width, height));
		entityManager.addEntity(new Enemy(handler, 600, 400, width, height));
		entityManager.addEntity(new Rock(handler, 200, 500, width, height));
		entityManager.addEntity(new Enemy(handler, 1000, 400, width, height));
		entityManager.addEntity(new Enemy(handler, 800, 400, width, height));
		
		loadWorld(path); 
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY); 
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void tick() {
		entityManager.tick(); 
	}
	
	public void render(Graphics g) { 
		int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILEWIDTH); 
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);   
		int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILEHEIGHT);  
		int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);   		
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getCamera().getxOffset()),
							  			(int) (y * Tile.TILEHEIGHT - handler.getCamera().getyOffset()));  
			}
		}
		entityManager.render(g); 
	}
	
	public Tile getTile(int x, int y ) {
		if(x < 0 || y < 0 || x>= width || y>= height) { 
			return Tile.grassTile; 
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.dirtTile; 
		} 
		return t; 
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path); 
		String[] tokens = file.split("\\s+"); 
		width = Utils.parseInt(tokens[0]); 
		height = Utils.parseInt(tokens[1]); 
		spawnX = Utils.parseInt(tokens[2]); 
		spawnY = Utils.parseInt(tokens[3]); 
		
		tiles = new int[width][height]; 
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); 
			}
		}
		
	}
	
	public int getWidth() {
		return width; 
	}
	
	public int getHeight() {
		return height; 
	}
	
}
