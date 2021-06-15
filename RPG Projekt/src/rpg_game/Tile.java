package rpg_game; 

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	
	public static Tile[] tiles = new Tile[256]; 
	public static Tile grassTile = new Grass(0); 
	public static Tile dirtTile = new Dirt(1);
	public static Tile rockTile = new Rocks(2); 
	public static Tile roadTile = new Road(3);
	public static Tile roadTileBeg = new RoadBeg(4); 
	public static Tile roadTileDown = new RoadTurnDown(5);
	public static Tile roadTileUp = new RoadTurnUp(6);
	public static Tile roadTileEnd = new RoadEnd(7);
	public static Tile waterTile = new Water(8);
	public static Tile waterTile2 = new Water2(9);

	
	
	
	
	
	public static final int TILEWIDTH = 64; 
	public static final int TILEHEIGHT = 64; 
	protected BufferedImage texture; 
	protected  final int id; 
	
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture; 
		this.id = id;  
		
		tiles[id] = this; 
		
	}
	

	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false; 
	}
	
	public int getId() {
		return id; 
	}
}
