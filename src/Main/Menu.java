package Main;

import javax.swing.*;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Menu{

    /* Fields */
    public JFrame mainFrame;

    /* Constructor */
    public Menu(){
        mainFrame = new JFrame("Home");
        final JFXPanel fxPanel = new JFXPanel();

        mainFrame.add(fxPanel);
        mainFrame.setSize(1000, 563);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initFX(fxPanel);
                }catch(IOException e){System.out.println("ERROR");}
            }
        });
    }

    /* Initialize JavaFX */
    private void initFX(JFXPanel fxPanel) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Main/fxml/menu.fxml"));
        Scene scene = new Scene(root, 400, 400);
        fxPanel.setScene(scene);
    }

    /* Methods */
    public static void main(String[] args){
        new Menu();
    }
}