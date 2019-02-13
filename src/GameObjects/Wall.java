package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Handler;
import Main.ID;
import Main.Animation;
import Main.CalcoJavaGame;

public class Wall extends GameObjects.GameObject {

    public Wall(int x, int y, ID id){
        super(x, y, id);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
