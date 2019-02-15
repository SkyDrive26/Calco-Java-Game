package Items;

import Main.BufferedImageLoader;

public class ItemFlower extends Item {

	public ItemFlower() {
		super("Flower", new BufferedImageLoader().LoadImage("/Pngs/Flower01Stat.png"), ItemType.FLOWER);
		
	}

}
