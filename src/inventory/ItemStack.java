package inventory;

import Items.Item;

/**
 * This class holds one or multiple Items
 * @see Item
 */
public class ItemStack {

	private int amount;
	private Item item;

	/**
	 *This constructor creates an item stack for a single item
	 * @param item Item
	 * @see Item
	 */
	public ItemStack(Item item) {
		this.item = item;
		this.amount = 1;		
	}

	/**
	 * This constructor creates an item stack for multiple items of the same sort
	 * @param item Item
	 * @param amount Ammount
	 * @see Item
	 */
	public ItemStack(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	/**
	 * This method gets the item.
	 * @return Item of this item stack
	 * @see Item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * This method gets the ammount of this item
	 * @return Ammount of the items in this item stack
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * This method sets the ammount of items in this item stack
	 * @param amount Ammount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
