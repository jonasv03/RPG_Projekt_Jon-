package rpg_game;

import java.awt.Graphics;

public class GameState extends State {

	private Map map; 

	
	public GameState(Handler handler) { 
		super(handler); 
		map = new Map(handler, "res/maps/map1.txt"); 
		handler.setMap(map); 
		
	}
	
	@Override
	public void tick() {
		map.tick(); 

	}

	@Override
	public void render(Graphics g) {
		map.render(g); 

	}

	
}
