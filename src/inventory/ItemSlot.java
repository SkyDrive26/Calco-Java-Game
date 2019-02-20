package inventory;

import java.awt.Color;
import java.awt.Graphics;

import Items.Item;

public class ItemSlot {
	
	public static final int SLOTSIZE = 60;
	
	private int x, y;
	private ItemStack itemStack;
	
	public ItemSlot (int x, int y, ItemStack itemStack) {
		this.x = x;
		this.y = y;
		this.itemStack = itemStack;
	}
	
	public void tick() {}
	
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
	
	public ItemStack getItemStack() {
		return itemStack;
	}
	
	public void setItem(ItemStack item) {
		this.itemStack = item;
	}
	
	public boolean addItem(Item item, int amount) {
		if (itemStack != null) {
			if (item.getName().equals(itemStack.getItem().getName())) {
				this.itemStack.setAmount(this.itemStack.getAmount()+amount);
				return true;
			} else {
				return false;
			}
		} else {
			this.itemStack = new ItemStack(item, amount);
			return true;
		}
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setX(int x) {this.x = x;}

	public void setY(int y) {this.y = y;}

}
