package rpg_game;

import java.awt.image.BufferedImage;

public class SheetCrop {
	private BufferedImage sheet; 
	
	public SheetCrop(BufferedImage sheet) {
		this.sheet = sheet; 
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {  
		return sheet.getSubimage(x, y, width, height); 
	}
} 
