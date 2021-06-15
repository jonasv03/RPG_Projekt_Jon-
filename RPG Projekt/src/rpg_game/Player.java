package rpg_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends Alive {

	private Animation downAnim;
	private Animation upAnim;
	private Animation rightAnim;
	private Animation leftAnim;
	private Animation idleAnim; 	
	
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown; 
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Alive.DEFAULT_WIDTH_CREATURE, Alive.DEFAULT_HEIGHT_CREATURE); 
		
		bounds.x = 28; 
		bounds.y = 32; 
		bounds.width = 16; 
		bounds.height = 32; 
		
		idleAnim = new Animation(500, Assets.player_idle); 
		downAnim = new Animation(250, Assets.player_down); 
		upAnim = new Animation(250, Assets.player_up); 
		rightAnim = new Animation(250, Assets.player_right); 
		leftAnim = new Animation(250, Assets.player_left); 
	}

	@Override
	public void tick() {
		idleAnim.tick(); 
		downAnim.tick(); 
		upAnim.tick(); 
		rightAnim.tick(); 
		leftAnim.tick(); 

		
		
		getInput(); 
		move(); 
		handler.getCamera().centerOnCharacter(this);
		
		checkAttacks(); 
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer; 
		lastAttackTimer = System.currentTimeMillis(); 
		if(attackTimer < attackCooldown) return; 
		
		
		Rectangle cb = getCollisionBounds(0, 0); 
		Rectangle ar = new Rectangle(); 
		int arSize = 20; 
		ar.width = arSize; 
		ar.height = arSize; 
		
		if(handler.getKeys().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2; 
			ar.y = cb.y - arSize; 
		}
		if(handler.getKeys().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2; 
			ar.y = cb.y + cb.height; 
		}
		if(handler.getKeys().aRight) {
			ar.x = cb.x + cb.width; 
			ar.y = cb.y + cb.height / 2 - arSize / 2; 
		}
		if(handler.getKeys().aLeft) {
			ar.x = cb.x - arSize; 
			ar.y = cb.y + cb.height / 2 - arSize / 2; 
		}else { 
			return; 
		}
		
		attackTimer = 0; 
		
		for(Entity e : handler.getMap().getEntityManager().getEntities()) {
			if(e.equals(this)) continue; 
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return; 
			}
		}
	}
	
	public void die() {
		
	}
	
	private void getInput() {
		xMove = 0; 
		yMove = 0; 
		
		if(handler.getKeys().up) {
			yMove = -speed; 
		}
		if(handler.getKeys().down) {
			yMove = speed; 
		}
		if(handler.getKeys().right) {
			xMove = speed; 
		}
		if(handler.getKeys().left) {
			xMove = -speed; 
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()),  width, height, null); 
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) { 
			return leftAnim.getDirection();
		}else if(xMove > 0) {
			return rightAnim.getDirection();
		}else if(yMove < 0) {
			return upAnim.getDirection();
		}else if(yMove > 0) {
			return downAnim.getDirection(); 
		}
		else {
			return idleAnim.getDirection(); 
 		}
	}
	
}
