package Items;

import Main.BufferedImageLoader;
import Main.SpriteSheet;

import java.awt.image.BufferedImage;

public class ItemSprite {
    private static BufferedImage itemSpritePng = new BufferedImageLoader().LoadImage("/Pngs/Sprite_Sheet_Items.png");;
    private static SpriteSheet itemSpriteSheet =  new SpriteSheet(itemSpritePng);;

    public static BufferedImage getItemImage(String name){
        switch(name){
            case "Flower": return itemSpriteSheet.grabImage(1,1,32,32);
            case "Fish": return itemSpriteSheet.grabImage(1,2,32,32);
            case "Potato": return itemSpriteSheet.grabImage(1,3,32,32);
            default: return null;
        }
    }
}
