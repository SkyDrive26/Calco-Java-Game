package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.ID;

/**
 * This class is used to place sand on each rgb(255,255,0) line in the level
 * @see GameObject
 */
public class Sand extends GameObject {
	
    private BufferedImage sand;

	public Sand(int x, int y, ID id, BufferedImage sand) {
		super(x, y, id);
		this.sand = sand;
	}

    public void tick() {

    }

    /**
     * Used to place the sand.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.sand, x, y, null);
    }

    /**
     * Used to draw the hitbox of the sand.
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
