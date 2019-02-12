package Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Main.ID;
import inventory.Inventory;
import Main.CalcoJavaGame;


public class Player extends GameObjects.GameObject {
	
	
	Handler handler;
	CalcoJavaGame game;
	Color kleur = Color.RED; 
	
	
	public Player(int x, int y, ID id, Handler handler, CalcoJavaGame game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}
	
		
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(handler.isUp()) {
			velY = -5;						//Movement itself
			//animation = walkDown;			//What animation is needed
		    //animation.start();			// The animation itself
		}
		else if(!handler.isDown()) {
			velY = 0;
		}
		
		if (handler.isDown ()) {
			velY = 5;
		}
		else if (!handler.isUp()) {
			velY = 0;
		}
		
		if (handler.isRight()) {
			velX = 5;
		}
		else if (!handler.isLeft()) { 
			velX = 0;
		}
		
		if (handler.isLeft()) {
			velX = -5;
		}
		else if (!handler.isRight()) {
			velX = 0;
		}
		/*if (handler.isInventory()) {
			Inventory.openInventory();
			return;}
		else{
			Inventory.closeInventory();
			return;
		}*/
		}
			
		
		
			
	

	public void render(Graphics g) {
		g.setColor(kleur);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds() {
		return null;
	}
	

}
