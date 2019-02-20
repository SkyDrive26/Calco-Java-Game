package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.*;

/**
 * This class is used to place the wall on each rgb(0,255,0) line in the level
 * @see GameObject
 */
public class Wall extends GameObject {

    private BufferedImage wall;

    public Wall(int x, int y, ID id, BufferedImage wall){
        super(x, y, id);
        solid = true; 
        this.wall = wall;
    }

    public void tick() {

    }

    /**
     * Used to place the wall.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.wall, x, y, null);
    }

    /**
     * Used to determine the borders of the wall's hitbox
     * @return
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
