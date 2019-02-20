package Weapons;

import GameObjects.GameObject;
import Main.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WeaponObject extends GameObject {
	/**
	 * This class is used to add weapons to the map
	 */

	    private BufferedImage weaponImage;
	    private String weaponName;

	    /**
	     * This constructor loads the item into a new GameObject so that it can be used by the handler.
	     * This constructor also requests the BufferedImage-object from ItemSprite
	     * @param x Position on the map
	     * @param y Position on the map
	     * @param id Weapon ID
	     * @param weaponName Weapon name
	     * @see GameObject
	     * @see Main.Handler
	     * @see BufferedImage
	     * @see WeaponSprite
	     */
	    public WeaponObject(int x, int y, ID id, String weaponName){
	        super(x, y, id, true, new Weapon(weaponName));
	        this.weaponName = weaponName;
	        isWeapon = true;
	        this.weaponImage = WeaponSprite.getWeaponImage(weaponName);
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
	        g.drawImage(this.weaponImage, x, y, null);
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

