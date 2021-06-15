package rpg_game;

public class Water2 extends Tile{
	
	public Water2(int id) {
		super(Assets.water[1], id); 
	}
	
	@Override
	public boolean isSolid() {
		return true; 
	}

}

