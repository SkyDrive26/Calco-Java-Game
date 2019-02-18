package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import Items.ItemFlower;
import Main.Handler;
import Main.MouseInput;
/*
 * Contains all the code for the initialization of the Inventory
 */
public class Inventory {

	public static boolean isOpen = false;
	private boolean hasBeenPressed = false;
	private Handler handler;

	private int x, 
	y,
	width,
	height,
	numCols = 6,
	numRows = 4;


	private CopyOnWriteArrayList<ItemSlot> itemSlots = new CopyOnWriteArrayList<ItemSlot>();
	private CopyOnWriteArrayList<ItemSlot> currItemSlots;
	private ItemStack currSelectedSlot;

	public void setInventory (boolean b) {
		this.isOpen = b;
	}

	public boolean getInventory () {
		return this.isOpen;
	}

	public Inventory(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;

		initItemSlots();

		//TODO: REMOVE THIS
		itemSlots.get(0).addItem(new ItemFlower(), 3);
		itemSlots.get(1).addItem(new ItemFlower(), 10);
	}

	public void initInventory(){
		currItemSlots = new CopyOnWriteArrayList<ItemSlot>();

		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				if (j == (numRows -1)) {
					y += 35;
				}
				currItemSlots.add(new ItemSlot (x + (i * (ItemSlot.SLOTSIZE + 10)),
						y + (j * (ItemSlot.SLOTSIZE + 10)), null));
				if (j == (numRows -1)) {
					y -= 35;
				}
			}
		}
		width = numCols * (ItemSlot.SLOTSIZE + 10);
		height = numRows * (ItemSlot.SLOTSIZE + 10) + 35;

		int i = 0;
		for(ItemSlot is: itemSlots){
			if(is.getItemStack() != null){
				if(currItemSlots.get(i).addItem(is.getItemStack().getItem(), is.getItemStack().getAmount())){
				}else{
					currItemSlots.get(i).setItem(is.getItemStack());
				}
			}
			i++;
		}
	}

	public void tick() {
		if(isOpen) {
			Rectangle temp;
			for(ItemSlot is: currItemSlots) {
				is.tick();

				Rectangle temp2 = new Rectangle(is.getX(), is.getY(), ItemSlot.SLOTSIZE, ItemSlot.SLOTSIZE);

				if(handler.isMousePressed() && !hasBeenPressed) {
					temp = new Rectangle(handler.getMouseX(), handler.getMouseY(), 1, 1);

					if(temp2.contains(temp)&& !hasBeenPressed) {
						hasBeenPressed = true;
						if (currSelectedSlot == null) {
							if(is.getItemStack() != null) {
									currSelectedSlot = is.getItemStack();
									currItemSlots.get(currItemSlots.indexOf(is)).setItem(null);
									is.setItem(null);
								}
						} else {
							if (is.addItem(currSelectedSlot.getItem(), currSelectedSlot.getAmount())) {
								currItemSlots.get(currItemSlots.indexOf(is)).addItem(currSelectedSlot.getItem(), currSelectedSlot.getAmount());
							} else {
								is.setItem(currSelectedSlot);
							}
							currSelectedSlot = null;
						}
					}
				}
				if (hasBeenPressed && handler.isMousePressed()) {
					handler.setMousePressed(false,0,0);
					hasBeenPressed = false;
				}
			}
		}
	}

	public void render(Graphics g) {
		if(isOpen){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x - 17, y - 17, width + 30, height + 30);
			g.setColor(Color.BLACK);
			g.drawRect(x - 17, y - 17, width + 30, height + 30);

			for(ItemSlot is: currItemSlots) {
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

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void copyItemSlots(){
		initItemSlots();

		int i = 0;
		for(ItemSlot is: currItemSlots) {
			if (is.getItemStack() != null) {
				if (itemSlots.get(i).addItem(is.getItemStack().getItem(), is.getItemStack().getAmount())) {
				} else {
					itemSlots.get(i).setItem(is.getItemStack());
				}
			}
			i++;
		}
	}

	public void initItemSlots(){
		itemSlots = new CopyOnWriteArrayList<ItemSlot>();
		x = 0;
		y = 0;

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
	}
}
