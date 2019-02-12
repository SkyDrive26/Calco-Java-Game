package Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * <h1>Calco Java Game</h1>
 * This Java game is being developed by Calco MasterClassers
 * for educational purposes.
 * <p></p>
 *
 * @version 0.0.1
 * @since   07-02-2019
 */
public class Menu{

    /* Fields */
    private JFrame mainFrame;
    private JPanel panel;
    private JButton btnNewGame;
    private JButton btnLevelSelect;
    private JButton btnOptions;

    private ChangeListener changeListener = new ChangeListener() {
        /**
         * This method is used to remove the menu from
         * the mainFrame when a new game is started.
         * This also loads the game into the mainFrame.
         * @param e
         * @see ChangeListener
         */
        @Override
        public void stateChanged (ChangeEvent e){
            startNewGame();
        }
    };
    private Actions.newGameAction newGameAction = new Actions.newGameAction();
    /**
     * This is the constructor for the Menu class.
     * This is the part of the code that's being
     * executed when the Menu class has ben initialized.
     * <p>In here a JFrame is created and loaded.</p>
     */

    public Menu(){
        initMainFrame();
    }

    /* Initialize mainFrame */

    /**
     * This method is used to initialize and launch the
     * mainFrame.
     */
    private void initMainFrame(){
        mainFrame = new JFrame("Menu - Calco Jave Game");
        mainFrame.setSize(1000,563);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();
        mainFrame.setVisible(true);
    }

    /* Initialize Menu */

    /**
     * This method is used to load the menu into the window.
     */
    private void initMenu(){
        panel = new JPanel();
        panel.setBounds(0,0,1000,563);
        panel.setLayout(new GridLayout(3,3,10,10));

        newGameAction.addChangeListener(changeListener);

        btnNewGame = new JButton("New Game");
        btnLevelSelect = new JButton("Select Level");
        btnOptions = new JButton("Options");

        btnNewGame.addActionListener(this.newGameAction);
        btnNewGame.setPreferredSize(new Dimension(500, 80));

        btnLevelSelect.setSize(500,80);

        btnOptions.setSize(500,80);
        //btnNewGame.setBounds(800, 543, 400, 40);

        //btnLevelSelect.setBounds(800, 543, 400, 40);

        panel.add(btnNewGame);
        panel.add(btnLevelSelect);
        panel.add(btnOptions);

        mainFrame.add(panel);
    }

    /**
     * This method is used to load a new game into the window.
     */
    private void startNewGame(){
        CalcoJavaGame game = new CalcoJavaGame();
        mainFrame.remove(panel);
        mainFrame.add(game);
        mainFrame.validate();
        game.start();
    }

    /**
     * This is the main method which makes use of the game.
     * @param args
     * @return Nothing.
     */

    public static void main(String[] args){
        new Menu();
    }
}