package Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

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
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.decode("#303f9f"));

        /**
         * GridBagConstraints is used for positioning sizing of the buttons.
         */
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,0,10,0);
        constraints.ipadx = 100;
        constraints.ipady = 20;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;

        newGameAction.addChangeListener(changeListener);

        btnNewGame = createButton("New Game", new JButton());
        btnNewGame.addActionListener(this.newGameAction);
        btnLevelSelect = createButton("Select Level", new JButton());
        btnOptions = createButton("Options", new JButton());

        constraints.gridy = 0;
        panel.add(btnNewGame, constraints);

        constraints.gridy = 1;
        panel.add(btnLevelSelect, constraints);

        constraints.gridy = 2;
        panel.add(btnOptions, constraints);

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
     * This method is used to create buttons with predetermined
     * style and settings.
     * @param title This String represents the text in the button.
     * @param button This JButton represents the JButton object.
     * @return The method returns the configured JButton object.
     */
    private JButton createButton(String title, JButton button){
        button.setText(title);
        button.setFocusPainted(false);
        button.setRolloverEnabled(false);
        button.setBackground(Color.decode("#001970"));
        button.setForeground(Color.WHITE);
        return button;
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