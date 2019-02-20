package Items;

import java.awt.image.BufferedImage;

/**
 * This class holds inventory items.
 */
public class Item {

	public String name;
	public BufferedImage texture;

	/**
	 * This constructor holds the item.
	 * @param name String name for the name of the item
	 */
	public Item(String name){
		this.name = name;
		this.texture = ItemSprite.getItemImage(name);
	}

	/**
	 * This is used to get the name of the item.
	 * @return The name of the item.
	 */
	public String getName(){
		return this.name;
	}
}
