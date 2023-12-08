// Importing necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the GameMenu with different options for users
 * @authors Esteban Rodriguez & Alfonso Avila & Nohea
 */

// Class representing the game menu panel
public class GameMenu extends JPanel {

    // Declaration of buttons for various menu options
    private JButton settingsButton;
    private JButton saveButton;
    private JButton returnToGame;
    private JButton exitToMainMenu;

    // Constructor for the GameMenu class
    public GameMenu() {
        // Setting the layout of the panel to be a vertical box layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Initializing buttons with appropriate labels
        settingsButton = new JButton("Settings");
        saveButton = new JButton("Save Game");
        returnToGame = new JButton("Return to Game");
        exitToMainMenu = new JButton("Exit to Main Menu");

        // Setting preferred button size
        Dimension buttonSize = new Dimension(50, 100);
        settingsButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);
        returnToGame.setPreferredSize(buttonSize);
        exitToMainMenu.setPreferredSize(buttonSize);

        // Adding action listeners to the buttons

        // Listener for the "Return to Game" button
        returnToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set keypad as readable, hide the menu, and start the game
                KeyPad.setReadable(true);
                GamePlay.hideMenu();
                GamePlay.startGame();
            }
        });

        // Listener for the "Settings" button
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the settings menu
                openSettings();
            }
        });

        // Listener for the "Save Game" button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the game state with a specific identifier (in this case, "test")
                GameState.saveGame("test");
            }
        });

        // Listener for the "Exit to Main Menu" button
        exitToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the main menu and start the main menu music
                MainDisplay.showMainMenu();
                MainDisplay.startMainMenuMusic();
            }
        });

        // Adding buttons to the panel with vertical struts for spacing
        this.add(returnToGame);
        this.add(Box.createVerticalStrut(20)); // Space between buttons
        this.add(settingsButton);
        this.add(Box.createVerticalStrut(20)); // Space between buttons
        this.add(saveButton);
        this.add(Box.createVerticalStrut(20));
        this.add(exitToMainMenu);
        this.add(Box.createVerticalStrut(20));

        // Setting the panel as visible
        this.setVisible(true);
    }

    // Private method to open the settings menu
    private void openSettings() {
        // Creating a settings panel with options to modify volume, sound, and music
        JPanel settingsPanel = new JPanel();
        JButton volumeButton = new JButton("Modify Volume");
        JButton soundButton = new JButton("Modify Sound");
        JButton musicButton = new JButton("Modify Music");

        // Adding action listeners to the settings buttons

        // Listener for the "Modify Volume" button
        volumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for modifying volume (commented out)
                //settings.modifyVolume();
            }
        });

        // Listener for the "Modify Sound" button
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for modifying sound (commented out)
                //settings.modifySound();
            }
        });

        // Listener for the "Modify Music" button
        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder for modifying music (commented out)
                //settings.modifyMusic();
            }
        });

        // Adding buttons to the settings panel
        settingsPanel.add(volumeButton);
        settingsPanel.add(soundButton);
        settingsPanel.add(musicButton);
    }
}
