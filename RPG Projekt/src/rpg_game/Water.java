package rpg_game;

public class Water extends Tile{
	
	public Water(int id) {
		super(Assets.water[0], id); 
	}
	
	@Override
	public boolean isSolid() {
		return true; 
	}

}
