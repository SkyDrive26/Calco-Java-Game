package Weapons;

import Main.BufferedImageLoader;
import Main.SpriteSheet;

import java.awt.image.BufferedImage;

/**
 * This class is the main class for weapon Images.
 * It is used to load the weapon spritesheet and get images from it
 */

public class WeaponSprite {

	    private static BufferedImage weaponSpritePng = new BufferedImageLoader().LoadImage("/Pngs/Sprite_Sheet_Weapons.png");;
	    private static SpriteSheet weaponSpriteSheet =  new SpriteSheet(weaponSpritePng);;

	    /**
	     * This method returns the requested BufferedImage-object.
	     * @param name Takes the name of the weapon as parameter.
	     * @return Returns BufferedImage
	     * @see BufferedImage
	     */
	    public static BufferedImage getWeaponImage(String name){
	        switch(name){
	            case "Sword11": return weaponSpriteSheet.grabImage(1,1,32,32); //Sword 11 to make it different from sword 1 (item)
	            default: return null;
	        }
	    }
	}



