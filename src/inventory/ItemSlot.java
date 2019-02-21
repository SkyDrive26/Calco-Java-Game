package inventory;

import java.awt.Color;
import java.awt.Graphics;

import Items.Item;

/**
 * This class is used to create itemslots for in an inventory
 */
public class ItemSlot {
	
	public static final int SLOTSIZE = 60;
	
	private int x, y;
	private ItemStack itemStack;

	/**
	 * This constructor initializes the slotitem on a certain cord
	 * @param x X cord
	 * @param y Y cord
	 * @param itemStack Stack of items
	 * @see ItemStack
	 */
	public ItemSlot (int x, int y, ItemStack itemStack) {
		this.x = x;
		this.y = y;
		this.itemStack = itemStack;
	}

	/**@hidden*/
	public void tick() {}

	/**
	 * This method is used to draw the itemslot into the inventory with any loaded items.
	 * @param g Graphics
	 * @see Graphics
	 */
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, SLOTSIZE, SLOTSIZE);
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SLOTSIZE, SLOTSIZE);
		
		if (itemStack != null) {
			g.drawImage(itemStack.getItem().texture,x, y, SLOTSIZE, SLOTSIZE, null );
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(itemStack.getAmount()), x +SLOTSIZE - 20, y + SLOTSIZE - 10);
		}
	}

	/**
	 * This method gets an ItemStack
	 * @return ItemStack
	 * @see ItemStack
	 */
	public ItemStack getItemStack() {
		return itemStack;
	}

	/**
	 * This method sets an ItemStack
	 * @param item ItemStack
	 * @see ItemStack
	 */
	public void setItem(ItemStack item) {
		this.itemStack = item;
	}

	/*public void isEmpty(){
		if(itemStack == null){
			System.out.println("NO ITEMSTACK");
		}
	}*/

	/**
	 * This method is used to add items to the itemslot.
	 * @param item Item item
	 * @param amount Amount of certain item
	 * @see Item
	 */
	public void addItem(Item item, int amount) {
		if (item.getName().equals(itemStack.getItem().getName())) {
			this.itemStack.setAmount(this.itemStack.getAmount()+amount);
		}
	}

	/**
	 * This method gets the X cord
	 * @return X cord
	 */
	public int getX() {
		return x;
	}

	/**
	 * This method gets the Y cord
	 * @return Y cord
	 */
	public int getY() {
		return y;
	}

	/**
	 * This method sets the X cord
	 * @param x X cord
	 */
	public void setX(int x) {this.x = x;}

	/**
	 * This method sets the Y cord
	 * @param y Y cord
	 */
	public void setY(int y) {this.y = y;}

}
