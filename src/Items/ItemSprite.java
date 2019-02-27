package Items;

import Main.BufferedImageLoader;
import Main.SpriteSheet;

import java.awt.image.BufferedImage;

/**
 * This class is the main class for Items Images.
 * It is used to load the item spritesheet and get images from it
 */
public class ItemSprite {
    private static BufferedImage itemSpritePng = new BufferedImageLoader().LoadImage("/Pngs/Sprite_Sheet_Items.png");;
    private static SpriteSheet itemSpriteSheet =  new SpriteSheet(itemSpritePng);;

    /**
     * This method returns the requested BufferedImage-object.
     * @param name Takes the name of the item as parameter.
     * @return Returns BufferedImage
     * @see BufferedImage
     */
    public static BufferedImage getItemImage(String name){
        switch(name){
            case "Flower": return itemSpriteSheet.grabImage(1,1,32,32);
            case "Fish": return itemSpriteSheet.grabImage(1,2,32,32);
            case "Potato": return itemSpriteSheet.grabImage(1,3,32,32);
            case "Sword": return itemSpriteSheet.grabImage()
            default: return null;
        }
    }
}
