package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.ID;

/**
 * This class is used to place grass on each rgb(0,100,0) line in the level
 * @see GameObject
 */
public class Grass extends GameObject {
	
    private BufferedImage grass;

	public Grass(int x, int y, ID id, BufferedImage grass) {
		super(x, y, id);
		this.grass = grass; 
	}

    public void tick() {

    }

    /**
     * Used to place the grass.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.grass, x, y, null);
    }

    /**
     * Used to draw the hitbox of the grass.
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
