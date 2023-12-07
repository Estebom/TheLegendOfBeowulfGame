//Esteban Rodriguez & Alfonso Avila & Nohea

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: 12/3/2023 add a return to main menu button
// TODO: 12/3/2023 add a link to settings class or define the same features
// Todo: 12/3/2023 create save funcitonality
public class GameMenu extends JPanel{



    private JButton settingsButton;

    private JButton saveButton;
    private JButton returnToGame;
    private JButton exitToMainMenu;

    public GameMenu() {




        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        settingsButton = new JButton("Settings");
        saveButton = new JButton("Save Game");
        returnToGame = new JButton("Return to Game");
        exitToMainMenu = new JButton("Exit to Main Menu");

        Dimension buttonSize = new Dimension(50,100);
        settingsButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);
        returnToGame.setPreferredSize(buttonSize);
        exitToMainMenu.setPreferredSize(buttonSize);


        returnToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                KeyPad.setReadable(true);
                GamePlay.hideMenu();
                GamePlay.startGame();
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettings();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // saveLoad.saveGame();                                This can be removed
                GameState.saveGame("test");           //Edit by Nohea
            }
        });

        exitToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainDisplay.showMainMenu();
                MainDisplay.startMainMenuMusic();
            }
        });


        this.add(returnToGame);
        this.add(Box.createVerticalStrut(20)); // Space between buttons
        this.add(settingsButton);
        this.add(Box.createVerticalStrut(20)); // Space between buttons
        this.add(saveButton);
        this.add(Box.createVerticalStrut(20));
        this.add(exitToMainMenu);
        this.add(Box.createVerticalStrut(20));





        this.setVisible(true);
    }



    private void openSettings() {
        // Display settings options
        //JFrame settingsFrame = new JFrame("Settings");
        JPanel settingsPanel = new JPanel();
        JButton volumeButton = new JButton("Modify Volume");
        JButton soundButton = new JButton("Modify Sound");
        JButton musicButton = new JButton("Modify Music");

        volumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //settings.modifyVolume();
            }
        });

        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //settings.modifySound();
            }
        });

        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //settings.modifyMusic();
            }
        });

        settingsPanel.add(volumeButton);
        settingsPanel.add(soundButton);
        settingsPanel.add(musicButton);

    }
}



