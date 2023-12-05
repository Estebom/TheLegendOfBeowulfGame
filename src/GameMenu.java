import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// TODO: 12/3/2023 add a return to main menu button
// TODO: 12/3/2023 add a link to settings class or define the same features
// Todo: 12/3/2023 create save funcitonality
public class GameMenu extends JPanel{
    private SaveLoad saveLoad;
    private Player player;

    private GamePlay gamePlay;

    private JButton settingsButton;

    private JButton saveButton;
    private JButton returnToGame;
    private MainDisplay mainDisplay;;

    public GameMenu(MainDisplay mainDisplay) {

        this.mainDisplay = mainDisplay;

        this.setSize(new Dimension(500, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        settingsButton = new JButton("Settings");
        saveButton = new JButton("Save Game");
        returnToGame = new JButton("Return to Game");

        returnToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMenu.this.setVisible(false);
                KeyPad.getInstance(gamePlay).setReadable(true);
                mainDisplay.showGamePlay();
                mainDisplay.revalidate();
                mainDisplay.repaint();
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
                saveLoad.saveGame();
            }
        });
        this.add(returnToGame);
        this.add(settingsButton);
        this.add(saveButton);




        this.setVisible(false);
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
