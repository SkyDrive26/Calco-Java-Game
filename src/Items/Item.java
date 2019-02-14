package Items;

import java.awt.image.BufferedImage;

public class Item {

		public String name;
		public BufferedImage texture;
		private ItemType itemType;
		
		public Item(String name, BufferedImage texture, ItemType itemType) {
			this.name = name;
			this.texture = texture;
			this.itemType = itemType;
		}
		
		public ItemType getItemType() {
			return itemType;
		}
}
