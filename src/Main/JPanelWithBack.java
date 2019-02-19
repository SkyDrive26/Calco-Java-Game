package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPanelWithBack extends JPanel {
    private BufferedImage backgroundImage;

    public JPanelWithBack(String fileName){
        backgroundImage = new BufferedImageLoader().LoadImage("/Pngs/" + fileName);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, null);
    }
}
