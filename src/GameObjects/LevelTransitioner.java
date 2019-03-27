package GameObjects;

import Main.ID;

import java.awt.*;

/**
 * This class is used to place sand on each rgb(255,255,0) line in the level
 * @see GameObject
 */
public class LevelTransitioner extends GameObject {
    char transitionDir; // U(p), D(own), L(eft), R(ight) en mogelijk X (unspecified)
//coordinaten en ID zijn belangrijk, maar misschien ook of het een transitie naar links,rechts,boven,onder of geen van allen is
    public LevelTransitioner(int x, int y, ID id, char transitionDir) {
		super(x, y, id);
		this.transitionDir = transitionDir;
	}

    public void tick() {

    }

//  Dit hoeft niet gerendered te worden?
    public void render(Graphics g) {

    }

    /**
     * Used to draw the hitbox of the transition thing.
     * @return Location and size of object
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
