package Player;

import java.awt.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObjects.LevelTransitioner;
import Main.*;
import Menus.InGameMenu;
import inventory.Inventory;
import GameObjects.GameObject;


public class Player extends GameObjects.GameObject {

	private static final Graphics Graphics = null;
	Handler handler;
	CalcoJavaGame game;
	Camera camera;
	InGameMenu inGameMenu;

	/**
	 * This boolean is used to enable debug mode.
	 * @param debug Used to enable debug mode.
	 */
	private boolean debug = false;

	private boolean inventoryIsOpen;
	private boolean inGameMenuIsOpen;

	/**
	 * Used to determine how far a player has moved into a wall as collision occurs.
	 * @see #collision
	 */
	private float wallOvershoot;
	/**
	 * Used to set the player's speed. The player's velX and velY values are based on this value.
	 */
	private int speed = 5;

	//animation images
	private BufferedImage[] walkingLeft = {Sprite.getSprite(3, 1), Sprite.getSprite(4, 1), Sprite.getSprite(5, 1), Sprite.getSprite(4, 1)};
	private BufferedImage[] walkingRight = {Sprite.getSprite(3, 2), Sprite.getSprite(4, 2), Sprite.getSprite(5, 2), Sprite.getSprite(4, 2)};
	private BufferedImage[] walkingUp = {Sprite.getSprite(3, 3), Sprite.getSprite(4, 3), Sprite.getSprite(5, 3), Sprite.getSprite(4, 3)};
	private BufferedImage[] walkingDown = {Sprite.getSprite(3, 0), Sprite.getSprite(4, 0), Sprite.getSprite(5, 0), Sprite.getSprite(4, 0)};
	private BufferedImage[] standing = {Sprite.getSprite(4, 0)};


	//animation states
	private Animation walkLeft = new Animation(walkingLeft, 10);
	private Animation walkRight = new Animation(walkingRight, 10);
	private Animation walkUp = new Animation(walkingUp, 10);
	private Animation walkDown = new Animation(walkingDown, 10);
	private Animation stand = new Animation(standing, 10);
	
	private Direction lastMovement = Direction.DOWN;


	//actual animation
	private Animation animation = stand;
	
	//New Inventory
	public Inventory inventory;


