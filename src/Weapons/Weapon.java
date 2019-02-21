package Weapons;

import java.awt.image.BufferedImage;
import Weapons.WeaponSprite;

/**
 * This class holds inventory weapons.
 */

public class Weapon {

		public String name;
		public BufferedImage texture;

		/**
		 * This constructor holds the weapons.
		 * @param name String name for the name of the weapon
		 */
		public Weapon(String name){
			this.name = name;
			this.texture = WeaponSprite.getWeaponImage(name);
		}

		/**
		 * This is used to get the name of the item.
		 * @return The name of the item.
		 */
		public String getName(){
			return this.name;
		}
	}

