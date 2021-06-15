package rpg_game;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32; 
 
	public static BufferedImage grass, roadBeg, road, roadTurn1, roadTurn2, roadEnd, blast, wall1, wall2, tree, rock, enemy; 
	public static BufferedImage[] player_down, player_up, player_right, player_left, player_idle, water; 
	
	
	public static void init() {
		SheetCrop sheet = new SheetCrop(ImgLoader.loadImage("/textures/sheet.png"));
		
		water = new BufferedImage[2]; 
		player_idle = new BufferedImage[2]; 
		player_down = new BufferedImage[4]; 
		player_up = new BufferedImage[4]; 
		player_right = new BufferedImage[4]; 
		player_left = new BufferedImage[4]; 
		
		water[0] = sheet.crop(width * 4, height * 3, width, height); 
		water[1] = sheet.crop(width * 5, height * 3, width, height); 
		
		player_idle[0] = sheet.crop(0, height, width, height); 
		player_idle[1] = sheet.crop(width, height * 5, width, height); 
		
		
		
		player_down[0] = sheet.crop(0, height, width, height);
		player_down[1] = sheet.crop(width, height, width, height);
		player_down[2] = sheet.crop(width * 2, height, width, height);
		player_down[3] = sheet.crop(width * 3, height, width, height); 
		
		player_up[0] = sheet.crop(0, height * 2, width, height);
		player_up[1] = sheet.crop(width, height * 2, width, height);
		player_up[2] = sheet.crop(width * 2, height * 2, width, height);
		player_up[3] = sheet.crop(width * 3, height * 2, width, height);

		player_right[0] = sheet.crop(0, height * 3, width, height);
		player_right[1] = sheet.crop(width, height * 3, width, height);
		player_right[2] = sheet.crop(width * 2, height * 3, width, height);
		player_right[3] = sheet.crop(width * 3, height * 3, width, height);

		player_left[0] = sheet.crop(0, height * 4, width, height);
		player_left[1] = sheet.crop(width, height * 4, width, height);
		player_left[2] = sheet.crop(width * 2, height * 4, width, height);
		player_left[3] = sheet.crop(width * 3, height * 4, width, height);

		
		
		grass = sheet.crop(0, 0, width, height);
		roadBeg = sheet.crop(width, 0, width, height);
		road = sheet.crop(width * 2, 0, width, height); 
		roadTurn1 = sheet.crop(width * 3, 0, width, height); 
		roadTurn2 = sheet.crop(width * 4, 0, width, height); 
		roadEnd = sheet.crop(width * 5, 0, width, height); 
		blast = sheet.crop(0, height * 5, width, height); 
		wall1 = sheet.crop(width * 4, height, width, height); 
		wall2 = sheet.crop(width * 5, height, width, height); 
		tree = sheet.crop(width * 4, height * 2, width, height); 
		rock = sheet.crop(width * 5, height * 2, width, height); 
		enemy = sheet.crop(width * 4, height * 4, width, height); 
	}
}