	public Player(int x, int y, ID id, Handler handler, CalcoJavaGame game, Camera camera) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		this.camera = camera;
		this.inGameMenu = new InGameMenu(game, handler);
		inventoryIsOpen = false;
		inGameMenuIsOpen = false;
		inventory = new Inventory (290, 124, handler);
	}
	


	public void tick() {
		x += velX;
		y += velY;
		this.collision();

		if (handler.isUp() && !inventoryIsOpen) {
			velY = -speed;                        //Movement itself
			animation = walkUp;            //What animation is needed
			animation.start();            // The animation itself
		    lastMovement= Direction.UP;
		} else if (!handler.isDown() && !inventoryIsOpen) {
			velY = 0;
		}

		if (handler.isDown() && !inventoryIsOpen) {
			velY = speed;
			animation = walkDown;
			animation.start();
			lastMovement= Direction.DOWN;
		} else if (!handler.isUp() && !inventoryIsOpen) {
			velY = 0;
		}

		if (handler.isRight() && !inventoryIsOpen) {
			velX = speed;
			animation = walkRight;
			lastMovement= Direction.RIGHT;
			animation.start();
		} else if (!handler.isLeft() && !inventoryIsOpen) {
			velX = 0;
		}

		if (handler.isLeft() && !inventoryIsOpen) {
			velX = -speed;
			animation = walkLeft;
			lastMovement= Direction.LEFT;
			animation.start();
		} else if (!handler.isRight() && !inventoryIsOpen) {
			velX = 0;
		}

		/* Open inventory when I is pressed and the inventory is not already open */
		if(handler.isInventory() && !inventoryIsOpen && !inGameMenuIsOpen){
			this.stopAllMovement();
			inventory.initInventory((int)camera.getX() + 290, (int) camera.getY() + 124);
			inventory.isOpen = true;
			inventoryIsOpen = true;
			handler.setInventory(false);
		}else if(handler.isInventory() && inventoryIsOpen){
			inventoryIsOpen = false;
			inventory.isOpen = false;
			handler.setInventory(false);
			
		}else if(handler.isInventory() && inGameMenuIsOpen){
			handler.setInventory(false);
		}
		

		/* Open in-game menu when escape is pressed */
		if(handler.isEscape() && !inGameMenuIsOpen){
			inGameMenuIsOpen = true;
			handler.setEscape(false);
			game.mainFrame.gamePanel.add(inGameMenu, BorderLayout.CENTER, 0);
			game.mainFrame.gamePanel.revalidate();
			game.mainFrame.gamePanel.repaint();
		}else if(handler.isEscape() && inGameMenuIsOpen){
			inGameMenuIsOpen = false;
			handler.setEscape(false);
			game.mainFrame.gamePanel.remove(inGameMenu);
			game.mainFrame.gamePanel.revalidate();
			game.mainFrame.gamePanel.repaint();
		}
    
		if (velX == 0 && velY == 0) {
			switch(lastMovement) {
			case RIGHT:
				animation= new Animation(new BufferedImage[] {walkingRight[1]}, 10);
				break;
			case LEFT:
				animation= new Animation(new BufferedImage[] {walkingLeft[1]}, 10);
				break;
			case UP:
				animation= new Animation(new BufferedImage[] {walkingUp[1]}, 10);
				break;
			case DOWN:
				animation= new Animation(new BufferedImage[] {walkingDown[1]}, 10);
				break;
			}
		}

		animation.update();
		inventory.tick();
	}

	public void render(Graphics g) {
		g.drawImage(animation.getSprite(), x, y, null);
		inventory.render(g);
		/**
		 * If debug mode is enabled, underlying code will draw the 4 hitboxes for the player character; up, down, left and right.
		 * @see debug
		 */
		if (debug) {
			g.setColor(Color.orange);
			g.fillRect(getBoundsUp().x, getBoundsUp().y, getBoundsUp().width, getBoundsUp().height);
			g.fillRect(getBoundsDown().x, getBoundsDown().y, getBoundsDown().width, getBoundsDown().height);
			g.fillRect(getBoundsLeft().x, getBoundsLeft().y, getBoundsLeft().width, getBoundsLeft().height);
			g.fillRect(getBoundsRight().x, getBoundsRight().y, getBoundsRight().width, getBoundsRight().height);
		}
	}

	/**
	 * Method used to place the player at the edge of the object collided with.
	 * @See wallOvershoot
	 */
	private void collision() {
		for (int i = 0; i < this.handler.object.size(); ++i) {
			GameObject tempObject = this.handler.object.get(i);
			if (tempObject.getSolid()) {

				if(getBoundsUp().intersects(tempObject.getBounds())) {
					if(velY < 0) {
						wallOvershoot = (int) Math.abs((getBoundsUp().y - (tempObject.getY() + tempObject.getBounds().getHeight())));
						y += wallOvershoot;
					}
				}
				if(getBoundsDown().intersects(tempObject.getBounds())) {
					if(velY > 0) {
						wallOvershoot = (int) Math.abs(((getBoundsDown().y + getBoundsDown().getHeight()) - tempObject.getY()));
						y -= wallOvershoot;
					}
				}
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					if(velX < 0) {
						wallOvershoot = (int) Math.abs((getBoundsLeft().x - (tempObject.getX() + tempObject.getBounds().getWidth())));
						x += wallOvershoot;
					}
				}
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					if(velX > 0) {
						wallOvershoot = (int) Math.abs(((getBoundsRight().x + getBoundsRight().getWidth()) - tempObject.getX()));
						x -= wallOvershoot;
					}
				}
				
			}

			//Aangepast dat als de speler een item aanraakt, deze opgepakt wordt.
			//Eerder controleerde hij bij getBoundsUp,Down,Left en Right
			if(tempObject.getIsItem()){
				if(getBounds().intersects(tempObject.getBounds())) {
					this.handler.removeObject(tempObject);
					this.inventory.addItem(tempObject.getItem());
				}
			}

			if(tempObject.getId() == ID.LevelTransitioner){
				if(getBounds().intersects(tempObject.getBounds())){
					/*Pak de X of Y coordinaat van de speler zodat hij in het nieuwe level op de juiste plek binnen komt
						Als speler links of rechts beeld uit loopt:
							y = y
							x = andere kant van scherm (0 of 2016)
						Als speler boven of onder beeld uit loopt:
							y = andere kant van scherm (0 of 2016)
							x = x

					Laad alle lagen opnieuw in, layer1, layer2 en layer3				*/
					game.levelTransition();
					System.out.println("BOE");
				}
			}

		}
	}

	/**
	 * Determines how large the player's hitbox is
	 * @return A rectangle which is the size of the player's hitbox, drawn from origin point x,y
	 */
	public Rectangle getBounds() {	return new Rectangle(x, y, 32, 32);	}
	public Rectangle getBoundsUp() { return new Rectangle((x + ((int)(Math.abs(getVelX())))), (y), (32-Math.abs((int)getVelX()*2)), 1);	}
	public Rectangle getBoundsDown() { return new Rectangle((x + ((int)(Math.abs((getVelX()))))), (y + 31), (32-Math.abs((int)getVelX()*2)), 1);	}
	public Rectangle getBoundsLeft() {	return new Rectangle((x), (y + ((int)(Math.abs(getVelY())))), 1, (32-Math.abs((int)getVelY()*2))); }
	public Rectangle getBoundsRight() { return new Rectangle((x + 31), (y + ((int)(Math.abs(getVelY())))), 1, (32-Math.abs((int)getVelY()*2)));	}

	public void stopAllMovement(){
		velX = 0;
		velY = 0;
	}

	enum Direction{
		LEFT, RIGHT, UP, DOWN;
	}

}
