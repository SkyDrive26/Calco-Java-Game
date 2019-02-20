package Items;

import GameObjects.GameObject;
import Main.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is used to add items to the map
 */
public class ItemObject extends GameObject {

    private BufferedImage itemImage;
    private String itemName;

    /**
     * This constructor loads the item into a new GameObject so that it can be used by the handler.
     * This constructor also requests the BufferedImage-object from ItemSprite
     * @param x Position on the map
     * @param y Position on the map
     * @param id Item ID
     * @param itemName Item name
     * @see GameObject
     * @see Main.Handler
     * @see BufferedImage
     * @see ItemSprite
     */
    public ItemObject(int x, int y, ID id, String itemName){
        super(x, y, id, true, new Item(itemName));
        this.itemName = itemName;
        isItem = true;
        this.itemImage = ItemSprite.getItemImage(itemName);
    }

    /**
     * This is here because it is mandatory
     */
    public void tick(){}

    /**
     * Draws the item onto the map
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.itemImage, x, y, null);
    }

    /**
     * Draws the invisible boundaries of the item
     * @return Invisible Rectangle
     * @see Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
