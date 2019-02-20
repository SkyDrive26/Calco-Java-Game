package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import Items.Item;
import Main.Handler;
import Main.MouseInput;

/**
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
	private Handler handler;

	/**
	 * This method sets the open-state of the inventory
	 * @param b Boolean open/closed -> true/false
	 */
	public void setInventory (boolean b) {
		this.isOpen = b;
	}

	/**
	 * This method gets the open-state of the inventory
	 * @return Boolean open/closed -> true/false
	 */
	public boolean getInventory () {
		return this.isOpen;
	}

	/**
	 * This constructor initializes the inventory by creating
	 * the default inventory with all related positions and initial
	 * objects.
	 * @param x X cord
	 * @param y Y cord
	 * @param handler Handler handler
	 * @see ItemSlot
	 */
	public Inventory(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;

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
	}

	/**
	 * This method is used to change the x and y cords every time the inventory is opened.
	 * This ensures that the inventory is always visible in the center of the screen.
	 * @param x Integer x
	 * @param y Integer y
	 * @see ItemSlot
	 */
	public void initInventory(int x, int y){
		this.x = x;
		this.y = y;
		int currCol = 0;
		int currRow = 0;

		for(ItemSlot is: itemSlots){
			if(currRow < numRows){
				is.setX(x + (currCol * (ItemSlot.SLOTSIZE + 10)));
				if(currRow == numRows-1){
					is.setY((y + 35) + (currRow * (ItemSlot.SLOTSIZE + 10)));
				} else {
					is.setY(y + (currRow * (ItemSlot.SLOTSIZE + 10)));
				}
			} else {
				currCol++;
				currRow = 0;
				is.setX(x + (currCol * (ItemSlot.SLOTSIZE + 10)));
				is.setY(y + (currRow * (ItemSlot.SLOTSIZE + 10)));
			}
			currRow++;
		}
	}

	/**
	 * The Tick method is used to check if the inventory is open and if so,
	 * it allows the player to move stuff in his/her inventory.
	 * @see ItemSlot
	 * @see Handler
	 */
	public void tick() {
		if(isOpen) {
			Rectangle temp;
			for(ItemSlot is: itemSlots) {
				is.tick();

				Rectangle temp2 = new Rectangle(is.getX(), is.getY(), ItemSlot.SLOTSIZE, ItemSlot.SLOTSIZE);

				if (handler.isMousePressed() && !hasBeenPressed) {
					temp = new Rectangle(handler.getMouseX(), handler.getMouseY(), 1, 1);

					if (temp2.contains(temp) && !hasBeenPressed) {
						hasBeenPressed = true;
						if (currSelectedSlot == null) {
							if (is.getItemStack() != null) {
								currSelectedSlot = is.getItemStack();
								itemSlots.get(itemSlots.indexOf(is)).setItem(null);
								is.setItem(null);
							}
						} else {
							/*
							if (is.addItem(currSelectedSlot.getItem(), currSelectedSlot.getAmount())) {
								itemSlots.get(itemSlots.indexOf(is)).addItem(currSelectedSlot.getItem(), currSelectedSlot.getAmount());
							} else {
								is.setItem(currSelectedSlot);
							}*/
							if(is.getItemStack() != null){
								itemSlots.get(itemSlots.indexOf(is)).addItem(currSelectedSlot.getItem(), currSelectedSlot.getAmount());
								System.out.println("ITEM STACK NOT NULL");
							}else{
								System.out.println("ITEM STACK NULL");
								itemSlots.get(itemSlots.indexOf(is)).setItem(currSelectedSlot);
							}
							currSelectedSlot = null;
						}
					}
				}
				if (hasBeenPressed && handler.isMousePressed()) {
					handler.setMousePressed(false, 0, 0);
					hasBeenPressed = false;
				}
			}
		}
	}

	/**
	 * This method is used to display the inventory.
	 * @param g Graphics
	 * @see Graphics
	 */
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

	/**
	 * Set the x cord of the inventory.
	 * @param x Integer x
	 */
	public void setX(int x){
		this.x = x;
	}

	/**
	 * Set the Y cord of the inventory.
	 * @param y Integer y
	 */
	public void setY(int y){
		this.y = y;
	}

	/**
	 * This method is used to load an item in the first available ItemSlot.
	 * @param item Item that has to be loaded.
	 * @see ItemSlot
	 */
	public void addItem(Item item){
		for(ItemSlot is: itemSlots){
			if(is.getItemStack() == null){
				itemSlots.get(itemSlots.indexOf(is)).setItem(new ItemStack(item, 1));
				break;
			}
		}
	}
}