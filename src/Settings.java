import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

    private Image backgroundImage;


    public Settings( ) {


        this.setPreferredSize(new Dimension(800, 675));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon backdrop = new ImageIcon("src\\CastleBackDrop.png");
        backgroundImage = backdrop.getImage();


        goBack = new JButton("Go Back to Main Menu");
        goBack.setMaximumSize(new Dimension(200, 50));
        goBack.setAlignmentX(Component.CENTER_ALIGNMENT); // center align the button
        goBack.setActionCommand("MainMenu");
        goBack.addActionListener(buttonListener);







        //"800x600", "1280x720",
        String[] resolutions = { "1920x1080", "2560x1440" };
        resolutionBox = new JComboBox<>(resolutions);
        resolutionBox.setMaximumSize(new Dimension(200, 50));
        resolutionBox.setAlignmentX(Component.CENTER_ALIGNMENT); // center align the combo box
        resolutionBox.addActionListener(e -> {
            String selectedResolution = (String)resolutionBox.getSelectedItem();
            String[] dimensions = selectedResolution.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            MainDisplay.getInstance().setSize(width, height);
            MainDisplay.getInstance().setLocationRelativeTo(null);
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

        musicVolumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameVolumeLabel = new JLabel("Game Volume");
        gameVolumeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        java.lang.System.out.println("Painting component");

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);


    }


    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("MainMenu")){
                MainDisplay.showMainMenu();
            }
        }
    };





}