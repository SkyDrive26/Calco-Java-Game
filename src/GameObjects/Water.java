package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.ID;

/**
 * This class is used to place water on each dark blue pixel (0,0,200) in the Layer_2.png file
 * @see GameObject
 */

public class Water extends GameObject {
	
    private BufferedImage water;

	public Water(int x, int y, ID id, BufferedImage water) {
		super(x, y, id);
		solid = true;
		this.water = water;
	}

    public void tick() {

    }

    /**
     * Used to place the water tile.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.water, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
