package NPCs;

import Main.CalcoJavaGame;
import Main.Camera;
import Main.Handler;
import Main.ID;

import java.awt.*;

public class Bandit extends GameObjects.GameObject {

    public Bandit(int x, int y, ID id) {
        super(x, y, id);
        solid = true;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(x,y,32,32);
    }

    public Rectangle getBounds(){
            return new Rectangle(x, y, 32, 32);
    }
}
