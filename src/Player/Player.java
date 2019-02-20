package Player;

import java.awt.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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

	private boolean inventoryIsOpen;
	private boolean inGameMenuIsOpen;

	//animation images
	private BufferedImage[] walkingLeft = {Sprite.getSprite(0, 1), Sprite.getSprite(1, 1), Sprite.getSprite(2, 1), Sprite.getSprite(1, 1)};
	private BufferedImage[] walkingRight = {Sprite.getSprite(0, 2), Sprite.getSprite(1, 2), Sprite.getSprite(2, 2), Sprite.getSprite(1, 2)};
	private BufferedImage[] walkingUp = {Sprite.getSprite(0, 3), Sprite.getSprite(1, 3), Sprite.getSprite(2, 3), Sprite.getSprite(1, 3)};
	private BufferedImage[] walkingDown = {Sprite.getSprite(0, 0), Sprite.getSprite(1, 0), Sprite.getSprite(2, 0), Sprite.getSprite(1, 0)};
	private BufferedImage[] standing = {Sprite.getSprite(1, 0)};


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
		this.collision();
		x += velX;
		y += velY;

		if (handler.isUp() && !inventoryIsOpen) {
			velY = -5;                        //Movement itself
			animation = walkUp;            //What animation is needed
			animation.start();            // The animation itself
		    lastMovement= Direction.UP;
		} else if (!handler.isDown() && !inventoryIsOpen) {
			velY = 0;
		}

		if (handler.isDown() && !inventoryIsOpen) {
			velY = 5;
			animation = walkDown;
			animation.start();
			lastMovement= Direction.DOWN;
		} else if (!handler.isUp() && !inventoryIsOpen) {
			velY = 0;
		}

		if (handler.isRight() && !inventoryIsOpen) {
			velX = 5;
			animation = walkRight;
			lastMovement= Direction.RIGHT;
			animation.start();
		} else if (!handler.isLeft() && !inventoryIsOpen) {
			velX = 0;
		}

		if (handler.isLeft() && !inventoryIsOpen) {
			velX = -5;
			animation = walkLeft;
			lastMovement= Direction.LEFT;
			animation.start();
		} else if (!handler.isRight() && !inventoryIsOpen) {
			velX = 0;
		}

		/* Open inventory when I is pressed and the inventory is not already open */
		if(handler.isInventory() && !inventoryIsOpen && !inGameMenuIsOpen){
			//inventory.setX((int)camera.getX() + 290);
			//inventory.setY((int) camera.getY() + 124);
			inventory.initInventory((int)camera.getX() + 290, (int) camera.getY() + 124);
			inventory.isOpen = true;
			inventoryIsOpen = true;
			handler.setInventory(false);
		}else if(handler.isInventory() && inventoryIsOpen){
			inventoryIsOpen = false;
			//inventory.copyItemSlots();
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
	}

	private void collision() {
		for (int i = 0; i < this.handler.object.size(); ++i) {
			GameObject tempObject = this.handler.object.get(i);
			if (tempObject.getSolid()) {
				
				/*
				if (this.getBoundsUp().intersects(tempObject.getBounds()) && this.velY < 0.0F) {
					this.y = (int) ((float) this.y + this.velY * -1.0F);
				}

				if (this.getBoundsDown().intersects(tempObject.getBounds()) && this.velY > 0.0F) {
					this.y = (int) ((float) this.y + this.velY * -1.0F);
				}

				if (this.getBoundsLeft().intersects(tempObject.getBounds()) && this.velX < 0.0F) {
					this.x = (int) ((float) this.x + this.velX * -1.0F);
				}

				if (this.getBoundsRight().intersects(tempObject.getBounds()) && this.velX > 0.0F) {
					this.x = (int) ((float) this.x + this.velX * -1.0F);
				}
				*/
				
				if(getBoundsUp().intersects(tempObject.getBounds())) {
					if(velY < 0) {
						y += velY * -1;
					}
				}
				if(getBoundsDown().intersects(tempObject.getBounds())) {
					if(velY > 0) {
						y += velY * -1;
					}
				}
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					if(velX < 0) {
						x += velX * -1;
					}
				}
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					if(velX > 0) {
						x += velX * -1;
					}
				}
				
			}

			if(tempObject.getIsItem()){
				if(getBoundsUp().intersects(tempObject.getBounds())) {
					this.handler.removeObject(tempObject);
					this.inventory.addItem(tempObject.getItem());
				}
				if(getBoundsDown().intersects(tempObject.getBounds())) {
					this.handler.removeObject(tempObject);
					this.inventory.addItem(tempObject.getItem());
				}
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					this.handler.removeObject(tempObject);
					this.inventory.addItem(tempObject.getItem());
				}
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					this.handler.removeObject(tempObject);
					this.inventory.addItem(tempObject.getItem());
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	public Rectangle getBoundsUp() {
		return new Rectangle((x + 2), (y -3), 28, 3);
	}
	public Rectangle getBoundsDown() {
		return new Rectangle((x + 2), (y + 32), 28, 3);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((x - 3), (y + 1), 3, 30);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((x + 32), (y + 1), 3, 30);
	}
	
	enum Direction{
		LEFT, RIGHT, UP, DOWN;
	}

}
