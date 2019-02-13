package Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Handler;
import Main.ID;
import inventory.Inventory;
import Main.Sprite;
import Main.Animation;
import Main.CalcoJavaGame;


public class Player extends GameObjects.GameObject {

	Handler handler;
	CalcoJavaGame game;

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


	//actual animation
	private Animation animation = stand;


	public Player(int x, int y, ID id, Handler handler, CalcoJavaGame game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}


	public void tick() {
		this.collision();
		x += velX;
		y += velY;

		if (handler.isUp()) {
			velY = -5;                        //Movement itself
			animation = walkUp;            //What animation is needed
			animation.start();            // The animation itself
		} else if (!handler.isDown()) {
			velY = 0;
		}

		if (handler.isDown()) {
			velY = 5;
			animation = walkDown;
			animation.start();
		} else if (!handler.isUp()) {
			velY = 0;
		}

		if (handler.isRight()) {
			velX = 5;
			animation = walkRight;
			animation.start();
		} else if (!handler.isLeft()) {
			velX = 0;
		}

		if (handler.isLeft()) {
			velX = -5;
			animation = walkLeft;
			animation.start();
		} else if (!handler.isRight()) {
			velX = 0;
		}

		/*if (handler.isInventory()) {
			Inventory.openInventory();
			return;}
		else{
			Inventory.closeInventory();
			return;
		}*/


		if (velX == 0 && velY == 0) {
			animation.stop();
		}


		animation.update();

	}

			
	

	public void render(Graphics g) {
		g.drawImage(animation.getSprite(), x, y, null);
	}

	private void collision() {
		for (int i = 0; i < this.handler.object.size(); ++i) {
			GameObjects.GameObject tempObject = (GameObjects.GameObject) this.handler.object.get(i);
			if (tempObject.getId() == ID.Wall) {
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
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	public Rectangle getBoundsUp() {
		return new Rectangle((x + 1), (y -3), 30, 3);
	}
	public Rectangle getBoundsDown() {
		return new Rectangle((x + 1), (y + 32), 30, 3);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((x - 3), (y + 1), 3, 30);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((x + 32), (y + 1), 3, 30);
	}
	

}
