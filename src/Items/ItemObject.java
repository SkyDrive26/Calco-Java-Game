package Items;

import GameObjects.GameObject;
import Main.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemObject extends GameObject {

    private BufferedImage itemImage;
    private String itemName;

    public ItemObject(int x, int y, ID id, String itemName){
        super(x, y, id, true, new Item(itemName));
        this.itemName = itemName;
        isItem = true;
        this.itemImage = ItemSprite.getItemImage(itemName);
    }

    public void tick(){}

    public void render(Graphics g) {
        g.drawImage(this.itemImage, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
