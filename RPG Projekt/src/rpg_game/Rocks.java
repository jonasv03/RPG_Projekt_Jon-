package rpg_game;

import java.awt.image.BufferedImage;

public class Rocks extends Tile {

	public Rocks(int id) {
		super(Assets.wall1, id); 
	}
	
	@Override
	public boolean isSolid() {
		return true; 
	}

	
}
