package Menus;

import Main.CalcoJavaGame;
import Main.Handler;
import Main.JPanelWithBack;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This method is used to popup the in-game menu.
 * @see javax.swing.JPanel
 */
public class InGameMenu extends JPanelWithBack {
    private CalcoJavaGame game;
    private Handler handler;

    public InGameMenu(CalcoJavaGame game, Handler handler){
        super("inGameBack.jpg");
        this.game = game;
        this.handler = handler;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#303f9f"));

        /**
         * GridBagConstraints is used for positioning sizing of the buttons.
         */
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,0,5,5);
        constraints.ipadx = 100;
        constraints.ipady = 20;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;

        JLabel lblTitle = createLabel("Menu", new JLabel());

        JButton btnOptions = createButton("Options", new JButton());
        btnOptions.setEnabled(false); // Disable button

        JButton btnQuit = createButton("Quit to Menu", new JButton());
        btnQuit.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { game.mainFrame.returnFromGame(); }});

        JButton btnExit = createButton("Exit", new JButton());
        btnExit.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { System.exit(0); }});

        JButton btnReturn = createButton("Return to Game", new JButton());
        btnReturn.setEnabled(false); // Disable button
        btnReturn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { handler.setEscape(true); }});

        constraints.gridwidth = 2;
        constraints.gridy = 0;
        add(lblTitle, constraints);

        constraints.gridy = 1;
        add(btnOptions, constraints);

        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(btnQuit, constraints);

        constraints.gridx = 1;
        add(btnExit, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        add(btnReturn, constraints);
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
        button.setBackground(Color.decode("#424242"));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }

    /**
     * This method is used to create labels with predetermined
     * style and settings.
     * @param title This String represents the text in the label.
     * @param label This JLabel represents the JLabel object.
     * @return The methods returns the configured JLabel object.
     */
    private JLabel createLabel(String title, JLabel label){
        label = new JLabel(title);
        label.setFont(new Font("Calibri", Font.PLAIN, 64));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
}
