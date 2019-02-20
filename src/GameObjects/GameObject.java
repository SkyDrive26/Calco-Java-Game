package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import Items.Item;
import Main.ID;

/**
 * This class is used as a parent class for all game objects.
 * This allows the objects to be passed to the handler.
 */
public abstract class GameObject {

	 protected int x, y;
	 protected float velX = 0, velY = 0;
	 protected ID id;
	 protected boolean solid = false;
	 protected boolean isItem = false;
	 protected Item item;

	/**
	 * This constructor is used by items on the map.
	 * @param x X cord
	 * @param y Y cord
	 * @param id Item ID
	 * @param isItem Tells if the object is an item
	 * @param item	The Item itself.
	 */
	 public GameObject(int x, int y, ID id, boolean isItem, Item item){
	 	this.x = x;
	 	this.y = y;
	 	this.id = id;
	 	this.isItem = isItem;
	 	this.item = item;
	 }

	/**
	 * This constructor is used to set up GameObjects with a solid hitbox.
	 * @param x X cords
	 * @param y Y cords
	 * @param id Object ID
	 * @param solid Tells if the object is solid.
	 */
	 public GameObject (int x, int y, ID id, boolean solid) {
		 this.x = x;
		 this.y = y;
		 this.id = id;
		 this.solid = solid;
	 }

	/**
	 * This constructor is used to set up GameObjects
	 * @param x X cords
	 * @param y Y cords
	 * @param id Object ID
	 */
	 public GameObject (int x, int y, ID id) {
		 this.x = x;
		 this.y = y;
		 this.id = id;
	 }

	 public abstract void tick();
	 public abstract void render(Graphics g);
	 public abstract Rectangle getBounds();

	/**
	 * This method is used to return the object ID
	 * @return	Returns Object ID
	 */
	public ID getId() {
	 	return id;
	 }

	/**
	 * This method sets the object ID
	 * @param id Object ID
	 */
	public void setId(ID id) {
		this.id = id;
	}

	/**
	 * This method gets the X cord
	 * @return X cord
	 */
	public int getX() {
		return x;
	}

	/**
	 * This method sets the X cord
	 * @param x X cord
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * This method gets the Y cord
	 * @return Y cord
	 */
	public int getY() {
		return y;
	}

	/**
	 * This method sets the Y cord
	 * @param y Y cord
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * This method gets the X velocity
	 * @return X velocity
	 */
	public float getVelX() {
		return velX;
	}

	/**
	 * This method sets the X velocity
	 * @param velX X velocity
	 */
	public void setVelX(float velX) {
		this.velX = velX;
	}

	/**
	 * This method gets the Y velocity
	 * @return Y velocity
	 */
	public float getVelY() {
		return velY;
	}

	/**
	 * This method sets the Y velocity
	 * @param velY Y velocity
	 */
	public void setVelY(float velY) {
		this.velY = velY;
	}

	/**
	 * This method gets the solid-state of an object
	 * @return Boolean solid state
	 */
	public boolean getSolid() {
		return solid;
	}

	/**
	 * This method sets the solid-state of an object
	 * @param solid Boolean solid state
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	/**
	 * This method gets if an object is an item
	 * @return Boolean isItem
	 */
	public boolean getIsItem(){
		return isItem;
	}

	/**
	 * This method sets if an object is an item
	 * @param isItem Boolean isItem
	 */
	public void setItem(boolean isItem){
		this.isItem = isItem;
	}

	/**
	 * This method gets the item itself.
	 * @return Item item
	 */
	public Item getItem(){
		return item;
	}
}
