package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import Items.ItemFlower;
import Main.MouseInput;
/*
 * Contains all the code for the initialization of the Inventory
 */
public class Inventory {

	public static boolean isOpen = false;
	private boolean hasBeenPressed = false;

	private int x, 
	y,
	width,
	height,
	numCols = 6,
	numRows = 4;


	private CopyOnWriteArrayList<ItemSlot> itemSlots;
	private ItemStack currSelectedSlot;

	public void setInventory (boolean b) {
		this.isOpen = b;
	}

	public boolean getInventory () {
		return this.isOpen;
	}

	public Inventory(int x, int y) {
		
		this.x = x;
		this.y = y;
		itemSlots = new CopyOnWriteArrayList<ItemSlot>();

		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				if (j == (numRows -1)) {
					y += 35;
				}
				itemSlots.add(new ItemSlot (x + (i * (ItemSlot.SLOTSIZE + 10)),
						y + (j * (ItemSlot.SLOTSIZE + 10)), null));
				if (j == (numRows -1)) {
					y -= 35;
				}
			}
		}
		width = numCols * (ItemSlot.SLOTSIZE + 10);
		height = numRows * (ItemSlot.SLOTSIZE + 10) + 35;

		//TODO: REMOVE THIS
		itemSlots.get(0).addItem(new ItemFlower(), 3);
		itemSlots.get(1).addItem(new ItemFlower(), 10);
	}
	public void tick() {
		if(isOpen) {
			Rectangle temp = new Rectangle(MouseInput.MouseX, MouseInput.MouseY, 1, 1);
			for(ItemSlot is: itemSlots) {
				is.tick();

				Rectangle temp2 = new Rectangle(is.getX(), is.getY(), ItemSlot.SLOTSIZE, ItemSlot.SLOTSIZE);

				/*if(MouseInput.mouseClicked() && !hasBeenPressed) {


					if(temp2.contains(temp)&& !hasBeenPressed) {
						hasBeenPressed = true;
						if (currSelectedSlot == null) {
							if(is.getItemStack() != null) {
								{
									currSelectedSlot = is.getItemStack();

									is.setItem(null);
								} 
							} else {
								if (is.addItem(currSelectedSlot.getItem(),
										currSelectedSlot.getAmount())) {
								} else {
									is.setItem(currSelectedSlot);
								}
								currSelectedSlot = null;
							}
						}
					}
				}
				if (hasBeenPressed && MouseInput.mouseClicked()) {
					hasBeenPressed = false;
				}
			}*/
		}}

	}

	public void render(Graphics g) {
		if(isOpen){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x - 17, y - 17, width + 30, height + 30);
			g.setColor(Color.BLACK);
			g.drawRect(x - 17, y - 17, width + 30, height + 30);

			for(ItemSlot is: itemSlots) {
				is.render(g);
			}

			if (currSelectedSlot != null) {
				g.drawImage(currSelectedSlot.getItem().texture, MouseInput.MouseX,
						MouseInput.MouseY, null);
				g.drawString(Integer.toString(currSelectedSlot.getAmount()),
						MouseInput.MouseX + 27, MouseInput.MouseY + 33);
			}
		}
	}
}
