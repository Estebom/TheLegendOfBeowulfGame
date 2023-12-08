import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;

public class Settings extends JPanel {
    private JButton goBack;
    private JComboBox<String> resolutionBox;
    private JSlider gameVolumeSlider;
    private JSlider musicVolumeSlider;
    private JLabel gameVolumeLabel;
    private JLabel musicVolumeLabel;
    private Clip musicClip; // Clip object to control music playback

    public Settings() {
        this.setPreferredSize(new Dimension(800, 675));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        goBack = new JButton("Go Back to Main Menu");
        goBack.setMaximumSize(new Dimension(200, 50));
        goBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        goBack.setActionCommand("MainMenu");
        goBack.addActionListener(buttonListener);

        String[] resolutions = { "800x600", "1280x720", "1920x1080", "2560x1440" };
        resolutionBox = new JComboBox<>(resolutions);
        resolutionBox.setMaximumSize(new Dimension(200, 50));
        resolutionBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        resolutionBox.addActionListener(e -> {
            String selectedResolution = (String) resolutionBox.getSelectedItem();
            String[] dimensions = selectedResolution.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            MainDisplay.getInstance().setSize(width, height);
            MainDisplay.getInstance().setLocationRelativeTo(null);
        });

        gameVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        gameVolumeSlider.setMajorTickSpacing(10);
        gameVolumeSlider.setPaintTicks(true);
        gameVolumeSlider.setPaintLabels(true);
        gameVolumeSlider.setMaximumSize(new Dimension(200, 50));
        gameVolumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);

        musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        musicVolumeSlider.setMajorTickSpacing(10);
        musicVolumeSlider.setPaintTicks(true);
        musicVolumeSlider.setPaintLabels(true);
        musicVolumeSlider.setMaximumSize(new Dimension(200, 50));
        musicVolumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        musicVolumeSlider.addChangeListener(e -> {
            int volume = musicVolumeSlider.getValue();
            FloatControl control = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-80.0f + volume);
        });

        gameVolumeLabel = new JLabel("Game Volume");
        gameVolumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        musicVolumeLabel = new JLabel("Music Volume");
        musicVolumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(20));
        this.add(goBack);
        this.add(Box.createVerticalStrut(20));
        this.add(resolutionBox);
        this.add(Box.createVerticalStrut(20));
        this.add(gameVolumeLabel);
        this.add(gameVolumeSlider);
        this.add(Box.createVerticalStrut(20));
        this.add(musicVolumeLabel);
        this.add(musicVolumeSlider);
        this.add(Box.createVerticalStrut(20));
        this.setVisible(true);

        // Load music file and start playing
        try {
            File musicFile = new File("MainMenuTheme.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            playMusic();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to play music
    public void playMusic() {
        if (musicClip != null && !musicClip.isRunning()) {
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("MainMenu")) {
                stopMusic();
                MainDisplay.showMainMenu();
            }
        }
    };

    // Method to stop music
    public void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }
}
