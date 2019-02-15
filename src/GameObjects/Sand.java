package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.ID;

public class Sand extends GameObject {
	
    private BufferedImage sand;

	public Sand(int x, int y, ID id, BufferedImage sand) {
		super(x, y, id);
		this.sand = sand;
	}

    public void tick() {

    }

    /**
     * Used to place the bush.
     * @param g Graphics
     * @see Graphics
     */
    public void render(Graphics g) {
        g.drawImage(this.sand, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
