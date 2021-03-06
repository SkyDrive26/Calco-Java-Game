package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.*;

/**
 * This class is used to place a bush on each rgb(100,100,0) line in the level
 * @see GameObject
 */
public class Bush extends GameObject {
	
	private BufferedImage bush;

    public Bush(int x, int y, ID id, BufferedImage bush){
        super(x, y, id);
        solid = true;
        this.bush = bush;
    }

    public void tick() {

    }

    /**
     * Used to place the bush.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.bush, x, y, null);
    }

    /**
     * Used to draw the hitbox of the bush.
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 16);
    }

}
