package Items;

import GameObjects.GameObject;
import Main.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

	public String name;
	public BufferedImage texture;
	private ItemType itemType;

	public Item(String name){//}, ItemType itemType) {
		this.name = name;
		this.texture = ItemSprite.getItemImage(name);
		//this.itemType = itemType;
	}

	/*public ItemType getItemType() {
		return itemType;
	}*/

	public String getName(){
		return this.name;
	}
}
