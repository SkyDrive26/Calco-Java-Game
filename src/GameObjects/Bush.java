package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.*;

/**
 * This class is used to place the wall on each green line in the level
 * @see GameObject
 */
public class Bush extends GameObject {

    public Bush(int x, int y, ID id){
        super(x, y, id);
        solid = false; 
    }

    public void tick() {

    }

    /**
     * Used to place the bush.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
    	g.setColor(Color.green);
        g.fillRect(this.x, this.y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
