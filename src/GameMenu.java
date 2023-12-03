import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameMenu implements KeyListener {
    private Settings settings;
    private SaveGame saveGame;
    private Player player;

    public GameMenu(Player player) {
        this.settings = new Settings();
        this.saveGame = new SaveGame();
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key-pressed events
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_S:
                saveGame.saveGame();
                break;
            case KeyEvent.VK_ESCAPE:
                openMenu();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key-released events
    }

    private void openMenu() {
        // Display menu options
        JFrame menuFrame = new JFrame("Game Menu");
        JPanel menuPanel = new JPanel();
        JButton settingsButton = new JButton("Settings");
        JButton saveButton = new JButton("Save Game");

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettings();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame.saveGame();
            }
        });

        menuPanel.add(settingsButton);
        menuPanel.add(saveButton);
        menuFrame.add(menuPanel);

        menuFrame.setSize(300, 200);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    private void openSettings() {
        // Display settings options
        JFrame settingsFrame = new JFrame("Settings");
        JPanel settingsPanel = new JPanel();
        JButton volumeButton = new JButton("Modify Volume");
        JButton soundButton = new JButton("Modify Sound");
        JButton musicButton = new JButton("Modify Music");

        volumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.modifyVolume();
            }
        });

        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.modifySound();
            }
        });

        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.modifyMusic();
            }
        });

        settingsPanel.add(volumeButton);
        settingsPanel.add(soundButton);
        settingsPanel.add(musicButton);
        settingsFrame.add(settingsPanel);

        settingsFrame.setSize(300, 200);
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setVisible(true);
    }
}
