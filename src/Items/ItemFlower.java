package Items;

import java.awt.image.BufferedImage;

import Main.BufferedImageLoader;

public class ItemFlower extends Item {

	public ItemFlower() {
		super("Flower", new BufferedImageLoader().loadResource("items/"), itemType);
		
	}

}
