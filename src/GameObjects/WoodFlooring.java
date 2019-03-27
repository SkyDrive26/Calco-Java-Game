package GameObjects;

import Main.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is used to place wood flooring on each rgb(255,100,0) line in the level
 * @see GameObject
 */
public class WoodFlooring extends GameObject {

    private BufferedImage woodFlooring;

	public WoodFlooring(int x, int y, ID id, BufferedImage woodFlooring) {
		super(x, y, id);
		this.woodFlooring = woodFlooring;
	}

    public void tick() {

    }

    /**
     * Used to place the wood flooring.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.woodFlooring, x, y, null);
    }

    /**
     * Used to draw the hitbox of the wood flooring.
     * @return The size and location of the object
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
