package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.ID;

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
     * Used to place the bush.
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
