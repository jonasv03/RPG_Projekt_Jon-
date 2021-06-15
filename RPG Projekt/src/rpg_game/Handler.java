package rpg_game;

public class Handler {

	private Game game; 
	
	public Handler(Game game) { 
		this.game = game; 
		
	} 
	
	public Camera getCamera() {
		return game.getCamera(); 
	}
	
	public Keys getKeys() {
		return game.getKeys(); 	
	} 
	
	public int getWidth() { 
		return game.getWidth(); 
	}
	
	public int getHeight() {
		return game.getHeight(); 
	}
	
	public Game getGame() {
		return game;
	}
	
	
	

	public void setGame(Game game) {
		this.game = game;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	private Map map; 
	

}
