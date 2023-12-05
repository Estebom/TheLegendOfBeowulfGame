import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel {
    private JButton goBack;
    private JComboBox<String> resolutionBox;
    private JSlider gameVolumeSlider;
    private JSlider musicVolumeSlider;
    private JLabel gameVolumeLabel;
    private JLabel musicVolumeLabel;
    private final MainDisplay mainDisplay;

    public Settings(MainDisplay mainDisplay) {

        this.mainDisplay = mainDisplay;
        this.setPreferredSize(new Dimension(800, 675));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));




        goBack = new JButton("Go Back to Main Menu");
        goBack.setMaximumSize(new Dimension(200, 50));
        goBack.setAlignmentX(Component.CENTER_ALIGNMENT); // center align the button
        goBack.setActionCommand("MainMenu");
        goBack.addActionListener(buttonListener);








        String[] resolutions = { "800x600", "1280x720", "1920x1080", "2560x1440" };
        resolutionBox = new JComboBox<>(resolutions);
        resolutionBox.setMaximumSize(new Dimension(200, 50));
        resolutionBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        resolutionBox.addActionListener(e -> {
            String selectedResolution = (String)resolutionBox.getSelectedItem();
            String[] dimensions = selectedResolution.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            mainDisplay.setSize(width, height);
            mainDisplay.updateLayout();
            mainDisplay.setLocationRelativeTo(null);
        });

        gameVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // range from 0 to 100, initially set to 50
        gameVolumeSlider.setMajorTickSpacing(10);
        gameVolumeSlider.setPaintTicks(true);
        gameVolumeSlider.setPaintLabels(true);
        gameVolumeSlider.setMaximumSize(new Dimension(200, 50));
        gameVolumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT); // center align the slider

        musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50); // range from 0 to 100, initially set to 50
        musicVolumeSlider.setMajorTickSpacing(10);
        musicVolumeSlider.setPaintTicks(true);
        musicVolumeSlider.setPaintLabels(true);
        musicVolumeSlider.setMaximumSize(new Dimension(200, 50));
        musicVolumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT); // center align the slider

        gameVolumeLabel = new JLabel("Game Volume");
        gameVolumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center align the label
        musicVolumeLabel = new JLabel("Music Volume");
        musicVolumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center align the label

        this.add(Box.createVerticalStrut(20)); // add some space at the top
        this.add(goBack);
        this.add(Box.createVerticalStrut(20)); // add some space
        this.add(resolutionBox);
        this.add(Box.createVerticalStrut(20)); // add some space
        this.add(gameVolumeLabel);
        this.add(gameVolumeSlider);
        this.add(Box.createVerticalStrut(20)); // add some space
        this.add(musicVolumeLabel);
        this.add(musicVolumeSlider);
        this.add(Box.createVerticalStrut(20)); // add some space at the bottom
        this.setVisible(true);
    }

    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("MainMenu")){
                mainDisplay.showMainMenu();

            }
        }
    };





}
